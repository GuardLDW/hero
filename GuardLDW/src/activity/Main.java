package activity;


import com.hero.app.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import receiver.NetworkReceiver;
import util.ActivityControl;
import util.Fold;
import util.Music;

public class Main extends Activity{

	private Button enterButton;
	private Button loadButton;
	private Button setButton;
	private Button houseButton;
	private Button exitButton;
	private Button registerButton;
	private Button logInButton;
	private Button specialButton;
	
	public static int i = 0;
	
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        ActivityControl.addActivity(this);
        
        enterButton = (Button)findViewById(R.id.button_entergame);
        loadButton = (Button)findViewById(R.id.button_loadgame);
        setButton = (Button)findViewById(R.id.button_setgame);
        houseButton = (Button)findViewById(R.id.button_housegame);
        exitButton = (Button)findViewById(R.id.button_exitgame);
        registerButton = (Button)findViewById(R.id.button_mainregister);
        logInButton = (Button)findViewById(R.id.button_mainlogin);
        specialButton = (Button)findViewById(R.id.button_special);
        
        //建立初始文件,只会运行一次（函数中控制）
        Fold.build(Main.this);
        
        //初始化背景音乐
        if(Fold.load("music", Main.this).equals("1")){
        	Music.startBackgroundMusic(Main.this);
        }else{
        	Music.stopBackgroundMusic();
        }
        
        //加载脚本，只会运行一次（函数中控制）
        Fold.saveScript(Main.this);
        
        
        enterButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				i = 0;
				Intent intent = new Intent(Main.this, StartGame.class);
				startActivity(intent);
				
			}       	
        });
        
        loadButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Main.this, LoadGame.class);
				startActivity(intent);
			}
        });
        
        setButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Main.this, Set.class);
				startActivity(intent);				
			}       	
        });
        
        houseButton.setOnClickListener(new OnClickListener(){
        	
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");	
				sendBroadcast(intent);
				if(NetworkReceiver.network == 1){
					Intent intent1 = new Intent(Main.this, House.class);
					startActivity(intent1);
				}else if(NetworkReceiver.network == 0){
					Toast.makeText(Main.this, "network is unavailable", Toast.LENGTH_SHORT).show();
				}else if (NetworkReceiver.network == 2){
					Toast.makeText(Main.this, "loading...try again", Toast.LENGTH_SHORT).show();
				}
			}       	
        });
        
        exitButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {	
				Music.stopBackgroundMusic();//如果音乐正在播放，停止音乐
				ActivityControl.finishAll();				
			}       	
        });
        
        registerButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");	
				sendBroadcast(intent);		
				if(NetworkReceiver.network == 1){//有网络的情况进入注册界面
					Intent intent1 = new Intent(Main.this, Register.class);
					startActivity(intent1);
				}else if(NetworkReceiver.network == 0){
					Toast.makeText(Main.this, "network is unavailable", Toast.LENGTH_SHORT).show();
				}
			}       	
        });
        
        
        logInButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");	
				sendBroadcast(intent);		
				if(NetworkReceiver.network == 1){//有网络的情况进入注册界面
					Intent intent1 = new Intent(Main.this, LogIn.class);
					startActivity(intent1);
				}else if(NetworkReceiver.network == 0){
					Toast.makeText(Main.this, "network is unavailable", Toast.LENGTH_SHORT).show();
				}			
			}       	
        });
        
        
        specialButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(Fold.load("gaonai", Main.this).equals("1") && Fold.load("shiixn", Main.this).equals("1")){        	
					Intent intent = new Intent(Main.this, Speical.class);
					startActivity(intent);
				}else{
					Toast.makeText(Main.this, "请先通关所有线路", Toast.LENGTH_SHORT).show();
				}
			}       	
        });
        
	}
	
	
    @Override
    public void onBackPressed()//对Back键的监听
    { 
    	AlertDialog.Builder dialog = new AlertDialog.Builder(Main.this);
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
