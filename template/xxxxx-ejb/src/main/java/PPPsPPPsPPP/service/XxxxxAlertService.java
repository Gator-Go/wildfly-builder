
package ppp.ppp.ppp.service;

import ppp.ppp.ppp.entity.XxxxxAlert;
import ppp.ppp.ppp.event.msg.XxxxxAlertEvent;
import ppp.ppp.ppp.event.msg.AlertChangedEvent;
import ppp.ppp.ppp.settings.XxxxxProperty;

import java.text.SimpleDateFormat;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateful;
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


// The @Stateful annotation eliminates the need for manual transaction demarcation
@Stateful
@Model
public class XxxxxAlertService {

    @Inject
    private Logger log;

    @Inject
    @XxxxxProperty(name = "Format.DateTimeStr")
    private String FormatDateTimeStr;

    @Inject
    @XxxxxProperty(name = "Show.Sync")
    private Boolean ShowSync;

    @Inject
    private EntityManager em;

    @Inject
    private Event<AlertChangedEvent> alertChangedEventMsg;


   /**
    * This service returns the xxxxx alert object when given an cloud id.
    *
    * @param id <code>Integer</code> object containing the xxxxxAlert id.
    * @return <code>XxxxxAlert</code> object that contains the Xxxxx Alert.
    */
   public XxxxxAlert getXxxxxAlertByCloudId(Long deviceId, Long xxxxxAlertId)
   {
      log.info("getXxxxxAlertByCloudId deviceId = " + deviceId.toString() + "  xxxxxAlertId = " + xxxxxAlertId.toString());

      List<XxxxxAlert> xxxxxAlertList = em.createQuery("SELECT t FROM XxxxxAlert t where t.deviceId = :deviceId AND t.xxxxxAlertId = :xxxxxAlertId")
           .setParameter("deviceId", deviceId).setParameter("xxxxxAlertId", xxxxxAlertId).getResultList();

      XxxxxAlert xxxxxAlert = null;
      if (xxxxxAlertList.size() > 0)
          xxxxxAlert = xxxxxAlertList.get(0);

      return xxxxxAlert;
   }



   /**
    * This service returns the Alert object when given an id.
    *
    * @param id <code>Integer</code> object containing the xxxxxAlert id.
    * @return <code>XxxxxAlert</code> object that contains the Xxxxx Alert.
    */
   public XxxxxAlert getXxxxxAlert(Long id)
   {
      log.info("getXxxxxAlert id = " + id.toString());
      XxxxxAlert xxxxxAlert = em.find(XxxxxAlert.class, id);


      return xxxxxAlert;
   }


    /**
     * This service creates the Alert object and returns the outcome.
     *
     * @param xxxxxAlert <code>XxxxxAlert</code> object containing the xxxxx alert info to add.
     * @return <code>String</code> object that indicates the success of the xxxxxAlert add.
     */
    public void onXxxxxAlert(@Observes XxxxxAlertEvent xxxxxAlertEvent) {

	SimpleDateFormat formatDateTime = new SimpleDateFormat (FormatDateTimeStr);
        XxxxxAlert xxxxxAlert = new XxxxxAlert();
        xxxxxAlert.setXxxxxAlertType(xxxxxAlertEvent.getXxxxxAlertType());
        xxxxxAlert.setXxxxxAlertSource(xxxxxAlertEvent.getXxxxxAlertSource());
        xxxxxAlert.setXxxxxAlertMessage(xxxxxAlertEvent.getXxxxxAlertMessage());
        try{xxxxxAlert.setOccurredAt(formatDateTime.parse(xxxxxAlertEvent.getOccurredAt()));}catch(Exception ex){}

        try {
            xxxxxAlert.setId(null);
            em.persist(xxxxxAlert);
            xxxxxAlert.setDeviceId(new Long(1));
            xxxxxAlert.setXxxxxAlertId(xxxxxAlert.getId());
            em.merge(xxxxxAlert);
            em.flush();
            em.clear();

        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            String errorMsg = "XxxxxAlert add exception: " + t.getMessage();
            log.info(errorMsg);
        }

        AlertChangedEvent alertChangedEvent = new AlertChangedEvent();
        alertChangedEvent.setName("AlertChangedEvent");
        alertChangedEventMsg.fire(alertChangedEvent);

    }


    /**
     * This service creates the Xxxxx Alert object and returns the outcome.
     *
     * @param xxxxxAlert <code>XxxxxAlert</code> object containing the xxxxx alert info to add.
     * @return <code>String</code> object that indicates the success of the xxxxx alert add.
     */
    public String addXxxxxAlert(XxxxxAlert xxxxxAlert)
    {
	SimpleDateFormat formatDateTime = new SimpleDateFormat (FormatDateTimeStr);
        if (xxxxxAlert.getDeviceId() == null)
            xxxxxAlert.setDeviceId(new Long(1));

        try {
            xxxxxAlert.setId(null);
            em.persist(xxxxxAlert);
            xxxxxAlert.setXxxxxAlertId(xxxxxAlert.getId());
            em.merge(xxxxxAlert);
            em.flush();
            em.clear();

        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            String errorMsg = "Alert add exception: " + t.getMessage();
            log.info(errorMsg);
            return errorMsg;
        }

        AlertChangedEvent alertChangedEvent = new AlertChangedEvent();
        alertChangedEvent.setName("AlertChangedEvent");
        alertChangedEventMsg.fire(alertChangedEvent);

        return "success";
    }


   /**
    * This service returns all xxxxx alert objects.
    *
    * @return <code>List</code> object that contains all existing xxxxx alerts.
    */
   @SuppressWarnings("unchecked")
   public List <XxxxxAlert> findAllXxxxxAlerts()
   {
        return em.createQuery("FROM XxxxxAlert l").getResultList();
   }


    public Long getCountXxxxxAlerts() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        criteria.select(cb.count(criteria.from(XxxxxAlert.class)));
        return em.createQuery(criteria).getSingleResult();
    }


    public List<XxxxxAlert> getXxxxxAlertPage(int offset, int max)
    {
	boolean showSync;
	// needed for a slow pi - error on startup with a singleton calling a singleton
	if (ShowSync == null) {
	    showSync = false;
            log.info("getXxxxxAlertPage ShowSync NULL");
	} else {
	    showSync = ShowSync.booleanValue();
            log.info("getXxxxxAlertPage ShowSync Value = " + ShowSync.booleanValue());
	}

	String query = "from XxxxxAlert l";
	if (showSync == false)
 	    query = query + " where l.xxxxxAlertType not like 'sync'";
	query = query + " order by l.id desc";
        return em.createQuery( query, XxxxxAlert.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }


}
