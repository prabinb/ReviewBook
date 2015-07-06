package services.impl;

import java.util.List;

import model.PostedReviewsInterest;
import model.User;
import model.UserReviews;
import model.VO.ProductTrendsVO;
import model.VO.UserReviewsVO;
import model.VO.UserTrendsVO;
import model.form.PostedReviewsInterestForm;

import org.apache.commons.lang3.StringUtils;

import services.UserService;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.google.common.collect.Lists;

public class UserServiceImpl implements UserService {
  @Override
  public boolean create(User user) {
    User existingUser =
        Ebean.find(User.class).where().eq("email_id", user.getEmailId()).findUnique();
    if (existingUser == null) {
      user.save();
      return true;
    }
    return false;
  }

  @Override
  public List<UserReviewsVO> getAllUserReviews(String emailId) {
    List<UserReviews> reviews =
        Ebean.find(UserReviews.class).where().eq("email_id", emailId).findList();
    List<UserReviewsVO> output = Lists.newArrayList();
    for (UserReviews review : reviews) {
      List<PostedReviewsInterest> postedReviewsInterest = Lists.newArrayList();
      postedReviewsInterest =
          Ebean.find(PostedReviewsInterest.class).where().eq("review_id", review.getReviewId())
              .findList();
      // find.fetch("postedUserReviews").where().eq("review_id, review.getReviewId()").findList();
      /*
       * review.setPostedReviewsInterest(Ebean.find(PostedReviewsInterest.class)
       * .fetch("PostedReviewsInterest").findList()); //
       * Ebean.find(UserReviews.class).fetch("postedUserReviews", new FetchConfig().query()); //
       */
      for (PostedReviewsInterest interest : postedReviewsInterest) {
        interest.setUserReviews(null);
      }
      review.setPostedReviewsInterest(postedReviewsInterest);
      UserReviewsVO vo = new UserReviewsVO(review);
      output.add(vo);
    }

    return output;
  }

  @Override
  public List<UserReviewsVO> getAllProductReviews(String getAllProductReviews) {
    List<UserReviews> reviews =
        Ebean.find(UserReviews.class).where().ilike("product_name", getAllProductReviews)
            .findList();
    List<UserReviewsVO> output = Lists.newArrayList();
    for (UserReviews review : reviews) {
      UserReviewsVO vo = new UserReviewsVO(review);
      output.add(vo);
    }
    return output;
  }

  @Override
  public boolean saveUserReview(UserReviews reviews) {
    reviews.save();
    return true;
  }

  @Override
  public Boolean savePostedReviewsInterest(PostedReviewsInterestForm postedReviewsInterestForm) {
    PostedReviewsInterest exists =
        Ebean
            .find(PostedReviewsInterest.class)
            .where()
            .and(Expr.eq("email_id", postedReviewsInterestForm.getEmailId()),
                Expr.eq("review_id", postedReviewsInterestForm.getReviewId())).findUnique();
    if (exists == null) {
      PostedReviewsInterest postedReviewsInterest = new PostedReviewsInterest();
      postedReviewsInterest.setEmailId(postedReviewsInterestForm.getEmailId());
      // postedReviewsInterest.setReviewId(postedReviewsInterestForm.getReviewId());
      postedReviewsInterest.setHelpful(postedReviewsInterestForm.isHelpful());
      UserReviews userReviews =
          Ebean.find(UserReviews.class).where()
              .eq("review_id", postedReviewsInterestForm.getReviewId()).findUnique();
      /*
       * List<PostedReviewsInterest> existingPostedReviewsInterest =
       * userReviews.getPostedReviewsInterest(); if (existingPostedReviewsInterest == null) {
       * existingPostedReviewsInterest = Lists.newArrayList(); }
       * existingPostedReviewsInterest.add(postedReviewsInterest);
       */
      postedReviewsInterest.setUserReviews(userReviews);
      Ebean.save(postedReviewsInterest);
      return Boolean.TRUE;
    } else {
      // User can post his interest only once for a particular review.
      return Boolean.FALSE;
    }
  }

  @Override
  public List<UserReviewsVO> getAllReviews(String search) {
    List<UserReviews> reviews = null;
    if (StringUtils.isEmpty(search)) {
      reviews = Ebean.find(UserReviews.class).findList();
    } else {
      search += '%';
      reviews =
          Ebean.find(UserReviews.class).where()
              .or(Expr.like("review_title", search), Expr.like("product_name", search)).findList();

    }
    List<UserReviewsVO> output = Lists.newArrayList();
    if (reviews != null) {
      for (UserReviews review : reviews) {
        UserReviewsVO vo = new UserReviewsVO(review);
        output.add(vo);
      }
    }
    return output;
  }

  @Override
  public List<ProductTrendsVO> getTrendingProducts() {
    String sql =
        " select product_name, sum(is_recommended) as is_recommended, count(*) as total_count "
            + " from user_reviews " + " group by product_name ";

    RawSql rawSql = RawSqlBuilder.parse(sql).create();
    Query<ProductTrendsVO> query = Ebean.find(ProductTrendsVO.class);
    query.setRawSql(rawSql).order().desc("is_recommended").setMaxRows(4);
    List<ProductTrendsVO> output = query.findList();
    return output;
  }

  @Override
  public List<UserTrendsVO> getTrendingUsers() {
    String sql =
        " select email_id, count(*) as total_reviews " + " from user_reviews "
            + " group by email_id";

    RawSql rawSql = RawSqlBuilder.parse(sql).create();
    Query<UserTrendsVO> query = Ebean.find(UserTrendsVO.class);
    query.setRawSql(rawSql).order().desc("total_reviews").setMaxRows(4);
    List<UserTrendsVO> output = query.findList();
    return output;
  }
}
