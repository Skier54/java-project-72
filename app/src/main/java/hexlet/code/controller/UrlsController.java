package hexlet.code.controller;

import hexlet.code.dto.urls.BuildPage;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import hexlet.code.util.ParserUrls;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.jsoup.Jsoup;

import java.net.MalformedURLException;
import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {
    public static void index(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var lastChecks =  UrlCheckRepository.findLastCheck();
        var page = new UrlsPage(urls, lastChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flashType"));
        ctx.render("urls/build.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Url-адрес не найден"));

        var page = new UrlPage(url);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flashType"));
        var urlChecks = UrlCheckRepository.findCheck(id);
        page.getUrl().setUrlCheck(urlChecks);
        ctx.render("urls/show.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        var name = ctx.formParam("url");
        if (name == null || name.trim().isEmpty()) {
            ctx.sessionAttribute("flash", "Поле не должно быть пустым");
            ctx.sessionAttribute("flashType", "danger");
            BuildPage page = new BuildPage(name);
            page.setFlash(ctx.consumeSessionAttribute("flash"));
            page.setFlashType(ctx.consumeSessionAttribute("flashType"));
            ctx.render("urls/index.jte", model("page", page));
            return;
        }
        String nameParser = name.trim();
        try {
            nameParser = ParserUrls.parseUrl(nameParser);

            if (UrlRepository.findByName(nameParser).isPresent()) {
                ctx.sessionAttribute("flash", "Страница уже существует");
                ctx.sessionAttribute("flashType", "warning");
                ctx.redirect("/urls");
                return;
            }

            var url = new Url(nameParser);
            UrlRepository.save(url);
            ctx.sessionAttribute("flash", "Страница успешно добавлена");
            ctx.sessionAttribute("flashType", "success");
            ctx.redirect("/urls");

        } catch (IllegalArgumentException | MalformedURLException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("flashType", "danger");
            BuildPage page = new BuildPage(name);
            page.setFlash(ctx.sessionAttribute("flash")); // Используйте тут, а не consume с ошибкой
            page.setFlashType(ctx.sessionAttribute("flashType"));
            ctx.render("urls/index.jte", model("page", page));
            return;
        }

        //if (UrlRepository.findByName(nameParser).isPresent()) {
        //    ctx.sessionAttribute("flash", "Страница уже существует");
        //    ctx.sessionAttribute("flashType", "warning");
        //    ctx.redirect("/urls");
        //    return;
        //}

        //var url = new Url(nameParser);
        //UrlRepository.save(url);
        //ctx.sessionAttribute("flash", "Страница успешно добавлена");
        //ctx.sessionAttribute("flashType", "success");
        //ctx.redirect("/urls");
    }

    public static void check(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Url-адрес не найден"));

        try {
            var response = Unirest.get(url.getName()).asString();
            var doc = Jsoup.parse(response.getBody());
            var statusCode = response.getStatus();
            var title = doc.title();
            var h1 = doc.select("h1").text();
            var description = doc.select("meta[name=description]").attr("content");
            var urlCheck = new UrlCheck(statusCode, title, h1, description, id);
            UrlCheckRepository.saveCheck(urlCheck);
            ctx.sessionAttribute("flash", "Сайт успешно проверен");
            ctx.sessionAttribute("flashType", "success");
            ctx.redirect(NamedRoutes.urlPath(id));
        } catch (SQLException | UnirestException e) {
            ctx.sessionAttribute("flash", "не удается установить соединение");
            ctx.sessionAttribute("flashType", "danger");
            UrlPage page = new UrlPage(url);
            page.setFlash(ctx.consumeSessionAttribute("flash"));
            page.setFlashType(ctx.consumeSessionAttribute("flashType"));
            ctx.render("urls/show.jte", model("page", page));
        }
    }
}
