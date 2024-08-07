import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.SwingConstants.*;
import java.awt.event.*;
import java.awt.color.*;
import java.sql.*;
import java.util.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

class CFrame extends JFrame{
   JPanel[] myPane = new JPanel[8];
   
   JLabel[] myLabel = new JLabel[5];
  
   String[] myLabelString = {"菜單點餐系統","會員名稱:","電話號碼:","剩餘金額:","單價"};
     
   JTextField myTxtFdTo = new  JTextField();
   
   JButton login = new JButton("登入");
   JButton logout = new JButton("登出");
   JButton backSystem = new JButton("後端管理系統");
   JButton Record = new JButton("消費紀錄");
   
   static JTextField number = new JTextField();
   static JTextField last = new JTextField();
   static JTextField porint = new JTextField();
  
   JButton[][] myBtn = new JButton[3][6];
   static String[][] itemBtnString = {{"招牌鍋貼(4元)","韭菜鍋貼(4元)","咖哩鍋貼(5元)","韓式鍋貼(5元)","蔬菜鍋貼(5元)","冷凍鍋貼(5元)"},
                            {"招牌水餃(4元)","韭菜水餃(4元)","鮮蝦水餃(5元)","咖哩水餃(5元)","蔬菜水餃(5元)","冷凍水餃(5元)"},
                            {"玉米濃湯(25元)","酸辣湯(25元)","白豆漿(15元)","旗魚丸湯(25元)","黑豆漿(15元)","貢丸湯(20元)"}};
   int[][]          price = {{4,4,5,5,5,5},
                             {4,4,5,5,5,5},
                             {25,25,15,25,15,20}};
      
   JButton FD = new JButton("鍋貼類");
   JButton DG = new JButton("水餃類");
   JButton SOUP = new JButton("湯品/飲料");
 
   JButton[][] Btn = new JButton[4][4];
   String[][] BtnString = {{"外帶","7","8","9"},
                          {"內用","4","5","6"},
                          {"清空","1","2","3"},
                          {"結帳","確定","10","清除"}};
   //暫存記憶體儲存空間  
   int w=0,total=0;
   String Ao=new String();
   String Bo=new String();
   String ao=new String();
   String Co;
   //時間顯示方程式
   Calendar calendar = new GregorianCalendar();
   Label Atime=new Label("");
   
   //  table setting   *******
      Object[][] data={};
      String[] columns={"商品","數量","金額"};
      DefaultTableModel mo = new DefaultTableModel(new Object[][]{},new Object[]{"商品","數量","金額"});
      JTable jt=new JTable(data,columns);
      JTextField Rerd = new JTextField("");
      String[] aCustomer = {"0956883122","朱泓勳","鍋貼拉幹","4","50"};
    
