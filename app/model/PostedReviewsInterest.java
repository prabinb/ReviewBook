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
  private UserReviews userReviews;
  private String emailId;
  private Boolean helpful;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "review_id", referencedColumnName = "review_id", table = "user_reviews")
  public UserReviews getUserReviews() {
    return userReviews;
  }

  public void setUserReviews(UserReviews userReviews) {
    this.userReviews = userReviews;
  }

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


}
