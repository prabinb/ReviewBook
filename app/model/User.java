package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.avaje.ebean.Model;

@Entity
@Table(name = "user")
public class User extends Model {

  @Id
  String emailId;
  String fullname;
  List<UserReviews> userReviews;

  @Column(name = "email_id")
  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  @Column(name = "full_name")
  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  public List<UserReviews> getUserReviews() {
    return userReviews;
  }

  public void setUserReviews(List<UserReviews> userReviews) {
    this.userReviews = userReviews;
  }



}
