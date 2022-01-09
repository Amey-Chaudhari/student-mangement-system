/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementsystem;


import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class studentmanagementsystem {
        public static void main(String[] args) throws IOException{
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("driver registered");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }

        try{

           
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
            System.out.println("Connection established.");
            System.out.println();
            System.out.println("****************************************************************");
            System.out.println("*****************Student Management System**********************");
            System.out.println("****************************************************************");
            System.out.println();
            
            Statement stmt = con.createStatement();

            System.out.println("Select any one by typing no. from 1 to 5: ");
            System.out.println("1 ==> Update data");
            System.out.println("2 ==> Delete data");
            System.out.println("3 ==> Print all student data");
            System.out.println("4 ==> Print by roll no./Name/dept");
            System.out.println("5 ==> insert new student data to database");
            System.out.println();
            System.out.print("Enter what you want to do? Enter option no. 1 to 5 : ");
            System.out.println();
            
            Scanner input = new Scanner(System.in);
            int  z = input.nextInt();

            switch (z) {
//update
                case 1:                       
                        System.out.println("What you want to update");
                        System.out.println("1. name");
                        System.out.println("2. cgpa");
                        System.out.println("3. Department");
                        System.out.print("Enter the option no.: ");
                        Scanner scan = new Scanner(System.in);
                        int option = scan.nextInt();
                        System.out.println();
                        System.out.println("Enter your roll number:");
                        Scanner sc1 = new Scanner(System.in);
                        int  r1 = sc1.nextInt();
                        System.out.println();
                    
                        if(option == 1){
                       
                        PreparedStatement stm=con.prepareStatement("update student set sname=? where roll=?");
                        System.out.println("Enter student name you want to update");
                        Scanner na = new Scanner(System.in);
                        String name1=na.nextLine();
                        stm.setString(1,name1);//1 specifies the first parameter in the query i.e. name  
                        stm.setInt(2,r1);  
  
                        int i=stm.executeUpdate();  
                        System.out.println(i+" records updated");
                        }
                        
                        else if(option==2){
                         PreparedStatement stm=con.prepareStatement("update student set cgpa=? where roll=?");
                        System.out.println("Enter student cgpa you want to update");
                        Scanner na = new Scanner(System.in);
                        Float cgpa =na.nextFloat();
                        stm.setFloat(1,cgpa);//1 specifies the first parameter in the query i.e. name  
                        stm.setInt(2,r1);  
  
                        int i=stm.executeUpdate();  
                        System.out.println(i+" records updated");
                        }
                        
                        else if(option == 3){
                        PreparedStatement stm=con.prepareStatement("update student set dept=? where roll=?");
                        System.out.println("Enter student Department you want to update");
                        Scanner na = new Scanner(System.in);
                        String dept=na.nextLine();
                        stm.setString(1,dept);//1 specifies the first parameter in the query i.e. name  
                        stm.setInt(2,r1); 
  
                        int i=stm.executeUpdate();  
                        System.out.println(i+" records updated");
                        }
                        else{System.out.println("invalid option");}
                    break;

//delete
                case 2:
                    
                    System.out.println("Enter the roll number to delete:");
                    Scanner sc2 = new Scanner(System.in);
                    int  r2 = sc2.nextInt();

                    String query2="delete from student where roll="+r2;
                    int rowsaffected = stmt.executeUpdate(query2);
                    if(rowsaffected>0)
                        System.out.println(rowsaffected + " Rows deleted.");
                    else
                        System.out.println("no row deleted.");
                    

                    break;

//print all
                case 3:
                    String query3 = "select * from student";
                    ResultSet rs3 = stmt.executeQuery(query3);
                    System.out.println();
                    while(rs3.next()){
                        
                        System.out.print("roll: "+ rs3.getInt("roll"));
                        System.out.print(" name: "+ rs3.getString("sname"));
                        System.out.print(" cgpa: "+ rs3.getFloat("cgpa"));
                        System.out.print(" Department: "+ rs3.getString("dept"));
                        System.out.println();
                          }
                    break;
                    
//print by roll no.,dept
                
                case 4:
                    System.out.println();
                    System.out.println("Print list by ");
                    System.out.println("1. By roll no.");        
                    System.out.println("2. By CGPA");
                    System.out.println("3. By Department");    
                    System.out.println();
                    System.out.println("Enter the option number: ");
                    Scanner scan1 = new Scanner(System.in);
                    int  opt = scan1.nextInt();
                    
                 if(opt==1){
                    System.out.println("Enter the roll number to print:");
                    Scanner sc4 = new Scanner(System.in);
                    int  r4 = sc4.nextInt();
                    
                    String query4 = "select dept, roll,sname,cgpa from STUdent where roll="+r4;
            
                     ResultSet rs4 = stmt.executeQuery(query4);
                     
                       while(rs4.next()){
                        System.out.print("roll: "+ rs4.getInt(2));
                        System.out.print(" name: "+ rs4.getString("sname"));
                        System.out.print(" cgpa: "+ rs4.getFloat("cgpa"));
                        System.out.print(" dept: "+ rs4.getString(1));
                        System.out.println();
                          }

                        stmt.close();
                        con.close();

                    }
                 else if(opt==2){
                                     System.out.println("Enter the cgpa to print list:");
                    Scanner sc4 = new Scanner(System.in);
                    Float r4 = sc4.nextFloat();
                    
                    String query4 = "select dept, roll,sname,cgpa from STUdent where cgpa="+r4;
            
                     ResultSet rs4 = stmt.executeQuery(query4);
                     
                       while(rs4.next()){
                        System.out.print("roll: "+ rs4.getInt(2));
                        System.out.print(" name: "+ rs4.getString("sname"));
                        System.out.print(" cgpa: "+ rs4.getFloat("cgpa"));
                        System.out.print(" dept: "+ rs4.getString(1));
                        System.out.println();
                          }

                        stmt.close();
                        con.close();

                 }
                 else if(opt==3){
                    System.out.println("Enter the Department to print list:");
                    Scanner sc4 = new Scanner(System.in);
                    String r4 = sc4.next();
                    
                    String query4 = "select dept, roll,sname,cgpa from STUdent where dept="+ "'"+r4+"'";
            
                     ResultSet rs4 = stmt.executeQuery(query4);
                     
                       while(rs4.next()){
                        System.out.print("roll: "+ rs4.getInt("roll"));
                        System.out.print(" name: "+ rs4.getString("sname"));
                        System.out.print(" cgpa: "+ rs4.getFloat("cgpa"));
                        System.out.print(" dept: "+ rs4.getString("dept"));
                        System.out.println();
                          }
                        stmt.close();
                        con.close();
                 }
                 
                 else{System.out.println("Invalid option");}

                        break;
       
//insert student
                case 5:
                    System.out.println();
                    System.out.println("Enter student data ==> ");
                    String query6 = "insert into student values(?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(query6);
            
                   
                    System.out.println("enter roll no.:");  

                    Scanner sc12 = new Scanner(System.in);
                    int r = sc12.nextInt();
            
                    System.out.println("enter name:");  
                    Scanner sc13 = new Scanner(System.in);
                    String nam = sc13.nextLine();
            
                    System.out.println("enter CGPA:");  
                    Scanner sc14 = new Scanner(System.in);
                    float cgpa = sc14.nextFloat();
                    DecimalFormat df = new DecimalFormat("#.##");
                    
                    
                    System.out.println("enter department:");  
                    Scanner sc15 = new Scanner(System.in);
                    String dep = sc15.nextLine();

                    ps.setInt(1,r);  
                    ps.setString(2,nam);  
                    ps.setString(3,df.format(cgpa)); 
                    ps.setString(4,dep);
                    int j = ps.executeUpdate();  
                    System.out.println(j+" records affected");   
                    
                break;
                    
default: System.out.println("Invalid option");
            }

        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
