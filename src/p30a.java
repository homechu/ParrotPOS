import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel; 
import java.sql.*;

class BFrame extends JFrame{
     JPanel pane1=new JPanel();
     JPanel pane2=new JPanel();
     JPanel pane3=new JPanel();
     JPanel pane4=new JPanel();
     JPanel pane5=new JPanel();
     JPanel pane6=new JPanel();
     JPanel[] mypane=new JPanel[5]; 
     Font font1 = new Font("微軟正黑體", Font.BOLD, 40);
     Font font2 = new Font("微軟正黑體", Font.BOLD, 30);
     Font font3 = new Font("微軟正黑體", Font.PLAIN,50);
     JLabel myLabel1=new JLabel("鍋貼店-進銷存管理系統");
     JLabel myLabel2=new JLabel("櫻舞科技公司");
     JLabel myLabel3=new JLabel(" ");
     JLabel myLabel4=new JLabel();
     JButton escBtn=new JButton("Esc結束離開");
     JButton[][] fdBtn = new JButton[1][3];
     String[][] fdBtnString = {{"食材進貨","查詢存貨","存貨清單"}};
     JButton[][] ftBtn = new JButton[1][3];
     String[][] ftBtnString = {{"新增廠商","查詢廠商","廠商清單"}};
     JButton[][] cmBtn = new JButton[1][3];
     String[][] cmBtnString = {{"新增會員","查詢會員","會員清單"}};
     JButton[][] epBtn = new JButton[1][3];
     String[][] epBtnString = {{"新增員工","查詢員工","員工清單"}};
     JButton[][] tsBtn = new JButton[1][2];
     String[][] tsBtnString = {{"查詢交易","交易清單"}};
     JButton[][] listBtn=new JButton[5][1];
     String[][] listBtnString={{"食材相關資料"},{"廠商相關資料"},{"會員相關資料"},{"人事相關資料"},{"交易紀錄"}};
     
     final CardLayout cards = new CardLayout(); //卡片切換方程式*******************************
     final JPanel container = new JPanel(cards);
   
     
     
