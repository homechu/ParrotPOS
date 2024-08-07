/*************************************************************************************************************************
   說明: 1. 此程式為一個完整的簡單系統-學生管理系統(sms),供同學們學習參考用
         2. 程式為project.java
         3. 請先將類別CDB_dbma中建構子內的: createDB(); 與 createTB_student(); 這兩行刪掉其註解符號(//)
            重編譯後執行project,以便建立smsdb資料庫與student資料表
                 //建構子:類別CDB_dbma
                 public CDM_dbma(){
                       //createDB();                //建立資料庫smsdb, 完成後請註解掉不作用,以免重複建立會出錯
                       //createTB_student();        //建立資料表student, 完成後請註解掉不作用,以免重複建立會出錯
                 }
         4. 建好smsdb資料庫與student資料表後,再將上述兩行註解掉(加//);然後存檔重新編譯(javac project.java)
         5. 之後再執行java project, 就可使用此系統去[新增學生資料]與[查詢學生資料]
         6. 請讀懂程式,就可以仿照此系統的程式用到你的專案中
         加油!只有努力突破,辛勤耕耘的人,才能了解收穫與成長的滋味.
**************************************************************************************************************************/


 //匯入需要的各類套件
 import javax.swing.*;
 import java.awt.event.*;
 import java.awt.*;
 import javax.swing.border.*;
 import java.sql.*;


 //類別區

 //------------------------------------------------------------------------------------------
 //系統主控類別
 //CSMS: Class StudentManagementSystem (學生管理系統-SMS)

 class CSMS{                    

     //建立本系統所需的各個物件
     CHCI_frame  myFrame = new CHCI_frame();       //人機互動層: 使用者介面物件(myFrame,裡面又含有:myFrame.myMenu,myFrame.myOP_pane,myFrame.myQR_pane)
     CPD_student myStudent = new CPD_student();    //問題領域層: 學生物件(myStudent)    
     CDM_dbma myDBMA = new CDM_dbma();             //資料管理層: 資料庫操作存取物件(myDBMA)
     CFD_check myCheck = new CFD_check();          //基礎層: 檢查物件(myCheck)

     //CSMS的建構子:
     CSMS(){
             
             //設定系統中相關物件是由哪一個[事件傾聽程式]負責處理其動作 
             myFrame.myMenu.queryBtn.addActionListener(ProcessFunSelection);               //主功能選單的[查詢學生資料]按鈕
             myFrame.myMenu.insertBtn.addActionListener(ProcessFunSelection);              //主功能選單的[新增學生資料]按鈕
             myFrame.myMenu.exitBtn.addActionListener(ProcessFunSelection);                //主功能選單的[關閉系統離開]按鈕
             myFrame.myOP_pane.saveBtn.addActionListener(ProcessSaveStudentRecord);        //[新增學生資料]操作畫面的[儲存]按鈕
             myFrame.myQR_pane.submitBtn.addActionListener(ProcessSubmitStudentQuery);     //[查詢學生資料]查詢畫面的[查詢]按鈕
     }
     

      
     //事件傾聽程式: 處理主功能選單選按
     public ActionListener ProcessFunSelection = new ActionListener(){
         public void actionPerformed(ActionEvent e){
      
              if( e.getSource() == myFrame.myMenu.insertBtn ){
                       myFrame.myOP_pane.setVisible(true);        //顯示[新增學生資料]操作畫面
                       myFrame.myOP_pane.clearPane();             //清空[新增學生資料]操作畫面
                       myFrame.myQR_pane.setVisible(false);       //隱藏[查詢學生資料]查詢畫面      
                       myFrame.myQR_pane.clearPane();             //清空[查詢學生資料]查詢畫面
              }

              if( e.getSource() == myFrame.myMenu.queryBtn ){
                       myFrame.myOP_pane.setVisible(false);       //隱藏[新增學生資料]操作畫面
                       myFrame.myOP_pane.clearPane();             //清空[新增學生資料]操作畫面
                       myFrame.myQR_pane.setVisible(true);        //顯示[查詢學生資料]查詢畫面
                       myFrame.myQR_pane.clearPane();             //清空[查詢學生資料]查詢畫面
              }

              if( e.getSource() == myFrame.myMenu.exitBtn ){
                       System.exit(0);                            //關閉程式
              }
 
         }    
     };

     //事件傾聽程式: 處理學生資料儲存
     public ActionListener ProcessSaveStudentRecord = new ActionListener(){
         public void actionPerformed(ActionEvent e){
              
              boolean checkPass = true;            //用來記錄[輸入的學生資料]檢查結果
              String nameString = myFrame.myOP_pane.nTxtFd.getText();    //取得[輸入的學生資料]中的[姓名字串]
              String chineseString = myFrame.myOP_pane.cTxtFd.getText().trim();  //取得[輸入的學生資料]中的[國文成績字串] (註:trim()方法會把字串後面空白除掉)
              String englishString = myFrame.myOP_pane.eTxtFd.getText().trim();  //取得[輸入的學生資料]中的[英文成績字串]

              if( myCheck.checkNumber( chineseString ) == false ){  //利用檢查物件(myCheck)的checkNumber()方法,檢查chineseString是否為正確的數值格式,如:98,80,...等
                     checkPass = false;
                     JOptionPane.showMessageDialog(null,"[國文成績] 輸入資料錯誤!","操作警訊",JOptionPane.ERROR_MESSAGE);
              }

              if( myCheck.checkNumber( englishString ) == false ){  //利用檢查物件(myCheck)的checkNumber()方法,檢查englishString是否為正確的數值格式,如:98,80,...等
                     checkPass = false;
                     JOptionPane.showMessageDialog(null,"[英文成績] 輸入資料錯誤!","操作警訊",JOptionPane.ERROR_MESSAGE);
              }

              if(  nameString.length() == 0 ){    //檢查nameString是否有輸入任何字元,length()為計算字串長度方法,若長度為0則未輸入資料
                     checkPass = false;
                     JOptionPane.showMessageDialog(null,"[學生姓名] 空白未輸入資料!","操作警訊",JOptionPane.ERROR_MESSAGE);
              }

              //如果上述三個檢查都沒發現錯誤,則checkPass會維持true,即通過檢查,因此將學生的姓名,國文分數,英文分數設定到myStudent物件中對應屬性質  
              if( checkPass == true ){
                    myStudent.setName(nameString);
                    myStudent.setChinese( Integer.parseInt(chineseString) );    //註:Integer.parseInt()是將字串轉成整數的方法
                    myStudent.setEnglish( Integer.parseInt(englishString) );

                    myDBMA.insertRD_into_TB_student(myStudent);   //將學生物件傳入[資料庫操作存取物件(myDBMA)]的儲存學生紀錄方法(insertRD_into_TB_student())去儲存學生紀錄到資料庫
              }
 
         }    
     };

     //事件傾聽程式: 處理學生資料查詢
     public ActionListener ProcessSubmitStudentQuery = new ActionListener(){
         public void actionPerformed(ActionEvent e){
      
              String nameString = myFrame.myQR_pane.nTxtFd.getText();  //取得[輸入的查詢資料]中的[姓名字串]
              
              if(  nameString.length() > 0 ){    //如果[姓名字串]長度大於0,即有輸入姓名資料,才進入查詢處理
                     
                     String[] findResult = myDBMA.findRD_in_TB_student(nameString);   //呼叫[資料庫操作存取物件(myDBMA)]的查詢學生紀錄方法(findRD_into_TB_student())去查詢學生紀錄,並回傳儲存到findResult中
                     myFrame.myQR_pane.cTxtFd.setText(findResult[1]);    //將findResult陣列裝的索引1的元素(即中文成績字串)顯示在查詢結果欄位
                     myFrame.myQR_pane.eTxtFd.setText(findResult[2]);    //將findResult陣列裝的索引2的元素(即英文成績字串)顯示在查詢結果欄位

              } else {
                   JOptionPane.showMessageDialog(null,"[學生姓名] 空白未輸入資料，請填寫後再查詢!","操作警訊",JOptionPane.ERROR_MESSAGE);
              }
         }    
     };


 } //end for: class CSMS

 //-----------------------------------------------------------------------------------------




 //------------------------------------------------------------------------------------------
 //人機互動層類別
 //CHCI_frame: Class HumanComputerInteraction_frame (人機介面-框架類別)

 class CHCI_frame extends JFrame{      //系統的視窗

     CHCI_menu  myMenu = new CHCI_menu();                  //主功能選單物件(為JPanel,內含3個按鈕)     
     CHCI_OP_panel  myOP_pane = new CHCI_OP_panel();       //新增學生資料畫面物件(為JPanel,內含標籤,文字欄位,按鈕等)
     CHCI_QR_panel  myQR_pane = new CHCI_QR_panel();       //查詢學生資料畫面物件(為JPanel,內含標籤,文字欄位,按鈕等)              
    
     //建構子:類別CHCI_frame
     public CHCI_frame(){

         add(myMenu);        //將[主功能選單物件]加到此視窗中
         add(myOP_pane);     //將[新增學生資料畫面物件]加到此視窗中
         add(myQR_pane);     //將[查詢學生資料畫面物件]加到此視窗中

         myOP_pane.setVisible(true);   //預設[新增學生資料]畫面顯示
         myQR_pane.setVisible(false);  //預設[查詢學生資料]畫面隱藏

         //系統視窗的基本設定
         setTitle("學生管理系統-Student Management System (SMS)");
         setLocation(100,50);
         setSize(600,400);
         setLayout(null);
         setVisible(true);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

     }

 } //end for: class CHCI_frame

 //-----------------------------------------------------------------------------------------


 //------------------------------------------------------------------------------------------
 //人機互動層類別
 //CHCI_menu: Class HumanComputerInteraction_menu (人機介面-主功能選單類別)

 class CHCI_menu extends JPanel{    

     JButton queryBtn = new JButton("查詢學生資料"); 
     JButton insertBtn = new JButton("新增學生資料"); 
     JButton exitBtn = new JButton("關閉系統離開");                  
    
     //建構子:類別CHCI_menu
     public CHCI_menu(){

         exitBtn.setBounds(50,10,140,30);
         add(exitBtn);

         queryBtn.setBounds(230,10,140,30);
         add(queryBtn);

         insertBtn.setBounds(410,10,140,30);
         add(insertBtn);

         setBackground(Color.pink);
         setLocation(0,0);
         setSize(600,50);
         setLayout(null);
         setVisible(true);

     }

 } //end for: class CHCI_menu

 //-----------------------------------------------------------------------------------------



 //------------------------------------------------------------------------------------------
 //人機互動層類別
 //CHCI_OP_panel: Class HumanComputerInteraction_OPeration_panel (人機介面-[新增學生資料]操作畫面類別)

 class CHCI_OP_panel extends JPanel{

     JLabel tLabel = new JLabel("請輸入姓名成績");
     JLabel nLabel = new JLabel("學生姓名");
     JLabel cLabel = new JLabel("國文成績");
     JLabel eLabel = new JLabel("英文成績");

     JTextField nTxtFd = new JTextField("");
     JTextField cTxtFd = new JTextField("");
     JTextField eTxtFd = new JTextField("");

     JButton clearBtn = new JButton("清除"); 
     JButton saveBtn = new JButton("儲存");                   
    
     //建構子:類別CHCI_OP_panel
     public CHCI_OP_panel(){

         tLabel.setBounds(10,10,310,30);
         add(tLabel);

         nLabel.setBounds(10,50,100,30);
         add(nLabel);
         nTxtFd.setBounds(120,50,200,30);
         add(nTxtFd);

         cLabel.setBounds(10,90,100,30);
         add(cLabel);
         cTxtFd.setBounds(120,90,200,30);
         add(cTxtFd);

         eLabel.setBounds(10,130,100,30);
         add(eLabel);
         eTxtFd.setBounds(120,130,200,30);
         add(eTxtFd);

         clearBtn.setBounds(150,250,100,40);
         clearBtn.addActionListener(ProcessClearFields);   //[新增學生資料]操作畫面中[清除]按鈕加到[事件傾聽程式]
         add(clearBtn);

         saveBtn.setBounds(350,250,100,40);
         add(saveBtn);

         setBackground(Color.yellow);
         setLocation(0,50);
         setSize(600,350);
         setLayout(null);
         setVisible(true);

     }

     //方法:清空容器內欄位
     public void clearPane(){

           nTxtFd.setText("");
           cTxtFd.setText("");
           eTxtFd.setText("");

     }

     //事件傾聽程式: 處理點按[清除]按鈕
     public ActionListener ProcessClearFields = new ActionListener(){
         public void actionPerformed(ActionEvent e){
      
              clearPane();
 
         }    
     };
     

           

 } //end for: class CHCI_OP_panel

 //-----------------------------------------------------------------------------------------



 //------------------------------------------------------------------------------------------
 //人機互動層類別
 //CHCI_QR_panel: Class HumanComputerInteraction_QueRy_panel (人機介面-[查詢學生資料]操作畫面類別)

 class CHCI_QR_panel extends JPanel{

     JLabel tLabel = new JLabel("請輸入欲查詢的學生姓名");
     JLabel nLabel = new JLabel("學生姓名");
     JLabel cLabel = new JLabel("查得國文成績");
     JLabel eLabel = new JLabel("查得英文成績");

     JTextField nTxtFd = new JTextField("");
     JTextField cTxtFd = new JTextField("");
     JTextField eTxtFd = new JTextField("");

     JButton resetBtn = new JButton("清除"); 
     JButton submitBtn = new JButton("查詢");                   
    
     //建構子:類別CHCI_QR_panel
     public CHCI_QR_panel(){

         tLabel.setBounds(10,10,310,30);
         add(tLabel);

         nLabel.setBounds(10,50,100,30);
         add(nLabel);

         nTxtFd.setBounds(120,50,200,30);
         add(nTxtFd);

         submitBtn.setBounds(330,50,100,30);
         add(submitBtn);

         resetBtn.setBounds(440,50,100,30);
         resetBtn.addActionListener(ProcessClearFields);  //[查詢學生資料]操作畫面中[清除]按鈕加到[事件傾聽程式]
         add(resetBtn);

         cLabel.setBounds(10,130,100,30);
         add(cLabel);
         cTxtFd.setBounds(120,130,200,30);
         cTxtFd.setEditable(false);
         add(cTxtFd);

         eLabel.setBounds(10,170,100,30);
         add(eLabel);
         eTxtFd.setBounds(120,170,200,30);
         eTxtFd.setEditable(false);
         add(eTxtFd);

         setBackground(Color.green);
         setLocation(0,50);
         setSize(600,350);
         setLayout(null);
         setVisible(true);

     }

     //方法:清空容器內欄位
     public void clearPane(){

           nTxtFd.setText("");
           cTxtFd.setText("");
           eTxtFd.setText("");

     }

     //事件傾聽程式: 處理點按[清除]按鈕
     public ActionListener ProcessClearFields = new ActionListener(){
         public void actionPerformed(ActionEvent e){
      
              clearPane();
 
         }    
     };

 } //end for: class CHCI_QR_panel

 //-----------------------------------------------------------------------------------------






 //------------------------------------------------------------------------------------------
 //問題領域層類別
 //CPD_student: Class ProblemDomain_student (學生類別)

 class CPD_student{                    

     private String name;   //屬性:姓名字串
     private int chinese;   //屬性:國文成績整數
     private int english;   //屬性:英文成績整數
    
     //建構子:類別CPD_student
     public CPD_student(){
         name = "";
         chinese = 0;
         english = 0;
     }

     //方法:設定姓名
     public void setName(String aName){
         name = aName;
     }

     //方法:設定中文成績
     public void setChinese(int chinese_score){
         chinese = chinese_score;
     }

     //方法:設定英文成績
     public void setEnglish(int english_score){
         english = english_score;
     }

     //方法:取得姓名
     public String getName(){
         return( name );
     }

     //方法:取得中文成績
     public int getChinese(){
         return( chinese );
     }

     //方法:取得英文成績
     public int getEnglish(){
         return( english );
     }

 } //end for: class CPD_student

 //-----------------------------------------------------------------------------------------




 //-----------------------------------------------------------------------------------------
 //資料管理層類別
 //CDM_dbma: Class DatabaseManagement_database manipulation and acess (資料庫操作與存取類別)

 class CDM_dbma{                    

     Connection connection;
     Statement statement;
    
     //建構子:類別Cdbma
     public CDM_dbma(){
         //createDB();                //建立資料庫smsdb, 完成後請註解掉不作用,以免重複建立會出錯
         //createTB_student();        //建立資料表student, 完成後請註解掉不作用,以免重複建立會出錯
     }

    
     //傳入一筆[學生姓名],查詢出該學生的[國文]與[英文]成績資料並回傳其查得[學生姓名][國文][英文]字串結果
     public String[] findRD_in_TB_student(String aName){

             Connection connection;
             Statement statement;
             ResultSet result;
             String cmdData;

             String myName="";
             int myChinese=0, myEnglish=0;
             String[] myResult = new String[3];
     
             //資料庫前置作業
             try{
                   Class.forName("com.mysql.jdbc.Driver");
             } catch(Exception e){
                   JOptionPane.showMessageDialog(null,"MySQL驅動程式安裝失敗!"); 
             }

             try{
                   connection = DriverManager.getConnection("jdbc:mysql://localhost/smsdb" + "?user=root&password=mysql");
                   statement = connection.createStatement();
             } catch(SQLException e){
                   JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
             } catch(Exception e){
                   JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
             }

             try{
                    cmdData = "SELECT * FROM student WHERE name ='"+aName+"'";
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/smsdb" + "?user=root&password=mysql");
                    statement = connection.createStatement();
                    result = statement.executeQuery(cmdData);
          
                    while( result.next() ){
                          myName = result.getString("name");
                          myChinese = result.getInt("chinese");
                          myEnglish = result.getInt("english");
                     }
                     statement.close();
                     
              } catch(SQLException e){
                     JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
              } 

              myResult[0] = myName;
              myResult[1] = String.valueOf(myChinese);   //String.valueOf() 是將[整數值]轉成字串
              myResult[2] = String.valueOf(myEnglish);

              return( myResult );            
     }


     //傳入完整的一筆學生物件資料(aStudent),然後將此資料存入資料庫的student資料表中
     public void insertRD_into_TB_student(CPD_student aStudent){

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
                   connection = DriverManager.getConnection("jdbc:mysql://localhost/smsdb" + "?user=root&password=mysql");
                   statement = connection.createStatement();
             } catch(SQLException e){
                   JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
             } catch(Exception e){
                   JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
             }

             //在smsdb資料庫中, 新增一筆顧客資料到資料表: student   
             try{  
                    cmdData = "INSERT INTO student(name,chinese,english)"+
                              "VALUES('"+aStudent.getName()+"',"+aStudent.getChinese()+","+aStudent.getEnglish()+")";

                    connection = DriverManager.getConnection("jdbc:mysql://localhost/smsdb" + "?user=root&password=mysql");
                    statement = connection.createStatement();
                    statement.executeUpdate(cmdData);
                    JOptionPane.showMessageDialog(null,"在smsdb資料庫中, 成功寫入一筆[學生記錄]到student資料表中!");
                    statement.close();

             } catch(SQLException e){
                    JOptionPane.showMessageDialog(null,"在smsdb資料庫中, 寫入一筆[學生記錄]到student資料表中發生錯誤!");
             }
     } 

     
     //建立資料庫smsdb中的資料表:student
     public void createTB_student(){

            try{
                 connection = DriverManager.getConnection("jdbc:mysql://localhost/smsdb"+"?user=root&password=mysql");
                 statement = connection.createStatement();

                 String createTB = "CREATE TABLE student(";
                 createTB += "name             VARCHAR(15), ";    //學生姓名
                 createTB += "chinese          INT, ";            //國文成績
                 createTB += "english          INT)";             //英文成績

                 statement.executeUpdate(createTB);
                 JOptionPane.showMessageDialog(null,"student資料表建立成功!");
                 statement.close();
       
            } catch(SQLException e){
                 if(statement != null) 
                       JOptionPane.showMessageDialog(null,"student資料表已存在,可正常使用!"); 
                 else
                       JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
           } catch(Exception e){
              JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
           }  
     } 


     //方法:建立資料庫smsdb
     public void createDB(){

         //安裝MySQL驅動程式, 與建立資料庫smsdb
         try{
              Class.forName("com.mysql.jdbc.Driver");
         } catch(Exception e){
              JOptionPane.showMessageDialog(null,"MySQL驅動程式安裝失敗!"); 
         }

         //建立 smsdb資料庫
         try{
              connection = DriverManager.getConnection("jdbc:mysql://localhost/"+
                                                       "?user=root&password=mysql");
              statement = connection.createStatement();
              String createDB = "CREATE DATABASE smsdb";
              statement.executeUpdate(createDB);
              JOptionPane.showMessageDialog(null,"smsdb資料庫建立成功!");
              statement.close();
              
         } catch(SQLException e){
              if(statement != null) 
                  JOptionPane.showMessageDialog(null,"smsdb資料庫已存在,可正常使用!");
              else
                  JOptionPane.showMessageDialog(null,"MySQL無法啟動!");
         } catch(Exception e){
              JOptionPane.showMessageDialog(null,"資料庫操作發生其他錯誤!");
         }
       
     } //end for: public void createDB()
 } //end for: class CDM_dbma

