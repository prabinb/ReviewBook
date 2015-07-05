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
        Ebean.find(User.class).where().eq("email_id", user.getEmailId()).findUnique();
    if (existingUser == null) {
      user.save();
      return true;
    }
    return false;
  }

  @Override
  public List<UserReviews> getAllUserReviews(String emailId) {
    List<UserReviews> reviews =
        Ebean.find(UserReviews.class).where().eq("email_id", emailId).findList();
    return reviews;
  }

  @Override
  public List<UserReviews> getAllProductReviews(String getAllProductReviews) {
    List<UserReviews> reviews =
        Ebean.find(UserReviews.class).where().eq("product_name", getAllProductReviews).findList();
    return reviews;
  }

  @Override
  public boolean saveUserReview(UserReviews reviews) {
    reviews.save();
    return true;
  }
}
