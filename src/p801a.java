import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.border.*;

class AFrame extends JFrame{

JPanel pane1 = new JPanel();
JPanel pane2 = new JPanel();
JLabel myLabel1 = new JLabel("電話號碼:");
JTextField myTxtFd1 = new JTextField();
JLabel myLabel3 = new JLabel("會員登入");

JButton myBtn1 = new JButton("登   入");
JButton myBtn2 = new JButton("離   開");
JButton[][] myBtn3 = new JButton[4][3];
String[][] myBtnString = { {"1","2","3"},{"4","5","6"},{"7","8","9"},{"0"," ←","清除"}};
String tel="0912345678";

String a;



Font font1= new Font("標楷體",Font.BOLD,22);
Font font2= new Font("標楷體",Font.BOLD,36);


AFrame(){
      
      pane1.setBounds(0,0,800,600);
      pane1.setBackground(Color.getHSBColor(0.12f,0.3f,1.0f));
      pane1.setLayout(null);
      add(pane1);
      
      pane2.setBounds(270,180,300,300);
      pane2.setBackground(Color.getHSBColor(0.12f,0.3f,1.0f));
      pane2.setLayout(null);
      pane2.setVisible(true);
      pane1.add(pane2);
      pane2.setLayout( new GridLayout(4,3,2,2));
      for(int x=0; x<myBtn3.length;x++){
             for(int y=0; y<myBtn3[0].length; y++){
                      myBtn3[x][y] = new JButton(myBtnString[x][y]);
                      myBtn3[x][y].setBackground(Color.getHSBColor(0.12f,0.49f,0.81f));
                      myBtn3[x][y].addActionListener(ProcessBtnPress);
                      myBtn3[x][y].setFont(font1);
                      myBtn3[x][y].setForeground(Color.white);
                      pane2.add(myBtn3[x][y]);
                }
            }



      myLabel1.setBounds(210,100,150,50);
      myLabel1.setFont(font1);
      pane1.add(myLabel1);
      myTxtFd1.setBounds(350,100,250,50);
      pane1.add(myTxtFd1);

      

      myLabel3.setBounds(325,20,600,50);
      myLabel3.setFont(font2);
      myLabel3.setForeground(Color.getHSBColor(0.62f,0.41f,0.7f));
      pane1.add(myLabel3);
      
      

      myBtn1.setBounds(200,500,100,50);
      myBtn1.setFont(new Font("微軟正黑體",Font.BOLD,24));
      myBtn1.setBackground(Color.getHSBColor(0.35f,0.32f,0.9f));
      myBtn1.setForeground(Color.white);
      myBtn1.addActionListener(ProcessBtnPress);
      pane1.add(myBtn1);

      myBtn2.setBounds(530,500,100,50);
      myBtn2.setFont(new Font("微軟正黑體",Font.BOLD,24));
      myBtn2.setBackground(Color.getHSBColor(1f,0.6f,1.0f));
      myBtn2.setForeground(Color.white);
      myBtn2.addActionListener(ProcessBtnPress);
      pane1.add(myBtn2);
      
      





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
       
       if(e.getSource() == myBtn2)AFrame.this.dispose();
  
       if(e.getSource() == myBtn1){
       	    	   
          if (a.length() == 10){
    	    findRD_in_TB_customer(a);
    	    }
        else{  
        	JOptionPane.showMessageDialog(null,"請輸入9碼號碼，請填寫後再查詢!","操作警訊",JOptionPane.ERROR_MESSAGE);
        }
         }
       
          if(e.getSource() == myBtn3[3][0]){
                         
                         myTxtFd1.setText(a+myBtnString[3][0]);
                         
                         }
          if(e.getSource() == myBtn3[0][0]){
                         
                         myTxtFd1.setText(a+myBtnString[0][0]);
                         
                         }
          if(e.getSource() == myBtn3[0][1]){
                         
                         myTxtFd1.setText(a+myBtnString[0][1]);
                         
                         }
          if(e.getSource() == myBtn3[0][2]){
                         
                         myTxtFd1.setText(a+myBtnString[0][2]);
                         
                         } 
          if(e.getSource() == myBtn3[1][0]){
                         
                         myTxtFd1.setText(a+myBtnString[1][0]);
                         
                         }
          if(e.getSource() == myBtn3[1][1]){
                         
                         myTxtFd1.setText(a+myBtnString[1][1]);
                         
                         }
          if(e.getSource() == myBtn3[1][2]){
                         
                         myTxtFd1.setText(a+myBtnString[1][2]);
                         
                         }
          if(e.getSource() == myBtn3[2][0]){
                         
                         myTxtFd1.setText(a+myBtnString[2][0]);
                         
                         } 
          if(e.getSource() == myBtn3[2][1]){
                         
                         myTxtFd1.setText(a+myBtnString[2][1]);
                         
                         }
          if(e.getSource() == myBtn3[2][2]){
                         
                         myTxtFd1.setText(a+myBtnString[2][2]);
                         
                         }
          if(e.getSource() == myBtn3[3][1]){
                         
                         int single2 = Integer.valueOf(a).intValue();
                         int single3 = single2/10;
                         String last = String.valueOf(single3);
                         myTxtFd1.setText(0+last);
                         if(last.equals("0")){
                          myTxtFd1.setText("0");
                         }
                         }
         
                      
          if(e.getSource() == myBtn3[3][2]){
                         
                         myTxtFd1.setText("");
                         
                         }    


       }
      
     };
     
   
   public  void findRD_in_TB_customer(String cmData){

       Connection connection;
       Statement statement;
       ResultSet result;
       String cmdData;
       String name="",address="",telephone="",email="";
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
                cmdData = "SELECT * FROM customer WHERE cm_no ='"+cmData+"'";
                connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                statement = connection.createStatement();
                result = statement.executeQuery(cmdData);
      
                while( result.next() ){
                      telephone = result.getString("cm_no");
                      name = result.getString("cm_name");
                      mo = result.getInt("cm_mo");
                 }
                
                if(name!=""){
                	CFrame.number.setText(""+name+"");
                    CFrame.last.setText(""+telephone+"");
                    CFrame.porint.setText(""+mo+"");
                	AFrame.this.dispose();
                	}
                else {JOptionPane.showMessageDialog(null,"查無此會員，請重新查詢!");}
                 statement.close();
         }catch(SQLException e){
                     JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
          }             
                  
}

};
public class p801a{
  public static void main(String[] args){
        
      AFrame frame1 = new AFrame();
     }
}


