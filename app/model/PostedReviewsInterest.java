/**
 * 
 */
package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.avaje.ebean.Model;



/**
 * @author PAmbure
 *
 */

@Entity
@Table(name = "posted_reviews_interest", uniqueConstraints = {@UniqueConstraint(columnNames = {
    "email_id", "review_id"})})
public class PostedReviewsInterest extends Model {
  private int reviewId;
  private String emailId;
  private Boolean helpful;

  @Column(name = "email_id")
  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  @Column(name = "helpful")
  public Boolean getHelpful() {
    return helpful;
  }

  public void setHelpful(Boolean helpful) {
    this.helpful = helpful;
  }

  @Column(name = "review_id")
public int getReviewId() {
	return reviewId;
}

public void setReviewId(int reviewId) {
	this.reviewId = reviewId;
}


}
