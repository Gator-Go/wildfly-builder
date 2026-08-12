package ppp.ppp.ppp.service;

import ppp.ppp.ppp.entity.UserSms;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.enterprise.inject.Model;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


/**
 * Lllll
 *
 * This is a Jakarta EE (CDI + EJB) stateful service class that provides
 * basic CRUD operations for UserSms entities via JPA.
 * It uses an injected EntityManager and logger. The methods are:
 * - getUserSms(Long id) — Finds and returns a UserSms by ID (also logs
 *   the size of its associated events collection).
 * - addUserSms(UserSms) — Persists a new UserSms and returns its ID.
 * - editUserSms(UserSms) — Updates first name, last name, SMS number, or events
 *   if supplied; returns "Success" or "Not Found".
 * - deleteUserSms(Long id) — Removes the entity by ID; returns "Success" or
 *   "Not Found".
 * - findAllUserSmss() — Returns a list of all UserSms entities.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Stateful
@Model
public class SmsSubsService
{

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

   /**
    * This service returns an UserSms object when given an id.
    *
    * @param id <code>Integer</code> object containing the userSms id.
    * @return <code>UserSms</code> object that contains the UserSms.
    */
   public UserSms getUserSms(Long id)
   {
      log.info("getUserSms id = " + id);
      UserSms userSms = em.find(UserSms.class, id);
      log.info("Size of the event set returned from this method:  " + userSms.getEvents().size());
      return userSms;
   }

   /**
    * This service creates a userSms object and returns its id.
    *
    * @param userSms <code>UserSms</code> object containing the userSms info to add.
    * @return <code>Long</code> object that contains the userSms id.
    */

   public Long addUserSms(UserSms userSms)
   {

      em.persist(userSms);

      return userSms.getId();
   }


   /**
    * This service edits an UserSms object.
    *
    * @param editUserSms <code>UserSms</code> object containing the userSms edit info.
    * @return <code>String</code> object that indicates the success of the userSms edit.
    */
   public String editUserSms(UserSms editUserSms)
   {
      UserSms userSms = null;
      try {
         userSms = em.getReference(UserSms.class, editUserSms.getId());
      } catch (EntityNotFoundException notFound) {

         return "Not Found";
      }

      if (editUserSms.getFirstName() != null)
         userSms.setFirstName(editUserSms.getFirstName());
      if (editUserSms.getLastName() != null)
         userSms.setLastName(editUserSms.getLastName());
      if (editUserSms.getSms() != null)
         userSms.setSms(editUserSms.getSms());
      if (editUserSms.getEvents() != null)
         userSms.setEvents(editUserSms.getEvents());

      em.merge(userSms);

      return "Success";
   }

   /**
    * This service deletes an UserSms object.
    *
    * @param id <code>Long</code> object containing the userSms id.
    * @return <code>String</code> object that indicates the delete userSms success.
    */
   public String deleteUserSms(Long id)
   {
      log.info("deleteUserSms id = " + id);
      UserSms userSms = null;
      try {
         userSms = em.getReference(UserSms.class, id);
      } catch (EntityNotFoundException notFound) {

         return "Not Found";
      }

      em.remove(userSms);

      return "Success";
   }
   
   
   /**
    * This service returns all UserSms objects.
    *
    * @return <code>List</code> object that contains all existing userSmss.
    */
   @SuppressWarnings("unchecked")
   public List <UserSms> findAllUserSmss()
   {
      return em.createQuery("FROM UserSms l").getResultList();
   }


}
