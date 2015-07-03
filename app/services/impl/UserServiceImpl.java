package services.impl;

import java.util.List;

import model.User;
import model.UserReviews;
import model.form.PostReviewForm;
import services.UserService;

import com.avaje.ebean.Ebean;

public class UserServiceImpl implements UserService {
	@Override
	public boolean create(User user) {
		User existingUser = Ebean.find(User.class).where()
				.eq("emailId", user.getEmailId()).findUnique();
		if (existingUser == null) {
			user.save();
			return true;
		}
		return false;
	}

	@Override
	public List<UserReviews> getUserReviews(String emailId) {
		return Ebean.find(UserReviews.class).where().eq("emailId", emailId)
				.findList();

	}

	@Override
	public List<UserReviews> getAllReviews() {
		return Ebean.find(UserReviews.class).findList();

	}

	@Override
	public boolean saveUserReview(PostReviewForm reviewForm) {

		User existing = Ebean.find(User.class).where()
				.eq("emailId", reviewForm.getEmailId()).findUnique();

		if (existing == null) {
			return false;// How can u add a review without email id.. R u crazy??
		} else {
			UserReviews review = new UserReviews();
			review.setUser(existing);
			review.setProductName(reviewForm.getProductName());
			review.setReviewContent(reviewForm.getReviewContent());
			review.setReviewTitle(reviewForm.getReviewTitle());
			Ebean.save(review);
			return true;
		}

	}

}
