package ppp.ppp.ppp.service;

import ppp.ppp.ppp.entity.UserEmail;

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
 * This is a Jakarta EE (CDI + EJB) stateful service class that performs basic CRUD
 * operations on UserEmail entities via JPA.
 * It uses an injected EntityManager and logger, and exposes these methods:
 * - getUserEmail(Long id) — Looks up a UserEmail by ID and returns it (also logs the
 *   size of its associated events collection).
 * - addUserEmail(UserEmail) — Persists a new UserEmail and returns its generated ID.
 * - editUserEmail(UserEmail) — Updates selected fields (first name, last name, email,
 *   events) of an existing entity if they are non-null, then merges it. Returns "Success"
 *   or "Not Found".
 * - deleteUserEmail(Long id) — Removes the entity by ID. Returns "Success" or "Not Found".
 * - findAllUserEmails() — Returns a list of all UserEmail entities via a simple JPQL query.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Stateful
@Model
public class EmailSubsService
{

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

   /**
    * This service returns an UserEmail object when given an id.
    *
    * @param id <code>Integer</code> object containing the userEmail id.
    * @return <code>UserEmail</code> object that contains the UserEmail.
    */
   public UserEmail getUserEmail(Long id)
   {
      log.info("getUserEmail id = " + id);
      UserEmail userEmail = em.find(UserEmail.class, id);
      log.info("Size of the event set returned from this method:  " + userEmail.getEvents().size());
      return userEmail;
   }

   /**
    * This service creates a userEmail object and returns its id.
    *
    * @param userEmail <code>UserEmail</code> object containing the userEmail info to add.
    * @return <code>Long</code> object that contains the userEmail id.
    */

   public Long addUserEmail(UserEmail userEmail)
   {

      em.persist(userEmail);

      return userEmail.getId();
   }


   /**
    * This service edits an UserEmail object.
    *
    * @param editUserEmail <code>UserEmail</code> object containing the userEmail edit info.
    * @return <code>String</code> object that indicates the success of the userEmail edit.
    */
   public String editUserEmail(UserEmail editUserEmail)
   {
      UserEmail userEmail = null;
      try {
         userEmail = em.getReference(UserEmail.class, editUserEmail.getId());
      } catch (EntityNotFoundException notFound) {

         return "Not Found";
      }

      if (editUserEmail.getFirstName() != null)
         userEmail.setFirstName(editUserEmail.getFirstName());
      if (editUserEmail.getLastName() != null)
         userEmail.setLastName(editUserEmail.getLastName());
      if (editUserEmail.getEmail() != null)
         userEmail.setEmail(editUserEmail.getEmail());
      if (editUserEmail.getEvents() != null)
         userEmail.setEvents(editUserEmail.getEvents());

      em.merge(userEmail);

      return "Success";
   }

   /**
    * This service deletes an UserEmail object.
    *
    * @param id <code>Long</code> object containing the userEmail id.
    * @return <code>String</code> object that indicates the delete userEmail success.
    */
   public String deleteUserEmail(Long id)
   {
      log.info("deleteUserEmail id = " + id);
      UserEmail userEmail = null;
      try {
         userEmail = em.getReference(UserEmail.class, id);
      } catch (EntityNotFoundException notFound) {

         return "Not Found";
      }

      em.remove(userEmail);

      return "Success";
   }
   
   
   /**
    * This service returns all UserEmail objects.
    *
    * @return <code>List</code> object that contains all existing userEmails.
    */
   @SuppressWarnings("unchecked")
   public List <UserEmail> findAllUserEmails()
   {
      return em.createQuery("FROM UserEmail l").getResultList();
   }


}
