package model;

//MS-SQL
/*create table [User]
  (id int identity(1,1) primary key ,name varchar(100), phone nvarchar(15), email nvarchar(100),
    password nvarchar(500), address nvarchar(200), department varchar(25), [balance] int,
    [status] varchar(20))
*/

public class User {

    // Attributes
    public int id;
    public String name;
    public String phone;
    public String email;
    public String password;
    public String address;
    public String department;
    public int balance;
    public String status;
    //public String createdOn;

    public User() {

    }

    public User(int id, String name, String phone, String email, String password,
                String address, String department,int balance,String status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
        this.department = department;
        this.balance=balance;
        this.status = "Activated";
       // this.createdOn = createdOn;
    }

    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Name:\t\t"+name);
        System.out.println("Phone:\t\t"+phone);
        System.out.println("Email:\t\t"+email);
        System.out.println("Address:\t"+address);
        System.out.println("Department:\t"+department);
        System.out.println("Balance:\t"+balance);
        System.out.println("Status: \t"+status);
        System.out.println("Id: \t"+id);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", password=" + password
                + ", address=" + address + ", department=" + department + ",balance="+balance+ ",status=" + status+ "]";
    }

}
