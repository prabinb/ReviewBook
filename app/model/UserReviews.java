package model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.avaje.ebean.Model;

@Entity
@Table(name = "user_reviews", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"email_id", "product_name" }) })
public class UserReviews extends Model {

	@Id
	@GeneratedValue
	@Column(name = "review_id")
	int reviewId;
	User user;
	String productName;
	String reviewContent;
	String reviewTitle;
	boolean recommend;
	String imageType;
	byte[] imageData;
	Date postedDate;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "email_id", referencedColumnName = "email_id", table = "user")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "product_name")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "review_content", columnDefinition = "TEXT")
	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	@Column(name = "review_title")
	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	@Column(name = "is_recommended")
	public boolean isRecommend() {
		return recommend;
	}

	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	@Column(name = "image_type", unique = false, nullable = true)
	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Column(name = "image_data", columnDefinition="BLOB")
	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	@Column(name="posted_date",columnDefinition="DATETIME")
	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

}
