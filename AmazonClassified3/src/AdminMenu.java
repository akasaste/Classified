

import java.util.Date;
import java.util.List;
import java.util.Objects;

import controller.classifiedService;
import controller.reportService;
import db.UserDAO;
import model.User;


public class AdminMenu extends Menu{

    private static AdminMenu menu = new AdminMenu();

    public static AdminMenu getInstance() {
        return menu;
    }

    UserDAO dao=new UserDAO();

    private AdminMenu() {

    }

    public void showMenu() {

        System.out.println("Navigating to Admin Menu...");

        // Login Code should come before the Menu becomes Visible to the Admin
        User adminUser = new User();

        System.out.println("Enter Your Email:");
        adminUser.email = scanner.nextLine();

        System.out.println("Enter Your Password:");
        adminUser.password = scanner.nextLine();

        if(Objects.equals(adminUser.email, "akash@gmail.com") && Objects.equals(adminUser.password, "123")) {

            // Link the Admin User to the Session User :)
            ClassifiedSession.user=adminUser;
            System.out.println("*********************");
            System.out.println("Welcome to Admin App");
            System.out.println("Hello, "+adminUser.email.substring(0,adminUser.email.indexOf("@")));

            System.out.println("Its: "+new Date());
            System.out.println("*********************");



            boolean quit = false;

            while(true) {
                System.out.println("\n1. Classified Control Menu\n" +  //app/reject/add/remove/categorize classified
                        "2. User Control Menu\n" +
                        "3. Generate Reports\n" +
                        "4. Exit to Main Menu");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:

                        System.out.println("\nWelcome to the Classified Control Menu");
                        System.out.println("\nPlease Select an Option : \n");

                        System.out.println("1. Add a New Classified\n" +
                                "2. Remove an Existing Classified\n" +
                                "3. Approve Classified\n" +
                                "4. Reject Classified \n" +
                                "5.Categorize Classified\n"+
                                "0. Return to Admin Menu\n");

                        System.out.println("Enter Your Choice: ");
                        int classifiedChoice = Integer.parseInt(scanner.nextLine());

                        if(classifiedChoice == 1) {
                            classifiedService.addClassified();
                        }else if(classifiedChoice == 2) {
                            classifiedService.removeClassified();
                        }else if (classifiedChoice == 3) {
                            classifiedService.approveClassified();
                        } else if (classifiedChoice==4) {
                            classifiedService.rejectClassified();
                        } else if (classifiedChoice==5) {
                            if(classifiedService.categorize()){
                                System.out.println("Classified Categorized Successfully");
                            }
                        } else if (classifiedChoice==0) {
                            break;
                        } else {
                            System.err.println("Invalid Choice..");
                        }

                        break;

                    case 2:

                        System.out.println("1: User Control");
                        List<User> users=dao.retrieve("Select * from [User]");
                        for(User object : users) {
                            object.prettyPrint();
                        }
                        auth.updateUser();
                        break;

                    case 3:
                        System.out.println("Reports");
                        reportService.viewReport();
                        break;
                    case 4:
                        System.out.println("Thank You for Using Admin App !!");
                        quit = true;
                        break;

                    default:
                        System.err.println("Invalid Choice...");
                        break;
                }

                if(quit) {
                    break;
                }

            }
        }else {
            System.err.println("Invalid Credentials. Please Try Again !!");
        }
    }

}
