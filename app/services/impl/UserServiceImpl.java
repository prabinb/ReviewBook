package services.impl;

import java.util.List;

import model.PostedReviewsInterest;
import model.User;
import model.UserReviews;
import model.form.PostedReviewsInterestForm;

import org.apache.commons.lang3.StringUtils;

import services.UserService;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;

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
    /*
     * for (UserReviews review : reviews) {
     * review.setPostedReviewsInterest(Ebean.find(PostedReviewsInterest.class)
     * .fetch("PostedReviewsInterest").findList()); //
     * Ebean.find(UserReviews.class).fetch("postedUserReviews", new FetchConfig().query()); //
     * review.getPostedReviewsInterest(); }
     */
    return reviews;
  }

  @Override
  public List<UserReviews> getAllProductReviews(String getAllProductReviews) {
    List<UserReviews> reviews =
        Ebean.find(UserReviews.class).where().ilike("product_name", getAllProductReviews)
            .findList();
    return reviews;
  }

  @Override
  public boolean saveUserReview(UserReviews reviews) {
    reviews.save();
    return true;
  }

  @Override
  public Boolean savePostedReviewsInterest(PostedReviewsInterestForm postedReviewsInterestForm) {
    PostedReviewsInterest exists =
        Ebean
            .find(PostedReviewsInterest.class)
            .where()
            .and(Expr.eq("email_id", postedReviewsInterestForm.getEmailId()),
                Expr.eq("review_id", postedReviewsInterestForm.getReviewId())).findUnique();
    if (exists == null) {
      PostedReviewsInterest postedReviewsInterest = new PostedReviewsInterest();
      postedReviewsInterest.setEmailId(postedReviewsInterestForm.getEmailId());
      // postedReviewsInterest.setReviewId(postedReviewsInterestForm.getReviewId());
      postedReviewsInterest.setHelpful(postedReviewsInterestForm.isHelpful());
      UserReviews userReviews =
          Ebean.find(UserReviews.class).where()
              .eq("review_id", postedReviewsInterestForm.getReviewId()).findUnique();
      /*
       * List<PostedReviewsInterest> existingPostedReviewsInterest =
       * userReviews.getPostedReviewsInterest(); if (existingPostedReviewsInterest == null) {
       * existingPostedReviewsInterest = Lists.newArrayList(); }
       * existingPostedReviewsInterest.add(postedReviewsInterest);
       */
      postedReviewsInterest.setUserReviews(userReviews);
      Ebean.save(postedReviewsInterest);
      return Boolean.TRUE;
    } else {
      // User can post his interest only once for a particular review.
      return Boolean.FALSE;
    }
  }

  @Override
  public List<UserReviews> getAllReviews(String search) {
    if (StringUtils.isEmpty(search)) {
      List<UserReviews> reviews = Ebean.find(UserReviews.class).findList();
      return reviews;
    } else {
      search += '%';
      List<UserReviews> reviews =
          Ebean.find(UserReviews.class).where()
              .or(Expr.like("review_title", search), Expr.like("product_name", search)).findList();
      return reviews;
    }
  }

}
