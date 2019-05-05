package controllers;

import java.util.List;

import models.Assessments;

import models.Trainers;
import models.Member;
import play.Logger;
import play.mvc.Controller;

public class TrainerCtrl extends Controller
{
    public static void index(Long id)
    {
        Member member = Member.findById(id);
        List<Assessments> assessments = member.assessmentsList;

        Logger.info ("Member id = " + member);
        render("members.html",assessments, member);


    }


}
