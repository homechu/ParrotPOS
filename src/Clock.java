import java.awt.*;
import java.util.*; //�n�ϥΨ� Calendar ��...

import javax.swing.JFrame;
import javax.swing.JPanel;

class Clock extends JFrame{ 
    Label lab=new Label("");
    Calendar calendar = new GregorianCalendar();  //�إߤ@�� Calendar
	Clock(){
		setLayout(null);                 // ���������������]�w
        setSize(180,50);                // �]�w����������200�B�e��150�ӹ���
        setBackground(Color.yellow);  // �]�w���⪺�I��
        setLocation(0,0);   
        setBounds(100,0,100,80); // �]�w��������m
        lab.setBounds(0,0,180,50);      // �]�w���Ҫ���m
        setBackground(Color.pink);    // �]�w���Ҫ��C��  
        add(lab);                          // �N���Ҫ���lab�[�J������
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);             // �N������ܥX��
        
        
        
	}
    private void setTime()  //�]�w�t�ήɶ�
    {
        calendar.setTimeInMillis(System.currentTimeMillis());
    }
    
    public int getYear()  //���o�~
    {
        return calendar.get(Calendar.YEAR);
    }
    
    public int getMonth()  //���o��
    {
        return (calendar.get(Calendar.MONTH)+1);
    }
    
    public int getDate()  //���o��
    {
        return calendar.get(Calendar.DATE);
    }
    
    public int getHour()  //���o�p��
    {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    
    public int getMinute()  //���o����
    {
        return calendar.get(Calendar.MINUTE);
    }
        
    public int getSecond()  //���o���
    {
        return calendar.get(Calendar.SECOND);
    }
    
    public void showCurrentTime()  //��ܥثe�ɶ�
    {
        while(true)
        {
            setTime();
            lab.setText(getYear()+"/"+getMonth()+"/"+
                        getDate()+" "+getHour()+":"+
                        getMinute()+":"+getSecond());
        }
    }
  
    public static void main(String [] args)
    {
       
        Clock clo1 = new Clock(); 
        clo1.showCurrentTime();        
    }
}


