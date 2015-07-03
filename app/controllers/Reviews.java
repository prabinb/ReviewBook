package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;
import services.impl.UserServiceImpl;

public class Reviews extends Controller {

public Result getAllReviews()
{
 UserService userService = new UserServiceImpl();	
return ok(Json.toJson(userService.getAllReviews()));
}

}