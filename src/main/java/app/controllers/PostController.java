package app.controllers;

import app.entities.Post;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.PostMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class PostController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/posts/{id}", ctx -> findPostById(ctx, connectionPool));
        app.post("/posts/{id}/delete", ctx -> ctx.render("index.html"));
        app.get("/posts", ctx -> findAll(ctx, connectionPool));
    }

    public static void findPostById(Context ctx, ConnectionPool connectionPool) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        try {
            Post post = PostMapper.findPost(id, connectionPool);
            ctx.attribute("post", post);
            ctx.render("post-details.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }
    }

    public static void findAll(Context ctx, ConnectionPool connectionPool) {
        // check om bruger er logget ind
        User user = ctx.sessionAttribute("currentUser");
        if (user == null) {
            ctx.redirect("/login");
        } else {
            try {
                List<Post> posts = PostMapper.findAll(connectionPool);
                ctx.attribute("postList", posts);
                ctx.render("post.html");
            } catch (DatabaseException e) {
                ctx.attribute("message", e.getMessage());
                ctx.render("index.html");
            }
        }
    }
}