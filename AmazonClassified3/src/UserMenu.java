

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

import controller.classifiedService;
import controller.paymentService;
import db.ClassifiedDAO;
import model.Classified;
import model.User;

public class UserMenu extends Menu{

    private static UserMenu menu = new UserMenu();

    public static UserMenu getInstance() {
        return menu;
    }

    ClassifiedDAO classifiedDAO=new ClassifiedDAO();
    Classified classified=new Classified();

    private UserMenu() {

    }

    public void showMenu() {

        System.out.println("Navigating to User Menu...");

        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("3: Cancel");

        System.out.println("Enter Your Choice: ");
        int initialChoice = Integer.parseInt(scanner.nextLine());

        boolean result = false;

        User user = new User();


        if(initialChoice == 1) {

            System.out.println("Enter Your Name:");
            user.name = scanner.nextLine();

            System.out.println("Enter Your Phone:");
            user.phone = scanner.nextLine();

            System.out.println("Enter Your Email:");
            user.email = scanner.nextLine();

            System.out.println("Enter Your Password:");
            user.password = scanner.nextLine();

            try {
                // Hash the Password of User :)
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
                user.password = Base64.getEncoder().encodeToString(hash);
            }catch (Exception e) {
                System.err.println("Something Went Wrong: "+e);
            }

            System.out.println("Enter Your Address:");
            user.address = scanner.nextLine();

            System.out.println("Enter Your Department:");
            user.department = scanner.nextLine();

            result = auth.registerUser(user);

        }else if(initialChoice == 2) {

            System.out.println("Enter Your Email:");
            user.email = scanner.nextLine();

            System.out.println("Enter Your Password:");
            user.password = scanner.nextLine();

            try {
                // Encoded to Hash i.e. SHA-256 so as to match correctly
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
                user.password = Base64.getEncoder().encodeToString(hash);
            }catch (Exception e) {
                System.err.println("Something Went Wrong: "+e);
            }

            result = auth.loginUser(user);

        }else if(initialChoice == 3) {
            System.out.println("Thank You for Using Bus Pass App");
        }else {
            System.err.println("Invalid Choice...");
            System.out.println("Thank You for Using Bus Pass App");
        }

        if(result ) {


            System.out.println("^^^^^^^^^^^^^^^^^^^");
            System.out.println("Welcome to User App");
            System.out.println("Hello, "+user.name);
            System.out.println("Its: "+new Date());
            System.out.println("^^^^^^^^^^^^^^^^^^^");

            boolean quit3 = false;

            while(true) {

                System.out.println("1: Manage Profile");
                System.out.println("2: Post Classified");
                System.out.println("3: View Classified");
                System.out.println("4: Connect with other Users to Buy/Sell");
                System.out.println("5: Integrate Payment Options");
                System.out.println("6: Press 6 to exit");

                int choice7 = Integer.parseInt(scanner.nextLine());

                switch (choice7) {
                    case 1:
                        System.out.println("View your Uploaded Classifieds with their Status");
                        classifiedService.viewOwnClassified(user.id);
                        boolean quit1=false;
                        while(true) {
                            System.out.println("\n1. Delete your classified\n" +  //app/reject/add/remove/categorize classified
                                    "2. Change Price of Classified\n" +
                                    "0. Go to previous Menu");
                            int choice1= scanner.nextInt();
                            switch(choice1){
                                case 1:
                                    System.out.println("Enter Classified id to be deleted");
                                    int choice2= scanner.nextInt();
                                    classified.id=choice2;
                                    classifiedDAO.delete(classified);
                                    break;

                                    case 2:
                                        System.out.println("Enter Classified id to change Price: ");
                                        int choice3= scanner.nextInt();
                                        System.out.println("Enter Price");
                                        int choice4= scanner.nextInt();
                                        classifiedDAO.update("Update Classified set price = "+choice4+"" +
                                                " where id = "+choice3+ "and userId= "+user.id);
                                        System.out.println("Changes Done");
                                        break;

                                case 0:
                                    quit1=true;
                                    break;

                                default:
                                    System.out.println("Invalid Choice");
                                    break;
                            }
                            if(quit1){
                                break;
                            }
                        }
                        break;

                    case 2:
                        System.out.println("****************************");
                        classifiedService.addClassified();
                        classifiedService.useridUpdate(user.id);
                        break;

                    case 3:
                        classifiedService.viewClassified(user.id);
                        break;

                    case 4:
                        classifiedService.viewClassified(user.id);
                        System.out.println("Enter the Classified Id you want to buy from the List above:");
                        int a=scanner.nextInt();
                        if(classifiedService.buy(a, user.id)){
                            System.out.println("Buying Successful");
                        }
                        else{
                            System.out.println("Buying went wrong, please check with correct details again");
                        }
                        break;

                    case 5:
                        System.out.println("Enter your choice ");
                        System.out.println("Press 1 to add Card Details");
                        System.out.println("Press 2 to add Balance");
                        int c= scanner.nextInt();
                        if(c==1){
                            System.out.println("Enter Credit/Debit Card details");
                            String cardNo= scanner.next();
                            System.out.println("Enter CVV ");
                            int cvv= scanner.nextInt();
                            paymentService.addCard(user.id, cardNo,cvv);
                        }
                        if(c==2){
                            paymentService.addBalance(user.id);
                        }
                        break;

                    case 6:
                        System.out.println("Thank You for Using User App !!");
                        quit3 = true;
                        break;

                    default:
                        System.err.println("Invalid Choice...");
                        break;
                }

                if(quit3) {
                    break;
                }

            }
        }
        else {
            System.err.println("Authentication Failed..");
        }
    }


}
