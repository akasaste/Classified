package db;

import model.Payment;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO implements DAO<Payment>{
    DB db = DB.getInstance();
    @Override
    public int insert(Payment object) {
        String sql = "INSERT INTO Payment (userid, cardNo, cvv) " +
                "VALUES ('"+object.userid+"', '"+object.cardNo+"', '"+object.cvv+"')";
        return db.executeSQL(sql);
    }

    @Override
    public int update(Payment object) {
        return 0;
    }

    @Override
    public int update(String sql) {
        return 0;
    }

    @Override
    public int delete(Payment object) {
        return 0;
    }

    @Override
    public int adminUpdate(Payment object) {
        return 0;
    }

    @Override
    public List<Payment> retrieve() {
        return null;
    }

    @Override
    public List<Payment> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);

        ArrayList<Payment> payments = new ArrayList<Payment>();
        try {
            while(set.next()) {

                Payment payment = new Payment();

                // Read the row from ResultSet and put the data into User Object
                payment.userid = set.getInt("userid");
                payment.cvv = set.getInt("cvv");
                payment.cardNo= set.getString("cardNo");
                //user.createdOn = set.getString("createdOn");

                payments.add(payment);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }


        return payments;
    }
}
