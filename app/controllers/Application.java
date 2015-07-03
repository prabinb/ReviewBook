package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public Result index() {
        return ok(views.html.index.render("Your new application is ready  ."));
    }

    public Result sayHello(String name) {
        return ok(views.html.index.render("Hello Mr/Mrs ." + name));
    }

    public Result saveUserInfo() {
        return ok(views.html.index.render("Saving data"));
    }
}
