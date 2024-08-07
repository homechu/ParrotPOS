//查詢廠商
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel; 
import java.sql.*;

class fdftFrame extends JPanel{

     JPanel pane1=new JPanel();
     JPanel pane2=new JPanel();
     JLabel BLabel = new JLabel("查詢廠商");
     JLabel tLabel = new JLabel("電話:");
     JLabel nLabel = new JLabel("廠商名稱:");
     JLabel emLabel = new JLabel("e-mail:");
     JLabel aLabel = new JLabel("聯絡地址:");
     JLabel faxLabel = new JLabel("傳真:");
     //JLabel paLabel = new JLabel("密碼:");
     JTextField nTxtFd = new JTextField("");
     JTextField tTxtFd = new JTextField("");
     JTextField emTxtFd = new JTextField("");
     JTextField aTxtFd = new JTextField("");
     JTextField faxTxtFd = new JTextField("");
    // JTextField paTxtFd = new JTextField("");
     JButton myBtn1=new JButton("查詢");
     JButton myBtn2=new JButton("清除");
     JButton myBtn3=new JButton("離開");
     Font font1 = new Font("標楷體", Font.BOLD, 40);
     Font font2 = new Font("標楷體", Font.BOLD, 20);
     Font font3 = new Font("標楷體", Font.BOLD, 30);
     Color color = new Color(144,238,144); //淡綠色
     String ftData;

    fdftFrame(){
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
      nLabel.setBounds(30,50,200,50);
      nLabel.setFont(font3);
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

      nTxtFd.setBounds(200,50,300,50);
      nTxtFd.setFont(font2);
      pane2.add(nTxtFd);
      tTxtFd.setBounds(140,120,300,50);
      tTxtFd.setFont(font2);
      tTxtFd.setEditable(false);   
      pane2.add(tTxtFd);
      emTxtFd.setBounds(140,190,300,50);
      emTxtFd.setFont(font2);
      emTxtFd.setEditable(false);
      pane2.add(emTxtFd);
      aTxtFd.setBounds(140,260,300,50);
      aTxtFd.setFont(font2);
      aTxtFd.setEditable(false);
      pane2.add(aTxtFd);
      faxTxtFd.setBounds(140,330,300,50);
      faxTxtFd.setFont(font2);
      faxTxtFd.setEditable(false);
      pane2.add(faxTxtFd);

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
      setLocation(100,120);
      setSize(800,600);
      setVisible(true);
       
      
         
     

   }
   //查詢員工，從factory資料表
   public void findRD_in_TB_factory(String ftData){

           Connection connection;
           Statement statement;
           ResultSet result;
           String ftdData;
           String name="",telephone="",email="",address="",fax="";
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
                    ftdData = "SELECT * FROM factory WHERE ft_name ='"+ftData+"'";
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                    statement = connection.createStatement();
                    result = statement.executeQuery(ftdData);
          
                    while( result.next() ){
                          telephone = result.getString("ft_no");
                          name = result.getString("ft_name");
                          address = result.getString("ft_address");
                          email = result.getString("ft_email");
                          fax = result.getString("ft_fax");
                     }
                    nTxtFd.setText(""+name+"");
                    tTxtFd.setText(""+telephone+"");
                    emTxtFd.setText(""+email+"");
                    aTxtFd.setText(""+address+"");
                    faxTxtFd.setText(""+fax+"");
                     statement.close();
             }catch(SQLException e){
                         JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
              }             
                      
    }

    public ActionListener ProcessBtnPress = new ActionListener(){
       public void actionPerformed(ActionEvent e){
    	   
         if(e.getSource() == myBtn3){ 
        	myBtn1.setEnabled(true);
            fdftFrame.this.setVisible(false);  //關閉視窗
         }
         if(e.getSource() == myBtn1){
            String ftData=nTxtFd.getText();
            findRD_in_TB_factory(ftData);
            myBtn1.setEnabled(false); 
          
           
         }
         if(e.getSource() == myBtn2){ 
           tTxtFd.setText("");
           nTxtFd.setText("");
           emTxtFd.setText("");
           aTxtFd.setText("");
           faxTxtFd.setText("");
           myBtn1.setEnabled(true);
         }
       }
    };
}

 //主程式
 public class fdft{
    public static void main(String[] args){
      fdftFrame frame1 = new fdftFrame();

    }
}