
package ppp.ppp.ppp.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
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
 * This is a JPA entity mapped to the XXXXX_RPT_SORT table.
 * It represents a sort option for a report and has a many-to-one
 * relationship with its parent Report.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Entity
@XmlRootElement
@Table(name = "XXXXX_RPT_SORT")
public class RptSort implements Serializable {
    /** Default value included to remove warning. Remove or modify at will. **/
    private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(generator="RptSortSeq")
   @SequenceGenerator(name="RptSortSeq",sequenceName="XXXXX_RPT_SORT_SEQ")
   @Column(name = "ID")
   private Long id;

   // SORT_OPTION field from database
   @NotNull
   @NotEmpty
   @Column(name = "SORT_OPTION")
   private String sortOption;

   // Parent Report from database
   @ManyToOne
   @JoinColumn(name="REPORT_ID")
   private Report sortReport;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   /**
    * Get method for sortOption data item
    * @return String object that indicates the sortOption.
    */
   public String getSortOption()
   {
      return sortOption;
   }
   
   /**
    * Set method for sortOption data item
    * @sort sortOption String object that sets the sortOption.
    */
   public void setSortOption(String sortOption)
   {
      this.sortOption = sortOption;
   }
   


   /**
    * Get method for report parent
    * @return Report object that indicates the report.
    */
   public Report getSortReport()
   {
      return sortReport;
   }
   
   /**
    * Set method for report parent
    * @sort report Report object that sets the report.
    */
   public void setSortReport(Report sortReport)
   {
      this.sortReport = sortReport;
   }


}
