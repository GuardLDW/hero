package activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hero.app.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import util.ActivityControl;
import util.Fold;
import util.Music;
import android.widget.AdapterView.OnItemClickListener;

public class StartGame extends Activity{

	private ImageView gameImage;
	private Button dialogButton;
	private Button gamestartsetButton;
	private Button saveButton;
	private Button backButton;
	private Button fastButton;
	private Button clearButton;
	private Button cancelsetButton;
	
	protected void onCreate(Bundle savedInstanceState) {	
		
		ActivityControl.addActivity(this);
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.game);
        
        gameImage = (ImageView)findViewById(R.id.image_game);
        dialogButton = (Button)findViewById(R.id.button_dialog);
        gamestartsetButton = (Button)findViewById(R.id.button_startgameset);
        saveButton = (Button)findViewById(R.id.button_save);
        backButton = (Button)findViewById(R.id.button_backtotitle);
        fastButton = (Button)findViewById(R.id.button_fast);
        clearButton = (Button)findViewById(R.id.button_remove);
        cancelsetButton = (Button)findViewById(R.id.button_cancelset);
        
        backButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartGame.this, Main.class);
				startActivity(intent);					
			}        	
        });
        
        
        fastButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Main.i = 391;
				Intent intent = new Intent(StartGame.this, StartGame.class);
				startActivity(intent);		
			}     	
        });
        
        
        clearButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {				
				dialogButton.setText("");
				dialogButton.setBackgroundColor(Color.parseColor("#00FFFFFF"));//设置按钮的背景颜色			
			}        	
        });
        
        gamestartsetButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				saveButton.setEnabled(true);
				saveButton.setBackgroundColor(android.graphics.Color.parseColor("#7B68EE"));
				saveButton.setText("保存存档");
				
				backButton.setEnabled(true);
				backButton.setBackgroundColor(android.graphics.Color.parseColor("#7B68EE"));
				backButton.setText("返回标题画面");
				
				fastButton.setEnabled(true);
				fastButton.setBackgroundColor(android.graphics.Color.parseColor("#7B68EE"));
				fastButton.setText("快进到选项");
				
				clearButton.setEnabled(true);
				clearButton.setBackgroundColor(android.graphics.Color.parseColor("#7B68EE"));
				clearButton.setText("清除对话框");
			}
        	
        });
        
        cancelsetButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				saveButton.setEnabled(false);
				saveButton.setBackgroundColor(android.graphics.Color.parseColor("#00FFFFFF"));
				saveButton.setText("");
				
				backButton.setEnabled(false);
				backButton.setBackgroundColor(android.graphics.Color.parseColor("#00FFFFFF"));
				backButton.setText("");
				
				fastButton.setEnabled(false);
				fastButton.setBackgroundColor(android.graphics.Color.parseColor("#00FFFFFF"));
				fastButton.setText("");
				
				clearButton.setEnabled(false);
				clearButton.setBackgroundColor(android.graphics.Color.parseColor("#00FFFFFF"));
				clearButton.setText("");				
			}        	
        });
        
        
        saveButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				setContentView(R.layout.loadgame);
				//在loadList设置适配器前更改save数组的值
		        Fold.checkSave(StartGame.this);			
				final ArrayAdapter<String> adapter = new ArrayAdapter<String>(StartGame.this, android.R.layout.simple_list_item_1, LoadGame.save);//适配器中储存着存档格式
		       	final ListView loadList = (ListView)findViewById(R.id.listview_loadgame);//列表组件
		       	loadList.setAdapter(adapter);//使用适配器，加载ListView的值
		       	loadList.setOnItemClickListener(new OnItemClickListener(){//定义了ListView的点击子项响应方法

		    	@Override
		    	public void onItemClick(AdapterView<?> parent, View view, int position,long id) 
		    	{	 
		    		long l = System.currentTimeMillis();
		    		Date date = new Date(l);
		    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    		final String str = dateFormat.format(date);
		    		
		    		
		    		if(position == 0)//position是数组中的下标
		    		{   
		    			AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
		    			dialog.setMessage("进度保存在存档1，确认？");
		    			dialog.setCancelable(false);//只能点击对话框
		    			dialog.setPositiveButton("确认", new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{
		    					//将当前i值与存档时间保存到相应文件
		    					Fold.save("save0", "存档1           " + str, StartGame.this);
		    					Fold.save("savei0", String.valueOf(Main.i), StartGame.this);
		    					//能够即时显示存档时间
		    					LoadGame.save[0] = "存档1          " + str;
		    	    			loadList.setAdapter(adapter);//重新生成数据 
		    				}
		    			});  
		    			dialog.setNegativeButton("取消",new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{ 
		    				}
		    			});  
		    			dialog.show();
	   			
		    		}
		    		else if(position == 1)
		    		{   
		    			AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
		    			dialog.setMessage("进度保存在存档2，确认？");
		    			dialog.setCancelable(false);
		    			dialog.setPositiveButton("确认", new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{
		    					Fold.save("save1", "存档2           " + str, StartGame.this);
		    					Fold.save("savei1", String.valueOf(Main.i), StartGame.this);
		    	    			LoadGame.save[1] = "存档2          " + str;
		    	    			loadList.setAdapter(adapter);//重新生成数据	 
		    				}
		    			});  
		    			dialog.setNegativeButton("取消",new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{ 
		    				}
		    			});  
		    			dialog.show();		
		    		}
		    		else if(position == 2)//position是数组中的下标
		    		{   
		    			AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
		    			dialog.setMessage("进度保存在存档3，确认？");
		    			dialog.setCancelable(false);//只能点击对话框
		    			dialog.setPositiveButton("确认", new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{
		    					Fold.save("save2", "存档3           " + str, StartGame.this);
		    					Fold.save("savei2", String.valueOf(Main.i), StartGame.this);
		    	    			LoadGame.save[2] = "存档3          " + str;
		    	    			loadList.setAdapter(adapter);//重新生成数据
		    				}
		    			});  
		    			dialog.setNegativeButton("取消",new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{ 
		    				}
		    			});  
		    			dialog.show();
	   			
		    		}
		    		else if(position == 3)//position是数组中的下标
		    		{   
		    			AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
		    			dialog.setMessage("进度保存在存档4，确认？");
		    			dialog.setCancelable(false);//只能点击对话框
		    			dialog.setPositiveButton("确认", new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{
		    					Fold.save("save3", "存档4           " + str, StartGame.this);
		    					Fold.save("savei3", String.valueOf(Main.i), StartGame.this);
		    	    			LoadGame.save[3] = "存档4          " + str;
		    	    			loadList.setAdapter(adapter);//重新生成数据	 
		    				}
		    			});  
		    			dialog.setNegativeButton("取消",new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{ 
		    				}
		    			});  
		    			dialog.show();
	   			
		    		}
		    		else if(position == 4)//position是数组中的下标
		    		{   
		    			AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
		    			dialog.setMessage("进度保存在存档5，确认？");
		    			dialog.setCancelable(false);//只能点击对话框
		    			dialog.setPositiveButton("确认", new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{
		    					Fold.save("save4", "存档5           " + str, StartGame.this);
		    					Fold.save("savei4", String.valueOf(Main.i), StartGame.this);
		    	    			LoadGame.save[4] = "存档5          " + str;
		    	    			loadList.setAdapter(adapter);//重新生成数据 
		    				}
		    			});  
		    			dialog.setNegativeButton("取消",new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{ 
		    				}
		    			});  
		    			dialog.show();
	   			
		    		}
		    		else if(position == 5)//position是数组中的下标
		    		{   
		    			AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
		    			dialog.setMessage("进度保存在存档6，确认？");
		    			dialog.setCancelable(false);//只能点击对话框
		    			dialog.setPositiveButton("确认", new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{
		    					Fold.save("save5", "存档6           " + str, StartGame.this);
		    					Fold.save("savei5", String.valueOf(Main.i), StartGame.this);
		    	    			LoadGame.save[5] = "存档6          " + str;
		    	    			loadList.setAdapter(adapter);//重新生成数据 
		    				}
		    			});  
		    			dialog.setNegativeButton("取消",new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{		    					
		    				}
		    			});  
		    			dialog.show();			
		    		}
		    }			
	    });
		       	Button backtogame = (Button)findViewById(R.id.button_loadgameback);//定义菜单保存存档界面的返回键
		       	backtogame.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						
						Intent intent = new Intent(StartGame.this, StartGame.class);
					    startActivity(intent);
					}    		
		       	});
				
			}
        	
        });
        
        //进入该活动时，首先根据i值加载对话内容与背景图片
        dialogButton.setText(Fold.loadScript(StartGame.this, String.valueOf(Main.i)));
        gameImage.setImageResource(R.drawable.game1);
        
 
        
        dialogButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				dialogButton.setBackgroundColor(Color.parseColor("#7fDA70D6"));//点击按钮即可抵消清除对话框的效果	
				//点击一次按钮i值加1
				Boolean once = true;
				if(once){
					Main.i = Main.i + 1;
					dialogButton.setText(Fold.loadScript(StartGame.this, String.valueOf(Main.i)));
					once = false;
					}
				}	
			});
        
  
        }
	

	
    @Override
    public void onBackPressed()//对Back键的监听
    { 
    	AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
		dialog.setMessage("是否要退出游戏");
		dialog.setCancelable(false);//只能点击对话框
		dialog.setPositiveButton("确认", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				Music.stopBackgroundMusic();//如果音乐正在播放，停止音乐
				ActivityControl.finishAll();

			}
		});  
		dialog.setNegativeButton("取消",new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{ 
			}
		});  
		dialog.show();
		return;		
    }	

}
