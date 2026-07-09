package ppp.ppp.ppp.servlet.helper;

import ppp.ppp.ppp.service.ReportService;
import ppp.ppp.ppp.servlet.helper.Option;
import ppp.ppp.ppp.entity.Report;
import ppp.ppp.ppp.entity.RptSort;
import ppp.ppp.ppp.entity.RptParam;
import ppp.ppp.ppp.entity.RptList;
import ppp.ppp.ppp.report.MimeType;
import ppp.ppp.ppp.report.ReportParamTypeEnum;
import ppp.ppp.ppp.report.DatePickerEnum;
import ppp.ppp.ppp.report.ConstRpt;
import ppp.ppp.ppp.settings.XxxxxProperty;

import java.util.logging.Logger;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


@RequestScoped
public class RptDataHelper
{
    @Inject
    private Logger log;

    @Inject
    @XxxxxProperty(name = "Format.DateStr")
    private String FormatDateStr;

    @Inject
    private ReportService reportService;

    @Inject
    private EntityManager em;

   /**
    * This method loads the generate report req attributes.
    *
    * @param req A HttpServletRequest object to be loaded with generate report data.
    */
   public void loadGenerateReport(HttpServletRequest req, Report report)
   {

      SimpleDateFormat xmlFileDateTime = new SimpleDateFormat ("MMddyyyy_HHmmss");
      String dateTime = "_" + xmlFileDateTime.format(new Date());

      req.setAttribute(ConstRpt.ID, report.getId());
      req.setAttribute("reportName", report.getReportName() + dateTime);
      req.setAttribute("reportDesc", report.getReportDesc());
      req.setAttribute("reportFileName", report.getReportFileName());
      req.setAttribute("rptSortOptions", report.getRptSort());
      req.setAttribute("rptParams", report.getRptParam());
 //log.info("*** Report Description *** " + report.getReportDesc());

      String mimeSelected = "pdf";
      List<Option> mimeTypes = new ArrayList<Option>();
      for (MimeType.MimeTypeEnum values : MimeType.MimeTypeEnum.values()) {
        Option mimeOption = new Option();
        mimeOption.setOption(values.getValue());
        if (values.getValue().equals(mimeSelected))
          mimeOption.setSelected(ConstRpt.SELECTED_VALUE);
        mimeTypes.add(mimeOption);
      }
      req.setAttribute("mimeTypes", mimeTypes);

      String sortSelected = "None";
      List<Option> sortOptions = new ArrayList<Option>();
      Option sortOption = new Option();
      sortOption.setOption("None");
      if (sortOption.equals("None"))
        sortOption.setSelected(ConstRpt.SELECTED_VALUE);
      sortOptions.add(sortOption);

      for (RptSort rptSort : report.getRptSort()) {

        Option rptOption = new Option();
        rptOption.setOption(rptSort.getSortOption());
        if (rptOption.equals(rptSort.getSortOption()))
          rptOption.setSelected(ConstRpt.SELECTED_VALUE);
        sortOptions.add(rptOption);
      }
      req.setAttribute("sortOptions", sortOptions);


      List<RptParamOption> rptParamOptions = new ArrayList<RptParamOption>();
      for (RptParam rptParam : report.getRptParam()) {
        RptParamOption rptParamOption = new RptParamOption();
        rptParamOption.setName(rptParam.getParamName());
        rptParamOption.setDateFormat(rptParam.getParamDateFormat());
        rptParamOption.setDataType(rptParam.getParamDataType());
        String instructStr = rptParam.getParamDataType() + " Value";

        if (rptParam.getParamType().equals("RANGE"))
           instructStr = instructStr + " Between " + rptParam.getParamMinValue() + " And " + rptParam.getParamMaxValue();

        if (rptParam.getParamDataType().equals("Date") || rptParam.getParamDataType().equals("Timestamp")) {
           rptParamOption.setDateFlag("true");
           if (rptParam.getParamDateFormat().equals("yy/oo"))
             instructStr = instructStr + " - Format [YYYY/DDD]";
           if (rptParam.getParamDateFormat().equals("yy/oo 00:00"))
             instructStr = instructStr + " - Format [YYYY/DDD HH:MM]";
        }

        if (rptParam.getParamType().equals("VALUE") || rptParam.getParamType().equals("RANGE")) {
           rptParamOption.setValueFlag("true");
        }

        rptParamOption.setInstructStr(instructStr);

        if (rptParam.getParamType().equals("LIST")) {
           rptParamOption.setListFlag("true");

           List<Option> options = new ArrayList<Option>();
           String listOptions = "All|" + rptParam.getParamListOptions();
           String[] parts = listOptions.split("\\|");
           for ( int x = 0; x < parts.length; x++) {
              Option option = new Option();
              option.setOption(parts[x]);
              options.add(option);
           }
           rptParamOption.setListOptions(options);
        }

        if (rptParam.getParamType().equals("SELECT")) {
           rptParamOption.setSelectFlag("true");

           List<String> selects = em.createNativeQuery(rptParam.getParamSqlStmt()).getResultList();
           List<Option> selectOptions = new ArrayList<Option>();
           Option allOption = new Option();
           allOption.setOption("All");
           selectOptions.add(allOption);

           for (String select : selects) {
              Option selectOption = new Option();
              selectOption.setOption(select);
              selectOptions.add(selectOption);
           }
           rptParamOption.setListOptions(selectOptions);
        }

        rptParamOptions.add(rptParamOption);
      }
      req.setAttribute("rptParamOptions", rptParamOptions);
   }


