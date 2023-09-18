package db;

import model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User>{

    DB db = DB.getInstance();

    @Override
    public int insert(User object) {
        String sql = "INSERT INTO [User] (name, phone, email, password, address, department,balance, status) " +
                "VALUES ('"+object.name+"', '"+object.phone+"', '"+object.email+"', '"+object.password+"'," +
                " '"+object.address+"', '"+object.department+"','"+object.balance+"', "+"'Activated'"+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(User object) {
        String sql = "UPDATE [User] set name = '"+object.name+"', phone='"+object.phone+"'," +
                " password='"+object.password+"', address='"+object.address+"', department='"
                +object.department+"' WHERE email = '"+object.email+"'";
        return db.executeSQL(sql);
    }

    @Override
    public int update(String sql) {
        return db.executeSQL(sql);
    }

    @Override
    public int delete(User object) {
        String sql = "DELETE FROM [User] WHERE email = '"+object.email+"'";
        return db.executeSQL(sql);
    }

    @Override
    public int adminUpdate(User object) {
        return 0;
    }

    @Override
    public List<User> retrieve() {

        String sql = "SELECT * from [User]";

        ResultSet set = db.executeQuery(sql);

        ArrayList<User> users = new ArrayList<User>();

        try {
            while(set.next()) {

                User user = new User();

                // Read the row from ResultSet and put the data into User Object
                user.id = set.getInt("id");
                user.name = set.getString("name");
                user.phone = set.getString("phone");
                user.email = set.getString("email");
                user.password = set.getString("password");
                user.address = set.getString("address");
                user.department = set.getString("department");
                user.balance=set.getInt("balance");
                user.status = set.getString("status");
                //user.createdOn = set.getString("createdOn");

                users.add(user);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }


        return users;
    }

    @Override
    public List<User> retrieve(String sql) {

        ResultSet set = db.executeQuery(sql);

        ArrayList<User> users = new ArrayList<User>();
        try {
            while(set.next()) {

                User user = new User();

                // Read the row from ResultSet and put the data into User Object
                user.id = set.getInt("id");
                user.name = set.getString("name");
                user.phone = set.getString("phone");
                user.email = set.getString("email");
                user.password = set.getString("password");
                user.address = set.getString("address");
                user.department = set.getString("department");
                user.status = set.getString("status");
                user.balance= set.getInt("balance");
                //user.createdOn = set.getString("createdOn");

                users.add(user);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }


        return users;
    }

}