     BFrame(){
    	   	 
       pane1.setBounds(0,0,1200,50);
       pane1.setBackground(Color.getHSBColor(0.14f,0.7f,1.0f));
       pane1.setLayout(null);
       add(pane1);
       myLabel1.setBounds(400,10,800,35);
       myLabel1.setFont(font1);
       pane1.add(myLabel1);
       
       pane2.setBounds(3,50,244,530);
       pane2.setLayout(null);
       pane2.setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
       add(pane2);
       pane2.setLayout( new GridLayout(5,1,1,5));
       for(int x=0; x<5; x++){
          listBtn[x][0] = new JButton(listBtnString[x][0]);

          listBtn[x][0].setFont(font2);
          listBtn[x][0].setBackground(Color.getHSBColor(0.10f,0.57f,1.0f));

          
          listBtn[x][0].addActionListener(ProcessBtnPress); 
          pane2.add(listBtn[x][0]);
          }


       pane3.setBounds(0,585,248,180);
       pane3.setBackground(Color.getHSBColor(0.16f,0.36f,1.0f));
       pane3.setLayout(null);
       add(pane3);
       escBtn.setBounds(0,0,248,180);
       escBtn.setFont(font2);
       escBtn.setBackground(Color.getHSBColor(0.0f,0.8f,0.97f));
       escBtn.addActionListener(ProcessBtnPress);
       pane3.add(escBtn);

       pane4.setBounds(248,50,950,110);
       pane4.setBackground(Color.getHSBColor(0.16f,0.40f,1.0f));
       pane4.setLayout(null);
       add(pane4);
       myLabel2.setBounds(735,35,200,100);
       myLabel2.setFont(font2);
       myLabel2.setForeground(Color.getHSBColor(0.7f,1.0f,0.7f));
       pane4.add(myLabel2);
       myLabel3.setBounds(10,0,700,100);
       myLabel3.setForeground(Color.getHSBColor(0.0f,0.0f,0.0f));
       myLabel3.setFont(font3);
       pane4.add(myLabel3);
       
       myLabel4.setBounds(0,0,950,427);
       myLabel4 = new JLabel(new ImageIcon(ClassLoader.getSystemResource("logo.jpg"))); //放入圖片
       
       //卡片進入方程式*******************************
       add(container);
       container.setBounds(248,153,950,428);
       container.setBackground(Color.getHSBColor(0.16f,0.4f,1.0f));
       container.add(myLabel4,BorderLayout.CENTER);
       container.add(new incmFrame(),"incmFrame");
       container.add(new fdcmFrame(),"fdcmFrame");
      
       container.add(new inepFrame(),"inepFrame");
       container.add(new fdepFrame(),"fdepFrame");
       
       container.add(new infdFrame(),"infdFrame");
       container.add(new fdfdFrame(), "fdfdFrame");
       
       container.add(new inftFrame(), "inftFrame");
       container.add(new fdftFrame(), "fdftFrame");
       
       container.add(new fdtsFrame(), "fdtsFrame");
       container.add(new transaction(), "transaction");
       
       for(int x=0;x<mypane.length;x++){
    	   mypane[x]=new JPanel();
    	   mypane[x].setBounds(250,585,948,180);
    	   add(mypane[x]);
    	   mypane[x].setLayout(new GridLayout(1,3,20,0));
    	   mypane[x].setVisible(false);
       }
       
       mypane[0].setBackground(Color.getHSBColor(0.16f,0.40f,1.0f));
       for(int y=0;y<3;y++){
    	   fdBtn[0][y]=new JButton(fdBtnString[0][y]);
           fdBtn[0][y].setFont(font1);
           fdBtn[0][0].setForeground(Color.white);
           fdBtn[0][0].setBackground(Color.getHSBColor(0.1f,0.57f,0.76f));
           if(fdBtn[0][1]!=null){
           fdBtn[0][1].setForeground(Color.white);
           fdBtn[0][1].setBackground(Color.getHSBColor(0.32f,0.58f,0.77f));
           }
           if(fdBtn[0][2]!=null){
           fdBtn[0][2].setForeground(Color.white);
           fdBtn[0][2].setBackground(Color.getHSBColor(0.43f,0.27f,0.57f));
           
           }
           fdBtn[0][y].addActionListener(ProcessBtnPress);
    	   mypane[0].add(fdBtn[0][y]);
       }
       mypane[1].setBackground(Color.getHSBColor(0.16f,0.40f,1.0f));
       for(int y=0;y<3;y++){
    	   ftBtn[0][y]=new JButton(ftBtnString[0][y]);
           ftBtn[0][y].setFont(font1);
           ftBtn[0][0].setForeground(Color.white);
           ftBtn[0][0].setBackground(Color.getHSBColor(0.1f,0.57f,0.76f));
           if(ftBtn[0][1]!=null){
           ftBtn[0][1].setForeground(Color.white);
           ftBtn[0][1].setBackground(Color.getHSBColor(0.32f,0.58f,0.77f));
           }
           if(ftBtn[0][2]!=null){
           ftBtn[0][2].setForeground(Color.white);
           ftBtn[0][2].setBackground(Color.getHSBColor(0.43f,0.27f,0.57f));
           
           }
    	   ftBtn[0][y].addActionListener(ProcessBtnPress);
    	   mypane[1].add(ftBtn[0][y]);
       }
       mypane[2].setBackground(Color.getHSBColor(0.16f,0.40f,1.0f));
       for(int y=0;y<3;y++){
    	   cmBtn[0][y]=new JButton(cmBtnString[0][y]);
           cmBtn[0][y].setFont(font1);
           cmBtn[0][0].setForeground(Color.white);
           cmBtn[0][0].setBackground(Color.getHSBColor(0.1f,0.57f,0.76f));
           if (cmBtn[0][1]!=null){
        	   cmBtn[0][1].setForeground(Color.white);
        	   cmBtn[0][1].setBackground(Color.getHSBColor(0.32f,0.58f,0.77f));
        	   }
           if(cmBtn[0][2]!=null){
        	   cmBtn[0][2].setForeground(Color.white);
               cmBtn[0][2].setBackground(Color.getHSBColor(0.43f,0.27f,0.57f));        	   
           }
           cmBtn[0][y].addActionListener(ProcessBtnPress);
    	   mypane[2].add(cmBtn[0][y]);
       }
       mypane[3].setBackground(Color.getHSBColor(0.16f,0.40f,1.0f));
       for(int y=0;y<3;y++){
    	   epBtn[0][y]=new JButton(epBtnString[0][y]);
           epBtn[0][y].setFont(font1);
           epBtn[0][0].setForeground(Color.white);
           epBtn[0][0].setBackground(Color.getHSBColor(0.1f,0.57f,0.76f));
           if (epBtn[0][1]!=null){
        	   epBtn[0][1].setForeground(Color.white);
        	   epBtn[0][1].setBackground(Color.getHSBColor(0.32f,0.58f,0.77f));
        	   }
           if(epBtn[0][2]!=null){
        	   epBtn[0][2].setForeground(Color.white);
               epBtn[0][2].setBackground(Color.getHSBColor(0.43f,0.27f,0.57f));        	   
           }
    	   epBtn[0][y].addActionListener(ProcessBtnPress);
    	   mypane[3].add(epBtn[0][y]);
       }
       mypane[4].setBackground(Color.getHSBColor(0.16f,0.40f,1.0f));
       for(int y=0;y<2;y++){
    	   tsBtn[0][y]=new JButton(tsBtnString[0][y]);
           tsBtn[0][y].setFont(font1);
           tsBtn[0][0].setForeground(Color.white);
           tsBtn[0][0].setBackground(Color.getHSBColor(0.32f,0.58f,0.77f));
           if(tsBtn[0][1]!=null){
            tsBtn[0][1].setForeground(Color.white);
            tsBtn[0][1].setBackground(Color.getHSBColor(0.43f,0.27f,0.57f));         
           }
    	   tsBtn[0][y].addActionListener(ProcessBtnPress);
    	   mypane[4].add(tsBtn[0][y]);
       }
       
      
       
       setTitle("後端管理系統");
       setLayout(null);
       setLocation(100,120);
       setSize(1206,800);
       setVisible(true);
       setResizable(false);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       

     
     }


