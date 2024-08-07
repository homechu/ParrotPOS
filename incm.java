//新增會員
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel; 
import java.sql.*;

 
class incmFrame extends JFrame{

     JPanel pane1=new JPanel();
     JPanel pane2=new JPanel();
     JLabel BLabel = new JLabel("新增會員");
     JLabel nLabel = new JLabel("姓名:");
     JLabel tLabel = new JLabel("電話:");
     JLabel emLabel = new JLabel("e-mail:");
     JLabel aLabel = new JLabel("地址:");
     JLabel mLabel = new JLabel("儲值金額:");
     JTextField nTxtFd = new JTextField("");
     JTextField tTxtFd = new JTextField("");
     JTextField emTxtFd = new JTextField("");
     JTextField aTxtFd = new JTextField("");
     JTextField mTxtFd = new JTextField("");
     JButton myBtn1=new JButton("新增");
     JButton myBtn2=new JButton("清除");
     JButton myBtn3=new JButton("離開");
     Font font1 = new Font("標楷體", Font.BOLD, 40);
     Font font2 = new Font("標楷體", Font.BOLD, 20);
     Color color = new Color(135,206,235);  //天藍色
     
    
     //建構子
    incmFrame(){
     
     pane1.setBounds(0,0,800,50);
     pane1.setLayout(null);
     add(pane1);
     BLabel.setBounds(300,5,350,35);
     BLabel.setFont(font1);
     pane1.add(BLabel);

     pane2.setBounds(0,50,800,545);
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
     mLabel.setBounds(30,330,100,50);
     mLabel.setFont(font2);
     pane2.add(mLabel);

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
     mTxtFd.setBounds(140,330,300,50);
     mTxtFd.setFont(font2);
     pane2.add(mTxtFd);

     myBtn1.setBounds(50,420,150,80);
     myBtn1.setFont(font2);
     myBtn1.addActionListener(ProcessBtnPress);
     pane2.add(myBtn1);
     myBtn2.setBounds(250,420,150,80);
     myBtn2.setFont(font2);
     myBtn2.addActionListener(ProcessBtnPress);
     pane2.add(myBtn2);
     myBtn3.setBounds(600,420,150,80);
     myBtn3.setFont(font2);
     myBtn3.addActionListener(ProcessBtnPress);
     pane2.add(myBtn3);

     setTitle("新增會員");
     setLayout(null);
     setLocation(100,120);
     setSize(800,600);
     setVisible(true);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }

  
     //傳入完整的一筆顧客資料字串陣列(cmData),然後將此顧客資料適當轉換後,存入資料庫的customer資料表中
       public void insertRD_into_TB_customer(String[] cmData){

             Connection connection;
             Statement statement;
             String cmdData;
            
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

             //在posdb資料庫中, 新增一筆顧客資料到資料表: customer   
             try{  
                    cmdData = "INSERT INTO customer(cm_no,cm_name,cm_email,cm_address,cm_mo)"+
                                            "VALUES('"+cmData[0]+"','"+cmData[1]+"','"+cmData[2]+"','"+cmData[3]+"',"+Integer.parseInt(cmData[4])+")";

                    connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                    statement = connection.createStatement();
                    statement.executeUpdate(cmdData);
                    JOptionPane.showMessageDialog(null,"在posdb資料庫中, 成功寫入一筆[顧客記錄]到customer資料表中!");
                    statement.close();

             } catch(SQLException e){
                    JOptionPane.showMessageDialog(null,"在posdb資料庫中, 寫入一筆[顧客記錄]到customer資料表中發生錯誤!");
             }
        } 
    

    public ActionListener ProcessBtnPress = new ActionListener(){
       public void actionPerformed(ActionEvent e){
    	   
         if(e.getSource() == myBtn3){ 
        	 incmFrame.this.dispose();  //關閉視窗
         }
         if(e.getSource() == myBtn1){ 
           String[] cmData = {tTxtFd.getText(),nTxtFd.getText(),emTxtFd.getText(), aTxtFd.getText(),mTxtFd.getText()};
           insertRD_into_TB_customer(cmData); 
           nTxtFd.setText("");
           tTxtFd.setText("");
           emTxtFd.setText("");
           aTxtFd.setText("");
           mTxtFd.setText("");
         }
         if(e.getSource() == myBtn2){ 
           nTxtFd.setText("");
           tTxtFd.setText("");
           emTxtFd.setText("");
           aTxtFd.setText("");
           mTxtFd.setText("");
         }
       }
    };

 }
//主程式
 public class incm{
   public static void main(String[] args){

        incmFrame frame1=new incmFrame();
        
   }
}