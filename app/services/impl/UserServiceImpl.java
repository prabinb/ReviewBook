package services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.PostedReviewsInterest;
import model.ProductCategories;
import model.User;
import model.UserReviews;
import model.VO.ProductTrendsVO;
import model.VO.UserReviewsVO;
import model.VO.UserTrendsVO;
import model.form.PostedReviewsInterestForm;

import org.apache.commons.lang3.StringUtils;

import services.UserService;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.google.common.collect.Lists;

public class UserServiceImpl implements UserService {
	@Override
	public boolean create(User user) {
		User existingUser = Ebean.find(User.class).where()
				.eq("email_id", user.getEmailId()).findUnique();
		if (existingUser == null) {
			user.save();
			return true;
		}
		return false;
	}

	@Override
	public List<UserReviewsVO> getAllUserReviews(String emailId) {
		List<UserReviews> reviews = Ebean.find(UserReviews.class).where()
				.eq("email_id", emailId).order().desc("posted_date").findList();
		List<UserReviewsVO> output = Lists.newArrayList();
		for (UserReviews review : reviews) {
			int helpfulCount = Ebean
					.find(PostedReviewsInterest.class)
					.where()
					.eq("review_id", review.getReviewId())
					.and(Expr.eq("email_id", review.getUser().getEmailId()),
							Expr.eq("helpful", 1)).findRowCount();

			int notHelpfulCount = Ebean
					.find(PostedReviewsInterest.class)
					.where()
					.eq("review_id", review.getReviewId())
					.and(Expr.eq("email_id", review.getUser().getEmailId()),
							Expr.eq("helpful", 0)).findRowCount();
			UserReviewsVO vo = new UserReviewsVO(review, helpfulCount,
					notHelpfulCount);
			output.add(vo);
		}

		return output;
	}

	@Override
	public List<UserReviewsVO> getAllProductReviews(String getAllProductReviews) {
		List<UserReviews> reviews = Ebean.find(UserReviews.class).where()
				.ilike("product_name", getAllProductReviews).order()
				.desc("posted_date").findList();
		List<UserReviewsVO> output = Lists.newArrayList();
		for (UserReviews review : reviews) {
			int helpfulCount = Ebean
					.find(PostedReviewsInterest.class)
					.where()
					.eq("review_id", review.getReviewId())
					.and(Expr.eq("email_id", review.getUser().getEmailId()),
							Expr.eq("helpful", 1)).findRowCount();

			int notHelpfulCount = Ebean
					.find(PostedReviewsInterest.class)
					.where()
					.eq("review_id", review.getReviewId())
					.and(Expr.eq("email_id", review.getUser().getEmailId()),
							Expr.eq("helpful", 0)).findRowCount();
			UserReviewsVO vo = new UserReviewsVO(review, helpfulCount,
					notHelpfulCount);
			output.add(vo);
		}
		return output;
	}

	@Override
	public boolean saveUserReview(UserReviews reviews) {
		reviews.setPostedDate(new Date());
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
		if (exists == null) {
			PostedReviewsInterest postedReviewsInterest = new PostedReviewsInterest();
			postedReviewsInterest.setReviewId(postedReviewsInterestForm
					.getReviewId());
			postedReviewsInterest.setEmailId(postedReviewsInterestForm
					.getEmailId());
			postedReviewsInterest.setHelpful(postedReviewsInterestForm
					.isHelpful());
			Ebean.save(postedReviewsInterest);
			return Boolean.TRUE;
		} else {
			// User can post his interest only once for a particular review.
			return Boolean.FALSE;
		}
	}

	@Override
	public List<UserReviewsVO> getAllReviews(String search) {
		List<UserReviews> reviews = null;
		if (StringUtils.isEmpty(search)) {
			reviews = Ebean.find(UserReviews.class).order().desc("posted_date")
					.findList();
		} else {
			search += '%';
			reviews = Ebean
					.find(UserReviews.class)
					.where()
					.or(Expr.like("review_title", search),
							Expr.like("product_name", search)).order()
					.desc("posted_date").findList();

		}
		List<UserReviewsVO> output = Lists.newArrayList();
		if (reviews != null) {
			for (UserReviews review : reviews) {
				int helpfulCount = Ebean
						.find(PostedReviewsInterest.class)
						.where()
						.eq("review_id", review.getReviewId())
						.and(Expr.eq("email_id", review.getUser().getEmailId()),
								Expr.eq("helpful", 1)).findList().size();

				int notHelpfulCount = Ebean
						.find(PostedReviewsInterest.class)
						.where()
						.eq("review_id", review.getReviewId())
						.and(Expr.eq("email_id", review.getUser().getEmailId()),
								Expr.eq("helpful", 0)).findRowCount();
				UserReviewsVO vo = new UserReviewsVO(review, helpfulCount,
						notHelpfulCount);
				output.add(vo);
			}
		}
		return output;
	}

	@Override
	public List<ProductTrendsVO> getTrendingProducts() {
		String sql = " select product_name, sum(is_recommended) as is_recommended, count(*) as total_count "
				+ " from user_reviews " + " group by product_name ";

		RawSql rawSql = RawSqlBuilder.parse(sql).create();
		Query<ProductTrendsVO> query = Ebean.find(ProductTrendsVO.class);
		query.setRawSql(rawSql).order().desc("is_recommended").setMaxRows(4);
		List<ProductTrendsVO> output = query.findList();
		return output;
	}

	@Override
	public List<UserTrendsVO> getTrendingUsers() {
		String sql = " select u.full_name as fullName, t.reviews_count as reviewsCount from "
				+ " (select count(*) as reviews_count, email_id from user_reviews group by email_id) t "
				+ " join user u " + " on u.email_id= t.email_id ";

		RawSql rawSql = RawSqlBuilder.parse(sql).create();
		Query<UserTrendsVO> query = Ebean.find(UserTrendsVO.class);
		query.setRawSql(rawSql).order().desc("reviews_count").setMaxRows(4);
		List<UserTrendsVO> output = query.findList();
		return output;
	}

	@Override
	public List<String> getProductSuggestions(String searchString) {
		List<UserReviews> userReviews = Ebean.find(UserReviews.class)
				.select("productName").setDistinct(true).where()
				.ilike("productName", "%" + searchString + "%").setMaxRows(4)
				.findList();
		List<String> productNames = new ArrayList<String>(userReviews.size());
		for (UserReviews userReview : userReviews) {
			productNames.add(userReview.getProductName());
		}
		return productNames;
	}

	public List<ProductCategories> listProductCategories() {
		return Ebean.find(ProductCategories.class).findList();
	}
	
	public List<UserReviewsVO> getReviewForCategory(int categoryId){
		List<UserReviews> reviews = Ebean.find(UserReviews.class).where()
				.eq("product_category_id", categoryId).order().desc("posted_date").findList();
		List<UserReviewsVO> output = Lists.newArrayList();
		for (UserReviews review : reviews) {
			int helpfulCount = Ebean
					.find(PostedReviewsInterest.class)
					.where()
					.eq("review_id", review.getReviewId())
					.and(Expr.eq("email_id", review.getUser().getEmailId()),
							Expr.eq("helpful", 1)).findRowCount();

			int notHelpfulCount = Ebean
					.find(PostedReviewsInterest.class)
					.where()
					.eq("review_id", review.getReviewId())
					.and(Expr.eq("email_id", review.getUser().getEmailId()),
							Expr.eq("helpful", 0)).findRowCount();
			UserReviewsVO vo = new UserReviewsVO(review, helpfulCount,
					notHelpfulCount);
			output.add(vo);
		}

		return output;
	}
}
