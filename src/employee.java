//員工清單
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*; 
import java.sql.*;


 public class employee extends JPanel {
	
	
	
	JTable tableA = new JTable();
	DefaultTableModel tm = new DefaultTableModel(new Object[][]{},new Object[]{"員工名稱","電話","e-mail","職稱"});

	JLabel myLabel1 = new JLabel("員工清單");
	Font font= new Font("標楷體",Font.BOLD,40);
        JButton myBtn = new JButton("離開");
	
    employee(){
		
		this.setSize(1000, 800);
		this.setVisible(true);
		this.setLayout(null);
		
		
		SetBtn();
		SetTableA();
		SetmyLabel1();
		
		
	}
	
	public void findRD_in_TB_employee(String aNo){   //連接資料庫

             Connection connection;
             Statement statement;

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
         }

          //顯示在畫面中
         private void SetTableA(){ 
             Connection connection;
             Statement statement;
             ResultSet result;
             String name="",no="",email="",post="";
            
                try{
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                    statement = connection.createStatement();
                    result = statement.executeQuery("SELECT * FROM employee");
                    while( result.next() ){
                          no = result.getString("ep_no");
                          name = result.getString("ep_name");
                          email = result.getString("ep_email");
                          post = result.getString("ep_post");
                          tm.addRow(new Object[]{""+name+"",""+no+"",""+email+"",""+post+""});
                          
                     }
                  tableA.setModel(tm);
	     
		JScrollPane Scroll = new JScrollPane(tableA,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                                             JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		Scroll.setBounds(0, 100, this.getWidth(), 700);
		this.add(Scroll);
                     statement.close();
                     
              } catch(SQLException e){
                     JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
              }             
	}

        private void SetmyLabel1(){  //標題
		
		JPanel Panel = new JPanel();
		Panel.setBounds(0,10,1000,50);
                this.add(Panel);
                myLabel1.setFont(font);
                myLabel1.setBounds(300,5,350,35);
                Panel.add(myLabel1);
                
	}
        private void SetBtn(){   //按鈕
                JPanel Pane2 = new JPanel();
		        Pane2.setBounds(800,5,200,70);
                this.add(Pane2);
                myBtn.setFont(font);
                myBtn.addActionListener(ProcessBtnPress); 
                Pane2.add(myBtn); 
        } 

  public ActionListener ProcessBtnPress = new ActionListener(){
    public void actionPerformed(ActionEvent e){
         if(e.getSource() == myBtn)
        	 employee.this.setVisible(false); 
           
    }
  };

  public static void main(String[] args) { 
               new employee();
	}

	
 }
