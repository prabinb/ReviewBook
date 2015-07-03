package model.form;

import play.data.validation.Constraints.Required;

public class PostReviewForm {
	@Required
	private String productName;
	@Required
	private String reviewContent;
	@Required
	private String reviewTitle;
	@Required
	private String emailId;

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

	public PostReviewForm getReview() {
		
		PostReviewForm form= new PostReviewForm();
		form.setEmailId(emailId);
		form.setProductName(productName);
		form.setReviewContent(reviewContent);
		form.setReviewTitle(reviewTitle);
		return form;
	}
}
