/*************************************************************************************************************************
   ����: 1. ���{�����@�ӧ��㪺²��t��-�ǥͺ޲z�t��(sms),�ѦP�ǭ̾ǲ߰Ѧҥ�
         2. �{����project.java
         3. �Х��N���OCDB_dbma���غc�l����: createDB(); �P createTB_student(); �o���R������ѲŸ�(//)
            ���sĶ�����project,�H�K�إ�smsdb��Ʈw�Pstudent��ƪ�
                 //�غc�l:���OCDB_dbma
                 public CDM_dbma(){
                       //createDB();                //�إ߸�Ʈwsmsdb, ������е��ѱ����@��,�H�K���ƫإ߷|�X��
                       //createTB_student();        //�إ߸�ƪ�student, ������е��ѱ����@��,�H�K���ƫإ߷|�X��
                 }
         4. �ئnsmsdb��Ʈw�Pstudent��ƪ��,�A�N�W�z�����ѱ�(�[//);�M��s�ɭ��s�sĶ(javac project.java)
         5. ����A����java project, �N�i�ϥΦ��t�Υh[�s�W�ǥ͸��]�P[�d�߾ǥ͸��]
         6. ��Ū���{��,�N�i�H��Ӧ��t�Ϊ��{���Ψ�A���M�פ�
         �[�o!�u���V�O��},���ԯѯЪ��H,�~��F�Ѧ�ì�P����������.
**************************************************************************************************************************/


 //�פJ�ݭn���U���M��
 import javax.swing.*;
 import java.awt.event.*;
 import java.awt.*;
 import javax.swing.border.*;
 import java.sql.*;


 //���O��

 //------------------------------------------------------------------------------------------
 //�t�ΥD�����O
 //CSMS: Class StudentManagementSystem (�ǥͺ޲z�t��-SMS)

 class CSMS{                    

     //�إߥ��t�Ωһݪ��U�Ӫ���
     CHCI_frame  myFrame = new CHCI_frame();       //�H�����ʼh: �ϥΪ̤�������(myFrame,�̭��S�t��:myFrame.myMenu,myFrame.myOP_pane,myFrame.myQR_pane)
     CPD_student myStudent = new CPD_student();    //���D���h: �ǥͪ���(myStudent)    
     CDM_dbma myDBMA = new CDM_dbma();             //��ƺ޲z�h: ��Ʈw�ާ@�s������(myDBMA)
     CFD_check myCheck = new CFD_check();          //��¦�h: �ˬd����(myCheck)

     //CSMS���غc�l:
     CSMS(){
             
             //�]�w�t�Τ���������O�ѭ��@��[�ƥ��ť�{��]�t�d�B�z��ʧ@ 
             myFrame.myMenu.queryBtn.addActionListener(ProcessFunSelection);               //�D�\���檺[�d�߾ǥ͸��]���s
             myFrame.myMenu.insertBtn.addActionListener(ProcessFunSelection);              //�D�\���檺[�s�W�ǥ͸��]���s
             myFrame.myMenu.exitBtn.addActionListener(ProcessFunSelection);                //�D�\���檺[�����t�����}]���s
             myFrame.myOP_pane.saveBtn.addActionListener(ProcessSaveStudentRecord);        //[�s�W�ǥ͸��]�ާ@�e����[�x�s]���s
             myFrame.myQR_pane.submitBtn.addActionListener(ProcessSubmitStudentQuery);     //[�d�߾ǥ͸��]�d�ߵe����[�d��]���s
     }
     

      
     //�ƥ��ť�{��: �B�z�D�\������
     public ActionListener ProcessFunSelection = new ActionListener(){
         public void actionPerformed(ActionEvent e){
      
              if( e.getSource() == myFrame.myMenu.insertBtn ){
                       myFrame.myOP_pane.setVisible(true);        //���[�s�W�ǥ͸��]�ާ@�e��
                       myFrame.myOP_pane.clearPane();             //�M��[�s�W�ǥ͸��]�ާ@�e��
                       myFrame.myQR_pane.setVisible(false);       //����[�d�߾ǥ͸��]�d�ߵe��      
                       myFrame.myQR_pane.clearPane();             //�M��[�d�߾ǥ͸��]�d�ߵe��
              }

              if( e.getSource() == myFrame.myMenu.queryBtn ){
                       myFrame.myOP_pane.setVisible(false);       //����[�s�W�ǥ͸��]�ާ@�e��
                       myFrame.myOP_pane.clearPane();             //�M��[�s�W�ǥ͸��]�ާ@�e��
                       myFrame.myQR_pane.setVisible(true);        //���[�d�߾ǥ͸��]�d�ߵe��
                       myFrame.myQR_pane.clearPane();             //�M��[�d�߾ǥ͸��]�d�ߵe��
              }

              if( e.getSource() == myFrame.myMenu.exitBtn ){
                       System.exit(0);                            //�����{��
              }
 
         }    
     };

     //�ƥ��ť�{��: �B�z�ǥ͸���x�s
     public ActionListener ProcessSaveStudentRecord = new ActionListener(){
         public void actionPerformed(ActionEvent e){
              
              boolean checkPass = true;            //�ΨӰO��[��J���ǥ͸��]�ˬd���G
              String nameString = myFrame.myOP_pane.nTxtFd.getText();    //���o[��J���ǥ͸��]����[�m�W�r��]
              String chineseString = myFrame.myOP_pane.cTxtFd.getText().trim();  //���o[��J���ǥ͸��]����[��妨�Z�r��] (��:trim()��k�|��r��᭱�ťհ���)
              String englishString = myFrame.myOP_pane.eTxtFd.getText().trim();  //���o[��J���ǥ͸��]����[�^�妨�Z�r��]

              if( myCheck.checkNumber( chineseString ) == false ){  //�Q���ˬd����(myCheck)��checkNumber()��k,�ˬdchineseString�O�_�����T���ƭȮ榡,�p:98,80,...��
                     checkPass = false;
                     JOptionPane.showMessageDialog(null,"[��妨�Z] ��J��ƿ��~!","�ާ@ĵ�T",JOptionPane.ERROR_MESSAGE);
              }

              if( myCheck.checkNumber( englishString ) == false ){  //�Q���ˬd����(myCheck)��checkNumber()��k,�ˬdenglishString�O�_�����T���ƭȮ榡,�p:98,80,...��
                     checkPass = false;
                     JOptionPane.showMessageDialog(null,"[�^�妨�Z] ��J��ƿ��~!","�ާ@ĵ�T",JOptionPane.ERROR_MESSAGE);
              }

              if(  nameString.length() == 0 ){    //�ˬdnameString�O�_����J����r��,length()���p��r����פ�k,�Y���׬�0�h����J���
                     checkPass = false;
                     JOptionPane.showMessageDialog(null,"[�ǥͩm�W] �ťե���J���!","�ާ@ĵ�T",JOptionPane.ERROR_MESSAGE);
              }

              //�p�G�W�z�T���ˬd���S�o�{���~,�hcheckPass�|����true,�Y�q�L�ˬd,�]���N�ǥͪ��m�W,������,�^����Ƴ]�w��myStudent���󤤹����ݩʽ�  
              if( checkPass == true ){
                    myStudent.setName(nameString);
                    myStudent.setChinese( Integer.parseInt(chineseString) );    //��:Integer.parseInt()�O�N�r���ন��ƪ���k
                    myStudent.setEnglish( Integer.parseInt(englishString) );

                    myDBMA.insertRD_into_TB_student(myStudent);   //�N�ǥͪ���ǤJ[��Ʈw�ާ@�s������(myDBMA)]���x�s�ǥͬ�����k(insertRD_into_TB_student())�h�x�s�ǥͬ������Ʈw
              }
 
         }    
     };

     //�ƥ��ť�{��: �B�z�ǥ͸�Ƭd��
     public ActionListener ProcessSubmitStudentQuery = new ActionListener(){
         public void actionPerformed(ActionEvent e){
      
              String nameString = myFrame.myQR_pane.nTxtFd.getText();  //���o[��J���d�߸��]����[�m�W�r��]
              
              if(  nameString.length() > 0 ){    //�p�G[�m�W�r��]���פj��0,�Y����J�m�W���,�~�i�J�d�߳B�z
                     
                     String[] findResult = myDBMA.findRD_in_TB_student(nameString);   //�I�s[��Ʈw�ާ@�s������(myDBMA)]���d�߾ǥͬ�����k(findRD_into_TB_student())�h�d�߾ǥͬ���,�æ^���x�s��findResult��
                     myFrame.myQR_pane.cTxtFd.setText(findResult[1]);    //�NfindResult�}�C�˪�����1������(�Y���妨�Z�r��)��ܦb�d�ߵ��G���
                     myFrame.myQR_pane.eTxtFd.setText(findResult[2]);    //�NfindResult�}�C�˪�����2������(�Y�^�妨�Z�r��)��ܦb�d�ߵ��G���

              } else {
                   JOptionPane.showMessageDialog(null,"[�ǥͩm�W] �ťե���J��ơA�ж�g��A�d��!","�ާ@ĵ�T",JOptionPane.ERROR_MESSAGE);
              }
         }    
     };


 } //end for: class CSMS

 //-----------------------------------------------------------------------------------------




 //------------------------------------------------------------------------------------------
 //�H�����ʼh���O
 //CHCI_frame: Class HumanComputerInteraction_frame (�H������-�ج[���O)

 class CHCI_frame extends JFrame{      //�t�Ϊ�����

     CHCI_menu  myMenu = new CHCI_menu();                  //�D�\���檫��(��JPanel,���t3�ӫ��s)     
     CHCI_OP_panel  myOP_pane = new CHCI_OP_panel();       //�s�W�ǥ͸�Ƶe������(��JPanel,���t����,��r���,���s��)
     CHCI_QR_panel  myQR_pane = new CHCI_QR_panel();       //�d�߾ǥ͸�Ƶe������(��JPanel,���t����,��r���,���s��)              
    
     //�غc�l:���OCHCI_frame
     public CHCI_frame(){

         add(myMenu);        //�N[�D�\���檫��]�[�즹������
         add(myOP_pane);     //�N[�s�W�ǥ͸�Ƶe������]�[�즹������
         add(myQR_pane);     //�N[�d�߾ǥ͸�Ƶe������]�[�즹������

         myOP_pane.setVisible(true);   //�w�][�s�W�ǥ͸��]�e�����
         myQR_pane.setVisible(false);  //�w�][�d�߾ǥ͸��]�e������

         //�t�ε������򥻳]�w
         setTitle("�ǥͺ޲z�t��-Student Management System (SMS)");
         setLocation(100,50);
         setSize(600,400);
         setLayout(null);
         setVisible(true);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

     }

 } //end for: class CHCI_frame

 //-----------------------------------------------------------------------------------------


 //------------------------------------------------------------------------------------------
 //�H�����ʼh���O
 //CHCI_menu: Class HumanComputerInteraction_menu (�H������-�D�\�������O)

 class CHCI_menu extends JPanel{    

     JButton queryBtn = new JButton("�d�߾ǥ͸��"); 
     JButton insertBtn = new JButton("�s�W�ǥ͸��"); 
     JButton exitBtn = new JButton("�����t�����}");                  
    
     //�غc�l:���OCHCI_menu
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
 //�H�����ʼh���O
 //CHCI_OP_panel: Class HumanComputerInteraction_OPeration_panel (�H������-[�s�W�ǥ͸��]�ާ@�e�����O)

 class CHCI_OP_panel extends JPanel{

     JLabel tLabel = new JLabel("�п�J�m�W���Z");
     JLabel nLabel = new JLabel("�ǥͩm�W");
     JLabel cLabel = new JLabel("��妨�Z");
     JLabel eLabel = new JLabel("�^�妨�Z");

     JTextField nTxtFd = new JTextField("");
     JTextField cTxtFd = new JTextField("");
     JTextField eTxtFd = new JTextField("");

     JButton clearBtn = new JButton("�M��"); 
     JButton saveBtn = new JButton("�x�s");                   
    
     //�غc�l:���OCHCI_OP_panel
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
         clearBtn.addActionListener(ProcessClearFields);   //[�s�W�ǥ͸��]�ާ@�e����[�M��]���s�[��[�ƥ��ť�{��]
         add(clearBtn);

         saveBtn.setBounds(350,250,100,40);
         add(saveBtn);

         setBackground(Color.yellow);
         setLocation(0,50);
         setSize(600,350);
         setLayout(null);
         setVisible(true);

     }

     //��k:�M�Ůe�������
     public void clearPane(){

           nTxtFd.setText("");
           cTxtFd.setText("");
           eTxtFd.setText("");

     }

     //�ƥ��ť�{��: �B�z�I��[�M��]���s
     public ActionListener ProcessClearFields = new ActionListener(){
         public void actionPerformed(ActionEvent e){
      
              clearPane();
 
         }    
     };
     

           

 } //end for: class CHCI_OP_panel

 //-----------------------------------------------------------------------------------------



 //------------------------------------------------------------------------------------------
 //�H�����ʼh���O
 //CHCI_QR_panel: Class HumanComputerInteraction_QueRy_panel (�H������-[�d�߾ǥ͸��]�ާ@�e�����O)

 class CHCI_QR_panel extends JPanel{

     JLabel tLabel = new JLabel("�п�J���d�ߪ��ǥͩm�W");
     JLabel nLabel = new JLabel("�ǥͩm�W");
     JLabel cLabel = new JLabel("�d�o��妨�Z");
     JLabel eLabel = new JLabel("�d�o�^�妨�Z");

     JTextField nTxtFd = new JTextField("");
     JTextField cTxtFd = new JTextField("");
     JTextField eTxtFd = new JTextField("");

     JButton resetBtn = new JButton("�M��"); 
     JButton submitBtn = new JButton("�d��");                   
    
     //�غc�l:���OCHCI_QR_panel
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
         resetBtn.addActionListener(ProcessClearFields);  //[�d�߾ǥ͸��]�ާ@�e����[�M��]���s�[��[�ƥ��ť�{��]
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

     //��k:�M�Ůe�������
     public void clearPane(){

           nTxtFd.setText("");
           cTxtFd.setText("");
           eTxtFd.setText("");

     }

     //�ƥ��ť�{��: �B�z�I��[�M��]���s
     public ActionListener ProcessClearFields = new ActionListener(){
         public void actionPerformed(ActionEvent e){
      
              clearPane();
 
         }    
     };

 } //end for: class CHCI_QR_panel

 //-----------------------------------------------------------------------------------------






 //------------------------------------------------------------------------------------------
 //���D���h���O
 //CPD_student: Class ProblemDomain_student (�ǥ����O)

 class CPD_student{                    

     private String name;   //�ݩ�:�m�W�r��
     private int chinese;   //�ݩ�:��妨�Z���
     private int english;   //�ݩ�:�^�妨�Z���
    
     //�غc�l:���OCPD_student
     public CPD_student(){
         name = "";
         chinese = 0;
         english = 0;
     }

     //��k:�]�w�m�W
     public void setName(String aName){
         name = aName;
     }

     //��k:�]�w���妨�Z
     public void setChinese(int chinese_score){
         chinese = chinese_score;
     }

     //��k:�]�w�^�妨�Z
     public void setEnglish(int english_score){
         english = english_score;
     }

     //��k:���o�m�W
     public String getName(){
         return( name );
     }

     //��k:���o���妨�Z
     public int getChinese(){
         return( chinese );
     }

     //��k:���o�^�妨�Z
     public int getEnglish(){
         return( english );
     }

 } //end for: class CPD_student

 //-----------------------------------------------------------------------------------------




 //-----------------------------------------------------------------------------------------
 //��ƺ޲z�h���O
 //CDM_dbma: Class DatabaseManagement_database manipulation and acess (��Ʈw�ާ@�P�s�����O)

 class CDM_dbma{                    

     Connection connection;
     Statement statement;
    
     //�غc�l:���OCdbma
     public CDM_dbma(){
         //createDB();                //�إ߸�Ʈwsmsdb, ������е��ѱ����@��,�H�K���ƫإ߷|�X��
         //createTB_student();        //�إ߸�ƪ�student, ������е��ѱ����@��,�H�K���ƫإ߷|�X��
     }

    
     //�ǤJ�@��[�ǥͩm�W],�d�ߥX�Ӿǥͪ�[���]�P[�^��]���Z��ƨæ^�Ǩ�d�o[�ǥͩm�W][���][�^��]�r�굲�G
     public String[] findRD_in_TB_student(String aName){

             Connection connection;
             Statement statement;
             ResultSet result;
             String cmdData;

             String myName="";
             int myChinese=0, myEnglish=0;
             String[] myResult = new String[3];
     
             //��Ʈw�e�m�@�~
             try{
                   Class.forName("com.mysql.jdbc.Driver");
             } catch(Exception e){
                   JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!"); 
             }

             try{
                   connection = DriverManager.getConnection("jdbc:mysql://localhost/smsdb" + "?user=root&password=mysql");
                   statement = connection.createStatement();
             } catch(SQLException e){
                   JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
             } catch(Exception e){
                   JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
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
                     JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
              } 

              myResult[0] = myName;
              myResult[1] = String.valueOf(myChinese);   //String.valueOf() �O�N[��ƭ�]�ন�r��
              myResult[2] = String.valueOf(myEnglish);

              return( myResult );            
     }


     //�ǤJ���㪺�@���ǥͪ�����(aStudent),�M��N����Ʀs�J��Ʈw��student��ƪ�
     public void insertRD_into_TB_student(CPD_student aStudent){

             Connection connection;
             Statement statement;
             String cmdData;

             //��Ʈw�e�m�@�~
             try{
                   Class.forName("com.mysql.jdbc.Driver");
             } catch(Exception e){
                   JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!"); 
             }

             try{
                   connection = DriverManager.getConnection("jdbc:mysql://localhost/smsdb" + "?user=root&password=mysql");
                   statement = connection.createStatement();
             } catch(SQLException e){
                   JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
             } catch(Exception e){
                   JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
             }

             //�bsmsdb��Ʈw��, �s�W�@���U�ȸ�ƨ��ƪ�: student   
             try{  
                    cmdData = "INSERT INTO student(name,chinese,english)"+
                              "VALUES('"+aStudent.getName()+"',"+aStudent.getChinese()+","+aStudent.getEnglish()+")";

                    connection = DriverManager.getConnection("jdbc:mysql://localhost/smsdb" + "?user=root&password=mysql");
                    statement = connection.createStatement();
                    statement.executeUpdate(cmdData);
                    JOptionPane.showMessageDialog(null,"�bsmsdb��Ʈw��, ���\�g�J�@��[�ǥͰO��]��student��ƪ�!");
                    statement.close();

             } catch(SQLException e){
                    JOptionPane.showMessageDialog(null,"�bsmsdb��Ʈw��, �g�J�@��[�ǥͰO��]��student��ƪ��o�Ϳ��~!");
             }
     } 

     
     //�إ߸�Ʈwsmsdb������ƪ�:student
     public void createTB_student(){

            try{
                 connection = DriverManager.getConnection("jdbc:mysql://localhost/smsdb"+"?user=root&password=mysql");
                 statement = connection.createStatement();

                 String createTB = "CREATE TABLE student(";
                 createTB += "name             VARCHAR(15), ";    //�ǥͩm�W
                 createTB += "chinese          INT, ";            //��妨�Z
                 createTB += "english          INT)";             //�^�妨�Z

                 statement.executeUpdate(createTB);
                 JOptionPane.showMessageDialog(null,"student��ƪ�إߦ��\!");
                 statement.close();
       
            } catch(SQLException e){
                 if(statement != null) 
                       JOptionPane.showMessageDialog(null,"student��ƪ�w�s�b,�i���`�ϥ�!"); 
                 else
                       JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
           } catch(Exception e){
              JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
           }  
     } 


     //��k:�إ߸�Ʈwsmsdb
     public void createDB(){

         //�w��MySQL�X�ʵ{��, �P�إ߸�Ʈwsmsdb
         try{
              Class.forName("com.mysql.jdbc.Driver");
         } catch(Exception e){
              JOptionPane.showMessageDialog(null,"MySQL�X�ʵ{���w�˥���!"); 
         }

         //�إ� smsdb��Ʈw
         try{
              connection = DriverManager.getConnection("jdbc:mysql://localhost/"+
                                                       "?user=root&password=mysql");
              statement = connection.createStatement();
              String createDB = "CREATE DATABASE smsdb";
              statement.executeUpdate(createDB);
              JOptionPane.showMessageDialog(null,"smsdb��Ʈw�إߦ��\!");
              statement.close();
              
         } catch(SQLException e){
              if(statement != null) 
                  JOptionPane.showMessageDialog(null,"smsdb��Ʈw�w�s�b,�i���`�ϥ�!");
              else
                  JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
         } catch(Exception e){
              JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
         }
       
     } //end for: public void createDB()
 } //end for: class CDM_dbma

