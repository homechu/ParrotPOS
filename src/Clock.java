import java.awt.*;
import java.util.*; //要使用到 Calendar 等...

import javax.swing.JFrame;
import javax.swing.JPanel;

class Clock extends JFrame{ 
    Label lab=new Label("");
    Calendar calendar = new GregorianCalendar();  //建立一個 Calendar
	Clock(){
		setLayout(null);                 // 取消視窗的版面設定
        setSize(180,50);                // 設定視窗的長為200、寬為150個像素
        setBackground(Color.yellow);  // 設定黃色的背景
        setLocation(0,0);   
        setBounds(100,0,100,80); // 設定視窗的位置
        lab.setBounds(0,0,180,50);      // 設定標籤的位置
        setBackground(Color.pink);    // 設定標籤的顏色  
        add(lab);                          // 將標籤物件lab加入視窗中
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);             // 將視窗顯示出來
        
        
        
	}
    private void setTime()  //設定系統時間
    {
        calendar.setTimeInMillis(System.currentTimeMillis());
    }
    
    public int getYear()  //取得年
    {
        return calendar.get(Calendar.YEAR);
    }
    
    public int getMonth()  //取得月
    {
        return (calendar.get(Calendar.MONTH)+1);
    }
    
    public int getDate()  //取得日
    {
        return calendar.get(Calendar.DATE);
    }
    
    public int getHour()  //取得小時
    {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    
    public int getMinute()  //取得分鐘
    {
        return calendar.get(Calendar.MINUTE);
    }
        
    public int getSecond()  //取得秒數
    {
        return calendar.get(Calendar.SECOND);
    }
    
    public void showCurrentTime()  //顯示目前時間
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