   CFrame(){
	   
	     Ao=""; // 判斷前置作業
	         
        for(int x=0;x<myLabel.length;x++){   //**** 字串輸入 ****
           myLabel[x] = new JLabel(myLabelString[x],JLabel.CENTER);
         }
       
        
           
           for(int x=0;x<myPane.length;x++)
           myPane[x] = new JPanel();
           
           // panel[0] setting    *********
           myPane[0].setLayout( new FlowLayout());
           myPane[0].setBounds(0,0,1200,80);
           myPane[0].setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
           myLabel[0].setBounds(0,0,0,0);
           myLabel[0].setFont(new Font("微軟正黑體", Font.BOLD, 45));
           myPane[0].add(myLabel[0]);
         
           
           login.setBounds(0,0,100,80);
           login.setFont(new Font("微軟正黑體",Font.BOLD, 30));
           login.setForeground(Color.white);
           login.setBackground(Color.getHSBColor(0.04f,0.41f,0.83f));
           add(login);
           
           logout.setBounds(930,0,100,80);
           logout.setFont(new Font("微軟正黑體",Font.BOLD, 30));
           logout.setForeground(Color.white);
           logout.setBackground(Color.getHSBColor(0.0f,0.49f,1.00f));
           add(logout);
           
           backSystem.setFont(new Font("微軟正黑體",Font.BOLD, 21));
           backSystem.setBounds(1035,0,170,80);
           backSystem.setForeground(Color.white);
           backSystem.setBackground(Color.getHSBColor(0.13f,0.30f,0.85f));
           
           add(backSystem);
           
           //**********      myPane[1] setting     **********         
           myPane[1].setLayout(null);
           myPane[1].setBounds(0,80,460,330);
           myPane[1].setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
           myLabel[1].setBounds(0,10,200,50);
           myLabel[1].setFont(new Font("微軟正黑體", Font.BOLD, 25));
           myPane[1].add(myLabel[1]);
           
           number.setText("來賓");
           number.setEditable(false);
           number.setBounds(200,15,200,50);
           myPane[1].add(number);
           
           myLabel[2].setBounds(0,80,200,50);
           myLabel[2].setFont(new Font("微軟正黑體", Font.BOLD, 25));
           myPane[1].add(myLabel[2]);
           
           last.setText("來賓");
           last.setEditable(false);
           last.setBounds(200,80,200,50);
           myPane[1].add(last);
           
           myLabel[3].setBounds(0,150,200,50);
           myLabel[3].setFont(new Font("微軟正黑體", Font.BOLD, 25));
           myPane[1].add(myLabel[3]);
           
           porint.setText("來賓");
           porint.setEditable(false);
           porint.setBounds(200,150,200,50);
           myPane[1].add(porint);
                     
           Record.setBounds(140,230,180,70);
           Record.setFont(new Font("微軟正黑體",Font.BOLD,25));
           Record.setBackground(Color.getHSBColor(0.10f,0.57f,1.0f));
           myPane[1].add(Record);
           
           //隱藏式選單設計********************************
           myPane[2].setLayout(new GridLayout(2,3,5,5));
           myPane[2].setBounds(642,81,540,325);
           myPane[2].setVisible(true);
           myPane[2].setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
           myPane[6].setLayout(new GridLayout(2,3,5,5));
           myPane[6].setBounds(642,81,540,325);
           myPane[6].setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
           myPane[6].setVisible(false);
           myPane[6].setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
           myPane[7].setLayout(new GridLayout(2,3,5,5));
           myPane[7].setBounds(642,81,540,325);
           myPane[7].setVisible(false);
           myPane[7].setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
           
           //myPane[3]     ***********   setting   ***********
           myPane[3].setLayout(new GridLayout(4,4,10,15));
           myPane[3].setBounds(620,410,560,350);  
           myPane[3].setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
                    
           //myPane[4]     setting   ***********
           myPane[4].setLayout(new GridLayout(1,5,15,20));
           myPane[4].setBounds(10,410,600,275);  
           myPane[4].setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
           jt.setFont(new Font("微軟正黑體", Font.BOLD, 20));   //JTable設定
           jt.setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
           jt.setPreferredScrollableViewportSize(new Dimension(600,300));
           TableColumn column=jt.getColumnModel().getColumn(0);   //*******格子寬度更改
           column.setPreferredWidth(150);
           jt.setRowHeight(50); 
           

                      
           myPane[4].add(new JScrollPane(jt),BorderLayout.CENTER);
           JLabel Reord = new JLabel("總金額:");
           Reord.setBounds(10,690,120,80);
           Reord.setFont(new Font("微軟正黑體", Font.BOLD, 25));
           
           
           add(Reord);
           
           Rerd.setBounds(110,700,500,70);
           Rerd.setFont(new Font("微軟正黑體", Font.BOLD, 25));
           add(Rerd);
                      
           //myPane[5]     setting   ***********
           myPane[5].setLayout(new GridLayout(3,3,2,2));
           myPane[5].setBounds(460,80,180,325);
           myPane[5].setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
           
           FD.setFont(new Font("微軟正黑體", Font.BOLD, 30)); //******鍋貼設定******
           FD.addActionListener(ProcessBtnPress);
           ImageIcon imagea = new ImageIcon(getClass().getResource("a.jpg"));
           imagea.setImage(imagea.getImage().getScaledInstance(256,144,Image.SCALE_DEFAULT));
           FD.setIcon(imagea);
          
           DG.setFont(new Font("微軟正黑體", Font.BOLD, 30));
           DG.addActionListener(ProcessBtnPress);
           ImageIcon imageb = new ImageIcon(getClass().getResource("c.jpg"));
           imageb.setImage(imageb.getImage().getScaledInstance(240,144,Image.SCALE_DEFAULT));
           DG.setIcon(imageb);
         
           SOUP.setFont(new Font("微軟正黑體", Font.BOLD, 30));
           SOUP.addActionListener(ProcessBtnPress);
           ImageIcon imagec = new ImageIcon(getClass().getResource("b.jpg"));
           imagec.setImage(imagec.getImage().getScaledInstance(250,190,Image.SCALE_DEFAULT));
           SOUP.setIcon(imagec);
       
           myPane[5].add(FD);
           myPane[5].add(DG);
           myPane[5].add(SOUP);		
           
           for(int x=0;x<myPane.length;x++)
           add(myPane[x]);
                       
         
         //菜單添加程式碼*********************************************
        for(int x=0;x<1;x++){
            for(int y=0;y<myBtn[0].length;y++){
               myBtn[x][y] = new JButton(itemBtnString[x][y]);
              // myBtn[x][y].setBackground(Color.red);
               myBtn[x][y].setFont(new Font("微軟正黑體", Font.BOLD, 22));
              myBtn[x][y].setBackground(Color.getHSBColor(0.10f, 0.54f,0.67f));
              myBtn[x][y].setForeground(Color.white);
              // myBtn[x][y].setForeground(Color.white);
               myBtn[x][y].addActionListener(MainBtnPress);
               myPane[2].add(myBtn[x][y]);
              }
         }
        
        for(int x=1;x<2;x++){
            for(int y=0;y<myBtn[0].length;y++){
               myBtn[x][y] = new JButton(itemBtnString[x][y]);
              // myBtn[x][y].setBackground(Color.red);
               myBtn[x][y].setFont(new Font("微軟正黑體", Font.BOLD, 22));
              // myBtn[x][y].setForeground(Color.white);
              myBtn[x][y].setBackground(Color.getHSBColor(0.10f, 0.54f,0.67f));
              myBtn[x][y].setForeground(Color.white);
               myBtn[x][y].addActionListener(MainBtnPress);
               myPane[6].add(myBtn[x][y]);
              }
         }
        for(int x=2;x<3;x++){
            for(int y=0;y<myBtn[0].length;y++){
               myBtn[x][y] = new JButton(itemBtnString[x][y]);
              // myBtn[x][y].setBackground(Color.red);
               myBtn[x][y].setFont(new Font("微軟正黑體", Font.BOLD, 20));
              // myBtn[x][y].setForeground(Color.white);
               myBtn[x][y].setBackground(Color.getHSBColor(0.10f, 0.54f,0.67f));
               myBtn[x][y].setForeground(Color.white);
               myBtn[x][y].addActionListener(MainBtnPress);
               myPane[7].add(myBtn[x][y]);
              }
         }
        
        for(int x=0;x<Btn.length;x++){
            for(int y=0;y<Btn[0].length;y++){
               Btn[x][y] = new JButton(BtnString[x][y]);
               Btn[x][y].setFont(new Font("微軟正黑體", Font.BOLD, 35));
               Btn[x][y].setBackground(Color.getHSBColor(0.0f,0.30f,1.0f));
               Btn[x][0].setBackground(Color.getHSBColor(0.10f,0.57f,1.0f));
               myPane[3].add(Btn[x][y]);
               if ((x != 3) & (y != 0) )//避開 功能性按鈕
               Btn[x][y].addActionListener(NumberBtnPress);
               else if(x==3 & y==2)// 按鈕10監聽
                       Btn[x][y].addActionListener(NumberBtnPress);
             }
         }
        //日期時間設定
        setTime();
        Atime.setBounds(50,0,180,50);
        myPane[0].add(Atime);
        
        //功能性按鈕事件監聽***********************************
        Btn[3][1].addActionListener(ADDBtnPress);//確定按鈕監聽
        Btn[3][3].addActionListener(EditBtnPress);//清除按鈕監聽
        
        Btn[0][0].addActionListener(EitBtnPress);//內用外帶清空按鈕監聽
        Btn[1][0].addActionListener(EitBtnPress);
        Btn[2][0].addActionListener(EitBtnPress);
        
        Btn[3][0].addActionListener(EndBtnPress);//結帳按鈕監聽
        login.addActionListener(EndBtnPress);
        logout.addActionListener(EndBtnPress);
        backSystem.addActionListener(EndBtnPress);
        Record.addActionListener(EndBtnPress);
        
        jt.setModel(mo);

        setTitle("櫻舞股份有限公司");
        setLayout(null);
        setLocation(100,100);
        setSize(1200,800);
        setBackground(Color.white);
        setVisible(true);
        setResizable(false);//調整大小
        // setExtendedState(Frame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
   //隱藏式表單監聽***********************************************
    public ActionListener ProcessBtnPress = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == FD){
                          myPane[2].setVisible(true);
                          myPane[6].setVisible(false);
                          myPane[7].setVisible(false);
                        }
            if(e.getSource() == DG){
                          myPane[2].setVisible(false);
                          myPane[6].setVisible(true);
                          myPane[7].setVisible(false);
                        }
            if(e.getSource() == SOUP){
                          myPane[2].setVisible(false);
                          myPane[6].setVisible(false);
                          myPane[7].setVisible(true);
                        }
        }
    };
     //主選單添加程式************************************************
     public ActionListener MainBtnPress = new ActionListener(){    
        public void actionPerformed(ActionEvent e){
            for(int x=0;x<myBtn.length;x++){
                for(int y=0;y<myBtn[0].length;y++){
                   if(e.getSource() == myBtn[x][y]){
                         myBtn[x][y].setBackground(Color.white);
                         myBtn[x][y].setForeground(Color.black);
                         Ao=myBtn[x][y].getText();
                         w = price[x][y];
                    }
                   else{
                         myBtn[x][y].setBackground(Color.getHSBColor(0.10f, 0.54f, 0.67f));
                         myBtn[x][y].setForeground(Color.white);
                       }  
                                
                 }
            }
        }
        
    };
   
        
  //數字鍵添加程式****************************************************
       public ActionListener NumberBtnPress = new ActionListener(){  
        public void actionPerformed(ActionEvent e){
            for(int x=0;x<Btn.length;x++){
                for(int y=1;y<Btn.length;y++){
                   if(e.getSource() == Btn[x][y]){
                	   int co;
                         Btn[x][y].setBackground(Color.white);
                         Btn[x][y].setForeground(Color.black);
                         Bo=Btn[x][y].getText();
                         co = Integer.parseInt(Btn[x][y].getText()) * w;
                         total += co;
                         Co = Integer.toString(co);
                         
                    }
                   else{
                         Btn[x][y].setBackground(Color.getHSBColor(0.0f,0.30f,1.0f));
                         Btn[x][y].setForeground(Color.BLACK);
                       }  
                                
                 }
            }
        }
        
    };
    //資料表添加程式***********
        public ActionListener ADDBtnPress = new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
                  int BB = 0;
                  if(Ao!=""){
                   if(e.getSource() == Btn[3][1]){
                	   Ao=Ao+ao;
                	   mo.addRow(new Object[]{Ao,Bo,Co});
			           mo.fireTableDataChanged();
			           jt.updateUI();
			           Rerd.setText(Integer.toString(total));
			           Ao="";
			          
			        }
                   
                  }else {
                	   JOptionPane.showMessageDialog(null,"請選擇餐點!");
                   }
                  for(int x=0;x<Btn.length;x++){
                   for(int y=1;y<Btn.length;y++){
                   if(e.getSource() == Btn[3][1]){
                         Btn[x][y].setBackground(Color.getHSBColor(0.0f,0.30f,1.0f));
                         Btn[x][y].setForeground(Color.BLACK);
                        
                     }
                   }
                 }
                for(int a=0;a<myBtn.length;a++){
                for(int b=0;b<myBtn[0].length;b++){
                   if(e.getSource() == Btn[3][1]){
                         myBtn[a][b].setBackground(Color.getHSBColor(0.10f, 0.54f, 0.67f));
                         myBtn[a][b].setForeground(Color.white);
                     }
                   }
                 }
            }
        };
        public ActionListener EitBtnPress = new ActionListener(){     //內用外帶清空事件監聽
            public void actionPerformed(ActionEvent e){
            	if(e.getSource() == Btn[0][0]){
            		ao=Btn[0][0].getText();
            		
            		Btn[0][0].setBackground(Color.white);
            		Btn[1][0].setBackground(Color.getHSBColor(0.10f,0.57f,1.0f));
                }
            	if(e.getSource() == Btn[1][0]){
            		ao=Btn[1][0].getText();
            		
            		Btn[1][0].setBackground(Color.white);
            		Btn[0][0].setBackground(Color.getHSBColor(0.10f,0.57f,1.0f));
                }
            	if(e.getSource() == Btn[2][0]){
            		Btn[1][0].setBackground(Color.getHSBColor(0.10f,0.57f,1.0f));
            		Btn[0][0].setBackground(Color.getHSBColor(0.10f,0.57f,1.0f));
            	    mo.setRowCount(0);
        			mo.fireTableDataChanged();
        			total = 0 ;
                    Rerd.setText(Integer.toString(total));
                    
                    
                }
            }
            
        };
        
      //資料表刪除程式**********************
        public ActionListener EditBtnPress = new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            
                   if(e.getSource()==Btn[3][3]){
			         if(jt.getSelectedRow() != -1){
			        	   	String Etotal = (String)jt.getValueAt(jt.getSelectedRow(),2);
				                mo.removeRow(jt.getSelectedRow());
				                mo.fireTableDataChanged();
                                jt.updateUI();
                                total -= Integer.valueOf(Etotal);;
                                Rerd.setText(Integer.toString(total));
                                
             }
		    }
          }
       };
       
       public ActionListener EndBtnPress = new ActionListener(){ //儲存資料表
           public void actionPerformed(ActionEvent e){
               if(e.getSource()==Btn[3][0]){                     //結帳按鈕監聽
            	   aCustomer[0]=last.getText();
            	   aCustomer[1]=number.getText();
            	   int value = 0; 
            	   if(aCustomer[1].equals("來賓")==false ){
            	      value = Integer.valueOf(porint.getText())-Integer.valueOf(Rerd.getText());
            	   if(value >= 0){
            	   porint.setText(Integer.toString(value));
            	   UPDATA_TB_transaction(aCustomer[0],porint.getText());
            	                  }else{
            		                 JOptionPane.showMessageDialog(null,"餘額不足! 請儲值或登出以改為來賓點餐");
            	         
            	       }
            	   }
            	   if(value >= 0 | aCustomer[1].equals("來賓")){
          	   for(int i=0;i<jt.getRowCount();i++){
                     for(int j=0;j<3;j++){
                    	   aCustomer[j+2]=(String)jt.getValueAt(i,j);
                           }
                     insertRD_into_TB_transaction(aCustomer);
                   }
          	   
            	   JOptionPane.showMessageDialog(null,"交易成功!");
            	   }
               	  //讀取表格內的值
   		       }
               if(e.getSource()==login){AFrame frame1 = new AFrame();}
               if(e.getSource()==logout){
            	   porint.setText("來賓");
            	   last.setText("來賓");
            	   number.setText("來賓");
            	   JOptionPane.showMessageDialog(null,"你已經登出!");
            	   }
               if(e.getSource()==backSystem){AAFrame frame1 = new AAFrame();}
               if(e.getSource()==Record){fdtsFrame frame2 = new fdtsFrame();};
               mo.setRowCount(0);     //清空
   			   mo.fireTableDataChanged();
   			   total = 0 ;
               Rerd.setText(Integer.toString(total));
               Ao="";   // 選餐判斷回朔
           }
       };
     
       public void insertRD_into_TB_transaction(String[] cmData){

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
                  cmdData = "INSERT INTO transaction(cm_no,cm_name,ts_name,ts_num,ts_mo)"+
                                          "VALUES('"+cmData[0]+"','"+cmData[1]+"','"+cmData[2]+"','"+cmData[3]+"',"+
                                                    cmData[4]+")";

                  connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                  statement = connection.createStatement();
                  statement.executeUpdate(cmdData);
                  
                  statement.close();

           } catch(SQLException e){
                  JOptionPane.showMessageDialog(null,"資料表發生錯誤!");
           }
       }
       public void UPDATA_TB_transaction(String cmData,String mo){

           Connection connection;
           Statement statement;
           String cmdDate;
           int moo=Integer.valueOf(mo);

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

           //在posdb資料庫中, 更新一筆顧客資料到資料表: customer   
           try{  
        	      cmdDate = "UPDATE customer SET cm_mo ="+moo+" WHERE cm_no ="+cmData;
                  
                  connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                  statement = connection.createStatement();
                  statement.executeUpdate(cmdDate);
                  
                  statement.close();

           } catch(SQLException e){
        	      
                  JOptionPane.showMessageDialog(null,"資料表更新發生錯誤!");
           }
       }
       private void setTime()  //設定系統時間
       {
           calendar.setTimeInMillis(System.currentTimeMillis());
           Atime.setText(calendar.get(Calendar.YEAR)+"/"+getMonth()+"/"+
           calendar.get(Calendar.DATE));
       }
       public int getMonth()  //取得月
       {
           return (calendar.get(Calendar.MONTH)+1);
       }

  
  
}

 public class Parrot{
   public static void main(String[] args){

       CFrame frame1 = new CFrame();

  }
 }

