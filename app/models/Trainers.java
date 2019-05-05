package models;

import controllers.Accounts;
import play.Logger;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static play.mvc.Controller.redirect;

@Entity
public class Trainers extends Model {

    public String name, email, password;


    @OneToMany(cascade = CascadeType.ALL)
    public List < Member > membersList = new ArrayList < Member > ();


    public Trainers(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;



    }

    public static Trainers findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }


}