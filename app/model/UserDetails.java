package model;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;


@Entity
public class UserDetails extends  Model {


    @Id
    String emailId;
    String firstname;
    String lastname;



}

