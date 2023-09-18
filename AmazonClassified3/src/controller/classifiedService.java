package controller;

import db.ClassifiedDAO;
import db.ReportDAO;
import db.UserDAO;
import model.Classified;
import model.Report;
import model.User;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class classifiedService {
    static ClassifiedDAO classifiedDAO = new ClassifiedDAO();
    static UserDAO userDAO=new UserDAO();
    static User user=new User();
    static ReportDAO reportDAO=new ReportDAO();
    static Classified classified=new Classified();


    private static classifiedService classifiedService = new classifiedService();
    static Scanner scanner = new Scanner(System.in);

    public static classifiedService getInstance() {
        return classifiedService;
    }

    private classifiedService() {

    }

    public static void addClassified() {
        Classified classified = new Classified();
        classified.getDetails(false);
        int result = classifiedDAO.insert(classified);
        String message = (result > 0) ? "Classified Added Successfully and is in PENDING state, " +
                "will get posted and Visible to Others once Admin Approves it" : "Adding Classified Failed. Try Again..";
        System.out.println(message);
    }

    public static void removeClassified() {
        Classified classified = new Classified();
        List<Classified> classifieds=classifiedDAO.retrieve("Select * from Classified");
        if(classifieds.size()==0){
            System.out.println("No classifieds as of present uploaded or not approved by Admin");
        }
        else {
            for (Classified object : classifieds) {
                object.prettyPrint();
            }
            System.out.println("Enter Classified ID to be deleted: ");
            classified.id = scanner.nextInt();
            int result = classifiedDAO.delete(classified);
            String message = (result > 0) ? "Classified Deleted Successfully" : "Deleting Classified Failed. Try Again..";
            System.out.println(message);
        }
    }

    public static void approveClassified(){
        int result=0;

        List<Classified> classifieds=classifiedDAO.retrieve("Select * from Classified");
        for(Classified object : classifieds) {
            object.prettyPrint();
        }
        if(classifieds.size()==0){
            System.out.println("No classifieds as of presently uploaded or not yet approved by Admin");
        }
        else {
            System.out.println("Enter Classified ID to be approved from the above list");
            classified.id = scanner.nextInt();
            List<Classified> classifieds1 = classifiedDAO.retrieve("Select * from Classified where id= " + classified.id);
            Classified cs = classifieds1.get(0);
            classified.userId = cs.userId;
            if (classified.userId == 0) {
                List<User> users = userDAO.retrieve("Select * from [User]");
                for (User object : users) {
                    object.prettyPrint();
                }
                System.out.println("Its as Admin Classified , please enter an user ID from above displayed list to assign it");
                int assign = scanner.nextInt();
                List<User> users1 = userDAO.retrieve("Select * from [User] where id = " + assign);
                if (users1.size() > 0) {
                    classifiedDAO.update("Update Classified set userId= " + assign + "where id = " + classified.id);
                    result = classifiedDAO.adminUpdate(classified);
                } else {
                    System.out.println("Approval failed, please retry with correct UserID from above List to assign");
                }

            } else {
                result = classifiedDAO.adminUpdate(classified);
            }
            String message = (result > 0) ? "Classified Approved Successfully" : "Updating  Failed. Try Again..";
            System.out.println(message);
        }
    }

    public static void rejectClassified(){
        List<Classified> classifieds=classifiedDAO.retrieve("Select * from Classified");
        for(Classified object : classifieds) {
            object.prettyPrint();
        }
        if(classifieds.size()==0){
            System.out.println("No classifieds as of presently uploaded or not yet approved by Admin");
        }
        else {
            System.out.println("Enter Classified ID to be rejected from above List");
            int r = scanner.nextInt();
            classifiedDAO.update("Update Classified set status= 'Rejected' where id = " + r);
            System.out.println("Rejected Successfully");
        }
    }

    public static void viewClassified(int userid){
        List<Classified> objects = classifiedDAO.retrieve("Select * from Classified where status='Approved' and " +
                "userId <> "+userid);
        for(Classified object : objects) {
            object.prettyPrint();
        }
        if(objects.size()==0){
            System.out.println("No classifieds as of presently uploaded or not yet approved by Admin");
        }
    }

    public static void viewOwnClassified(int userid){
        List<Classified> objects = classifiedDAO.retrieve("Select * from Classified where userId= "+userid);
        if(objects.size()>0) {
            for (Classified object : objects) {
                object.prettyPrint();
            }
        }
        else {
            System.out.println("You don't have any Classified posted yet");
        }
    }
    public static void useridUpdate(int user){
        Classified classified=new Classified();
        classified.id=user;
        int result=classifiedDAO.useridUpdate(classified);
    }

    public static boolean buy(int classifiedid, int buyerid){
        Classified classified=new Classified();
        Report report=new Report();
        List<Classified> classifieds=classifiedDAO.retrieve("Select * from Classified " +
                "where id = "+classifiedid+"and status= 'Approved'");
        Classified cs=classifieds.get(0);
        classified.id= cs.id;
        classified.price=cs.price;
        classified.userId=cs.userId; //id of user to whom balance is to be sent
        report.classifiedId=classified.id;
        report.sellerId=classified.userId;
        report.transactionAmount=classified.price;
        report.buyerId=buyerid;
        List<User> users1=userDAO.retrieve("Select * from [User] where id= "+classified.userId );
        User us1=users1.get(0);
        user.status=us1.status;
        if(Objects.equals(user.status, "Deactivated")){
            System.out.println("User is deactivated by Admin you cant buy from this User");
            System.out.println("Wait till the Admin Activates the User again");
            return false;
        }
        else {
            List<User> users = userDAO.retrieve("Select * from [User] where id= " + buyerid);
            User us = users.get(0);
            user.balance = us.balance;
            user.name=us.name;
            if (user.balance >= classified.price) {
                userDAO.update("Update [User] set balance= " + classified.price + " + balance where id= " + classified.userId);
                userDAO.update("Update [User] set balance= balance - " + classified.price + " where id = " + buyerid);
                report.buyerName=user.name;
                report.sellerName= us1.name;
                reportDAO.insert(report);
                reportDAO.update("Update Report set soldON= CURRENT_TIMESTAMP where classifiedid= "+report.classifiedId);
                classifiedDAO.delete(classified);
                return true;
            } else {
                System.out.println("Insufficient Balance / Payment Integration not done: Integrate your" +
                        " Payment Method first");
                return false;
            }
        }
    }

    public static boolean categorize(){
        List<Classified> objects =classifiedDAO.retrieve("Select * from Classified");
        if(objects.size()>0) {
            for (Classified object : objects) {
                object.prettyPrint();
            }
        }
        System.out.println("Enter the Classified id you want to Categorize from the above list");
        int cat= scanner.nextInt();;
        List<Classified> objects2 =classifiedDAO.retrieve("Select * from Classified where id = "+cat);
        if(objects2.size()>0){
            System.out.println("Enter category type e.g: \nBooks,\nClothes,\nFashion,\nElectronics, etc");
            String category= scanner.next();
            classifiedDAO.update("Update Classified set category = '"+category+"' where id = "+cat);
            return true;
        }
        else{
            System.out.println("Categorization Failed, might be you Entered wrong Classified ID" +
                    ",Please Check again with correct Classified id");
            return false;
        }
    }
}
