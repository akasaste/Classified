package db;

import model.Payment;
import model.Report;
import model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO implements DAO<Report>{
    DB db = DB.getInstance();
    @Override
    public int insert(Report object) {
            String sql = "INSERT INTO Report (buyerId, buyerName, sellerId, SellerName, " +
                    "soldON, transactionAmount, classifiedID) " +
                    "VALUES ('"+object.buyerId+"', '"+object.buyerName+"'," +
                    " '"+object.sellerId+"', '"+object.sellerName+"', " +
                    "'"+object.soldON+"','"+object.transactionAmount+"','"+object.classifiedId+"')";
            return db.executeSQL(sql);

            //int buyerId,String buyerName,int sellerId,String sellerName,String soldON,int transactionAmount
    }

    @Override
    public int update(Report object) {
        return 0;
    }

    @Override
    public int update(String sql) {
        return db.executeSQL(sql);
    }

    @Override
    public int delete(Report object) {
        return 0;
    }

    @Override
    public int adminUpdate(Report object) {
        return 0;
    }

    @Override
    public List<Report> retrieve() {
        return null;
    }

    @Override
    public List<Report> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);

        ArrayList<Report> reports = new ArrayList<Report>();
        try {
            while(set.next()) {

                Report report=new Report();

                // Read the row from ResultSet and put the data into User Object
                report.classifiedId=set.getInt("classifiedId");
                report.buyerId=set.getInt("buyerId");
                report.buyerName=set.getString("buyerName");
                report.sellerId=set.getInt("sellerId");
                report.sellerName=set.getString("sellerName");
                report.transactionAmount=set.getInt("transactionAmount");
                report.soldON=set.getString("soldON");
                //user.createdOn = set.getString("createdOn");

                reports.add(report);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }


        return reports;
    }
}
