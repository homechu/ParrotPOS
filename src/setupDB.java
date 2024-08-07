 import java.awt.*;
 import javax.swing.*;
 import java.sql.*;

 class Cdbma{               //Cdbma: Class database manipulation and acess (資料庫操作與存取類別)     

     Connection connection;
     Statement statement,statemen,stateme,statem,state,stat,sta;
    
     //建構子:類別Cdbma
     public Cdbma(){
         createDB();            //建立資料庫posdb, 完成後請註解掉不作用,以免重複建立會出錯
         createTB_food();     //建立資料表customer, 完成後請註解掉不作用,以免重複建立會出錯
         createTB_factory();
         createTB_customer();
         createTB_employee();
         createTB_transaction();
         
     } 

     //建立資料庫posdb中的資料表:customer
     public void createTB_food(){

            try{
                 connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb"+"?user=root&password=mysql");
                 statemen = connection.createStatement();

                 String createTB = "CREATE TABLE food(";
                 createTB += "fd_day            VARCHAR(10) , ";    //顧客編號:CM0120151231001
                 createTB += "fd_name          VARCHAR(20), ";                //顧客姓名
                 createTB += "ft_name       VARCHAR(20), ";                //顧客地址    
                 createTB += "fd_num     INT(10), ";                //顧客電話
                 createTB += "fd_mo         INT(10))";                        //顧客級別                 
                                     
                 statemen.executeUpdate(createTB);
                 JOptionPane.showMessageDialog(null,"food資料表建立成功!");
                 statemen.close();
       
            } catch(SQLException e){
                 if(statemen != null) 
                       JOptionPane.showMessageDialog(null,"food資料表已存在,可正常使用!"); 
                 else
                       JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
           } catch(Exception e){
              JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
           }  
     } 
     public void createTB_factory(){

         try{
              connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb"+"?user=root&password=mysql");
              stateme = connection.createStatement();

              String createTB = "CREATE TABLE factory(";
              createTB += "ft_no            VARCHAR(15) , ";    //顧客編號:CM0120151231001
              createTB += "ft_name          VARCHAR(20), ";                //顧客姓名
              createTB += "ft_email       VARCHAR(20), ";                //顧客地址    
              createTB += "ft_address     VARCHAR(30), ";                //顧客電話
              createTB += "ft_fax         VARCHAR(15))";                        //顧客級別                 
                                  
              stateme.executeUpdate(createTB);
              JOptionPane.showMessageDialog(null,"factory資料表建立成功!");
              stateme.close();
    
         } catch(SQLException e){
              if(stateme != null) 
                    JOptionPane.showMessageDialog(null,"factory資料表已存在,可正常使用!"); 
              else
                    JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
        } catch(Exception e){
           JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        }  
  } 
     public void createTB_customer(){

         try{
              connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb"+"?user=root&password=mysql");
              statem = connection.createStatement();

              String createTB = "CREATE TABLE customer(";
              createTB += "cm_no            VARCHAR(10) , ";    //顧客編號:CM0120151231001
              createTB += "cm_name          VARCHAR(20), ";                //顧客姓名
              createTB += "cm_email       VARCHAR(20), ";                //顧客地址    
              createTB += "cm_address     VARCHAR(30), ";                //顧客電話
              createTB += "cm_mo          INT(5))";                        //顧客級別                 
                                  
              statem.executeUpdate(createTB);
              JOptionPane.showMessageDialog(null,"customer資料表建立成功!");
              statem.close();
    
         } catch(SQLException e){
              if(statem != null) 
                    JOptionPane.showMessageDialog(null,"customer資料表已存在,可正常使用!"); 
              else
                    JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
        } catch(Exception e){
           JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        }  
  } 
     public void createTB_employee(){

         try{
              connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb"+"?user=root&password=mysql");
              state = connection.createStatement();

              String createTB = "CREATE TABLE employee(";
              createTB += "ep_no            VARCHAR(10) , ";    //顧客編號:CM0120151231001
              createTB += "ep_name         VARCHAR(20), ";      //顧客姓名
              createTB += "ep_email       VARCHAR(20), ";                //顧客地址    
              createTB += "ep_post     VARCHAR(10), ";                //顧客電話
              createTB += "ep_pass         VARCHAR(10))";                        //顧客級別                 
                                  
              state.executeUpdate(createTB);
              JOptionPane.showMessageDialog(null,"employee資料表建立成功!");
              state.close();
    
         } catch(SQLException e){
              if(state != null) 
                    JOptionPane.showMessageDialog(null,"employee資料表已存在,可正常使用!"); 
              else
                    JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
        } catch(Exception e){
           JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        }  
  } 
     public void createTB_transaction(){

         try{
              connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb"+"?user=root&password=mysql");
              stat = connection.createStatement();

              String createTB = "CREATE TABLE transaction(";
              createTB += "cm_no            VARCHAR(10) , ";    //顧客編號:CM0120151231001
              createTB += "cm_name          VARCHAR(20), ";                //顧客姓名
              createTB += "ts_name       VARCHAR(20), ";                //顧客地址    
              createTB += "ts_num     INT(5), ";                //顧客電話
              createTB += "ts_mo         INT(5))";                        //顧客級別                 
                                  
              stat.executeUpdate(createTB);
              JOptionPane.showMessageDialog(null,"transaction資料表建立成功!");
              stat.close();
              System.exit(0);
    
         } catch(SQLException e){
              if(stat != null) 
                    JOptionPane.showMessageDialog(null,"transaction資料表已存在,可正常使用!"); 
              else
                    JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
        } catch(Exception e){
           JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
        }  
  } 

     //刪除資料庫posdb中的資料表: customer 
     

     //方法:建立資料庫posdb
     public void createDB(){

         //安裝MySQL驅動程式, 與建立資料庫posdb
         try{
              Class.forName("com.mysql.jdbc.Driver");
         } catch(Exception e){
              JOptionPane.showMessageDialog(null,"MySQL驅動程式安裝失敗!"); 
         }

         //建立 posdb資料庫
         try{
              connection = DriverManager.getConnection("jdbc:mysql://localhost/"+
                                                       "?user=root&password=mysql");
              sta = connection.createStatement();
              String createDB = "CREATE DATABASE posdb";
              sta.executeUpdate(createDB);
              JOptionPane.showMessageDialog(null,"posdb資料庫建立成功!");
              sta.close();
              
         } catch(SQLException e){
              if(sta != null) 
                  JOptionPane.showMessageDialog(null,"posdb資料庫已存在,可正常使用!");
              else
                  JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
         } catch(Exception e){
              JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
         }
       
     } //end for: public void createDB()
 } //end for: class Cdbms


 //主程式: setupDBv2.java
 public class setupDB{
    public static void main(String[] args){
    
         Cdbma myDBMS = new Cdbma();

    }
 }