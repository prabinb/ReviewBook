package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.avaje.ebean.Model;

@Entity
@Table(name = "user_reviews", uniqueConstraints = { @UniqueConstraint(columnNames = { "email_id", "product_name" }) })
public class UserReviews  extends Model{

	String emailId;
	String productName;
	String reviewContent;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email_id", referencedColumnName = "email_id")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Column(name = "product_name")
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Column(name="review_content")
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}	
	
}
