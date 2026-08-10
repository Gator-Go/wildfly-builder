
package ppp.ppp.ppp.entity;

import ppp.ppp.ppp.entity.UserEmail;
import ppp.ppp.ppp.entity.UserSms;
import ppp.ppp.ppp.entity.EventParameter;
import ppp.ppp.ppp.entity.EventSchedule;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Lllll
 *
 * This is a JPA entity mapped to the XXXXX_EVENT table.
 * It represents an administrative event with a name, source, large message body, and description.
 * It has a one-to-one relationship with EventSchedule, a one-to-many relationship with EventParameter,
 * and many-to-many relationships with both UserEmail and UserSms (for notification recipients).
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Entity
@XmlRootElement
@Table(name = "XXXXX_EVENT")
public class AdminEvent implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(generator="EventSeq")
   @SequenceGenerator(name="EventSeq",sequenceName="XXXXX_EVENT_SEQ")
   @Column(name = "EVENT_ID")
   private Long id;

   // NAME field from database
   @Column(name = "NAME")
   private String name;

   // SOURCE field from database
   @Column(name = "SOURCE")
   private String source;

   // MESSAGE field from database
   @Lob
   @Column(name = "MESSAGE", length=200000)
   private String message;

   // DESCRIPTION field from database
   @Column(name = "DESCRIPTION")
   private String description;

   // One-to-One association to EVENT_SCHEDULE
   @OneToOne(orphanRemoval=true, mappedBy="adminEvent")
   private EventSchedule eventSchedule;

   // Collection of EventParameters
   @OneToMany (orphanRemoval=true, fetch = FetchType.EAGER, mappedBy="adminEvent")
   private Collection<EventParameter> eventParameter = new ArrayList<EventParameter>();

   // Collection of UserEmail
   @ManyToMany(mappedBy="events", fetch = FetchType.EAGER)
   private Collection<UserEmail> userEmail = new ArrayList<UserEmail>();

   // Collection of UserSms
   @ManyToMany(mappedBy="events", fetch = FetchType.EAGER)
   private Collection<UserSms> userSms = new ArrayList<UserSms>();

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
    * Get method for name data item
    * @return String object that indicates the name.
    */
   public String getName()
   {
      return name;
   }
   /**
    * Set method for name data item
    * @param name String object that sets the name.
    */
   public void setName(String name)
   {
      this.name = name;
   }

   /**
    * Get method for source data item
    * @return String object that indicates the source.
    */
   public String getSource()
   {
      return source;
   }
   /**
    * Set method for source data item
    * @param source String object that sets the source.
    */
   public void setSource(String source)
   {
      this.source = source;
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
    * Get method for description data item
    * @return String object that indicates the description.
    */
   public String getDescription()
   {
      return description;
   }
   
   /**
    * Set method for description data item
    * @param description String object that sets the description.
    */
   public void setDescription(String description)
   {
      this.description = description;
   }

  

   /**
    * Get method for eventSchedule
    * @return EventSchedule object that indicates the eventSchedule.
    */
   public EventSchedule getEventSchedule()
   {
      return eventSchedule;
   }
   
   /**
    * Set method for eventSchedule
    * @param eventSchedule EventSchedule object that sets the eventSchedule.
    */
   public void setEventSchedule(EventSchedule eventSchedule)
   {
      this.eventSchedule = eventSchedule;
   }


   /**
    * Get method for eventParameter children
    * @return Collection object that indicates the eventParameter.
    */
   public Collection<EventParameter> getEventParameter()
   {
      return eventParameter;
   }
   
   /**
    * Set method for eventParameter children
    * @param eventParameter EventParameter object that sets the eventParameter.
    */
   public void setEventParameter(Collection<EventParameter> eventParameter)
   {
      this.eventParameter = eventParameter;
   }

   /**
    * Get method for userEmail children
    * @return Collection object that indicates the userEmail.
    */
   public Collection<UserEmail> getUserEmail()
   {
      return userEmail;
   }
   
   /**
    * Set method for userEmail children
    * @param userEmail UserEmail object that sets the userEmail.
    */
   public void setUserEmail(Collection<UserEmail> userEmail)
   {
      this.userEmail = userEmail;
   }

   /**
    * Get method for userSms children
    * @return Collection object that indicates the userSms.
    */
   public Collection<UserSms> getUserSms()
   {
      return userSms;
   }
   
   /**
    * Set method for userSms children
    * @param userSms UserSms object that sets the userSms.
    */
   public void setUserSms(Collection<UserSms> userSms)
   {
      this.userSms = userSms;
   }

}
