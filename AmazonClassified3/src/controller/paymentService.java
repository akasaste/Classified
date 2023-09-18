package controller;

import db.PaymentDAO;
import db.ReportDAO;
import db.UserDAO;
import model.Payment;

import java.util.List;
import java.util.Scanner;

public class paymentService {
    static PaymentDAO payDAO=new PaymentDAO();
    static Payment payment=new Payment();
    static Scanner scanner = new Scanner(System.in);
    static UserDAO userDAO = new UserDAO();
    private static paymentService service = new paymentService();

    private paymentService(){

    }

    public static paymentService getInstance() {
        return service;
    }

    public static void addCard(int userid,String cardNo,int cvv){
        payment.userid=userid;
        payment.cardNo=cardNo;
        payment.cvv=cvv;
        payDAO.insert(payment);
    }

    public static void addBalance(int userid){
        System.out.println("Enter Card Number");
        String c= scanner.next();
        System.out.println("Enter cvv");
        int cvv= scanner.nextInt();
        List<Payment> payments= payDAO.retrieve("Select * from Payment where userId= "+userid+" and cardNo= '"+c
                                     +"' and cvv = "+cvv);
        if(payments.size()>0) {
            Payment py = payments.get(0);
            payment.userid = py.userid;
            payment.cardNo = py.cardNo;
            payment.cvv = py.cvv;
            System.out.println("Enter amount to add to your account ");
            int amount= scanner.nextInt();
            userDAO.update("Update [User] set balance= "+amount+" +balance where id= "+payment.userid);
        }

        else{
            System.out.println("Card not found please add your card first");
        }

    }
}
