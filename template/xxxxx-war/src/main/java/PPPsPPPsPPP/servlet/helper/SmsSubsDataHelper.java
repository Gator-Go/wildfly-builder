package ppp.ppp.ppp.servlet.helper;

import ppp.ppp.ppp.entity.AdminEvent;
import ppp.ppp.ppp.service.EventService;
import ppp.ppp.ppp.entity.UserSms;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

/**
 * This class provides common methods to manage data in the SmsSubsServlet.
 *
 * @author Ron Kanengieter
 * @author <a href="mailto:ron@usgs.gov">Ron Kanengieter</a>
 * @version 1.0
 * @version $Id$
 */
@RequestScoped
public class SmsSubsDataHelper
{

    @Inject
    private EventService eventService;


   /**
    * This method loads Event SmsSubs selection data to the req attributes.
    *
    * @param req A HttpServletRequest object to be loaded with default data.
    */
   public void loadEventSmsSubsData(HttpServletRequest req)
   {

      List<OptionId> events = new ArrayList<OptionId>();
      OptionId option1 = new OptionId();
      option1.setOption("Select Event To Add");
      option1.setId("0");
      option1.setSelected("selected");
      events.add(option1);

      List<AdminEvent> adminEvents = eventService.findAllAdminEvents();
      Iterator <AdminEvent> i = adminEvents.iterator();
      while(i.hasNext())
      {
         AdminEvent adminEvent = i.next();
         // sending an Email on an Email event would put us in a loop same with sms
         if (adminEvent.getName().equals("EmailEvent") 
		|| adminEvent.getName().equals("GetEmailEvent")
		|| adminEvent.getName().equals("SmsEvent"))
           continue;
         OptionId option = new OptionId();
         option.setOption(adminEvent.getName());
         option.setId(adminEvent.getId().toString());
         events.add(option);
      }
      req.setAttribute("eventSelects", events);
   }





   /**
    * This method loads LandDatabase req parameter data to the req attribute date.
    * Would be used after an error and to re-show the data entered on the form.
    *
    * @param req A HttpServletRequest object to be loaded with parameter data.
    */

   public void loadSmsSubsDataFromReq(HttpServletRequest req, String newSmsSubsId)
   {
      req.setAttribute("returnOp", req.getParameter("returnOp"));
      req.setAttribute("returnAction", req.getParameter("returnAction"));
      req.setAttribute("id", req.getParameter("id"));
      req.setAttribute("firstName", req.getParameter("firstName"));
      req.setAttribute("lastName", req.getParameter("lastName"));
      req.setAttribute("sms", req.getParameter("sms"));

      List<SmsSubsFmt> smsSubsFmts = new ArrayList<SmsSubsFmt>();
      if (newSmsSubsId != null) {
        Long adminEventId = new Long(newSmsSubsId);
        AdminEvent adminEvent = eventService.getAdminEvent(adminEventId);

        SmsSubsFmt smsSubsFmt = new SmsSubsFmt();
        smsSubsFmt.setDeleteFlag("false");
        smsSubsFmt.setId(adminEvent.getId().toString());
        smsSubsFmt.setName(adminEvent.getName());
        smsSubsFmt.setDescription(adminEvent.getDescription());
        smsSubsFmts.add(smsSubsFmt);
      }

      int opRows = Integer.parseInt(req.getParameter("nbrOp"));
      for ( int x = 0; x < opRows; x++) {
        String deleteFlag = "false";
        String deleteId = "deleteFlag" + x;
        String delete = req.getParameter(deleteId);
        if (delete != null && delete.equals("true"))
          deleteFlag = "true";

        String idId = "id" + x;
        String id = req.getParameter(idId);

        String nameId = "name" + x;
        String name = req.getParameter(nameId);

        String descriptionId = "description" + x;
        String description = req.getParameter(descriptionId);

        SmsSubsFmt smsSubsFmt = new SmsSubsFmt();
        smsSubsFmt.setDeleteFlag(deleteFlag);
        smsSubsFmt.setId(id);
        smsSubsFmt.setName(name);
        smsSubsFmt.setDescription(description);
        smsSubsFmts.add(smsSubsFmt);
      }
      req.setAttribute("smsSubsFmts", smsSubsFmts);
      String opSize = "" + smsSubsFmts.size();
      req.setAttribute("nbrOp", opSize);

   }


   /**
    * This method loads data from a req object to a AdminSmsSubs object.
    *
    * @param adminSmsSubs A AdminSmsSubs object to be loaded with data.
    * @param req A HttpServletRequest object containing the data.
    */

   public void loadUserSms(UserSms userSms, HttpServletRequest req)
   {

      if(!req.getParameter("firstName").isEmpty())
      {
         String firstName = req.getParameter("firstName");
         userSms.setFirstName(firstName);
      }

      if(!req.getParameter("lastName").isEmpty())
      {
         String lastName = req.getParameter("lastName");
         userSms.setLastName(lastName);
      }

      if(!req.getParameter("sms").isEmpty())
      {
         String sms = req.getParameter("sms");
         userSms.setSms(sms);
      }

      Set<AdminEvent> events = new HashSet<AdminEvent>();
      int paramRows = Integer.parseInt(req.getParameter("nbrOp"));
      for ( int x = 0; x < paramRows; x++) {

        String idId = "id" + x;
        String id = req.getParameter(idId);
        Long adminEventId = new Long(id);
        AdminEvent adminEvent = eventService.getAdminEvent(adminEventId);

        events.add(adminEvent);
      }
      userSms.setEvents(events);

   }



   /**
    * This method loads data from a AdminSmsSubs object to a req object.
    *
    * @param adminSmsSubs A AdminSmsSubs object containing the data.
    * @param req A HttpServletRequest object to be loaded with data.
    */

   public void loadSmsSubsFormData(UserSms userSms, HttpServletRequest req)
   {
      req.setAttribute("id", userSms.getId().toString());
      req.setAttribute("firstName", userSms.getFirstName());
      req.setAttribute("lastName", userSms.getLastName());
      req.setAttribute("sms", userSms.getSms());

      List<SmsSubsFmt> smsSubsFmts = new ArrayList<SmsSubsFmt>();
      Set<AdminEvent> events = userSms.getEvents();
      Iterator <AdminEvent> i = events.iterator();
      while(i.hasNext())
      {
         AdminEvent adminEvent = i.next();
         SmsSubsFmt smsSubsFmt = new SmsSubsFmt();
         smsSubsFmt.setDeleteFlag("false");
         smsSubsFmt.setId(adminEvent.getId().toString());
         smsSubsFmt.setName(adminEvent.getName());
         smsSubsFmt.setDescription(adminEvent.getDescription());
         smsSubsFmts.add(smsSubsFmt);
      }
      req.setAttribute("smsSubsFmts", smsSubsFmts);
      String opSize = "" + smsSubsFmts.size();
      req.setAttribute("nbrOp", opSize);

   }

}
