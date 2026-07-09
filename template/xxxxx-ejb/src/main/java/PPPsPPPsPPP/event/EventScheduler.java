
package ppp.ppp.ppp.event;

import ppp.ppp.ppp.event.FireEvent;
import ppp.ppp.ppp.entity.EventSchedule;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.enterprise.event.Observes;

import jakarta.annotation.Resource;	
import jakarta.ejb.TimerService;
import jakarta.ejb.Timer;
import jakarta.ejb.ScheduleExpression;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.Timeout;
import jakarta.ejb.Startup;
import jakarta.ejb.Singleton;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.Root;

import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

@Singleton
@Startup
@ApplicationScoped
@Lock(LockType.READ) // allows timers to execute in parallel
public class EventScheduler{

    @Inject
    private Logger logger;

    @Inject
    private FireEvent fireEvent;

    @Inject
    private EntityManager em;

    @Resource
    private TimerService timerService;

    private List<EventSchedule> eventSchedules;


    public void onEventScheduleChanged(@Observes EventSchedule eventSchedule) {
        logger.info("**** onEventScheduleChanged called");
        retrieveAllEventSchedules();
    }


    public void scheduleEvent(ScheduleExpression schedule, String event) {
        logger.info("**** scheduleEvent called Event: " + event + " Schedule: " + schedule.toString());
	TimerConfig timerConfig = new TimerConfig();
	timerConfig.setInfo(event);
        timerService.createCalendarTimer(schedule, timerConfig);
    }

    @Timeout
    public void timeout(Timer timer) {

	String event = (String) timer.getInfo();

        for (EventSchedule eventSchedule : eventSchedules) {
	    if (eventSchedule.getAdminEvent().getName().equals(event)) {
                if (eventSchedule.getEnabled().booleanValue() == false) {
		    timer.cancel();
                } else {
                    fireEvent.fireEvent(eventSchedule.getAdminEvent().getName(), "", null);
                }
            }
	}
    }


    private void retrieveAllEventSchedules() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<EventSchedule> criteria = cb.createQuery(EventSchedule.class);
        Root<EventSchedule> root = criteria.from(EventSchedule.class);
        CriteriaQuery<EventSchedule> all = criteria.select(root);
        TypedQuery<EventSchedule> allQuery = em.createQuery(all);
        eventSchedules = allQuery.getResultList();
    }

    @PostConstruct
    public void scheduleStart() {

        retrieveAllEventSchedules();

        logger.info("@PostConstruct: Event Scheduler Started");
    }
}
