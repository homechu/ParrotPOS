//查詢存貨
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel; 
import java.sql.*;

class fdfdFrame extends JPanel{

     JPanel pane1=new JPanel();
     JPanel pane2=new JPanel();
     JLabel BLabel = new JLabel("查詢存貨");
     JLabel dLabel = new JLabel("進貨日期:");
     JLabel nLabel = new JLabel("食材名稱:");
     JLabel numLabel = new JLabel("進貨數量:");
     JLabel moLabel = new JLabel("金額:");
     JLabel ftnLabel = new JLabel("廠商名稱:");
     JTextField nTxtFd = new JTextField("");
     JTextField dTxtFd = new JTextField("");
     JTextField numTxtFd = new JTextField("");
     JTextField moTxtFd = new JTextField("");
     JTextField ftnTxtFd = new JTextField("");
     JButton myBtn1=new JButton("查詢");
     JButton myBtn2=new JButton("清除");
     JButton myBtn3=new JButton("離開");
     Font font1 = new Font("標楷體", Font.BOLD, 40);
     Font font2 = new Font("標楷體", Font.BOLD, 20);
     Font font3 = new Font("標楷體", Font.BOLD, 30);
     Color color = new Color(144,238,144); //淡綠色
     String ftData;

    fdfdFrame(){
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
      dLabel.setBounds(30,120,200,50);
      dLabel.setFont(font3);
      pane2.add(dLabel);
      ftnLabel.setBounds(30,190,100,50);
      ftnLabel.setFont(font2);
      pane2.add(ftnLabel);
      numLabel.setBounds(30,260,100,50);
      numLabel.setFont(font2);
      pane2.add(numLabel);
      moLabel.setBounds(30,330,100,50);
      moLabel.setFont(font2);
      pane2.add(moLabel);

      nTxtFd.setBounds(220,50,300,50);
      nTxtFd.setFont(font2);
      pane2.add(nTxtFd);
      dTxtFd.setBounds(220,120,300,50);
      dTxtFd.setFont(font2);   
      pane2.add(dTxtFd);
      ftnTxtFd.setBounds(140,190,300,50);
      ftnTxtFd.setFont(font2);
      ftnTxtFd.setEditable(false);
      pane2.add(ftnTxtFd);
      numTxtFd.setBounds(140,260,300,50);
      numTxtFd.setFont(font2);
      numTxtFd.setEditable(false);
      pane2.add(numTxtFd);
      moTxtFd.setBounds(140,330,300,50);
      moTxtFd.setFont(font2);
      moTxtFd.setEditable(false);
      pane2.add(moTxtFd);

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
   //查詢員工，從factory資料表
   public void findRD_in_TB_food(String fdData1,String fdData2){

           Connection connection;
           Statement statement;
           ResultSet result;
           String fddData;
           String name="",day="",ftname="";
           int num=0,mo=0;
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
                    fddData = "SELECT * FROM food WHERE fd_day ='"+fdData1+"' && fd_name ='"+fdData2+"'";
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                    statement = connection.createStatement();
                    result = statement.executeQuery(fddData);
          
                    while( result.next() ){
                          day = result.getString("fd_day");
                          name = result.getString("fd_name");
                          ftname = result.getString("ft_name");
                          num = result.getInt("fd_num");
                          mo = result.getInt("fd_mo");
                     }
                    nTxtFd.setText(""+name+"");
                    dTxtFd.setText(""+day+"");
                    ftnTxtFd.setText(""+ftname+"");
                    numTxtFd.setText(""+num+"");
                    moTxtFd.setText(""+mo+"");
                     statement.close();
             }catch(SQLException e){
                         JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
              }             
                      
    }

    public ActionListener ProcessBtnPress = new ActionListener(){
       public void actionPerformed(ActionEvent e){
    	   
         if(e.getSource() == myBtn3){ 
        	myBtn1.setEnabled(true);
            fdfdFrame.this.setVisible(false);  //關閉視窗
         }
         if(e.getSource() == myBtn1){
            String fdData1=dTxtFd.getText();
            String fdData2=nTxtFd.getText();
            findRD_in_TB_food(fdData1,fdData2);
            myBtn1.setEnabled(false); 
          
           
         }
         if(e.getSource() == myBtn2){ 
           dTxtFd.setText("");
           nTxtFd.setText("");
           ftnTxtFd.setText("");
           numTxtFd.setText("");
           moTxtFd.setText("");
           myBtn1.setEnabled(true);
         }
       }
    };
}

 //主程式
 public class fdfd{
    public static void main(String[] args){
      fdfdFrame frame1 = new fdfdFrame();

    }
}