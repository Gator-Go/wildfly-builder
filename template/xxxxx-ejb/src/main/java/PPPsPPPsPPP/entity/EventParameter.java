
package ppp.ppp.ppp.entity;

import ppp.ppp.ppp.entity.AdminEvent;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Lllll
 *
 * This is a JPA entity mapped to the XXXXX_EVENT_PARAMETER table.
 * It represents a parameter belonging to an AdminEvent, with fields for name,
 * whether it is required, and a description. It has a many-to-one relationship
 * back to its parent AdminEvent.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Entity
@XmlRootElement
@Table(name = "XXXXX_EVENT_PARAMETER")
public class EventParameter implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(generator="EventParameterSeq")
   @SequenceGenerator(name="EventParameterSeq",sequenceName="XXXXX_EVENT_PARAMETER_SEQ")
   @Column(name = "EVENT_PARAMETER_ID")
   private Long id;

   // NAME field from database
   @Column(name = "NAME")
   private String name;

   // REQUIRED field from database
   @Column(name = "REQUIRED")
   private Boolean required;

   // DESCRIPTION field from database
   @Column(name = "DESCRIPTION")
   private String description;

   // Event from database
   @ManyToOne
   @JoinColumn(name="EVENT_ID")
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
    * Get method for required data item
    * @return Boolean object that indicates the required.
    */
   public Boolean getRequired()
   {
      return required;
   }
   
   /**
    * Set method for required data item
    * @param required Boolean object that sets the required.
    */
   public void setRequired(Boolean required)
   {
      this.required = required;
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
