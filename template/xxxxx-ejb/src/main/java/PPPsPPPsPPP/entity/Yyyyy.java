
package ppp.ppp.ppp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;
import java.math.BigDecimal;

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
 * This is a JPA entity class mapped to the YYYYY database table.
 * It represents the YYYYY class with fields for ID (auto-generated via sequence), device/IDs,
 * and other database table fields. It includes the standard helper methods and a cloud ID.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Entity
@XmlRootElement
@Table(name = "YYYYY")
public class Yyyyy implements Serializable {
    /** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="YyyyySeq")
    @SequenceGenerator(name="YyyyySeq",sequenceName="YYYYY_SEQ")
    @Column(name = "ID")
    private Long id;
    private Long deviceId;
    private Long yyyyyId;
    private Date lastUpdate = new Date();
    private Boolean deleteFlag = new Boolean(false);

___ENTITY_DEFINITION_STRING___
___ENTITY_DEFINITION_ENUM___
___ENTITY_DEFINITION_TAG___
___ENTITY_DEFINITION_CLOB___
___ENTITY_DEFINITION_BOOLEAN___
___ENTITY_DEFINITION_INTEGER___
___ENTITY_DEFINITION_LONG___
___ENTITY_DEFINITION_DOUBLE___
___ENTITY_DEFINITION_BIG_DECIMAL___
___ENTITY_DEFINITION_MONEY___
___ENTITY_DEFINITION_LOC___
___ENTITY_DEFINITION_CURRENT_LOC___
___ENTITY_DEFINITION_DATE___
___ENTITY_DEFINITION_DATE_TIME___
___ENTITY_DEFINITION_CAMERA___
___ENTITY_DEFINITION_VIDEO___
___ENTITY_DEFINITION_THUMBNAIL___
___ENTITY_DEFINITION_POST___
___ENTITY_DEFINITION_ONE2MANY_CHILD___
___ENTITY_DEFINITION_ONE2MANY_CHILD_ALERT___
___ENTITY_DEFINITION_ONE2MANY_PARENT___
___ENTITY_DEFINITION_ONE2MANY_PARENT_ALERT___


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

    public Long getYyyyyId() {
        return yyyyyId;
    }
    public void setYyyyyId(Long yyyyyId) {
        this.yyyyyId = yyyyyId;
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

___ENTITY_METHOD_STRING___
___ENTITY_METHOD_ENUM___
___ENTITY_METHOD_TAG___
___ENTITY_METHOD_CLOB___
___ENTITY_METHOD_BOOLEAN___
___ENTITY_METHOD_INTEGER___
___ENTITY_METHOD_LONG___
___ENTITY_METHOD_DOUBLE___
___ENTITY_METHOD_BIG_DECIMAL___
___ENTITY_METHOD_MONEY___
___ENTITY_METHOD_LOC___
___ENTITY_METHOD_CURRENT_LOC___
___ENTITY_METHOD_DATE___
___ENTITY_METHOD_DATE_TIME___
___ENTITY_METHOD_CAMERA___
___ENTITY_METHOD_VIDEO___
___ENTITY_METHOD_THUMBNAIL___
___ENTITY_METHOD_POST___
___ENTITY_METHOD_ONE2MANY_CHILD___
___ENTITY_METHOD_ONE2MANY_CHILD_ALERT___
___ENTITY_METHOD_ONE2MANY_PARENT___
___ENTITY_METHOD_ONE2MANY_PARENT_ALERT___


   public String getCloudId() {

      return deviceId.toString() + "-" + yyyyyId.toString();
   }
}
