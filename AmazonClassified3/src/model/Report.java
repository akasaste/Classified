package model;

/*create table Report
  (buyerId int,buyerName varchar(100),sellerId int,
  sellerName nvarchar(100),
  soldON nvarchar(100),transactionAmount int,
  classifiedId int)  */
public class Report {
    public int buyerId;
    public String buyerName;
    public int sellerId;
    public String sellerName;

    public String soldON;
    public int transactionAmount;
    public int classifiedId;

    public Report(){

    }
    public Report(int buyerId,String buyerName,int sellerId,String sellerName,String soldON,int transactionAmount,int classifiedId){
        this.buyerId=buyerId;
        this.buyerName=buyerName;
        this.sellerId=sellerId;
        this.sellerName=sellerName;
        this.soldON=soldON;
        this.transactionAmount=transactionAmount;
        this.classifiedId=classifiedId;
    }

    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("classifiedId:\t\t"+classifiedId);
        System.out.println("buyerName:\t\t"+buyerName);
        System.out.println("buyerId:\t\t"+buyerId);
        System.out.println("sellerName:\t"+sellerName);
        System.out.println("sellerId:\t"+sellerId);
        System.out.println("Transaction Amount:\t"+transactionAmount);
        System.out.println("sold on: \t"+soldON);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }

    public String toString() {
        return "Payment [buyerId=" + buyerId + ", buyerName=" + buyerName +",sellerId="+sellerId+ "," +
                "sellerName="+sellerName+ ", soldON=" + soldON+ ", transactionAmount="
                + transactionAmount + ", classifiedId=" + classifiedId+" ]";
    }
}
