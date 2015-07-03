package model.form;

import model.User;
import play.data.validation.Constraints.Required;

public class UserForm {
  private String fullName;

  @Required
  private String emailId;


  public String getFullName() {
    return fullName;
  }


  public void setFullName(String fullName) {
    this.fullName = fullName;
  }


  public String getEmailId() {
    return emailId;
  }


  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }


  public User getUser() {
    User user = new User();
    user.setFullname(fullName);
    user.setEmailId(emailId);
    return user;
  }
}
