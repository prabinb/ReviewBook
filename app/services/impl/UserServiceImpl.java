package services.impl;

import java.util.List;

import model.PostedReviewsInterest;
import model.User;
import model.UserReviews;
import model.form.PostedReviewsInterestForm;
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
  
  @Override
	public Boolean savePostedReviewsInterest(
			PostedReviewsInterestForm postedReviewsInterestForm) {
		PostedReviewsInterest exists = Ebean
				.find(PostedReviewsInterest.class)
				.where()
				.and(Expr
						.eq("email_id", postedReviewsInterestForm.getEmailId()),
						Expr.eq("review_id",
								postedReviewsInterestForm.getReviewId()))
				.findUnique();
		if(exists == null){
			PostedReviewsInterest formDataToSave = new PostedReviewsInterest();
			formDataToSave.setEmailId(postedReviewsInterestForm.getEmailId());
			formDataToSave.setReviewId(postedReviewsInterestForm.getReviewId());
			formDataToSave.setHelpful(postedReviewsInterestForm.isHelpful());
			Ebean.save(formDataToSave);
			return Boolean.TRUE;
		}else{
//			User can post his interest only once for a particular review.
			return Boolean.FALSE;
		}
	}

}
