//�����s�f�M��
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*; 
import java.sql.*;


 public class food extends JPanel {
	
	
	
	JTable tableA = new JTable();
	DefaultTableModel tm = new DefaultTableModel(new Object[][]{},new Object[]{"�����W��","�i�f���","�t�ӦW��","�ƶq","���B"});

	JLabel myLabel1 = new JLabel("�����s�f�M��");
	Font font= new Font("�з���",Font.BOLD,40);
        JButton myBtn = new JButton("���}");
	
    food(){
		
		this.setSize(1000, 800);
		this.setVisible(true);
		this.setLayout(null);
		
		
		SetBtn();
		SetTableA();
		SetmyLabel1();
		
		
	}
	
	public void findRD_in_TB_food(String aNo){   //�s����Ʈw

             Connection connection;
             Statement statement;

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
         }

          //��ܦb�e����
         private void SetTableA(){ 
             Connection connection;
             Statement statement;
             ResultSet result;
             String name="",day="",ftname="";
             int	num=0,mo=0;
            
                try{
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/posdb" + "?user=root&password=mysql");
                    statement = connection.createStatement();
                    result = statement.executeQuery("SELECT * FROM food");
                    while( result.next() ){
                          day = result.getString("fd_day");
                          name = result.getString("fd_name");
                          ftname = result.getString("ft_name");
                          num = result.getInt("fd_num");
                          mo = result.getInt("fd_mo");
                          tm.addRow(new Object[]{""+name+"",""+day+"",""+ftname+"",""+num+"",""+mo+""});
                          
                     }
                  tableA.setModel(tm);
	     
		JScrollPane Scroll = new JScrollPane(tableA,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                                             JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		Scroll.setBounds(0, 100, this.getWidth(), 700);
		this.add(Scroll);
                     statement.close();
                     
              } catch(SQLException e){
                     JOptionPane.showMessageDialog(null,"��Ʈw�ާ@�o�ͨ�L���~!");
              }             
	}

        private void SetmyLabel1(){  //���D
		
		JPanel Panel = new JPanel();
		Panel.setBounds(0,10,1000,50);
                this.add(Panel);
                myLabel1.setFont(font);
                myLabel1.setBounds(300,5,350,35);
                Panel.add(myLabel1);
                
	}
        private void SetBtn(){   //���s
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
        	 food.this.setVisible(false); 
           
    }
  };

  public static void main(String[] args) { 
               new food();
	}

	
 }
