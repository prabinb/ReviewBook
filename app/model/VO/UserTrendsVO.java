package model.VO;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;

@Entity
@Sql
public class UserTrendsVO {
  String fullName;
  int reviewsCount;

  public String getFull_name() {
    return fullName;
  }

  public void setFull_name(String full_name) {
    this.fullName = full_name;
  }

  public int getReviews_count() {
    return reviewsCount;
  }

  public void setReviews_count(int reviews_count) {
    this.reviewsCount = reviews_count;
  }

}
