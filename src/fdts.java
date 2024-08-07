//查詢交易
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel; 
import java.sql.*;

class fdtsFrame extends JPanel{

     JPanel pane1=new JPanel();
     JPanel pane2=new JPanel();
     JPanel pane3=new JPanel();
     JLabel BLabel = new JLabel("查詢交易");
     JLabel cmtLabel = new JLabel("會員電話:");
     JLabel cmnLabel = new JLabel("會員姓名:");
     
   
     JTextField cmnTxtFd = new JTextField("");
     JTextField cmtTxtFd = new JTextField("");
    
     JButton myBtn1=new JButton("查詢");
     JButton myBtn2=new JButton("清除");
     JButton myBtn3=new JButton("離開");
     Font font1 = new Font("標楷體", Font.BOLD, 40);
     Font font2 = new Font("標楷體", Font.BOLD, 20);
     Font font3 = new Font("標楷體", Font.BOLD, 30);
     Color color = new Color(255,182,193); //淡綠色
     JTable tableA = new JTable();
 	 DefaultTableModel tm = new DefaultTableModel(new Object[][]{},new Object[]{"會員姓名","會員電話","商品名稱","數量","金額"});
 	JScrollPane Scroll = new JScrollPane(tableA,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
     
    fdtsFrame(){
    	
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
      
      
      
      
      cmnLabel.setBounds(30,50,200,50);
      cmnLabel.setFont(font3);
      pane2.add(cmnLabel);
      cmtLabel.setBounds(30,120,200,50);;
      cmtLabel.setFont(font3);
      pane2.add(cmtLabel);

      cmnTxtFd.setBounds(200,50,300,50);
      cmnTxtFd.setFont(font2);
      pane2.add(cmnTxtFd);
      
      cmtTxtFd.setBounds(200,120,300,50);
      cmtTxtFd.setFont(font2);   
      pane2.add(cmtTxtFd);

      myBtn1.setBounds(600,50,100,50);
      myBtn1.setFont(font2);
      myBtn1.addActionListener(ProcessBtnPress);
      pane2.add(myBtn1);
      myBtn2.setBounds(600,120,100,50);
      myBtn2.setFont(font2);
      myBtn2.addActionListener(ProcessBtnPress);
      pane2.add(myBtn2);
      myBtn3.setBounds(800,85,100,50);
      myBtn3.setFont(font2);
      myBtn3.addActionListener(ProcessBtnPress);
      pane2.add(myBtn3);
      
      cmnTxtFd.setText(CFrame.number.getText());
      cmtTxtFd.setText(CFrame.last.getText());
      
      
      setLayout(null);
      setLocation(100,120);
      setSize(800,600);
      setVisible(true);
          
      
      Scroll.setBounds(0, 220, pane2.getWidth(), 400);
      add(Scroll); 
     

   }
   //查詢員工，從transaction資料表
   public void findRD_in_TB_transaction(String tsData1,String tsData2){

           Connection connection;
           Statement statement;
           ResultSet result;
           String tsdData;
           String cmname="",cmtel="",name="";
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
                    tsdData = "SELECT * FROM transaction WHERE cm_no ='"+tsData1+"' && cm_name ='"+tsData2+"'";
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                    statement = connection.createStatement();
                    result = statement.executeQuery(tsdData);
          
                    while( result.next() ){
                        cmtel = result.getString("cm_no");
                        cmname = result.getString("cm_name");
                        name = result.getString("ts_name");
                        num = result.getInt("ts_num");
                        mo = result.getInt("ts_mo");
                        tm.addRow(new Object[]{""+cmname+"",""+cmtel+"",""+name+"",""+num+"",""+mo+""});
                        
                    }
                    tableA.setModel(tm);
	     
		            
                statement.close();
                   
            } catch(SQLException e){
                         JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
              }             
                      
    }
   

    public ActionListener ProcessBtnPress = new ActionListener(){
       public void actionPerformed(ActionEvent e){
    	   
         if(e.getSource() == myBtn3){ 
        	myBtn1.setEnabled(true);
        	tm.setRowCount(0);
            fdtsFrame.this.setVisible(false);  //關閉視窗
         }
         if(e.getSource() == myBtn1){
            String tsData1=cmtTxtFd.getText();
            String tsData2=cmnTxtFd.getText();
            findRD_in_TB_transaction(tsData1,tsData2);
            myBtn1.setEnabled(false); 
          
           
         }
         if(e.getSource() == myBtn2){ 
            cmnTxtFd.setText("");
            cmtTxtFd.setText("");
            tm.setRowCount(0);
            myBtn1.setEnabled(true);
         }
       }
    };
}

 //主程式
 public class fdts{
    public static void main(String[] args){
      fdtsFrame frame1 = new fdtsFrame();

    }
}