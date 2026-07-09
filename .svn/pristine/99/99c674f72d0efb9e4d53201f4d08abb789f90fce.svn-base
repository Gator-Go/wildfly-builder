
package ppp.ppp.ppp.entity;

import ppp.ppp.ppp.entity.AdminEvent;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name = "XXXXX_USER_SMS")
public class UserSms implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(generator="UserSmsSeq")
   @SequenceGenerator(name="UserSmsSeq",sequenceName="XXXXX_USER_SMS_SEQ")
   @Column(name = "USER_SMS_ID")
   private Long id;

   // WSDL_URL field from database
   @Column(name = "FIRSTNAME")
   private String firstName;

   // NAME field from database
   @Column(name = "LASTNAME")
   private String lastName;

   // PORT_NAME field from database
   @Column(name = "SMS")
   private String sms;


   // Set of Events subscribed to
   @ManyToMany()
   @JoinTable (name="XXXXX_USER_SMS_2_EVENT",
               joinColumns={@JoinColumn(name="USER_SMS_ID")},
               inverseJoinColumns={@JoinColumn(name="EVENT_ID")})
   private Set<AdminEvent> events = new HashSet<AdminEvent>();


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
    * Get method for firstName data item
    * @return String object that indicates the firstName.
    */
   public String getFirstName()
   {
      return firstName;
   }
   
   /**
    * Set method for firstName data item
    * @param firstName String object that sets the firstName.
    */
   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   /**
    * Get method for name lastName item
    * @return String object that indicates the lastName.
    */
   public String getLastName()
   {
      return lastName;
   }
   
   /**
    * Set method for lastName data item
    * @param name String object that sets the lastName.
    */
   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   /**
    * Get method for sms data item
    * @return String object that indicates the SMS.
    */
   public String getSms()
   {
      return sms;
   }
   
   /**
    * Set method for sms data item
    * @param name String object that sets the SMS.
    */
   public void setSms(String sms)
   {
      this.sms = sms;
   }

   

   /**
    * Get method for Events
    * @return Set<AdminEvent> object that indicates the Events.
    */
   public Set<AdminEvent> getEvents()
   {
      return events;
   }
   
   /**
    * Set method for Events
    * @param events Set<AdminEvent> object that sets the Events.
    */
   public void setEvents(Set<AdminEvent> events)
   {
      this.events = events;
   }

}
