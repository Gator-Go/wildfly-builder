package ppp.ppp.ppp.service;

import ppp.ppp.ppp.entity.Report;
import ppp.ppp.ppp.entity.RptSort;
import ppp.ppp.ppp.entity.RptParam;
import ppp.ppp.ppp.entity.RptList;

import java.util.logging.Logger;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.io.File;

import jakarta.inject.Inject;
import jakarta.enterprise.inject.Model;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


@Stateful
@Model
public class ReportService
{

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;



   /**
    * This service returns an Report object when given an id.
    *
    * @param id <code>Long</code> object containing the report id.
    * @return <code>Report</code> object that contains the Report.
    */
   public Report getReport(Long id)
   {
      log.info("getReport id = " + id);
      Report report = em.find(Report.class, id);
      log.info("RptParams size " + report.getRptParam().size());
      log.info("RptSort size " + report.getRptSort().size());
      return report;
   }

   /**
    * This service returns an RptParam object when given an id.
    *
    * @param id <code>Long</code> object containing the RptParam id.
    * @return <code>RptParam</code> object that contains the RptParam.
    */
   public RptParam getRptParam(Long id)
   {
      log.info("getRptParam id = " + id);
      RptParam rptParam = em.find(RptParam.class, id);
      return rptParam;
   }

   /**
    * This service creates a report object and returns its id.
    *
    * @param report <code>Report</code> object containing the report info to add.
    * @return <code>Long</code> object that contains the report id.
    */
   public Long addReport(Report report)
   {
      em.persist(report);

      return report.getId();
   }

   /**
    * This service creates a RptParam object and returns its id.
    *
    * @param rptParam <code>RptParam</code> object containing the RptParam info to add.
    * @return <code>Long</code> object that contains the RptParam id.
    */
   public Long addRptParam(RptParam rptParam)
   {
      em.persist(rptParam);

      return rptParam.getId();
   }

   /**
    * This service returns the RptList object when given an id.
    *
    * @param id <code>Integer</code> object containing the rptList id.
    * @return <code>RptList</code> object that contains the RptList.
    */
   public RptList getRptList(Long id)
   {
      log.info("getRptList id = " + id.toString());
      RptList rptList = em.find(RptList.class, id);


      return rptList;
   }

   /**
    * This service creates a RptSort object and returns its id.
    *
    * @param rptSort <code>RptSort</code> object containing the RptSort info to add.
    * @return <code>Long</code> object that contains the RptSort id.
    */
   public Long addRptSort(RptSort rptSort)
   {
      em.persist(rptSort);

      return rptSort.getId();
   }


    /**
     * This service creates the RptList object and returns the outcome.
     *
     * @param rptList <code>RptList</code> object containing the rptList info to add.
     * @return <code>String</code> object that indicates the success of the rptList add.
     */
    public String addRptList(RptList rptList)
    {

        try {
            rptList.setId(null);
            em.persist(rptList);
            if (rptList.getDeviceId() == null || rptList.getDeviceId().longValue() == 0)
                rptList.setDeviceId(new Long(1));
            if (rptList.getRptListId() == null || rptList.getRptListId().longValue() == 0)
                rptList.setRptListId(rptList.getId());
            em.merge(rptList);
            em.flush();
            em.clear();

        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            String errorMsg = "RptList add exception: " + t.getMessage();
            log.info(errorMsg);
            return errorMsg;
        }
        return "success";
    }

   /**
    * This service edits an Report object.
    *
    * @param editReport <code>Report</code> object containing the report edit info.
    * @return <code>String</code> object that indicates the success of the report edit.
    */
   public String editReport(Report editReport)
   {
      Report report = null;
      try {
         report = em.getReference(Report.class, editReport.getId());
      } catch (EntityNotFoundException notFound) {

         return "The Report ID " + editReport.getId().toString() + " not found.";
      }

      if (editReport.getReportName() != null)
         report.setReportName(editReport.getReportName());
      if (editReport.getReportDesc() != null)
         report.setReportDesc(editReport.getReportDesc());
      if (editReport.getReportFileName() != null)
         report.setReportFileName(editReport.getReportFileName());
      if (editReport.getRptParam() != null)
         report.setRptParam(editReport.getRptParam());
      if (editReport.getRptSort() != null)
         report.setRptSort(editReport.getRptSort());

      em.merge(report);

      return "success";
   }

   /**
    * This service edits an RptParam object.
    *
    * @param editRptParam <code>RptParam</code> object containing the RptParam edit info.
    */
   public void editRptParam(RptParam rptParam)
   {

      em.merge(rptParam);

   }

   /**
    * This service deletes an Report object.
    *
    * @param id <code>Long</code> object containing the report id.
    * @return <code>String</code> object that indicates the delete report success.
    */
   public String deleteReport(Long id)
   {
      log.info("deleteReport id = " + id);
      Report report = null;
      try {
         report = em.getReference(Report.class, id);
      } catch (EntityNotFoundException notFound) {

         return "The Report ID " + id.toString() + " not found.";
      }

      String jrxmlFile = System.getProperty("com.sw-builder.sync.app.jrxml.dir") + report.getReportFileName();
      File file = new File(jrxmlFile);
 
      if(!file.delete()){
         return "file_delete";
      }

      em.remove(report);

      return "success";
   }
   
    /**
     * This service deletes the RptList object.
     *
     * @param id <code>Long</code> object containing the rptList id.
     * @return <code>String</code> object that indicates the delete rptList success.
     */
    public String deleteRptList(Long id)
    {

        RptList rptList = null;
        try {
           rptList = em.getReference(RptList.class, id);
        } catch (EntityNotFoundException notFound) {

           return "The RptList ID " + id.toString() + " not found.";
        }

        try {

            String reportDir = System.getProperty("com.sw-builder.sync.app.report.dir");
            File reportFile = new File(reportDir + rptList.getRptName());
            reportFile.delete();

            em.remove(rptList);
            em.flush();
            em.clear();
        } catch (Exception e) {
            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }
            String errorMsg = "RptList delete exception: " + t.getMessage();
            log.info(errorMsg);
            return errorMsg;
        }
        return "success";
   }
   
   /**
    * This service returns all Report objects.
    *
    * @return <code>List</code> object that contains all existing reports.
    */
   @SuppressWarnings("unchecked")
   public List <Report> findAllReports()
   {
      return em.createQuery("FROM Report l").getResultList();
   }

   /**
    * This service returns all RptList objects.
    *
    * @return <code>List</code> object that contains all existing RptListing.
    */
   @SuppressWarnings("unchecked")
   public List <RptList> findAllRptListing()
   {
        return em.createQuery("FROM RptList l WHERE l.deleteFlag = FALSE").getResultList();
   }


    public Long getCountRptListing() {

        return (Long) em.createQuery("SELECT count(l) FROM RptList l WHERE l.deleteFlag = FALSE").getSingleResult();
    }


    public List<RptList> getRptListPage(int offset, int max)
    {
        return em.createQuery("from RptList l WHERE l.deleteFlag = FALSE order by l.id desc", RptList.class).setFirstResult(offset).setMaxResults(max).getResultList();
    }

}
