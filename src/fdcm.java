//�d�߷|��
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel; 
import java.sql.*;

class fdcmFrame extends JPanel{

     JPanel pane1=new JPanel();
     JPanel pane2=new JPanel();
     JLabel BLabel = new JLabel("�d�߷|��");
     JLabel nLabel = new JLabel("�m�W:");
     JLabel tLabel = new JLabel("�q��:");
     JLabel emLabel = new JLabel("e-mail:");
     JLabel aLabel = new JLabel("�a�}:");
     JLabel mLabel = new JLabel("�x�Ȫ��B:");
     JTextField nTxtFd = new JTextField("");
     JTextField tTxtFd = new JTextField("");
     JTextField emTxtFd = new JTextField("");
     JTextField aTxtFd = new JTextField("");
     JTextField mTxtFd = new JTextField("");
     JButton myBtn1=new JButton("�d��");
     JButton myBtn2=new JButton("�M��");
     JButton myBtn3=new JButton("���}");
     Font font1 = new Font("�з���", Font.BOLD, 40);
     Font font2 = new Font("�з���", Font.BOLD, 20);
     Font font3 = new Font("�з���", Font.BOLD, 30);
     Color color = new Color(144,238,144); //�H���
     String cmData;

    fdcmFrame(){
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
      aLabel.setBounds(30,260,100,50);
      aLabel.setFont(font2);
      pane2.add(aLabel);
      mLabel.setBounds(30,330,100,50);
      mLabel.setFont(font2);
      pane2.add(mLabel);

      tTxtFd.setBounds(140,50,300,50);
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
      aTxtFd.setBounds(140,260,300,50);
      aTxtFd.setFont(font2);
      aTxtFd.setEditable(false);
      pane2.add(aTxtFd);
      mTxtFd.setBounds(140,330,300,50);
      mTxtFd.setFont(font2);
      mTxtFd.setEditable(false);
      pane2.add(mTxtFd);

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

   public  void findRD_in_TB_customer(String cmData){

           Connection connection;
           Statement statement;
           ResultSet result;
           String cmdData;
           String name="",address="",telephone="",email="";
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
                    cmdData = "SELECT * FROM customer WHERE cm_no ='"+cmData+"'";
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                    statement = connection.createStatement();
                    result = statement.executeQuery(cmdData);
          
                    while( result.next() ){
                          telephone = result.getString("cm_no");
                          name = result.getString("cm_name");
                          address = result.getString("cm_address");
                          email = result.getString("cm_email");
                          mo = result.getInt("cm_mo");
                     }
                    nTxtFd.setText(""+name+"");
                    tTxtFd.setText(""+telephone+"");
                    emTxtFd.setText(""+email+"");
                    aTxtFd.setText(""+address+"");
                    mTxtFd.setText(""+mo+"");
                     statement.close();
             }catch(SQLException e){
                         JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
              }             
                      
    }

    public ActionListener ProcessBtnPress = new ActionListener(){
       public void actionPerformed(ActionEvent e){
    	   
         if(e.getSource() == myBtn3){ 
        	myBtn1.setEnabled(true);
            fdcmFrame.this.setVisible(false);  //��������
         }
         if(e.getSource() == myBtn1){
            String cmData=tTxtFd.getText();
            findRD_in_TB_customer(cmData);
            myBtn1.setEnabled(false); 
          
           
         }
         if(e.getSource() == myBtn2){ 
           nTxtFd.setText("");
           tTxtFd.setText("");
           emTxtFd.setText("");
           aTxtFd.setText("");
           mTxtFd.setText("");
           myBtn1.setEnabled(true);
         }
       }
    };
}

 //�D�{��
 public class fdcm{
    public static void main(String[] args){
      fdcmFrame frame1 = new fdcmFrame();

    }
}