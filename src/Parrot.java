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
  
   String[] myLabelString = {"����I�\�t��","�|���W��:","�q�ܸ��X:","�Ѿl���B:","���"};
     
   JTextField myTxtFdTo = new  JTextField();
   
   JButton login = new JButton("�n�J");
   JButton logout = new JButton("�n�X");
   JButton backSystem = new JButton("��ݺ޲z�t��");
   JButton Record = new JButton("���O����");
   
   static JTextField number = new JTextField();
   static JTextField last = new JTextField();
   static JTextField porint = new JTextField();
  
   JButton[][] myBtn = new JButton[3][6];
   static String[][] itemBtnString = {{"�۵P��K(4��)","������K(4��)","�@����K(5��)","������K(5��)","������K(5��)","�N����K(5��)"},
                            {"�۵P����(4��)","�������(4��)","�A������(5��)","�@������(5��)","�������(5��)","�N�����(5��)"},
                            {"�ɦ̿@��(25��)","�Ļ���(25��)","�ը���(15��)","�X���Y��(25��)","�¨���(15��)","�^�Y��(20��)"}};
   int[][]          price = {{4,4,5,5,5,5},
                             {4,4,5,5,5,5},
                             {25,25,15,25,15,20}};
      
   JButton FD = new JButton("��K��");
   JButton DG = new JButton("������");
   JButton SOUP = new JButton("���~/����");
 
   JButton[][] Btn = new JButton[4][4];
   String[][] BtnString = {{"�~�a","7","8","9"},
                          {"����","4","5","6"},
                          {"�M��","1","2","3"},
                          {"���b","�T�w","10","�M��"}};
   //�Ȧs�O�����x�s�Ŷ�  
   int w=0,total=0;
   String Ao=new String();
   String Bo=new String();
   String ao=new String();
   String Co;
   //�ɶ���ܤ�{��
   Calendar calendar = new GregorianCalendar();
   Label Atime=new Label("");
   
   //  table setting   *******
      Object[][] data={};
      String[] columns={"�ӫ~","�ƶq","���B"};
      DefaultTableModel mo = new DefaultTableModel(new Object[][]{},new Object[]{"�ӫ~","�ƶq","���B"});
      JTable jt=new JTable(data,columns);
      JTextField Rerd = new JTextField("");
      String[] aCustomer = {"0956883122","���l��","��K�ԷF","4","50"};
    
   CFrame(){
	   
	     Ao=""; // �P�_�e�m�@�~
	         
        for(int x=0;x<myLabel.length;x++){   //**** �r���J ****
           myLabel[x] = new JLabel(myLabelString[x],JLabel.CENTER);
         }
       
        
           
           for(int x=0;x<myPane.length;x++)
           myPane[x] = new JPanel();
           
           // panel[0] setting    *********
           myPane[0].setLayout( new FlowLayout());
           myPane[0].setBounds(0,0,1200,80);
           myPane[0].setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
           myLabel[0].setBounds(0,0,0,0);
           myLabel[0].setFont(new Font("�L�n������", Font.BOLD, 45));
           myPane[0].add(myLabel[0]);
         
           
           login.setBounds(0,0,100,80);
           login.setFont(new Font("�L�n������",Font.BOLD, 30));
           login.setForeground(Color.white);
           login.setBackground(Color.getHSBColor(0.04f,0.41f,0.83f));
           add(login);
           
           logout.setBounds(930,0,100,80);
           logout.setFont(new Font("�L�n������",Font.BOLD, 30));
           logout.setForeground(Color.white);
           logout.setBackground(Color.getHSBColor(0.0f,0.49f,1.00f));
           add(logout);
           
           backSystem.setFont(new Font("�L�n������",Font.BOLD, 21));
           backSystem.setBounds(1035,0,170,80);
           backSystem.setForeground(Color.white);
           backSystem.setBackground(Color.getHSBColor(0.13f,0.30f,0.85f));
           
           add(backSystem);
           
           //**********      myPane[1] setting     **********         
           myPane[1].setLayout(null);
           myPane[1].setBounds(0,80,460,330);
           myPane[1].setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
           myLabel[1].setBounds(0,10,200,50);
           myLabel[1].setFont(new Font("�L�n������", Font.BOLD, 25));
           myPane[1].add(myLabel[1]);
           
           number.setText("�ӻ�");
           number.setEditable(false);
           number.setBounds(200,15,200,50);
           myPane[1].add(number);
           
           myLabel[2].setBounds(0,80,200,50);
           myLabel[2].setFont(new Font("�L�n������", Font.BOLD, 25));
           myPane[1].add(myLabel[2]);
           
           last.setText("�ӻ�");
           last.setEditable(false);
           last.setBounds(200,80,200,50);
           myPane[1].add(last);
           
           myLabel[3].setBounds(0,150,200,50);
           myLabel[3].setFont(new Font("�L�n������", Font.BOLD, 25));
           myPane[1].add(myLabel[3]);
           
           porint.setText("�ӻ�");
           porint.setEditable(false);
           porint.setBounds(200,150,200,50);
           myPane[1].add(porint);
                     
           Record.setBounds(140,230,180,70);
           Record.setFont(new Font("�L�n������",Font.BOLD,25));
           Record.setBackground(Color.getHSBColor(0.10f,0.57f,1.0f));
           myPane[1].add(Record);
           
           //���æ����]�p********************************
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
           jt.setFont(new Font("�L�n������", Font.BOLD, 20));   //JTable�]�w
           jt.setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
           jt.setPreferredScrollableViewportSize(new Dimension(600,300));
           TableColumn column=jt.getColumnModel().getColumn(0);   //*******��l�e�ק��
           column.setPreferredWidth(150);
           jt.setRowHeight(50); 
           

                      
           myPane[4].add(new JScrollPane(jt),BorderLayout.CENTER);
           JLabel Reord = new JLabel("�`���B:");
           Reord.setBounds(10,690,120,80);
           Reord.setFont(new Font("�L�n������", Font.BOLD, 25));
           
           
           add(Reord);
           
           Rerd.setBounds(110,700,500,70);
           Rerd.setFont(new Font("�L�n������", Font.BOLD, 25));
           add(Rerd);
                      
           //myPane[5]     setting   ***********
           myPane[5].setLayout(new GridLayout(3,3,2,2));
           myPane[5].setBounds(460,80,180,325);
           myPane[5].setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
           
           FD.setFont(new Font("�L�n������", Font.BOLD, 30)); //******��K�]�w******
           FD.addActionListener(ProcessBtnPress);
           ImageIcon imagea = new ImageIcon(getClass().getResource("a.jpg"));
           imagea.setImage(imagea.getImage().getScaledInstance(256,144,Image.SCALE_DEFAULT));
           FD.setIcon(imagea);
          
           DG.setFont(new Font("�L�n������", Font.BOLD, 30));
           DG.addActionListener(ProcessBtnPress);
           ImageIcon imageb = new ImageIcon(getClass().getResource("c.jpg"));
           imageb.setImage(imageb.getImage().getScaledInstance(240,144,Image.SCALE_DEFAULT));
           DG.setIcon(imageb);
         
           SOUP.setFont(new Font("�L�n������", Font.BOLD, 30));
           SOUP.addActionListener(ProcessBtnPress);
           ImageIcon imagec = new ImageIcon(getClass().getResource("b.jpg"));
           imagec.setImage(imagec.getImage().getScaledInstance(250,190,Image.SCALE_DEFAULT));
           SOUP.setIcon(imagec);
       
           myPane[5].add(FD);
           myPane[5].add(DG);
           myPane[5].add(SOUP);		
           
           for(int x=0;x<myPane.length;x++)
           add(myPane[x]);
                       
         
         //���K�[�{���X*********************************************
        for(int x=0;x<1;x++){
            for(int y=0;y<myBtn[0].length;y++){
               myBtn[x][y] = new JButton(itemBtnString[x][y]);
              // myBtn[x][y].setBackground(Color.red);
               myBtn[x][y].setFont(new Font("�L�n������", Font.BOLD, 22));
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
               myBtn[x][y].setFont(new Font("�L�n������", Font.BOLD, 22));
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
               myBtn[x][y].setFont(new Font("�L�n������", Font.BOLD, 20));
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
               Btn[x][y].setFont(new Font("�L�n������", Font.BOLD, 35));
               Btn[x][y].setBackground(Color.getHSBColor(0.0f,0.30f,1.0f));
               Btn[x][0].setBackground(Color.getHSBColor(0.10f,0.57f,1.0f));
               myPane[3].add(Btn[x][y]);
               if ((x != 3) & (y != 0) )//�׶} �\��ʫ��s
               Btn[x][y].addActionListener(NumberBtnPress);
               else if(x==3 & y==2)// ���s10��ť
                       Btn[x][y].addActionListener(NumberBtnPress);
             }
         }
        //����ɶ��]�w
        setTime();
        Atime.setBounds(50,0,180,50);
        myPane[0].add(Atime);
        
        //�\��ʫ��s�ƥ��ť***********************************
        Btn[3][1].addActionListener(ADDBtnPress);//�T�w���s��ť
        Btn[3][3].addActionListener(EditBtnPress);//�M�����s��ť
        
        Btn[0][0].addActionListener(EitBtnPress);//���Υ~�a�M�ū��s��ť
        Btn[1][0].addActionListener(EitBtnPress);
        Btn[2][0].addActionListener(EitBtnPress);
        
        Btn[3][0].addActionListener(EndBtnPress);//���b���s��ť
        login.addActionListener(EndBtnPress);
        logout.addActionListener(EndBtnPress);
        backSystem.addActionListener(EndBtnPress);
        Record.addActionListener(EndBtnPress);
        
        jt.setModel(mo);

        setTitle("��R�ѥ��������q");
        setLayout(null);
        setLocation(100,100);
        setSize(1200,800);
        setBackground(Color.white);
        setVisible(true);
        setResizable(false);//�վ�j�p
        // setExtendedState(Frame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
   //���æ�����ť***********************************************
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
     //�D���K�[�{��************************************************
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
   
        
  //�Ʀr��K�[�{��****************************************************
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
    //��ƪ�K�[�{��***********
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
                	   JOptionPane.showMessageDialog(null,"�п���\�I!");
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
        public ActionListener EitBtnPress = new ActionListener(){     //���Υ~�a�M�Ũƥ��ť
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
        
      //��ƪ�R���{��**********************
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
       
       public ActionListener EndBtnPress = new ActionListener(){ //�x�s��ƪ�
           public void actionPerformed(ActionEvent e){
               if(e.getSource()==Btn[3][0]){                     //���b���s��ť
            	   aCustomer[0]=last.getText();
            	   aCustomer[1]=number.getText();
            	   int value = 0; 
            	   if(aCustomer[1].equals("�ӻ�")==false ){
            	      value = Integer.valueOf(porint.getText())-Integer.valueOf(Rerd.getText());
            	   if(value >= 0){
            	   porint.setText(Integer.toString(value));
            	   UPDATA_TB_transaction(aCustomer[0],porint.getText());
            	                  }else{
            		                 JOptionPane.showMessageDialog(null,"�l�B����! ���x�ȩεn�X�H�אּ�ӻ��I�\");
            	         
            	       }
            	   }
            	   if(value >= 0 | aCustomer[1].equals("�ӻ�")){
          	   for(int i=0;i<jt.getRowCount();i++){
                     for(int j=0;j<3;j++){
                    	   aCustomer[j+2]=(String)jt.getValueAt(i,j);
                           }
                     insertRD_into_TB_transaction(aCustomer);
                   }
          	   
            	   JOptionPane.showMessageDialog(null,"������\!");
            	   }
               	  //Ū����椺����
   		       }
               if(e.getSource()==login){AFrame frame1 = new AFrame();}
               if(e.getSource()==logout){
            	   porint.setText("�ӻ�");
            	   last.setText("�ӻ�");
            	   number.setText("�ӻ�");
            	   JOptionPane.showMessageDialog(null,"�A�w�g�n�X!");
            	   }
               if(e.getSource()==backSystem){AAFrame frame1 = new AAFrame();}
               if(e.getSource()==Record){fdtsFrame frame2 = new fdtsFrame();};
               mo.setRowCount(0);     //�M��
   			   mo.fireTableDataChanged();
   			   total = 0 ;
               Rerd.setText(Integer.toString(total));
               Ao="";   // ���\�P�_�^��
           }
       };
     
       public void insertRD_into_TB_transaction(String[] cmData){

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
                 connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                 statement = connection.createStatement();
           } catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"MySQL�L�k�Ұ�!");
           } catch(Exception e){
                 JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
           }

           //�bposdb��Ʈw��, �s�W�@���U�ȸ�ƨ��ƪ�: customer   
           try{  
                  cmdData = "INSERT INTO transaction(cm_no,cm_name,ts_name,ts_num,ts_mo)"+
                                          "VALUES('"+cmData[0]+"','"+cmData[1]+"','"+cmData[2]+"','"+cmData[3]+"',"+
                                                    cmData[4]+")";

                  connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                  statement = connection.createStatement();
                  statement.executeUpdate(cmdData);
                  
                  statement.close();

           } catch(SQLException e){
                  JOptionPane.showMessageDialog(null,"��ƪ�o�Ϳ��~!");
           }
       }
       public void UPDATA_TB_transaction(String cmData,String mo){

           Connection connection;
           Statement statement;
           String cmdDate;
           int moo=Integer.valueOf(mo);

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

           //�bposdb��Ʈw��, ��s�@���U�ȸ�ƨ��ƪ�: customer   
           try{  
        	      cmdDate = "UPDATE customer SET cm_mo ="+moo+" WHERE cm_no ="+cmData;
                  
                  connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                  statement = connection.createStatement();
                  statement.executeUpdate(cmdDate);
                  
                  statement.close();

           } catch(SQLException e){
        	      
                  JOptionPane.showMessageDialog(null,"��ƪ��s�o�Ϳ��~!");
           }
       }
       private void setTime()  //�]�w�t�ήɶ�
       {
           calendar.setTimeInMillis(System.currentTimeMillis());
           Atime.setText(calendar.get(Calendar.YEAR)+"/"+getMonth()+"/"+
           calendar.get(Calendar.DATE));
       }
       public int getMonth()  //���o��
       {
           return (calendar.get(Calendar.MONTH)+1);
       }

  
  
}

 public class Parrot{
   public static void main(String[] args){

       CFrame frame1 = new CFrame();

  }
 }

