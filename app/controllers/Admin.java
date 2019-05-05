package controllers;

import java.util.List;

import models.Assessments;
import play.Logger;
import play.mvc.Controller;

public class Admin extends Controller
{
    public static void index()
    {
        Logger.info("Rendering Admin");

        // TODO - get list of all songs, and send them to the view
        List<Assessments> assessments = Assessments.findAll();
        render ("admin.html", assessments);
    }
}
