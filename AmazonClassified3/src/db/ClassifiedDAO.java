package db;

import model.Classified;
import model.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassifiedDAO implements DAO<Classified> {

    DB db = DB.getInstance();
    UserDAO userDAO=new UserDAO();
    User user=new User();
    @Override
    public  int insert(Classified object) {
        User user=new User();
        String sql = "INSERT INTO Classified (title, productName, condition, description, price,status,userId, category ) VALUES " +
                "('"+object.title+"','"+object.productName+"','"+object.condition+"'," +
                " '"+object.description+"', '"+object.price+"',"+object.status+" ,"+user.id+",'"+object.category+"')";
        return db.executeSQL(sql);
    }
    //String title,String productName, String condition, String description,
//                      String price
    @Override
    public int update(Classified object) {
        String sql = "UPDATE Classified set title = '"+object.title+"', productName='"+object.productName+"'," +
                "condition='"+object.condition+"' ,description = '"+object.description+"'," +
                "price= '"+object.price+"',category = '"+object.category+"'" +
                " WHERE id = "+object.id;
        return db.executeSQL(sql);
    }

    @Override
    public int update(String sql) {
        return db.executeSQL(sql);
    }

    public int useridUpdate(Classified object){
        if(object.id==0){
            List<User> users = userDAO.retrieve("Select * from [User] ");
            User us = users.get(0);
            user.balance = us.balance;
            user.name=us.name;
        }
        String sql="UPDATE Classified set userId= "+object.id;
        return db.executeSQL(sql);
    }

    public int adminUpdate(Classified object){
        String sql="update Classified set status= 'Approved' where id= "+object.id;
        return db.executeSQL(sql);
    }
    @Override
    public int delete(Classified object) {
        String sql = "DELETE from Classified WHERE id = "+object.id;
        return db.executeSQL(sql);
    }

    @Override
    public List<Classified> retrieve() {

        String sql = "SELECT * from Classified";

        ResultSet set = db.executeQuery(sql);

        ArrayList<Classified> objects = new ArrayList<Classified>();

        try {
            while(set.next()) {

                Classified object = new Classified();

                // Read the row from ResultSet and put the data into Object
                object.id = set.getInt("id");
                object.title = set.getString("title");
                object.productName = set.getString("productName");
                object.condition = set.getString("condition");
                object.description = set.getString("description");
                object.price = set.getInt("price");
                object.status=set.getString("status");
                object.userId=set.getInt("userId");
                object.createdOn = set.getString("createdOn");
                object.category= set.getString("category");

                objects.add(object);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }


        return objects;
    }

    @Override
    public List<Classified> retrieve(String sql) {

        ResultSet set = db.executeQuery(sql);

        ArrayList<Classified> objects = new ArrayList<Classified>();

        try {
            while(set.next()) {

                Classified object = new Classified();

                // Read the row from ResultSet and put the data into Object
                object.id = set.getInt("id");
                object.title = set.getString("title");
                object.productName = set.getString("productName");
                object.condition = set.getString("condition");
                object.description = set.getString("description");
                object.price = set.getInt("price");
                object.userId=set.getInt("UserId");
                object.status=set.getString("status");
                object.createdOn = set.getString("createdOn");
                object.category=set.getString("category");

                objects.add(object);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }

        return objects;

    }

}