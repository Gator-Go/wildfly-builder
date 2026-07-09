package ppp.ppp.ppp.service;

import ppp.ppp.ppp.entity.AdminEvent;
import ppp.ppp.ppp.entity.EventSchedule;
import ppp.ppp.ppp.entity.EventParameter;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.enterprise.inject.Model;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


/**
 * This EJB3 provides the business logic needed to implement the services<br>
 * for the Event Component. This EJB3 is a Stateful bean that is the<br>
 * interface for all Event services.
 *
 * @author Ron Kanengieter
 * @author <a href="mailto:ron@usgs.gov">Ron Kanengieter</a>
 * @version 1.0
 * @version $Id$
 */
@Stateful
@Model
public class EventService
{

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<EventSchedule> eventScheduleSrc;


   /**
    * This service returns an AdminEvent object when given an name.
    *
    * @param name <code>Integer</code> object containing the adminEvent name.
    * @return <code>AdminEvent</code> object that contains the AdminEvent.
    */
    public AdminEvent getAdminEventbyName(String name)
    {
        try
        {
            Query myQuery = em.createQuery("FROM AdminEvent l WHERE l.name = :name");
        	myQuery.setParameter("name", name);

            AdminEvent adminEvent = (AdminEvent) myQuery.getSingleResult();
            log.info("Size of the userinfo collection returned from this method:  " + adminEvent.getUserEmail().size());
            return adminEvent;
        }
        catch (jakarta.persistence.NoResultException nre)
        {
            return null;
        }
    }

   /**
    * This service returns an AdminEvent object when given an id.
    *
    * @param id <code>Integer</code> object containing the adminEvent id.
    * @return <code>AdminEvent</code> object that contains the AdminEvent.
    */
   public AdminEvent getAdminEvent(Long id)
   {
      log.info("getAdminEvent id = " + id);
      AdminEvent adminEvent = em.find(AdminEvent.class, id);

      return adminEvent;
   }

   /**
    * This service creates a adminEvent object and returns its id.
    *
    * @param adminEvent <code>AdminEvent</code> object containing the adminEvent info to add.
    * @return <code>Long</code> object that contains the adminEvent id.
    */
   public Long addAdminEvent(AdminEvent adminEvent)
   {

      em.persist(adminEvent);

      Collection<EventParameter> eventParameterNew = new ArrayList<EventParameter>();
      Collection<EventParameter> eventParameterColl = adminEvent.getEventParameter();
      Iterator <EventParameter> i = eventParameterColl.iterator();
      while(i.hasNext())
      {
         EventParameter eventParameter = i.next();
         eventParameter.setAdminEvent(adminEvent);
         em.persist(eventParameter);
         eventParameterNew.add(eventParameter);
      }
      adminEvent.setEventParameter(eventParameterNew);
      em.merge(adminEvent);

      return adminEvent.getId();
   }

   /**
    * This service edits an AdminEvent object.
    *
    * @param editAdminEvent <code>AdminEvent</code> object containing the adminEvent edit info.
    * @return <code>String</code> object that indicates the success of the adminEvent edit.
    */
   public String editAdminEvent(AdminEvent editAdminEvent)
   {
      AdminEvent adminEvent = null;
      try {
         adminEvent = em.getReference(AdminEvent.class, editAdminEvent.getId());
      } catch (EntityNotFoundException notFound) {

         return "Not Found";
      }

      if (editAdminEvent.getName() != null)
         adminEvent.setName(editAdminEvent.getName());
      if (editAdminEvent.getDescription() != null)
         adminEvent.setDescription(editAdminEvent.getDescription());

      em.merge(adminEvent);

      return "Success";
   }

   /**
    * This service deletes an AdminEvent object.
    *
    * @param id <code>Long</code> object containing the adminEvent id.
    * @return <code>String</code> object that indicates the delete adminEvent success.
    */
   public String deleteAdminEvent(Long id)
   {
      log.info("deleteAdminEvent id = " + id);
      AdminEvent adminEvent = null;
      try {
         adminEvent = em.getReference(AdminEvent.class, id);
      } catch (EntityNotFoundException notFound) {

         return "Not Found";
      }

      em.remove(adminEvent);

      return "Success";
   }
   
   
   /**
    * This service returns all AdminEvent objects.
    *
    * @return <code>List</code> object that contains all existing adminEvents.
    */
   @SuppressWarnings("unchecked")
   public List <AdminEvent> findAllAdminEvents()
   {
      return em.createQuery("FROM AdminEvent l").getResultList();
   }