   /**
    * This method loads the generate report req attributes.
    *
    * @param req A HttpServletRequest object to be loaded with generate report data.
    */
   public void loadEditReport(HttpServletRequest req, Report report)
   {

      req.setAttribute(ConstRpt.ID, report.getId());
      req.setAttribute("reportName", report.getReportName());
      req.setAttribute("reportDesc", report.getReportDesc());
      req.setAttribute("reportFileName", report.getReportFileName());
      req.setAttribute("rptSortOptions", report.getRptSort());





      List<RptParamEdit> rptParamEdits = new ArrayList<RptParamEdit>();
      for (RptParam rptParam : report.getRptParam()) {

        RptParamEdit rptParamEdit = new RptParamEdit();
        rptParamEdit.setParamId(rptParam.getId().toString());
        rptParamEdit.setName(rptParam.getParamName());
        rptParamEdit.setDesc(rptParam.getParamDesc());
        rptParamEdit.setReq(rptParam.getParamRequired());
        rptParamEdit.setDataType(rptParam.getParamDataType());
        rptParamEdit.setParamType(rptParam.getParamType());


        if (rptParam.getParamType().equals("RANGE")) {
          rptParamEdit.setMinValue(rptParam.getParamMinValue());
          rptParamEdit.setMaxValue(rptParam.getParamMaxValue());
        }

        if (rptParam.getParamType().equals("LIST")) {
           rptParamEdit.setListOptions(rptParam.getParamListOptions());
        }

        if (rptParam.getParamType().equals("SELECT")) {
           rptParamEdit.setSqlStmt(rptParam.getParamSqlStmt());
        }

        if (rptParam.getParamDataType().equals("Date") || rptParam.getParamDataType().equals("Timestamp")) {
           rptParamEdit.setDateFormat(rptParam.getParamDateFormat());

           String datePickerSelected = rptParam.getParamDateFormat();
           List<Option> datePickers = new ArrayList<Option>();
           for (DatePickerEnum values : DatePickerEnum.values()) {
             Option datePicker = new Option();
             datePicker.setOption(values.getValue());
             if (values.getValue().equals(datePickerSelected))
               datePicker.setSelected(ConstRpt.SELECTED_VALUE);
             datePickers.add(datePicker);
           }
           rptParamEdit.setDatePickerOptions(datePickers);
        }


        String typeSelected = rptParam.getParamType();
        List<Option> paramOptions = new ArrayList<Option>();
        for (ReportParamTypeEnum values : ReportParamTypeEnum.values()) {
          Option paramType = new Option();
          paramType.setOption(values.getValue());
          if (values.getValue().equals(typeSelected))
            paramType.setSelected(ConstRpt.SELECTED_VALUE);
          paramOptions.add(paramType);
        }
        rptParamEdit.setParamOptions(paramOptions);

        rptParamEdits.add(rptParamEdit);
      }
      req.setAttribute("rptParamEdits", rptParamEdits);

   }


   /**
    * This method loads the report edit data.
    *
    * @param req A HttpServletRequest object to be loaded with generate report data.
    */
   public void editReport(HttpServletRequest req, Report report)
   {
     report.setReportName(req.getParameter("reportName"));
     report.setReportDesc(req.getParameter("reportDesc"));

     int nbrParam = report.getRptParam().size();
     for ( int x = 0; x < nbrParam; x++) {
        String idx = "" + (x + 1);

        String sParamId = req.getParameter("paramId" + idx);
        Long paramId = new Long(sParamId);
        RptParam rptParam = reportService.getRptParam(paramId);

        String dataType = req.getParameter("dataType" + idx);
        String paramType = req.getParameter("paramType" + idx);
        rptParam.setParamType(paramType);
        rptParam.setParamDesc(req.getParameter("desc" + idx));

        if (dataType.equals("Date") || dataType.equals("Timestamp")) {
           rptParam.setParamDateFormat(req.getParameter("dateFormat" + idx));
        }

        rptParam.setParamMinValue(null);
        rptParam.setParamMaxValue(null);
        rptParam.setParamListOptions(null);
        rptParam.setParamSqlStmt(null);

        if (paramType.equals("RANGE")) {
           rptParam.setParamMinValue(req.getParameter("minValue" + idx));
           rptParam.setParamMaxValue(req.getParameter("maxValue" + idx));
        }

        if (paramType.equals("LIST")) {
           rptParam.setParamListOptions(req.getParameter("listOptions" + idx));
        }

        if (paramType.equals("SELECT")) {
           rptParam.setParamSqlStmt(req.getParameter("sqlStmt" + idx));
        }

        reportService.editRptParam(rptParam);
     }
   }

    public boolean loadRptList(RptList rptList, HttpServletRequest req) {

        boolean okFlag = true;
        SimpleDateFormat formatDate = new SimpleDateFormat (FormatDateStr);

        rptList.setId(new Long(req.getParameter("id")));
        rptList.setDeviceId(new Long(req.getParameter("deviceId")));
        rptList.setRptListId(new Long(req.getParameter("rptListId")));
        try {
            rptList.setLastUpdate(formatDate.parse(req.getParameter("lastUpdate")));
        } catch(ParseException e) {
            rptList.setLastUpdate(new Date());
        }
        rptList.setDeleteFlag(new Boolean(req.getParameter("deleteFlag")));

        rptList.setRptName(req.getParameter("rptName"));

       return okFlag;
    }



}
