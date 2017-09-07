package com.tjub.ticket.activity;

import java.util.Arrays;

import com.tjub.ticket.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends BaseActivity{
	
	
	private Button btn;
	private TextView tvNum1;
	private TextView tvNum2;
	private TextView tvNum3;
	private TextView tvNum4;
	private TextView tvNum5;
	private TextView tvNum6;
	private TextView tvNum7;
	
	private static int flag = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_activity_layout);
		
		tvNum1 = (TextView) this.findViewById(R.id.tv_num1);
		tvNum2 = (TextView) this.findViewById(R.id.tv_num2);	
		tvNum3 = (TextView) this.findViewById(R.id.tv_num3);	
		tvNum4 = (TextView) this.findViewById(R.id.tv_num4);	
		tvNum5 = (TextView) this.findViewById(R.id.tv_num5);	
		tvNum6 = (TextView) this.findViewById(R.id.tv_num6);	
		tvNum7 = (TextView) this.findViewById(R.id.tv_num7);	
		btn = (Button) this.findViewById(R.id.btn_random);	
		
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(flag > 0)
				{
					Toast.makeText(getApplicationContext(), "别急啊..", Toast.LENGTH_SHORT).show();
					return;
				}
				
				flag = 10;
				new Thread(){    
			        public void run() { 
			        	while(flag > 0)
			        	{
			        		try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
			        		Message msg = new Message();  
					        msg.what = 1;  
					        handler.sendMessage(msg); 
			        		flag--;
			        	}
			          
			        }  
			    }.start(); 
			}
		});
		
	}
	
	 Handler handler = new Handler(){  
	        @Override  
	        public void handleMessage(Message msg) {  
	            super.handleMessage(msg);  
	            if(msg.what == 1){  
	            	int[] result = createNums();
	            	tvNum1.setText(String.valueOf(result[0]));
	            	tvNum2.setText(String.valueOf(result[1]));
	            	tvNum3.setText(String.valueOf(result[2]));
	            	tvNum4.setText(String.valueOf(result[3]));
	            	tvNum5.setText(String.valueOf(result[4]));
	            	tvNum6.setText(String.valueOf(result[5]));
	            	tvNum7.setText(String.valueOf((int) (Math.random() * 10000) % 16 + 1));
	            }  
	        }  

	 };  
	    
	private int[] createNums()
	{
		int[] result = new int[6];
		int total = 0;
		while(total < 6)
		{
			result[total] = getRandom(result);
			total++;
		}
		Arrays.sort(result);
		return result;
	}
	
	
	private int getRandom(int[] result)
	{
		int num = 0;
		while(true)
		{
			num = (int) (Math.random() * 10000) % 33 + 1;
			if(!isExist(result, num))
				break;
		}
		return num;
	}
	
	private boolean isExist(int[] result, int num)
	{
		int i = 0;
		for(; i < result.length; i++)
		{
			if(result[i] == num)
			{
				return true;
			}
		}
		
		return false;
	}
	
}
