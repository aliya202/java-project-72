package hexlet.code.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import hexlet.code.dto.UrlsPage;
import hexlet.code.dto.UrlPage;
import hexlet.code.model.UrlCheck;
import hexlet.code.util.NamedRoutes;

import hexlet.code.dto.MainPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import hexlet.code.repository.CheckRepository;


import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import hexlet.code.util.LinkChecker;

public class UrlController {

    public static void root(Context ctx) {
        MainPage page = new MainPage();
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        String inputUrl = ctx.formParam("url");
        if (inputUrl != null) {
            inputUrl = inputUrl.trim();
        }

        URL parsedUrl;
        String name;
        try {
            if (LinkChecker.isLinkValid(inputUrl)) {
                throw new IllegalArgumentException("Некорректный URL");
            }

            URI uri = new URI(inputUrl);
            parsedUrl = uri.toURL();
            name = parsedUrl.getProtocol() + "://" + parsedUrl.getAuthority();
            if (LinkChecker.isLinkValid(name)) {
                throw new IllegalArgumentException("Некорректный домен");
            }
        } catch (Exception e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("flash-type", "danger");
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }

        Url urlObj = new Url(name);
        if (UrlRepository.findName(name).isPresent()) {
            ctx.sessionAttribute("flash", "Страница уже существует");
            ctx.sessionAttribute("flash-type", "danger");
            ctx.redirect(NamedRoutes.urlsPath());
        } else {
            UrlRepository.save(urlObj);
            ctx.sessionAttribute("flash", "Страница успешно добавлена");
            ctx.sessionAttribute("flash-type", "success");
            ctx.redirect(NamedRoutes.urlsPath());
        }
    }

    public static void showList(Context ctx) throws SQLException {
        List<Url> urls = UrlRepository.getEntities();
        String flash = ctx.consumeSessionAttribute("flash");
        String flashType = ctx.consumeSessionAttribute("flash-type");
        UrlsPage page = new UrlsPage(urls, flash, flashType);
        ctx.render("urls/list.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        Url url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Url not found"));

        List<UrlCheck> checks = CheckRepository.findAllCheck(id);
        UrlPage page = new UrlPage(url, checks);
        ctx.render("urls/show.jte", model("page", page));
    }

}
