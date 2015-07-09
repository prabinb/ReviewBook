package model.VO;

import java.util.Date;
import java.util.List;

import model.PostedReviewsInterest;
import model.UserReviews;

public class UserReviewsVO {
	int reviewId;
	String fullName;
	String emailId;
	String productName;
	String reviewContent;
	String reviewTitle;
	boolean recommend;
	int helpfulCount;
	int notHelpfulCount;
	Date postedDate;
	int productCategoryId;

	public UserReviewsVO(UserReviews userReviews, int helpfulCount,
			int notHelpfulCount) {
		this.reviewId = userReviews.getReviewId();
		this.fullName = userReviews.getUser().getFullname();
		this.emailId = userReviews.getUser().getEmailId();
		this.productName = userReviews.getProductName();
		this.reviewContent = userReviews.getReviewContent();
		this.reviewTitle = userReviews.getReviewTitle();
		this.recommend = userReviews.isRecommend();
		this.helpfulCount = helpfulCount;
		this.notHelpfulCount = notHelpfulCount;
		this.postedDate = userReviews.getPostedDate();
		this.productCategoryId = userReviews.getProductCategory().getId();
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public boolean isRecommend() {
		return recommend;
	}

	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}

	public int getHelpfulCount() {
		return helpfulCount;
	}

	public void setHelpfulCount(int helpfulCount) {
		this.helpfulCount = helpfulCount;
	}

	public int getNotHelpfulCount() {
		return notHelpfulCount;
	}

	public void setNotHelpfulCount(int notHelpfulCount) {
		this.notHelpfulCount = notHelpfulCount;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public int getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

}
