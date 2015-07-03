package controllers;

import api.results.APIResult;
import api.results.Result;
import play.libs.Json;
import play.mvc.Controller;
import services.UserService;
import services.impl.UserServiceImpl;

public class Reviews extends Controller {

public Result getAllReviews()
{
 UserService userService = new UserServiceImpl();	
return (Result) ok(Json.toJson(userService.getAllReviews()));
}

}