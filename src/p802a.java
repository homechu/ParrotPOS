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
JLabel myLabel1 = new JLabel("�b��:");
JTextField myTxtFd1 = new JTextField();
JLabel myLabel2 = new JLabel("�K�X:");
JTextField myTxtFd2 = new JTextField();
JLabel myLabel3 = new JLabel("��R��ޤ��q��ݵn�J�e��");

JButton myBtn1 = new JButton("�n   �J");
JButton myBtn2 = new JButton("��   �}");
String a;
String b="";


Font font1= new Font("�з���",Font.BOLD,22);
Font font2= new Font("�з���",Font.BOLD,42);


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
      myBtn1.setFont(new Font("�L�n������",Font.BOLD,24));
      myBtn1.setBackground(Color.getHSBColor(0.35f,0.32f,0.9f));
      myBtn1.setForeground(Color.white);
      myBtn1.addActionListener(ProcessBtnPress);
      pane.add(myBtn1);

      myBtn2.setBounds(480,400,100,50);
      myBtn2.setFont(new Font("�L�n������",Font.BOLD,24));
      myBtn2.setBackground(Color.getHSBColor(1f,0.6f,1.0f));
      myBtn2.setForeground(Color.white);
      myBtn2.addActionListener(ProcessBtnPress);
      pane.add(myBtn2);
      
      





      setTitle("�n�J�e��");
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
         JOptionPane.showMessageDialog(null, "�п�J�b���K�X", "�T��", JOptionPane.ERROR_MESSAGE );
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
       
         //��Ʈw�e�m�@�~
         try{
               Class.forName("com.mysql.jdbc.Driver");
         } catch(Exception e){
               JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!"); 
         }

         try{
               connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
               statement = connection.createStatement();
         } catch(SQLException e){
               JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
         } catch(Exception e){
               JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
         }
        //��ܦb�e����
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
                    		 	
                    		JOptionPane.showMessageDialog(null,"�K�X���~�A�Э��s��J!");
                    }
                }else{
                	JOptionPane.showMessageDialog(null,"�d�L�����u�A�Э��s��J!");
                    }
          
                 statement.close();
         }catch(SQLException e){
                     JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
          }             
                  
         }
   

}

public class p802a{
  public static void main(String[] args){
        
      AAFrame frame1 = new AAFrame();
     }
}


