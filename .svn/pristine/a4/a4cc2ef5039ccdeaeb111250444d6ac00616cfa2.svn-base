
package ppp.ppp.ppp.service;

import ppp.ppp.ppp.entity.Yyyyy;

___IMPORT_OPTION_ONE2MANY_CHILD___
___IMPORT_OPTION_ONE2MANY_CHILD_ALERT___
___IMPORT_OPTION_ONE2MANY_PARENT___
___IMPORT_OPTION_ONE2MANY_PARENT_ALERT___

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateful;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.Root;

import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
import java.io.File;
import java.math.BigDecimal;

// The @Stateful annotation eliminates the need for manual transaction demarcation
@Stateful
@Model
public class YyyyyService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Yyyyy> yyyyyEventSrc;

___INJECT_REST_ONE2MANY_PARENT___

   /**
    * This service returns the Yyyyy object when given an cloud id.
    *
    * @param id <code>Integer</code> object containing the yyyyy id.
    * @return <code>Yyyyy</code> object that contains the Yyyyy.
    */
   public Yyyyy getYyyyyByCloudId(Long deviceId, Long yyyyyId)
   {
      log.info("getYyyyyByCloudId deviceId = " + deviceId.toString() + "  yyyyyId = " + yyyyyId.toString());

      List<Yyyyy> yyyyyList = em.createQuery("SELECT t FROM Yyyyy t where t.deviceId = :deviceId AND t.yyyyyId = :yyyyyId")
           .setParameter("deviceId", deviceId).setParameter("yyyyyId", yyyyyId).getResultList();

      Yyyyy yyyyy = null;
      if (yyyyyList.size() > 0)
          yyyyy = yyyyyList.get(0);

      return yyyyy;
   }



   /**
    * This service returns the Yyyyy object when given an id.
    *
    * @param id <code>Integer</code> object containing the yyyyy id.
    * @return <code>Yyyyy</code> object that contains the Yyyyy.
    */
   public Yyyyy getYyyyy(Long id)
   {
      log.info("getYyyyy id = " + id.toString());
      Yyyyy yyyyy = em.find(Yyyyy.class, id);

___GET_INFO_ONE2MANY_PARENT___
___GET_INFO_ONE2MANY_PARENT_ALERT___

      return yyyyy;
   }


    /**
     * This service creates the Yyyyy object and returns the outcome.
     *
     * @param yyyyy <code>Yyyyy</code> object containing the yyyyy info to add.
     * @return <code>String</code> object that indicates the success of the yyyyy add.
     */
    public String addYyyyy(Yyyyy yyyyy)
    {

        try {
___SAVE_NAME_CAMERA___
___SAVE_NAME_VIDEO___
___SAVE_NAME_THUMBNAIL___
___SAVE_NAME_POST___
            yyyyy.setId(null);
            em.persist(yyyyy);
            if (yyyyy.getDeviceId() == null || yyyyy.getDeviceId().longValue() == 0)
                yyyyy.setDeviceId(new Long(1));
            if (yyyyy.getYyyyyId() == null || yyyyy.getYyyyyId().longValue() == 0)
                yyyyy.setYyyyyId(yyyyy.getId());
            em.merge(yyyyy);
            em.flush();
            em.clear();

___RENAME_PICTURE_CAMERA___
___RENAME_PICTURE_VIDEO___
___RENAME_PICTURE_THUMBNAIL___
___RENAME_PICTURE_POST___
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            String errorMsg = "Yyyyy add exception: " + t.getMessage();
            log.info(errorMsg);
            return errorMsg;
        }
        return "success";
    }


    /**
     * This service creates the Yyyyy object and returns the outcome.
     *
     * @param yyyyy <code>Yyyyy</code> object containing the yyyyy info to add.
     * @return <code>String</code> object that indicates the success of the yyyyy add.
     */
    public String addYyyyySync(Yyyyy yyyyy)
    {

        try {
            yyyyy.setId(null);
            em.persist(yyyyy);
            if (yyyyy.getDeviceId() == null || yyyyy.getDeviceId().longValue() == 0)
                yyyyy.setDeviceId(new Long(1));
            if (yyyyy.getYyyyyId() == null || yyyyy.getYyyyyId().longValue() == 0)
                yyyyy.setYyyyyId(yyyyy.getId());
            em.merge(yyyyy);
            em.flush();
            em.clear();

        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            String errorMsg = "Yyyyy add exception: " + t.getMessage();
            log.info(errorMsg);
            return errorMsg;
        }
        return "success";
    }


    /**
     * This service edits the Yyyyy object.
     *
     * @param editYyyyy <code>Yyyyy</code> object containing the yyyyy edit info.
     * @return <code>String</code> object that indicates the success of the yyyyy edit.
     */
    public String editYyyyy(Yyyyy editYyyyy)
    {
        Yyyyy yyyyy = null;
        try {
           yyyyy = em.getReference(Yyyyy.class, editYyyyy.getId());
        } catch (EntityNotFoundException notFound) {

           return "The Yyyyy ID " + editYyyyy.getId().toString() + " not found.";
        }
        yyyyy.setLastUpdate(new Date());
        yyyyy.setDeleteFlag(editYyyyy.getDeleteFlag());

___SET_EDIT_STRING___
___SET_EDIT_ENUM___
___SET_EDIT_TAG___
___SET_EDIT_CLOB___
___SET_EDIT_BOOLEAN___
___SET_EDIT_INTEGER___
___SET_EDIT_LONG___
___SET_EDIT_DOUBLE___
___SET_EDIT_BIG_DECIMAL___
___SET_EDIT_MONEY___
___SET_EDIT_LOC___
___SET_EDIT_CURRENT_LOC___
___SET_EDIT_DATE___
___SET_EDIT_DATE_TIME___
___SET_EDIT_CAMERA___
___SET_EDIT_VIDEO___
___SET_EDIT_THUMBNAIL___
___SET_EDIT_POST___
___SET_EDIT_ONE2MANY_CHILD___
___SET_EDIT_ONE2MANY_CHILD_ALERT___

        try {
            em.merge(yyyyy);
            em.flush();
            em.clear();

___RENAME_PICTURE_CAMERA___
___RENAME_PICTURE_VIDEO___
___RENAME_PICTURE_THUMBNAIL___
___RENAME_PICTURE_POST___
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            String errorMsg = "Yyyyy edit exception: " + t.getMessage();
            log.info(errorMsg);
            return errorMsg;
        }
        return "success";
    }


    /**
     * This service deletes the Yyyyy object.
     *
     * @param id <code>Long</code> object containing the yyyyy id.
     * @return <code>String</code> object that indicates the delete yyyyy success.
     */
    public String deleteYyyyy(Long id)
    {

        Yyyyy yyyyy = null;
        try {
           yyyyy = em.getReference(Yyyyy.class, id);
        } catch (EntityNotFoundException notFound) {

           return "The Yyyyy ID " + id.toString() + " not found.";
        }

        try {
___DELETE_PICTURE_CAMERA___
___DELETE_PICTURE_VIDEO___
___DELETE_PICTURE_THUMBNAIL___
___DELETE_PICTURE_POST___

___DELETE_CHILD_ARRAY_PARENT___

            em.remove(yyyyy);
            em.flush();
            em.clear();
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            String errorMsg = "Yyyyy delete exception: " + t.getMessage();
            log.info(errorMsg);
            return errorMsg;
        }
        return "success";
   }



    /**
     * This service deletes the Yyyyy objects in array.
     *
     * @param id <code>yyyyys</code> object containing the yyyyy array.
     * @return <code>String</code> object that indicates the delete yyyyy success.
     */
    public String deleteYyyyyArray(Collection<Yyyyy> yyyyys)
    {

        try {

          for (Yyyyy yyyyy : yyyyys) {

___DELETE_PICTURE_CAMERA___
___DELETE_PICTURE_VIDEO___
___DELETE_PICTURE_THUMBNAIL___
___DELETE_PICTURE_POST___

___DELETE_CHILD_ARRAY_PARENT___

            em.remove(yyyyy);
          }

        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            String errorMsg = "Yyyyys delete exception: " + t.getMessage();
            log.info(errorMsg);
            return errorMsg;
        }
        return "success";
   }



    /**
     * This service deletes the Yyyyy object.
     *
     * @param id <code>Long</code> object containing the yyyyy id.
     * @return <code>String</code> object that indicates the delete yyyyy success.
     */
    public String deleteYyyyyFlag(Long id)
    {

        Yyyyy yyyyy = null;
        try {
           yyyyy = em.getReference(Yyyyy.class, id);
        } catch (EntityNotFoundException notFound) {

           return "The Yyyyy ID " + id.toString() + " not found.";
        }

        Boolean boolVal = new Boolean(true);
        if (yyyyy.getDeleteFlag().booleanValue() == true)
            boolVal = new Boolean(false);

        try {

___DELETE_CHILD_ARRAY_FLAG_PARENT___

            yyyyy.setLastUpdate(new Date());
            yyyyy.setDeleteFlag(boolVal);
            em.merge(yyyyy);
            em.flush();
            em.clear();
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            String errorMsg = "Yyyyy delete exception: " + t.getMessage();
            log.info(errorMsg);
            return errorMsg;
        }
        return "success";
   }



    /**
     * This service deletes the Yyyyy objects in array.
     *
     * @param id <code>yyyyys</code> object containing the yyyyy array.
     * @return <code>String</code> object that indicates the delete yyyyy success.
     */
    public String deleteYyyyyArrayFlag(Collection<Yyyyy> yyyyys, Boolean boolVal)
    {

        try {

          for (Yyyyy yyyyy : yyyyys) {

___DELETE_CHILD_ARRAY_FLAG_PARENT___

            yyyyy.setLastUpdate(new Date());
            yyyyy.setDeleteFlag(boolVal);
            em.merge(yyyyy);
          }

        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            String errorMsg = "Yyyyys delete exception: " + t.getMessage();
            log.info(errorMsg);
            return errorMsg;
        }
        return "success";
   }


   /**
    * This service returns all Yyyyy objects.
    *
    * @return <code>List</code> object that contains all existing Yyyyys.
    */
   @SuppressWarnings("unchecked")
   public List <Yyyyy> findAllYyyyys()
   {
        return em.createQuery("FROM Yyyyy l WHERE l.deleteFlag = FALSE ORDER BY l.lastUpdate DESC").getResultList();
   }


    public Long getCountYyyyys() {

        return (Long) em.createQuery("SELECT count(l) FROM Yyyyy l WHERE l.deleteFlag = FALSE").getSingleResult();
    }


    public List<Yyyyy> getYyyyyPage(int offset, int max)
    {
        return em.createQuery("from Yyyyy l WHERE l.deleteFlag = FALSE order by l.id desc", Yyyyy.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }


    public List<Yyyyy> searchYyyyys(Yyyyy yyyyy, Yyyyy yyyyy2) {

        log.info("searchYyyyys called");

        // Get the criteria builder instance from entity manager
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        // Create criteria query and pass the value object which needs to be populated as result
        CriteriaQuery<Yyyyy> yyyyyQuery = criteriaBuilder.createQuery(Yyyyy.class);

        // Tell to criteria query which tables/entities you want to fetch
        Root<Yyyyy> yyyyyRoot = yyyyyQuery.from(Yyyyy.class);

        // This list will contain all Predicates (where clause)
        List<Predicate> criteriaList = new ArrayList<Predicate>();

        if (yyyyy.getDeleteFlag() != null) {
            Predicate deleteFlagPred = criteriaBuilder.equal(yyyyyRoot.<Boolean>get("deleteFlag"), yyyyy.getDeleteFlag());
            criteriaList.add(deleteFlagPred);
        }

___LIST_SEARCH_STRING___
___LIST_SEARCH_ENUM___
___LIST_SEARCH_TAG___
___LIST_SEARCH_CLOB___
___LIST_SEARCH_BOOLEAN___
___LIST_SEARCH_INTEGER___
___LIST_SEARCH_LONG___
___LIST_SEARCH_DOUBLE___
___LIST_SEARCH_BIG_DECIMAL___
___LIST_SEARCH_MONEY___
___LIST_SEARCH_LOC___
___LIST_SEARCH_CURRENT_LOC___
___LIST_SEARCH_DATE___
___LIST_SEARCH_DATE_TIME___
___LIST_SEARCH_CAMERA___
___LIST_SEARCH_VIDEO___
___LIST_SEARCH_THUMBNAIL___
___LIST_SEARCH_POST___
___LIST_SEARCH_ONE2MANY_CHILD___
___LIST_SEARCH_ONE2MANY_CHILD_ALERT___

        if (criteriaList.size() == 0)
          yyyyyQuery.select(yyyyyRoot);
        else
          yyyyyQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[criteriaList.size()])));

        TypedQuery<Yyyyy> searchYyyyyResult = em.createQuery(yyyyyQuery);
        List<Yyyyy> yyyyys = searchYyyyyResult.getResultList();

        log.info("searchYyyyys finished with size of " + yyyyys.size());
        return yyyyys;
    }


}
