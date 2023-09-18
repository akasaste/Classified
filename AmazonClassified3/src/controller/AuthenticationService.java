package controller;

import db.UserDAO;
import model.Classified;
import model.User;

import java.util.List;
import java.util.Scanner;

public class
AuthenticationService {
    private static AuthenticationService service = new AuthenticationService();
    static Scanner scanner = new Scanner(System.in);

    UserDAO dao = new UserDAO();
    User user=new User();

    private AuthenticationService(){

    }

    public static AuthenticationService getInstance() {
        return service;
    }


    public boolean loginUser(User user) {

        String sql = "SELECT * FROM [User] WHERE email = '"+user.email+"' AND password = '"+user.password+"'";
        List<User> users = dao.retrieve(sql);
        if(users.size() > 0) {
            User u = users.get(0);
			user.id = u.id;
			user.name = u.name;
            return true;
        }

        return false;
    }

    public boolean registerUser(User user) {
        return dao.insert(user) > 0;
    }

    public void updateUser() {

        System.out.println("Enter user id to Activate/Deactivate from above list");
        int userid=scanner.nextInt();
        List<User> users=dao.retrieve("Select * from [User] where id = "+userid);
        User us=users.get(0);
        user.status=us.status;
        System.out.println("Present Status: "+ user.status);
        System.out.println("Press 1 to Activate \nPress 2 to Deactivate");
        int status= scanner.nextInt();
        if(status==1){
            dao.update("Update [User] set status = 'Activated' where id= "+userid);
        } else if (status==2) {
            dao.update("Update [User] set status = 'Deactivated' where id= "+userid);
        }else{
            System.out.println("Something went Wrong, No changes done, Please try again after sometime");
        }
    }

}
