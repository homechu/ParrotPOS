//�s�W���u
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel; 
import java.sql.*;

 
class inepFrame extends JPanel{

     JPanel pane1=new JPanel();
     JPanel pane2=new JPanel();
     JLabel BLabel = new JLabel("�s�W���u");
     JLabel nLabel = new JLabel("�m�W:");
     JLabel tLabel = new JLabel("�q��:");
     JLabel emLabel = new JLabel("e-mail:");
     JLabel poLabel = new JLabel("¾��:");
     JLabel paLabel = new JLabel("�K�X:");
     JTextField nTxtFd = new JTextField("");
     JTextField tTxtFd = new JTextField("");
     JTextField emTxtFd = new JTextField("");
     JTextField poTxtFd = new JTextField("");
     JTextField paTxtFd = new JTextField("");
     JButton myBtn1=new JButton("�s�W");
     JButton myBtn2=new JButton("�M��");
     JButton myBtn3=new JButton("���}");
     Font font1 = new Font("�з���", Font.BOLD, 40);
     Font font2 = new Font("�з���", Font.BOLD, 20);
     Color color = new Color(135,206,235);  //���Ŧ�
     
     
    //�غc�l
    inepFrame(){
     
     pane1.setBounds(0,10,1000,50);
     pane1.setLayout(null);
     add(pane1);
     BLabel.setBounds(300,5,350,35);
     BLabel.setFont(font1);
     pane1.add(BLabel);

     pane2.setBounds(0,40,2000,545);
     pane2.setLayout(null);
     pane2.setBackground(color);
     add(pane2);
     nLabel.setBounds(30,50,100,50);
     nLabel.setFont(font2);
     pane2.add(nLabel);
     tLabel.setBounds(30,120,100,50);
     tLabel.setFont(font2);
     pane2.add(tLabel);
     emLabel.setBounds(30,190,100,50);
     emLabel.setFont(font2);
     pane2.add(emLabel);
     poLabel.setBounds(30,260,100,50);
     poLabel.setFont(font2);
     pane2.add(poLabel);
     paLabel.setBounds(30,330,100,50);
     paLabel.setFont(font2);
     pane2.add(paLabel);

     nTxtFd.setBounds(140,50,300,50);
     nTxtFd.setFont(font2);
     pane2.add(nTxtFd);
     tTxtFd.setBounds(140,120,300,50);
     tTxtFd.setFont(font2);
     pane2.add(tTxtFd);
     emTxtFd.setBounds(140,190,300,50);
     emTxtFd.setFont(font2);
     pane2.add(emTxtFd);
     poTxtFd.setBounds(140,260,300,50);
     poTxtFd.setFont(font2);
     pane2.add(poTxtFd);
     paTxtFd.setBounds(140,330,300,50);
     paTxtFd.setFont(font2);
     pane2.add(paTxtFd);

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
     setVisible(false);
    
    }
  
     //�ǤJ���㪺�@���U�ȸ�Ʀr��}�C(epData),�M��N���U�ȸ�ƾA���ഫ��,�s�J��Ʈw��employee��ƪ�
       public void insertRD_into_TB_employee(String[] epData){

             Connection connection;
             Statement statement;
             String epdData;
            
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

             //�bposdb��Ʈw��, �s�W�@���U�ȸ�ƨ��ƪ�: employee   
             try{  
                    epdData = "INSERT INTO employee(ep_no,ep_name,ep_email,ep_post,ep_pass)"+
                                            "VALUES('"+epData[0]+"','"+epData[1]+"','"+epData[2]+"','"+epData[3]+"','"+epData[4]+"')";

                    connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                    statement = connection.createStatement();
                    statement.executeUpdate(epdData);
                    JOptionPane.showMessageDialog(null,"�bposdb��Ʈw��, ���\�g�J�@��[���u�O��]��employee��ƪ�!");
                    statement.close();

             } catch(SQLException e){
                    JOptionPane.showMessageDialog(null,"�bposdb��Ʈw��, �g�J�@��[���u�O��]��employee��ƪ��o�Ϳ��~!");
             }
        } 
    

    public ActionListener ProcessBtnPress = new ActionListener(){
       public void actionPerformed(ActionEvent e){
    	   
         if(e.getSource() == myBtn3){ 
        	 inepFrame.this.setVisible(false);  //��������
         }
         if(e.getSource() == myBtn1){ 
           String[] epData = {tTxtFd.getText(),nTxtFd.getText(),emTxtFd.getText(), poTxtFd.getText(),paTxtFd.getText()};
           insertRD_into_TB_employee(epData);
           nTxtFd.setText("");
           tTxtFd.setText("");
           emTxtFd.setText("");
           poTxtFd.setText("");
           paTxtFd.setText("");
         }
         if(e.getSource() == myBtn2){ 
           nTxtFd.setText("");
           tTxtFd.setText("");
           emTxtFd.setText("");
           poTxtFd.setText("");
           paTxtFd.setText("");
         }
       }
    };

 }
//�D�{��
 public class inep{
   public static void main(String[] args){

        inepFrame Panel2 =new inepFrame();
        
   }
 } 
