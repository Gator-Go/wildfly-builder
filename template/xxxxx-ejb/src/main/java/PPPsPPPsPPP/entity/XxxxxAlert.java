
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
 * This is a JPA entity mapped to the XXXXX_ALERT table.
 * It represents an alert record with device ID, alert ID,
 * type, source, a large message body, and the time it occurred.
 * It also provides a helper method to build a cloud ID.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Entity
@XmlRootElement
@Table(name = "XXXXX_ALERT")
public class XxxxxAlert implements Serializable {
    /** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="XxxxxAlertSeq")
    @SequenceGenerator(name="XxxxxAlertSeq",sequenceName="XXXXX_ALERT_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "DEVICE_ID")
    private Long deviceId;

    @Column(name = "XXXXX_ALERT_ID")
    private Long xxxxxAlertId;

    // ALERT_TYPE field from database
    @NotNull
    @NotEmpty
    @Column(name = "XXXXX_ALERT_TYPE")
    private String xxxxxAlertType;

    // ALERT_SOURCE field from database
    @NotNull
    @NotEmpty
    @Column(name = "XXXXX_ALERT_SOURCE")
    private String xxxxxAlertSource;

    // ALERT_MESSAGE field from database
    @NotNull
    @NotEmpty
    @Column(name = "XXXXX_ALERT_MESSAGE", length=200000)
    private String xxxxxAlertMessage;

    // OCCURRED_AT field from database
    @Column(name = "OCCURRED_AT")
    private Date occurredAt;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    public Long getXxxxxAlertId() { return xxxxxAlertId; }
    public void setXxxxxAlertId(Long xxxxxAlertId) { this.xxxxxAlertId = xxxxxAlertId; }

    public String getXxxxxAlertType() { return xxxxxAlertType; }
    public void setXxxxxAlertType(String xxxxxAlertType) { this.xxxxxAlertType = xxxxxAlertType; }

    public String getXxxxxAlertSource() { return xxxxxAlertSource; }
    public void setXxxxxAlertSource(String xxxxxAlertSource) { this.xxxxxAlertSource = xxxxxAlertSource; }

    public String getXxxxxAlertMessage() { return xxxxxAlertMessage; }
    public void setXxxxxAlertMessage(String xxxxxAlertMessage) { this.xxxxxAlertMessage = xxxxxAlertMessage; }

    public Date getOccurredAt() { return occurredAt; }
    public void setOccurredAt(Date occurredAt) { this.occurredAt = occurredAt; }
   
    public String getCloudId() {

       return deviceId.toString() + "-" + xxxxxAlertId.toString();
    }
}
