//�d�߭��u
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel; 
import java.sql.*;

class fdepFrame extends JPanel{

     JPanel pane1=new JPanel();
     JPanel pane2=new JPanel();
     JLabel BLabel = new JLabel("�d�߭��u");
     JLabel nLabel = new JLabel("�m�W:");
     JLabel tLabel = new JLabel("�п�J�q��:");
     JLabel emLabel = new JLabel("e-mail:");
     JLabel poLabel = new JLabel("¾��:");
     JTextField nTxtFd = new JTextField("");
     JTextField tTxtFd = new JTextField("");
     JTextField emTxtFd = new JTextField("");
     JTextField poTxtFd = new JTextField("");
     JButton myBtn1=new JButton("�d��");
     JButton myBtn2=new JButton("�M��");
     JButton myBtn3=new JButton("���}");
     Font font1 = new Font("�з���", Font.BOLD, 40);
     Font font2 = new Font("�з���", Font.BOLD, 20);
     Font font3 = new Font("�з���", Font.BOLD, 30);
     Color color = new Color(144,238,144); //�H���
     String epData;

    fdepFrame(){
      pane1.setBounds(0,10,1000,50);
      pane1.setLayout(null);
      add(pane1);
      BLabel.setBounds(300,5,350,35);
      BLabel.setFont(font1);
      pane1.add(BLabel);

      pane2.setBounds(0,40,1000,545);
      pane2.setLayout(null);
      pane2.setBackground(color);
      add(pane2);
      tLabel.setBounds(30,50,200,50);
      tLabel.setFont(font3);
      pane2.add(tLabel);
      nLabel.setBounds(30,120,100,50);
      nLabel.setFont(font2);
      pane2.add(nLabel);
      emLabel.setBounds(30,190,100,50);
      emLabel.setFont(font2);
      pane2.add(emLabel);
      poLabel.setBounds(30,260,100,50);
      poLabel.setFont(font2);
      pane2.add(poLabel);

      tTxtFd.setBounds(220,50,300,50);
      tTxtFd.setFont(font2);
      pane2.add(tTxtFd);
      nTxtFd.setBounds(140,120,300,50);
      nTxtFd.setFont(font2);
      nTxtFd.setEditable(false);   
      pane2.add(nTxtFd);
      emTxtFd.setBounds(140,190,300,50);
      emTxtFd.setFont(font2);
      emTxtFd.setEditable(false);
      pane2.add(emTxtFd);
      poTxtFd.setBounds(140,260,300,50);
      poTxtFd.setFont(font2);
      poTxtFd.setEditable(false);
      pane2.add(poTxtFd);

      myBtn1.setBounds(600,50,150,80);
      myBtn1.setFont(font2);
      myBtn1.addActionListener(ProcessBtnPress);
      pane2.add(myBtn1);
      myBtn2.setBounds(600,170,150,80);
      myBtn2.setFont(font2);
      myBtn2.addActionListener(ProcessBtnPress);
      pane2.add(myBtn2);
      myBtn3.setBounds(600,290,150,80);
      myBtn3.setFont(font2);
      myBtn3.addActionListener(ProcessBtnPress);
      pane2.add(myBtn3);

      
      setLayout(null);
      setLocation(250,110);
      setSize(949,470);
      setVisible(true);
        
      
         
     

   }
   //�d�߭��u�A�qemployee��ƪ�
   public void findRD_in_TB_employee(String epData){

           Connection connection;
           Statement statement;
           ResultSet result;
           String epdData;
           String name="",post="",telephone="",email="",pass="";
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
                    epdData = "SELECT * FROM employee WHERE ep_no ='"+epData+"'";
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                    statement = connection.createStatement();
                    result = statement.executeQuery(epdData);
          
                    while( result.next() ){
                          telephone = result.getString("ep_no");
                          name = result.getString("ep_name");
                          post = result.getString("ep_post");
                          email = result.getString("ep_email");       
                     }
                    nTxtFd.setText(""+name+"");
                    tTxtFd.setText(""+telephone+"");
                    emTxtFd.setText(""+email+"");
                    poTxtFd.setText(""+post+"");
                    statement.close();
             }catch(SQLException e){
                         JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
              }             
                      
    }

    public ActionListener ProcessBtnPress = new ActionListener(){
       public void actionPerformed(ActionEvent e){
    	   
         if(e.getSource() == myBtn3){ 
        	myBtn1.setEnabled(true);
            fdepFrame.this.setVisible(false);  //��������
         }
         if(e.getSource() == myBtn1){
            String epData=tTxtFd.getText();
            findRD_in_TB_employee(epData);
            myBtn1.setEnabled(false); 
          
           
         }
         if(e.getSource() == myBtn2){ 
           nTxtFd.setText("");
           tTxtFd.setText("");
           emTxtFd.setText("");
           poTxtFd.setText("");
           myBtn1.setEnabled(true);
         }
       }
    };
}

 //�D�{��
 public class fdep{
    public static void main(String[] args){
      fdepFrame frame1 = new fdepFrame();

    }
}