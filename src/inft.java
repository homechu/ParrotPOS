//�s�W�t��
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel; 
import java.sql.*;

 
class inftFrame extends JPanel{

     JPanel pane1=new JPanel();
     JPanel pane2=new JPanel();
     JLabel BLabel = new JLabel("�s�W�t��");
     JLabel nLabel = new JLabel("�t�ӦW��:");
     JLabel tLabel = new JLabel("�q��:");
     JLabel emLabel = new JLabel("e-mail:");
     JLabel aLabel = new JLabel("�p���a�}:");
     JLabel faxLabel = new JLabel("�ǯu:");
     JTextField nTxtFd = new JTextField("");
     JTextField tTxtFd = new JTextField("");
     JTextField emTxtFd = new JTextField("");
     JTextField aTxtFd = new JTextField("");
     JTextField faxTxtFd = new JTextField("");
     JButton myBtn1=new JButton("�s�W");
     JButton myBtn2=new JButton("�M��");
     JButton myBtn3=new JButton("���}");
     Font font1 = new Font("�з���", Font.BOLD, 40);
     Font font2 = new Font("�з���", Font.BOLD, 20);
     Color color = new Color(135,206,235);  //���Ŧ�
     
     
    //�غc�l
    inftFrame(){
     
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
     nLabel.setBounds(30,50,100,50);
     nLabel.setFont(font2);
     pane2.add(nLabel);
     tLabel.setBounds(30,120,100,50);
     tLabel.setFont(font2);
     pane2.add(tLabel);
     emLabel.setBounds(30,190,100,50);
     emLabel.setFont(font2);
     pane2.add(emLabel);
     aLabel.setBounds(30,260,100,50);
     aLabel.setFont(font2);
     pane2.add(aLabel);
     faxLabel.setBounds(30,330,100,50);
     faxLabel.setFont(font2);
     pane2.add(faxLabel);

     nTxtFd.setBounds(140,50,300,50);
     nTxtFd.setFont(font2);
     pane2.add(nTxtFd);
     tTxtFd.setBounds(140,120,300,50);
     tTxtFd.setFont(font2);
     pane2.add(tTxtFd);
     emTxtFd.setBounds(140,190,300,50);
     emTxtFd.setFont(font2);
     pane2.add(emTxtFd);
     aTxtFd.setBounds(140,260,300,50);
     aTxtFd.setFont(font2);
     pane2.add(aTxtFd);
     faxTxtFd.setBounds(140,330,300,50);
     faxTxtFd.setFont(font2);
     pane2.add(faxTxtFd);

     myBtn1.setBounds(750,50,150,80);
     myBtn1.setFont(font2);
     myBtn1.addActionListener(ProcessBtnPress);
     pane2.add(myBtn1);
     myBtn2.setBounds(750,170,150,80);
     myBtn2.setFont(font2);
     myBtn2.addActionListener(ProcessBtnPress);
     pane2.add(myBtn2);
     myBtn3.setBounds(750,290,150,80);
     myBtn3.setFont(font2);
     myBtn3.addActionListener(ProcessBtnPress);
     pane2.add(myBtn3);

     
     setLayout(null);
     setLocation(100,120);
     setSize(800,600);
     setVisible(true);
    
     }

  
     //�ǤJ���㪺�@���U�ȸ�Ʀr��}�C(ftData),�M��N���U�ȸ�ƾA���ഫ��,�s�J��Ʈw��factory��ƪ�
       public void insertRD_into_TB_factory(String[] ftData){

             Connection connection;
             Statement statement;
             String ftdData;
            
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

             //�bposdb��Ʈw��, �s�W�@���U�ȸ�ƨ��ƪ�: factory  
             try{  
                    ftdData = "INSERT INTO factory(ft_no,ft_name,ft_email,ft_address,ft_fax)"+
                                            "VALUES('"+ftData[0]+"','"+ftData[1]+"','"+ftData[2]+"','"+ftData[3]+"','"+(ftData[4])+"')";

                    connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                    statement = connection.createStatement();
                    statement.executeUpdate(ftdData);
                    JOptionPane.showMessageDialog(null,"�bposdb��Ʈw��, ���\�g�J�@��[�t�ӰO��]��factory��ƪ�!");
                    statement.close();

             } catch(SQLException e){
                    JOptionPane.showMessageDialog(null,"�bposdb��Ʈw��, �g�J�@��[�t�ӰO��]��factory��ƪ��o�Ϳ��~!");
             }
        } 
    

    public ActionListener ProcessBtnPress = new ActionListener(){
       public void actionPerformed(ActionEvent e){
    	   
         if(e.getSource() == myBtn3){ 
        	 inftFrame.this.setVisible(false);  //��������
         }
         if(e.getSource() == myBtn1){ 
           String[] ftData = {tTxtFd.getText(),nTxtFd.getText(),emTxtFd.getText(), aTxtFd.getText(),faxTxtFd.getText()};
           insertRD_into_TB_factory(ftData);
           nTxtFd.setText("");
           tTxtFd.setText("");
           emTxtFd.setText("");
           aTxtFd.setText("");
           faxTxtFd.setText("");
         }
         if(e.getSource() == myBtn2){ 
           nTxtFd.setText("");
           tTxtFd.setText("");
           emTxtFd.setText("");
           aTxtFd.setText("");
           faxTxtFd.setText("");
         }
       }
    };

 }
//�D�{��
 public class inft{
   public static void main(String[] args){

        inftFrame frame1=new inftFrame();
        
   }
 } 
