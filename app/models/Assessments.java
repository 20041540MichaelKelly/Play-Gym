package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import controllers.Accounts;
import play.db.jpa.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Assessments extends Model {
    public double weight, chest, thigh, upperArm, waist, hips;
    public String comment;


    public Assessments(double weight, double chest, double thigh, double upperArm, double waist, double hips, String comment) {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
        this.comment = comment;

    }

    public Long getId() {
        return id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTrend(double weight) {
        Member member = Accounts.getLoggedInMember();
        List < Assessments > assessments = member.assessmentsList;
        String color = "";

        for (Assessments assessments1: assessments) {
            if (member.getStartingWeight() > weight) {
                color = "green";
            } else {
                color = "red";
            }
        }
        return color;

    }

    public String getCurrentTimeUsingCalendar() {
        Member member = Accounts.getLoggedInMember();
        List < Assessments > assessments = member.assessmentsList;

        String timeStamp = "";

        for (Assessments assessments1: assessments) {

            timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

        }

        return timeStamp;
    }




    public double getWeight() {
        return weight;
    }
}