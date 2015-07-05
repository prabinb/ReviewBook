/**
 * 
 */
package model.form;

import play.data.validation.Constraints.Required;

/**
 * @author PAmbure
 *
 */
public class PostedReviewsInterestForm {
	
	@Required
	private int reviewId;
	@Required
	private String emailId;
	@Required
	private Boolean helpful;
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Boolean isHelpful() {
		return helpful;
	}
	public void setHelpful(Boolean helpful) {
		this.helpful = helpful;
	}
	
	public PostedReviewsInterestForm getPostedReviewsInterestForm(){
		PostedReviewsInterestForm form = new PostedReviewsInterestForm();
		form.setReviewId(reviewId);
		form.setEmailId(emailId);
		form.setHelpful(helpful);
		return form;
	}
}