   //監聽
     public ActionListener ProcessBtnPress = new ActionListener(){
       public void actionPerformed(ActionEvent e){
         
    	   
         //按鈕顯示
         for(int x=0;x<listBtn.length;x++){
        	 mypane[x].setVisible(false);
           if(e.getSource() == listBtn[x][0]){
              myLabel3.setText(listBtnString[x][0]);
              mypane[x].setVisible(true);
          }
         }

         //食材啽紐
         if(e.getSource() == fdBtn[0][0]){
        	 mypane[0].setVisible(true);
        	 cards.show(container, "infdFrame");
         }
         if(e.getSource() == fdBtn[0][1]){
        	 mypane[0].setVisible(true);
        	 cards.show(container, "fdfdFrame");
         }
         if(e.getSource() == fdBtn[0][2]){
        	 mypane[0].setVisible(true);
        	 container.add(new food(), "food");
             
        	 cards.show(container, "food");
        	 
         }
         
         //廠商按鈕
         if(e.getSource() == ftBtn[0][0]){
        	 mypane[1].setVisible(true);
        	 cards.show(container, "inftFrame");
         }
         if(e.getSource() == ftBtn[0][1]){
        	 mypane[1].setVisible(true);
        	 cards.show(container, "fdftFrame");
         }
         if(e.getSource() == ftBtn[0][2]){
        	 mypane[1].setVisible(true);
        	 container.add(new factory(), "factory");
             
        	 cards.show(container, "factory");
         }
         
         //會員按鈕
         if(e.getSource() == cmBtn[0][0]){
        	 mypane[2].setVisible(true);
        	 cards.show(container, "incmFrame");
             
             
         }
         if(e.getSource() == cmBtn[0][1]){
        	 mypane[2].setVisible(true);
        	 cards.show(container, "fdcmFrame");
        	 
         }
         if(e.getSource() == cmBtn[0][2]){
        	 mypane[2].setVisible(true);
        	 
             container.add(new customer(),"customer");
        	 cards.show(container, "customer");
         }
         
         //人事按鈕
         if(e.getSource() == epBtn[0][0]){
        	 mypane[3].setVisible(true);
        	 cards.show(container, "inepFrame");
         }
         if(e.getSource() == epBtn[0][1]){
        	 mypane[3].setVisible(true);
        	 cards.show(container, "fdepFrame");
         }
         if(e.getSource() == epBtn[0][2]){
        	 mypane[3].setVisible(true);
        	 container.add(new employee(),"employee");
        	 cards.show(container, "employee");
         }
         
         //交易紀錄按鈕
         if(e.getSource() == tsBtn[0][0]){
        	 mypane[4].setVisible(true);
        	 cards.show(container, "fdtsFrame");
         }
         if(e.getSource() == tsBtn[0][1]){
        	 mypane[4].setVisible(true);
        	 cards.show(container, "transaction");
         }
         
         
         //離開按鈕
         if(e.getSource() == escBtn){ 
            BFrame.this.dispose();  //關閉視窗
         }        
          
         
       }
     };
}                 
//主程式
public class p30a{
   public static void main(String[] args){

        BFrame frame1=new BFrame();
        
   }
}