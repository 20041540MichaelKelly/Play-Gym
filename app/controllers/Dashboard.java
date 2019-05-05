package controllers;

import models.Assessments;
import models.Member;
import models.Trainers;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessments> assessments = member.assessmentsList;
    List<Trainers> trainers = Trainers.findAll();
    render("dashboard.html", member, assessments, trainers);
  }

  public static void addAssessments(double weight, double hips, double thigh, double upperArm, double waist, double chest, String comment) {
    Member member = Accounts.getLoggedInMember();
    Assessments assessments = new Assessments(weight, hips, thigh, upperArm, waist, chest, comment);
    member.assessmentsList.add(assessments);
    member.save();

    redirect("/dashboard");
  }

  public static void deleteAssessments (Long id, Long assessmentid)
  {
    Member member = Member.findById(id);
    Assessments assessments = Assessments.findById(assessmentid);
    member.assessmentsList.remove(assessments);
    member.save();
    assessments.delete();
    Logger.info ("Removing " + assessments.weight);
    redirect ("/dashboard");
  }

/*  public static void updateAssessments(Long id,double weight, double hips, double thigh, double upperArm, double waist, double chest,String comment) {
    Assessments assessments =Assessments.findById(id);
    assessments.setWeight(weight);
    assessments.setChest(chest);
    assessments.setComment(comment);

    assessments.save();
    redirect("/trainer");
  }
  */





}
