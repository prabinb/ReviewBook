package services.impl;

import java.util.List;

import model.User;
import model.UserReviews;
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

	@Override
	public List<UserReviews> getUserReviews(String emailId) {
		return Ebean.find(UserReviews.class).where().eq("emailId", emailId).findList();

	}
}
