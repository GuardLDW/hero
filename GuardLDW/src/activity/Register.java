package activity;

import java.util.ArrayList;
import java.util.List;

import com.hero.app.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import db.User;
import receiver.NetworkReceiver;
import util.ActivityControl;
import util.AnalyzeData;
import util.HttpCallBackListener;
import util.HttpUtil;
import util.Music;

public class Register extends Activity{
	
	private EditText usernameEditText;
	private EditText passwordEditText;
	private Button registerButton;
	private Button backButton;
	private TextView registerResultTextView;
	private Boolean result = false;
	private List <User> userList = new ArrayList<User>();
	private String username = "";
	private String password = "";
	
	
	protected void onCreate(Bundle savedInstanceState) {
    	
		ActivityControl.addActivity(this);
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.register);
       
        //定义返回键
        backButton = (Button)findViewById(R.id.button_registerback);
        backButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Register.this, Main.class);
				startActivity(intent);
			}      	
        });
        
        usernameEditText = (EditText)findViewById(R.id.edittext_registername);
        passwordEditText = (EditText)findViewById(R.id.edittext_registerpassword);
        registerResultTextView = (TextView)findViewById(R.id.textview_registerresult);
        
        registerResultTextView.setText("显示注册结果");
        


        
        //注册按钮
        registerButton = (Button)findViewById(R.id.button_register);
        registerButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {	
				
				username = usernameEditText.getText().toString();
				password = passwordEditText.getText().toString();
                
                //有网络的情况允许注册	
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");	
				sendBroadcast(intent);		
				if(NetworkReceiver.network == 1){			
					
					//用户名与密码长度都在1-10，并且用户名不重复
					if (username.length() > 0 && username.length() <= 10  && password.length() > 0 && password.length() <= 10){				
						HttpUtil.sendHttpGetRequest("http://10.0.1.9:8026/weba/servlet/CustomerServlet", new HttpCallBackListener(){
						@Override
						
						public void onFinish(String response) {							
							
							userList = AnalyzeData.handleUserResponese(response);//对返回的数据进行解析,返回User对象的链表							
							//已有注册用户时，查询是否有用户名相同的情况
							if (userList != null){

								for(User user : userList){
									if (username.equals(user.getUsername())){
										result = false;
									}else{
										result = true;
										}
									}					
							}else{//无注册用户时
								result = true;
							}
						}
						@Override
						public void onError(Exception e) {	
							Log.d("what", "bug");
						}	
					});

				}else{//用户名与密码长度不符合要求
					result = false;
				
				}	
				}else if(NetworkReceiver.network == 0){
					Toast.makeText(Register.this, "network is unavailable", Toast.LENGTH_SHORT).show();
				}
			
			}
        });
        
		   //注册成功后，将该用户的用户名与密码,评论与点赞数，游戏进度（初始状态）提交到服务器
	       if(result){
	        	registerResultTextView.setText("注册成功");
	        	User successUser = new User();
	        	successUser.setUsername(username);
	        	successUser.setPassword(password);
	        	successUser.setComment("");
	        	HttpUtil.sendHttpPostRequest("http://10.0.2.2/user.json", successUser, new HttpCallBackListener(){

					@Override
					public void onFinish(String response) {
						// TODO Auto-generated method stub						
					}
					@Override
					public void onError(Exception e) {
						// TODO Auto-generated method stub						
					}	        	
	        	});
	        }else{
	        	registerResultTextView.setText("注册失败，请重新注册");
	        }
	}
        

	
	
	
    @Override
    public void onBackPressed()//对Back键的监听
    { 
    	AlertDialog.Builder dialog = new AlertDialog.Builder(Register.this);
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
