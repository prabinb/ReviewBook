package model;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;


@Entity
public class UserDetails extends Model {


  @Id
  String emailId;
  String firstname;
  String lastname;



}