//---------------------------------------------------


 //------------------------------------------------------------------------------------------
 //��¦�h���O
 //CFD_check: Class FunDation_check (��¦�h-�ˬd���O)

 class CFD_check{

     //�ˬd�ǤJ���r��O�_���ѼƦr�զ�
     public boolean checkNumber(String s){
  
         int checkResult = 1;   //�]�w�ˬd���G�����1
         int len = s.length();  //���o�ǤJ�r�����(len)
         String[] sList = new String[len];   //�إߤ@�Ӫ��׬�len���r��}�C,�Ψ��x�s�r���ѫ�U�Ӧr����
         
         //�N�r���Ѧ��@�ӭӦr��,���x�s��}�CsList��,�Ҧp: s="abc98", sList={"a","b","c","9","8"};
         for(int x=0; x<len-1; x++)
             sList[x] = s.substring(x,x+1);

         sList[len-1] = s.substring(len-1);

         //�v�@����sList���C�@�����ˬd�O�_�ݩ�0~9���Ʀr�r��,�`�N:�Ĥ@�Ӧr���u��1~9.
         //�u�n�����@�Ӧr�����ݩ�Ʀr�r��,�h isNumber = 0, checkResult�u�n����@��0�N�|�ܬ�0
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
    
         //�p�GcheckResult����1�N��C�Ӧr�����O�Ʀr�r��,�B�Ĥ@�ӥu��1~9,�������T����Ʈ榡
         if( checkResult == 1 )
               return(true);
         else
               return(false);

     }

 } //end for: class CFD_check

 //-----------------------------------------------------------------------------------------



//�D�{��: project.java

public class project{
   public static void main(String[] args){
  
        CSMS mySMS = new CSMS();             //CSMS: Class Student Management System �ǥͺ޲z�t�����O 
        
   }
}
             

     