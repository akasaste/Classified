package controller;

import db.ReportDAO;
import model.Report;

import java.util.List;

public class reportService {
    private static reportService reportservice = new reportService();
    static ReportDAO reportDAO=new ReportDAO();
    static Report report=new Report();

    private reportService(){

    }

    public static reportService getInstance() {
        return reportservice;
    }

    public static void viewReport(){
        List<Report> reports=reportDAO.retrieve("Select * from Report");
        for(Report object : reports) {
            object.prettyPrint();
        }
    }
}
