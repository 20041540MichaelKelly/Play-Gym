package models;

import controllers.Accounts;
import play.Logger;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Member extends Model {
    public double height, startingWeight;
    public String name, email, password, address, gender, weightClass, cole;
    public double bmi;
    double fweight;
    double mweight;

    @OneToMany(cascade = CascadeType.ALL)
    public List < Assessments > assessmentsList = new ArrayList < Assessments > ();

    public Member(String name, String gender, String email, String password, String address, double height, double startingWeight) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.address = address;
        this.height = height;
        this.startingWeight = startingWeight;

    }

    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public double getBmi() {
        Member member = Member.findById(id);
        List < Assessments > assessments = member.assessmentsList;
        for (Assessments assessments1: assessments) {
            double bmiVal = (assessments1.weight) / (member.height * member.height);
            double roundOff = Math.round(bmiVal * 100.0) / 100.0;
            bmi = roundOff;

            Logger.info("ass " + assessments1.weight);
            Logger.info("height " + bmiVal);
        }
        return bmi;


    }

    public String getWeightClass() {

        if (bmi < 18.5) {
            weightClass = "UnderWeight";
            return weightClass;

        } else if (bmi >= 18.5 && bmi < 24.9) {
            weightClass = "Normal Weight";
            return weightClass;
        } else if (bmi >= 25 && bmi < 29.9) {
            weightClass = "OverWeight";
            return weightClass;
        } else {
            weightClass = "Obese";
            return weightClass;
        }
    }

    public double getHeight() {
        return height;
    }

    public String getGender() {
        return gender;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getStartingWeight() {
        return startingWeight;
    }

    public String isIdealBodyWeight() {
        Member member = Member.findById(id);
        List < Assessments > assessessments = member.assessmentsList;
        String c = "";

        //  if (( height1> MIN_HEIGHT_FEET)) {
        for (Assessments assessments1: assessessments) {
            if (member.getGender().equals("male")) {
                mweight = 50 + 2.3 * (member.height - 1.52);

            } else {
                fweight = 45 + 2.3 * (member.height - 1.52);
            }


            Logger.info("heightAmountCm " + fweight);

            if (mweight > assessments1.weight || fweight > assessments1.weight) {
                c = "green";

            } else {
                c = "red";
            }
        }
        return c;

    }



    public String getCount() {
        Member member = Accounts.getLoggedInMember();
        List < Assessments > assessments = member.assessmentsList;
        int count = 0;
        for (Assessments assessments1: assessments) {
            count++;
        }
        String c = count + " Assessments";
        return c;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setStartingWeight(double startingWeight) {
        this.startingWeight = startingWeight;
    }



}