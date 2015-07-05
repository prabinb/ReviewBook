package controllers;


import model.form.UserForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;
import services.impl.UserServiceImpl;
import api.results.APIResult;

public class Login extends Controller {
  public UserService userService = new UserServiceImpl();

  public Result login() {
    Form<UserForm> form = Form.form(UserForm.class).bindFromRequest();
    if (form.hasErrors()) {
      return badRequest(form.errorsAsJson());
    } else {
      UserForm userForm = form.get();
      if (userService.create(userForm.getUser())) {
        return ok(Json.toJson(new APIResult("User created successfully.")));
      } else {
        return ok(Json.toJson(new APIResult("User exists.")));
      }
      /*
       * return internalServerError(Json.toJson(new APIResult(
       * "User with same name exists. Try with another name.")));
       */
    }
  }
}
