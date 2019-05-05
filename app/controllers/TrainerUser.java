package controllers;

import models.Assessments;
import models.Member;
import models.Trainers;
import play.Logger;
import play.mvc.Controller;

import javax.persistence.Entity;
import java.util.List;


public class TrainerUser extends Controller {


    public static void index()
    {
        Logger.info("Rendering Admin");
        List<Trainers>trainers = Trainers.findAll();
        List<Member>member = Member.findAll();
        List<Assessments>assessments = Member.findAll();

        render ("trainer.html", assessments, member, trainers);
    }

    public static void deleteMember (Long id, Long memberid)
    {
        Logger.info ("Removing ");
        Trainers trainers = Trainers.findById(id);
        Member members = Member.findById(memberid);
        trainers.membersList.remove(memberid);
        Logger.info ("Removing " + members.id);
        trainers.save();
        members.delete();
        Logger.info ("Removing " + members.name);
        redirect ("/trainer");
    }

}
