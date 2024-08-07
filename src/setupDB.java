 import java.awt.*;
 import javax.swing.*;
 import java.sql.*;

 class Cdbma{               //Cdbma: Class database manipulation and acess (��Ʈw�ާ@�P�s�����O)     

     Connection connection;
     Statement statement,statemen,stateme,statem,state,stat,sta;
    
     //�غc�l:���OCdbma
     public Cdbma(){
         createDB();            //�إ߸�Ʈwposdb, ������е��ѱ����@��,�H�K���ƫإ߷|�X��
         createTB_food();     //�إ߸�ƪ�customer, ������е��ѱ����@��,�H�K���ƫإ߷|�X��
         createTB_factory();
         createTB_customer();
         createTB_employee();
         createTB_transaction();
         
     } 

     //�إ߸�Ʈwposdb������ƪ�:customer
     public void createTB_food(){

            try{
                 connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb"+"?user=root&password=mysql");
                 statemen = connection.createStatement();

                 String createTB = "CREATE TABLE food(";
                 createTB += "fd_day            VARCHAR(10) , ";    //�U�Ƚs��:CM0120151231001
                 createTB += "fd_name          VARCHAR(20), ";                //�U�ȩm�W
                 createTB += "ft_name       VARCHAR(20), ";                //�U�Ȧa�}    
                 createTB += "fd_num     INT(10), ";                //�U�ȹq��
                 createTB += "fd_mo         INT(10))";                        //�U�ȯŧO                 
                                     
                 statemen.executeUpdate(createTB);
                 JOptionPane.showMessageDialog(null,"food��ƪ�إߦ��\!");
                 statemen.close();
       
            } catch(SQLException e){
                 if(statemen != null) 
                       JOptionPane.showMessageDialog(null,"food��ƪ�w�s�b,�i���`�ϥ�!"); 
                 else
                       JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
           } catch(Exception e){
              JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
           }  
     } 
     public void createTB_factory(){

         try{
              connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb"+"?user=root&password=mysql");
              stateme = connection.createStatement();

              String createTB = "CREATE TABLE factory(";
              createTB += "ft_no            VARCHAR(15) , ";    //�U�Ƚs��:CM0120151231001
              createTB += "ft_name          VARCHAR(20), ";                //�U�ȩm�W
              createTB += "ft_email       VARCHAR(20), ";                //�U�Ȧa�}    
              createTB += "ft_address     VARCHAR(30), ";                //�U�ȹq��
              createTB += "ft_fax         VARCHAR(15))";                        //�U�ȯŧO                 
                                  
              stateme.executeUpdate(createTB);
              JOptionPane.showMessageDialog(null,"factory��ƪ�إߦ��\!");
              stateme.close();
    
         } catch(SQLException e){
              if(stateme != null) 
                    JOptionPane.showMessageDialog(null,"factory��ƪ�w�s�b,�i���`�ϥ�!"); 
              else
                    JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
        } catch(Exception e){
           JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        }  
  } 
     public void createTB_customer(){

         try{
              connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb"+"?user=root&password=mysql");
              statem = connection.createStatement();

              String createTB = "CREATE TABLE customer(";
              createTB += "cm_no            VARCHAR(10) , ";    //�U�Ƚs��:CM0120151231001
              createTB += "cm_name          VARCHAR(20), ";                //�U�ȩm�W
              createTB += "cm_email       VARCHAR(20), ";                //�U�Ȧa�}    
              createTB += "cm_address     VARCHAR(30), ";                //�U�ȹq��
              createTB += "cm_mo          INT(5))";                        //�U�ȯŧO                 
                                  
              statem.executeUpdate(createTB);
              JOptionPane.showMessageDialog(null,"customer��ƪ�إߦ��\!");
              statem.close();
    
         } catch(SQLException e){
              if(statem != null) 
                    JOptionPane.showMessageDialog(null,"customer��ƪ�w�s�b,�i���`�ϥ�!"); 
              else
                    JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
        } catch(Exception e){
           JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        }  
  } 
     public void createTB_employee(){

         try{
              connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb"+"?user=root&password=mysql");
              state = connection.createStatement();

              String createTB = "CREATE TABLE employee(";
              createTB += "ep_no            VARCHAR(10) , ";    //�U�Ƚs��:CM0120151231001
              createTB += "ep_name         VARCHAR(20), ";      //�U�ȩm�W
              createTB += "ep_email       VARCHAR(20), ";                //�U�Ȧa�}    
              createTB += "ep_post     VARCHAR(10), ";                //�U�ȹq��
              createTB += "ep_pass         VARCHAR(10))";                        //�U�ȯŧO                 
                                  
              state.executeUpdate(createTB);
              JOptionPane.showMessageDialog(null,"employee��ƪ�إߦ��\!");
              state.close();
    
         } catch(SQLException e){
              if(state != null) 
                    JOptionPane.showMessageDialog(null,"employee��ƪ�w�s�b,�i���`�ϥ�!"); 
              else
                    JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
        } catch(Exception e){
           JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        }  
  } 
     public void createTB_transaction(){

         try{
              connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb"+"?user=root&password=mysql");
              stat = connection.createStatement();

              String createTB = "CREATE TABLE transaction(";
              createTB += "cm_no            VARCHAR(10) , ";    //�U�Ƚs��:CM0120151231001
              createTB += "cm_name          VARCHAR(20), ";                //�U�ȩm�W
              createTB += "ts_name       VARCHAR(20), ";                //�U�Ȧa�}    
              createTB += "ts_num     INT(5), ";                //�U�ȹq��
              createTB += "ts_mo         INT(5))";                        //�U�ȯŧO                 
                                  
              stat.executeUpdate(createTB);
              JOptionPane.showMessageDialog(null,"transaction��ƪ�إߦ��\!");
              stat.close();
              System.exit(0);
    
         } catch(SQLException e){
              if(stat != null) 
                    JOptionPane.showMessageDialog(null,"transaction��ƪ�w�s�b,�i���`�ϥ�!"); 
              else
                    JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
        } catch(Exception e){
           JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
        }  
  } 

     //�R����Ʈwposdb������ƪ�: customer 
     

     //��k:�إ߸�Ʈwposdb
     public void createDB(){

         //�w��MySQL�X�ʵ{��, �P�إ߸�Ʈwposdb
         try{
              Class.forName("com.mysql.jdbc.Driver");
         } catch(Exception e){
              JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!"); 
         }

         //�إ� posdb��Ʈw
         try{
              connection = DriverManager.getConnection("jdbc:mysql://localhost/"+
                                                       "?user=root&password=mysql");
              sta = connection.createStatement();
              String createDB = "CREATE DATABASE posdb";
              sta.executeUpdate(createDB);
              JOptionPane.showMessageDialog(null,"posdb��Ʈw�إߦ��\!");
              sta.close();
              
         } catch(SQLException e){
              if(sta != null) 
                  JOptionPane.showMessageDialog(null,"posdb��Ʈw�w�s�b,�i���`�ϥ�!");
              else
                  JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
         } catch(Exception e){
              JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
         }
       
     } //end for: public void createDB()
 } //end for: class Cdbms


 //�D�{��: setupDBv2.java
 public class setupDB{
    public static void main(String[] args){
    
         Cdbma myDBMS = new Cdbma();

    }
 }