package hexlet.code.controller;

import hexlet.code.dto.urls.BuildPage;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.ParserUrls;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.net.MalformedURLException;
import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {
    public static void index(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var page = new UrlsPage(urls);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("urls/build.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Url-адрес не найден"));

        var page = new UrlPage(url);
        ctx.render("urls/show.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        var name = ctx.formParam("url");
        if (name == null || name.trim().isEmpty()) {
            BuildPage page = new BuildPage(name);
            ctx.sessionAttribute("flash", "Поле не должно быть пустым");
            page.setFlash(ctx.consumeSessionAttribute("flash"));
            ctx.render("urls/index.jte", model("page", page));
            return;
        }
        String nameParser = name.trim();
        try {
            nameParser = ParserUrls.parseUrl(nameParser);
        } catch (MalformedURLException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            BuildPage page = new BuildPage(name);
            page.setFlash(ctx.consumeSessionAttribute("flash"));
            ctx.render("urls/index.jte", model("page", page));
            return;
        }

        if (UrlRepository.findByName(nameParser).isPresent()) {
            ctx.sessionAttribute("flash", "Страница уже существует");
            ctx.redirect("/urls");
            return;
        }

        var url = new Url(nameParser);
        UrlRepository.save(url);
        ctx.sessionAttribute("flash", "Страница успешно добавлена");
        ctx.redirect("/urls");
    }

    public static void check(Context ctx) throws SQLException {

    }
}
