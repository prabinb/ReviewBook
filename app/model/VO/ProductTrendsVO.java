package model.VO;

import javax.persistence.Entity;

import com.avaje.ebean.annotation.Sql;

@Entity
@Sql
public class ProductTrendsVO {

  String productName;
  int is_recommended;
  int total_count;


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public int getIs_recommended() {
    return is_recommended;
  }

  public void setIs_recommended(int is_recommended) {
    this.is_recommended = is_recommended;
  }

  public int getTotal_count() {
    return total_count;
  }

  public void setTotal_count(int total_count) {
    this.total_count = total_count;
  }

}
