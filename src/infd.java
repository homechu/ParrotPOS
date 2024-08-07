//食材進貨
import javax.swing.*;

import javax.swing.border.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel; 
import java.sql.*;

 
class infdFrame extends JPanel{
     
	
     JPanel pane1=new JPanel();
     JPanel pane2=new JPanel();
     JLabel BLabel = new JLabel("食材進貨");
     JLabel nLabel = new JLabel("食材名稱:");
     JLabel dLabel = new JLabel("進貨日期:");
     JLabel ftnLabel = new JLabel("進貨廠商:");
     JLabel numLabel = new JLabel("數量:");
     JLabel moLabel = new JLabel("金額:");
     JTextField nTxtFd = new JTextField("");
     JTextField dTxtFd = new JTextField("");
     JTextField ftnTxtFd = new JTextField("");
     JTextField numTxtFd = new JTextField("");
     JTextField moTxtFd = new JTextField("");
     JButton myBtn1=new JButton("新增");
     JButton myBtn2=new JButton("清除");
     JButton myBtn3=new JButton("離開");
     Font font1 = new Font("標楷體", Font.BOLD, 40);
     Font font2 = new Font("標楷體", Font.BOLD, 20);
     int a = 0;
     Color color = new Color(135,206,235);  //天藍色
     String[] list1={"","","","","","","","","","","","","","","","","",""};
     JList combo ;
    
    //建構子
    infdFrame(){
     
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
     dLabel.setBounds(30,120,100,50);
     dLabel.setFont(font2);
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

     nTxtFd.setBounds(140,50,300,50);
     nTxtFd.setFont(font2);
     pane2.add(nTxtFd);
     dTxtFd.setBounds(140,120,300,50);
     dTxtFd.setFont(font2);
     pane2.add(dTxtFd);
     ftnTxtFd.setBounds(140,190,300,50);
     ftnTxtFd.setFont(font2);
     pane2.add(ftnTxtFd);
     numTxtFd.setBounds(140,260,300,50);
     numTxtFd.setFont(font2);
     pane2.add(numTxtFd);
     moTxtFd.setBounds(140,330,300,50);
     moTxtFd.setFont(font2);
     pane2.add(moTxtFd);

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
     setLocation(250,110);
     setSize(949,470);
     setVisible(true);
     
     for(int is=0;is<3;is++)
    	 for(int ia=0;ia<6;ia++){
    		             if(is==0)
    		    		 list1[ia] = CFrame.itemBtnString[is][ia];
    		    		 if(is==1)
    		    			 list1[6+ia] = CFrame.itemBtnString[is][ia];
    		    		 if(is==2)
    		    			 list1[12+ia] = CFrame.itemBtnString[is][ia];
    	 }
    	 
     combo = new JList(list1);
     combo.setVisibleRowCount(4);
     JScrollPane AAA = new JScrollPane(combo);
     AAA.setBounds(445,50,300,190);
     combo.setFont(font2);
     pane2.add(AAA);
     combo.addListSelectionListener(FuckingChange);
     
     
     }
    public ListSelectionListener FuckingChange = new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
				Object[] values = combo.getSelectedValues();
				String text = "";
				for(int i=0;i< values.length;i++)
					text += values[i].toString();
				nTxtFd.setText(text);
			}
        };
		 
	 
     //傳入完整的一筆顧客資料字串陣列(fdData),然後將此顧客資料適當轉換後,存入資料庫的food資料表中
       public void insertRD_into_TB_food(String[] fdData){

             Connection connection;
             Statement statement;
             String fddData="";
            
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

             //在posdb資料庫中, 新增一筆顧客資料到資料表: food 
             try{  
                    fddData = "INSERT INTO food(fd_day,fd_name,ft_name,fd_num,fd_mo)"+
                                            "VALUES('"+fdData[0]+"','"+fdData[1]+"','"+fdData[2]+"',"+Integer.parseInt(fdData[3])+","+Integer.parseInt(fdData[4])+")";

                    connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                    statement = connection.createStatement();
                    statement.executeUpdate(fddData);
                    JOptionPane.showMessageDialog(null,"在posdb資料庫中, 成功寫入一筆[食材記錄]到food資料表中!");
                    
                    statement.close();

             } catch(SQLException e){
            	 
                    JOptionPane.showMessageDialog(null,"在posdb資料庫中, 寫入一筆[食材記錄]到food資料表中發生錯誤!");
              }
             
       }

    public ActionListener ProcessBtnPress = new ActionListener(){
       public void actionPerformed(ActionEvent e){
    	   
         if(e.getSource() == myBtn3){ 
        	 infdFrame.this.setVisible(false); //關閉視窗
         }
         if(e.getSource() == myBtn1){ 
           String[] fdData = {dTxtFd.getText(),nTxtFd.getText(),ftnTxtFd.getText(), numTxtFd.getText(),moTxtFd.getText()};
           insertRD_into_TB_food(fdData);
           
         }
         if(e.getSource() == myBtn2){ 
           nTxtFd.setText("");
           dTxtFd.setText("");
           ftnTxtFd.setText("");
           numTxtFd.setText("");
           moTxtFd.setText("");
         }
       }
    };
   
    	
    	 
    

 }
//主程式
 public class infd{
   public static void main(String[] args){

        infdFrame frame1=new infdFrame();
        
   }
 } 
