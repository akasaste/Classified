package model;

//MS-SQL
/*
   create table Payment
   (userid int,cardNo nvarchar(50), cvv int)
   */

public class Payment {
    public int userid;
    public String cardNo;
    public int cvv;

    public Payment(){

    }
    public Payment(int userid,String cardNo, int cvv){
        this.userid=userid;
        this.cardNo=cardNo;
        this.cvv=cvv;
    }

    public String toString() {
        return "Payment [userid=" + userid + ", cardNo=" + cardNo +",cvv="+cvv + "]";
    }
}
