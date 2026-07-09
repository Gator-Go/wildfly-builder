
package ppp.ppp.ppp.entity;

import ppp.ppp.ppp.entity.AdminEvent;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name = "XXXXX_EVENT_SCHEDULE")
public class EventSchedule implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(generator="EventScheduleSeq")
   @SequenceGenerator(name="EventScheduleSeq",sequenceName="XXXXX_EVENT_SCHEDULE_SEQ")
   @Column(name = "EVENT_SCHEDULE_ID")
   private Long id;

   // SCHEDULE field from database
   @Column(name = "SCHEDULE")
   private String schedule;

   // LAST_RUN field from database
   @Column(name = "LAST_RUN")
   private Date lastRun;

   // ENABLED field from database
   @Column(name = "ENABLED")
   private Boolean enabled;

   // MESSAGE field from database
   @Column(name = "MESSAGE")
   private String message;

   // Event from database
   @OneToOne
   @JoinColumn(name="EVENT_ID", referencedColumnName="EVENT_ID")
   private AdminEvent adminEvent;


   /**
    * Get method for id data item
    * @return Long object that indicates the id.
    */
    public Long getId() {
        return id;
    }

   /**
    * Set method for id data item
    * @param id Long object that sets the id.
    */
    public void setId(Long id) {
        this.id = id;
    }

   /**
    * Get method for schedule data item
    * @return String object that indicates the schedule.
    */
   public String getSchedule()
   {
      return schedule;
   }
   
   /**
    * Set method for schedule data item
    * @param schedule String object that sets the schedule.
    */
   public void setSchedule(String schedule)
   {
      this.schedule = schedule;
   }
   
   /**
    * Get method for lastRun data item
    * @return Date object that indicates the lastRun.
    */
   public Date getLastRun()
   {
      return lastRun;
   }
   
   /**
    * Set method for lastRun data item
    * @param lastRun Date object that sets the lastRun.
    */
   public void setLastRun(Date lastRun)
   {
      this.lastRun = lastRun;
   }

   /**
    * Get method for enabled data item
    * @return Boolean object that indicates the enabled.
    */
   public Boolean getEnabled()
   {
      return enabled;
   }
   
   /**
    * Set method for enabled data item
    * @param enabled Boolean object that sets the enabled.
    */
   public void setEnabled(Boolean enabled)
   {
      this.enabled = enabled;
   }
   

   /**
    * Get method for message data item
    * @return String object that indicates the message.
    */
   public String getMessage()
   {
      return message;
   }
   
   /**
    * Set method for message data item
    * @param message String object that sets the message.
    */
   public void setMessage(String message)
   {
      this.message = message;
   }
   

   /**
    * Get method for adminEvent parent
    * @return AdminEvent object that indicates the adminEvent.
    */
   public AdminEvent getAdminEvent()
   {
      return adminEvent;
   }
   
   /**
    * Set method for adminEvent parent
    * @param adminEvent AdminEvent object that sets the adminEvent.
    */
   public void setAdminEvent(AdminEvent adminEvent)
   {
      this.adminEvent = adminEvent;
   }
}
