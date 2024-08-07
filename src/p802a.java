import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.border.*;

class AAFrame extends JFrame{

JPanel pane = new JPanel();
JLabel myLabel1 = new JLabel("帳號:");
JTextField myTxtFd1 = new JTextField();
JLabel myLabel2 = new JLabel("密碼:");
JTextField myTxtFd2 = new JTextField();
JLabel myLabel3 = new JLabel("櫻舞科技公司後端登入畫面");

JButton myBtn1 = new JButton("登   入");
JButton myBtn2 = new JButton("離   開");
String a;
String b="";


Font font1= new Font("標楷體",Font.BOLD,22);
Font font2= new Font("標楷體",Font.BOLD,42);


AAFrame(){
      
      pane.setBounds(0,0,800,600);
      pane.setBackground(Color.getHSBColor(0.16f,0.09f,1f));
      pane.setLayout(null);
      add(pane);



      myLabel1.setBounds(210,200,150,50);
      myLabel1.setFont(font1);
      pane.add(myLabel1);
      myTxtFd1.setBounds(330,200,250,50);
      pane.add(myTxtFd1);

      myLabel2.setBounds(210,300,100,50);
      myLabel2.setFont(font1);
      pane.add(myLabel2);
      myTxtFd2.setBounds(330,300,250,50);
      pane.add(myTxtFd2);

      myLabel3.setBounds(160,50,600,80);
      myLabel3.setFont(font2);
      myLabel3.setForeground(Color.getHSBColor(0.57f,0.35f,0.62f));
      pane.add(myLabel3);
      
      

      myBtn1.setBounds(200,400,100,50);
      myBtn1.setFont(new Font("微軟正黑體",Font.BOLD,24));
      myBtn1.setBackground(Color.getHSBColor(0.35f,0.32f,0.9f));
      myBtn1.setForeground(Color.white);
      myBtn1.addActionListener(ProcessBtnPress);
      pane.add(myBtn1);

      myBtn2.setBounds(480,400,100,50);
      myBtn2.setFont(new Font("微軟正黑體",Font.BOLD,24));
      myBtn2.setBackground(Color.getHSBColor(1f,0.6f,1.0f));
      myBtn2.setForeground(Color.white);
      myBtn2.addActionListener(ProcessBtnPress);
      pane.add(myBtn2);
      
      





      setTitle("登入畫面");
      setLayout(null);
      setSize(800,600);
      setLocationRelativeTo(null);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
    public ActionListener ProcessBtnPress = new ActionListener(){
     public void actionPerformed(ActionEvent e){
       a=myTxtFd1.getText();
       b=myTxtFd2.getText();
       if(e.getSource() == myBtn2)AAFrame.this.dispose();
  
       if(e.getSource() == myBtn1){
        if (a.length()!=0 && b.length()!=0 ){
        	findRD_in_employee(a,b);
        	
         }
        else{  
         JOptionPane.showMessageDialog(null, "請輸入帳號密碼", "訊息", JOptionPane.ERROR_MESSAGE );
        }
       }
     }
   };
   public void findRD_in_employee(String cmData,String password){

       Connection connection;
       Statement statement;
       ResultSet result;
       String cmdData,cmcDt="";
       String name="",pass="";
       int mo=0;
       
         //資料庫前置作業
         try{
               Class.forName("com.mysql.jdbc.Driver");
         } catch(Exception e){
               JOptionPane.showMessageDialog(null,"MySQL驅動程式安裝失敗!"); 
         }

         try{
               connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
               statement = connection.createStatement();
         } catch(SQLException e){
               JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
         } catch(Exception e){
               JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
         }
        //顯示在畫面中
         try{
                cmdData = "SELECT * FROM employee WHERE ep_name ='"+cmData+"'";
                connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                statement = connection.createStatement();
                result = statement.executeQuery(cmdData);
      
                while( result.next() ){
                      pass = result.getString("ep_pass");
                      name = result.getString("ep_name");
                      
                 }
                           
                if(name!=""){
        	       	if(pass.equals(password)){
                    	BFrame frame1=new BFrame();
                    	AAFrame.this.dispose();
                }else{
                    		 	
                    		JOptionPane.showMessageDialog(null,"密碼錯誤，請重新輸入!");
                    }
                }else{
                	JOptionPane.showMessageDialog(null,"查無此員工，請重新輸入!");
                    }
          
                 statement.close();
         }catch(SQLException e){
                     JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
          }             
                  
         }
   

}

public class p802a{
  public static void main(String[] args){
        
      AAFrame frame1 = new AAFrame();
     }
}


