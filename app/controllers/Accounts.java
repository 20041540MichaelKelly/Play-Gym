package controllers;

import models.Assessments;
import models.Member;
import models.Trainers;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Accounts extends Controller
{
    public static void signup()
    {
        render("signup.html");
    }

    public static void login()
    {
        render("login.html");
    }

    public static void register(String name, String gender, String email, String password, String address, double height, double startingWeight)
    {
        Logger.info("Registering new user " + email);
        Member member = new Member(name, gender, email, password, address, height, startingWeight);
        member.save();
        redirect("/");
    }

    public static void authenticate(String email, String password)
    {
        Logger.info("Attempting to authenticate with " + email + ":" + password);
        Trainers trainers = Trainers.findByEmail(email);
        Member member = Member.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect ("/dashboard");
        } else if ((trainers != null) && (trainers.checkPassword(password))) {
            Logger.info("Authentication successful");
            session.put("logged_in_Trainerid", trainers.id);
            redirect ("/trainer");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }

    }

    public static void logout()
    {
        session.clear();
        redirect ("/");
    }

    public static Member getLoggedInMember()
    {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    public static Trainers getLoggedInTrainer()
    {
        Trainers trainers = null;
        if (session.contains("logged_in_Memberid")) {
            String trainerId = session.get("logged_in_Memberid");
            trainers = Trainers.findById(Long.parseLong(trainerId));
        } else {
            login();
        }
        return trainers;
    }

    public static void updateMember(String name, String gender, String email, String password, String address, double height, double startingWeight)
    {
        Logger.info("Registering new user " + email);
        Member member = Accounts.getLoggedInMember();
        member.setEmail(email);
        member.setName(name);
        member.setGender(gender);
        member.setPassword(password);
        member.setAddress(address);
        member.setHeight(height);
        member.setStartingWeight(startingWeight);



        member.save();
        redirect("/dashboard");
    }




}
