package model;

//MS-SQL
/*
CREATE TABLE Classified(id INT IDENTITY(1,1) PRIMARY KEY,title varchar(500),
                       productName varchar(50),condition varchar(100),description varchar(500),
					   price varchar(10),status varchar(10),userId int,
					   createdOn DATETIME DEFAULT GETDATE(), category nvarchar(100))


 */

import java.util.Scanner;
public class Classified {
    // Attributes :)
    public int id;
    User user = new User();
    //User u = users.get(0);

    public String title;
    public String description;
    public String productName;
    public String createdOn;

    public String condition;

    public int price;

    public String status;

    public int userId;
    public String category;
    public Classified() {

    }

    public Classified(int id, String title,String productName, String condition, String description,
                      int price,String status,int userId,String createdOn, String category) {
        this.id = id;
        this.title = title;
        this.productName = productName;
        this.condition=condition;
        this.description = description;
        this.price=price;
        this.status=status;
        this.userId=user.id;
        this.createdOn = createdOn;
        this.category=category;
    }

    public void getDetails(boolean updateMode) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Capturing Classified Details....");

        System.out.println("Enter Classified Title:");
        title = scanner.nextLine();

        System.out.println("Enter Product Name:");
        productName = scanner.nextLine();

        System.out.println("Enter Product Condition:");
        condition = scanner.nextLine();

        System.out.println("Enter Description:");
        description = scanner.nextLine();


        System.out.println("Enter Price for the Product:");
        price = scanner.nextInt();

        status="'Pending'";

        userId=user.id;

        if(updateMode) {
            System.out.println("Enter Classified ID:");
            id = Integer.parseInt(scanner.nextLine());
        }

    }

    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Classified ID:\t\t"+id);
        System.out.println("Title:\t\t"+title);
        System.out.println("Product Name :\t\t"+productName);
        System.out.println("Condition :\t\t"+condition);
        System.out.println("Description:\t\t"+description);
        System.out.println("Price:\t\t"+price);
        System.out.println("UserId: \t\t"+userId);
        System.out.println("Status: \t\t"+status);
        System.out.println("Created On:\t"+createdOn);
        System.out.println("Category: \t"+category);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }


    @Override
    public String toString() {
        return "Classified [id=" + id + ", title=" + title +",productName="+productName +
                ", condition="+condition+", description=" +
                description + ", price=" + price +", status=" +status+",UserId= "+userId+
                ", createdOn=" + createdOn +", category = "+category+ "]";
    }

}
