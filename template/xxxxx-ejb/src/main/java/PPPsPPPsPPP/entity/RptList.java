
package ppp.ppp.ppp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.text.DecimalFormat;

/**
 * Lllll
 *
 * This is a JPA entity mapped to the XXXXX_RPT_LIST table.
 * It represents a named report list entry with device/report IDs,
 * a soft-delete flag, and last-update timestamp. It includes helper
 * methods to return a display string and a cloud ID.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Entity
@XmlRootElement
@Table(name = "XXXXX_RPT_LIST")
public class RptList implements Serializable {
    /** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="RptListSeq")
    @SequenceGenerator(name="RptListSeq",sequenceName="XXXXX_RPT_LIST_SEQ")
    @Column(name = "ID")
    private Long id;
    private Long deviceId;
    private Long rptListId;
    private Date lastUpdate = new Date();
    private Boolean deleteFlag = new Boolean(false);

   // RPT_NAME field from database
   @NotNull
   @NotEmpty
   @Column(name = "RPT_NAME")
   private String rptName;



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getRptListId() {
        return rptListId;
    }
    public void setRptListId(Long rptListId) {
        this.rptListId = rptListId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

   /**
    * Get method for rptName data item
    * @return String object that indicates the rptName.
    */
   public String getRptName()
   {
      return rptName;
   }
   
   /**
    * Set method for rptName data item
    * @param rptName String object that sets the rptName.
    */
   public void setRptName(String rptName)
   {
      this.rptName = rptName;
   }
   


   public String getDisplayString() {

      String result = String.format(
       "%s",
         rptName
      );
      return result;
   }

   public String getCloudId() {

      return deviceId.toString() + "-" + rptListId.toString();
   }
}
