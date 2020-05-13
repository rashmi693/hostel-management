
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NewUser
 */
public class JDBC {
    public static void main(String arg[]) throws SQLException{
    Scanner x=new Scanner(System.in);
    PreparedStatement stmt ;
    CallableStatement cmt;
    ResultSet rs;
    String s,p;
    int k;
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","micro","soft");
    
  // stmt = con.prepareStatement("create table userdetails( name varchar(10),pass char(4))");
    //stmt.executeUpdate();
   do
   {
   System.out.println("1.INSERT 2.UPDATE 3.DELETE 5.Callable Statement insertr 6.Callable Statement showr 4.EXIT");
   k=x.nextInt();
   switch(k){
       

       case 1: stmt = con.prepareStatement("insert into userdetails values(?,?)");
             System.out.print("Enter username :");
             s=x.next();
             System.out.print("Enter password(4 character): ");
             p=x.next();
             stmt.setString(1, s);
             stmt.setString(2, p);
             int r=stmt.executeUpdate();
             if(r==1)
                 System.out.println("Inserted Successfully!!");
             else
                  System.out.println("Failed to insert");
             break;
       case 3: System.out.println("Enter username and password you want to delete: ");
        s=x.next();p=x.next();
        stmt=con.prepareStatement("delete from userdetails where name=? and pass=?");
        stmt.setString(1, s);
        stmt.setString(2, p);
       int r1= stmt.executeUpdate();
        if(r1==1)
            System.out.println("Deleted Successfully");
        else
              System.out.println("No Such entry found!!");
               break;
       case 2: System.out.println("Enter the username  and password u want to update??");
               s=x.next();
               p=x.next();
               stmt=con.prepareStatement("select * from userdetails where name=? and pass=?");
               stmt.setString(1, s);
               stmt.setString(2, p);
               rs=stmt.executeQuery();
               if(rs.next())
               {
                System.out.println("Enter the New password:");
                p=x.next();
                stmt=con.prepareStatement("update userdetails set pass=? where name=?");
                stmt.setString(1, p);
                stmt.setString(2, s);
                r=stmt.executeUpdate();
                if(r==1)
                System.out.println("Updated successfully!!!");
                 else
                 System.out.println("Failed to update!!"); 
                }
             else
                System.out.println("No such Records found!!!");
               break;
       case 5: cmt=con.prepareCall("{call insertr(?,?)}");
               cmt.setString(1, "Suman");
               cmt.setString(2, "2401");
               cmt.execute();
               System.out.println("Inserted!!");
               break;
               
       case 6: cmt=con.prepareCall("{call showr(?,?)}"); 
               cmt.setString(1, "Suman");
               cmt.registerOutParameter(2, Types.CHAR);
               cmt.execute();
               System.out.println("Password of Suman is "+cmt.getString(2));
             break;
    }
    stmt=con.prepareStatement("Select * from userdetails");
    rs=stmt.executeQuery();
    System.out.println("***Table Userdetails Status****");
    System.out.println("Username\tPassword");
    while(rs.next())
    {
        System.out.println(rs.getString(1)+"\t\t"+rs.getString(2));
    }
   }while(k!=4);
   }
    
   
    
}
