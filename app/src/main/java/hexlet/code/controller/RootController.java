package hexlet.code.controller;

import hexlet.code.dto.urls.BuildPage;
import io.javalin.http.Context;

import static io.javalin.rendering.template.TemplateUtil.model;

public class RootController {
    public static void index(Context ctx) {
        BuildPage page = new BuildPage();
        ctx.render("urls/index.jte", model("page", page));
    }
}