//---------------------------------------------------


 //------------------------------------------------------------------------------------------
 //基礎層類別
 //CFD_check: Class FunDation_check (基礎層-檢查類別)

 class CFD_check{

     //檢查傳入的字串是否都由數字組成
     public boolean checkNumber(String s){
  
         int checkResult = 1;   //設定檢查結果為整數1
         int len = s.length();  //取得傳入字串長度(len)
         String[] sList = new String[len];   //建立一個長度為len的字串陣列,用來儲存字串拆解後各個字元用
         
         //將字串拆解成一個個字元,並儲存到陣列sList中,例如: s="abc98", sList={"a","b","c","9","8"};
         for(int x=0; x<len-1; x++)
             sList[x] = s.substring(x,x+1);

         sList[len-1] = s.substring(len-1);

         //逐一的對sList中每一元素檢查是否屬於0~9的數字字元,注意:第一個字元只能1~9.
         //只要有任一個字元不屬於數字字元,則 isNumber = 0, checkResult只要乘到一個0就會變為0
         for(int x=0; x<len; x++){

             int isNumber = 0;
             int startIndex = 0;

             if( x == 0 )
                   startIndex = 1;
             else 
                   startIndex = 0;

             for(int y=startIndex; y<10; y++){
                   if( sList[x].equals( String.valueOf(y) )  ) isNumber = 1;
             }
             checkResult = checkResult * isNumber;
         }
    
         //如果checkResult維持1代表每個字元都是數字字元,且第一個只能1~9,此為正確的整數格式
         if( checkResult == 1 )
               return(true);
         else
               return(false);

     }

 } //end for: class CFD_check

 //-----------------------------------------------------------------------------------------



//主程式: project.java

public class project{
   public static void main(String[] args){
  
        CSMS mySMS = new CSMS();             //CSMS: Class Student Management System 學生管理系統類別 
        
   }
}
             

     