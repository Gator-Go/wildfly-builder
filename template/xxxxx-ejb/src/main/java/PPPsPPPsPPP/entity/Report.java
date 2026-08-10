
package ppp.ppp.ppp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

import jakarta.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Lllll
 *
 * This is a JPA entity mapped to the XXXXX_REPORT table.
 * It represents a report definition with a name, description,
 * and file name. It has one-to-many relationships with RptParam
 * (report parameters) and RptSort (sort criteria).
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Entity
@XmlRootElement
@Table(name = "XXXXX_REPORT")
public class Report implements Serializable {
    /** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(generator="ReportSeq")
   @SequenceGenerator(name="ReportSeq",sequenceName="XXXXX_REPORT_SEQ")
   @Column(name = "ID")
   private Long id;

   // REPORT_NAME field from database
   @NotNull
   @NotEmpty
   @Column(name = "REPORT_NAME")
   private String reportName;
   // REPORT_DESC field from database
   @NotNull
   @NotEmpty
   @Column(name = "REPORT_DESC")
   private String reportDesc;
   // REPORT_FILE_NAME field from database
   @NotNull
   @NotEmpty
   @Column(name = "REPORT_FILE_NAME")
   private String reportFileName;

   // Collection of RptParams
   @OneToMany (orphanRemoval=true, fetch = FetchType.LAZY, mappedBy="paramReport")
   private Collection<RptParam> rptParam = new ArrayList<RptParam>();

   // Collection of RptSorts
   @OneToMany (orphanRemoval=true, fetch = FetchType.LAZY, mappedBy="sortReport")
   private Collection<RptSort> rptSort = new ArrayList<RptSort>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   /**
    * Get method for reportName data item
    * @return String object that indicates the reportName.
    */
   public String getReportName()
   {
      return reportName;
   }
   
   /**
    * Set method for reportName data item
    * @param reportName String object that sets the reportName.
    */
   public void setReportName(String reportName)
   {
      this.reportName = reportName;
   }
   
   /**
    * Get method for reportDesc data item
    * @return String object that indicates the reportDesc.
    */
   public String getReportDesc()
   {
      return reportDesc;
   }
   
   /**
    * Set method for reportDesc data item
    * @param reportDesc String object that sets the reportDesc.
    */
   public void setReportDesc(String reportDesc)
   {
      this.reportDesc = reportDesc;
   }
   
   /**
    * Get method for reportFileName data item
    * @return String object that indicates the reportFileName.
    */
   public String getReportFileName()
   {
      return reportFileName;
   }
   
   /**
    * Set method for reportFileName data item
    * @param reportFileName String object that sets the reportFileName.
    */
   public void setReportFileName(String reportFileName)
   {
      this.reportFileName = reportFileName;
   }
   

   /**
    * Get method for rptParam children
    * @return Collection object that indicates the rptParam.
    */
   public Collection<RptParam> getRptParam()
   {
      return rptParam;
   }
   
   /**
    * Set method for rptParam children
    * @param rptParam RptParam object that sets the rptParam.
    */
   public void setRptParam(Collection<RptParam> rptParam)
   {
      this.rptParam = rptParam;
   }


   /**
    * Get method for rptSort children
    * @return Collection object that indicates the rptSort.
    */
   public Collection<RptSort> getRptSort()
   {
      return rptSort;
   }
   
   /**
    * Set method for rptSort children
    * @param rptSort RptSort object that sets the rptSort.
    */
   public void setRptSort(Collection<RptSort> rptSort)
   {
      this.rptSort = rptSort;
   }
}
