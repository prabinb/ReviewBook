package services.impl;

import model.User;
import services.UserService;

import com.avaje.ebean.Ebean;

public class UserServiceImpl implements UserService {
  @Override
  public boolean create(User user) {
    User existingUser =
        Ebean.find(User.class).where().eq("emailId", user.getEmailId()).findUnique();
    if (existingUser == null) {
      user.save();
      return true;
    }
    return false;
  }
}