   /**
    * This service creates a eventSchedule object and returns its id.
    *
    * @param eventSchedule <code>EventSchedule</code> object containing the eventSchedule info to add.
    * @return <code>Long</code> object that contains the eventSchedule id.
    */
   public Long addEventSchedule(EventSchedule eventSchedule)
   {

      em.persist(eventSchedule);

      // fire the EventSchedule event msg for cron schedule reload
      eventScheduleSrc.fire(eventSchedule);

      return eventSchedule.getId();
   }

   /**
    * This service edits an EventSchedule object.
    *
    * @param editEventSchedule <code>EventSchedule</code> object containing the eventSchedule edit info.
    * @return <code>String</code> object that indicates the success of the eventSchedule edit.
    */
   public String editEventSchedule(EventSchedule editEventSchedule)
   {
      EventSchedule eventSchedule = null;
      try {
         eventSchedule = em.getReference(EventSchedule.class, editEventSchedule.getId());
      } catch (EntityNotFoundException notFound) {

         return "Not Found";
      }

      if (editEventSchedule.getAdminEvent() != null)
         eventSchedule.setAdminEvent(editEventSchedule.getAdminEvent());
      if (editEventSchedule.getSchedule() != null)
         eventSchedule.setSchedule(editEventSchedule.getSchedule());
      if (editEventSchedule.getEnabled() != null)
         eventSchedule.setEnabled(editEventSchedule.getEnabled());
      if (editEventSchedule.getLastRun() != null)
         eventSchedule.setLastRun(editEventSchedule.getLastRun());
      if (editEventSchedule.getMessage() != null)
         eventSchedule.setMessage(editEventSchedule.getMessage());

      em.merge(eventSchedule);

      // fire the EventSchedule event msg for cron schedule reload
      eventScheduleSrc.fire(eventSchedule);

      return "Success";
   }


   /**
    * This service deletes an EventParameter object.
    *
    * @param id <code>Long</code> object containing the EventParameter id.
    * @return <code>String</code> object that indicates the delete EventParameter success.
    */
   public String deleteEventParameter(Long id)
   {
      log.info("delete EventParameter id = " + id);
      EventParameter eventParameter = null;
      try {
         eventParameter = em.getReference(EventParameter.class, id);
      } catch (EntityNotFoundException notFound) {

         return "Not Found";
      }

      em.remove(eventParameter);

      return "Success";
   }

   /**
    * This service edits an EventParameter object.
    *
    * @param editEventParameter <code>EventParameter</code> object containing the eventParameter edit info.
    * @return <code>String</code> object that indicates the success of the eventParameter edit.
    */
   public String editEventParameter(EventParameter editEventParameter)
   {
      EventParameter eventParameter = null;
      try {
         eventParameter = em.getReference(EventParameter.class, editEventParameter.getId());
      } catch (EntityNotFoundException notFound) {

         return "Not Found";
      }

      if (editEventParameter.getAdminEvent() != null)
         eventParameter.setAdminEvent(editEventParameter.getAdminEvent());
      if (editEventParameter.getRequired() != null)
         eventParameter.setRequired(editEventParameter.getRequired());
      if (editEventParameter.getName() != null)
         eventParameter.setName(editEventParameter.getName());
      if (editEventParameter.getDescription() != null)
         eventParameter.setDescription(editEventParameter.getDescription());

      em.merge(eventParameter);

      return "Success";
   }

   /**
    * This service creates a EventParameter object and returns its id.
    *
    * @param eventParameter <code>EventParameter</code> object containing the EventParameter info to add.
    * @return <code>Long</code> object that contains the EventParameter id.
    */
   public Long addEventParameter(EventParameter eventParameter)
   {

      em.persist(eventParameter);

      return eventParameter.getId();
   }


}
