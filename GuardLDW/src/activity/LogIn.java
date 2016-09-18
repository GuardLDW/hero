package activity;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.EditText;
import android.widget.Toast;
import db.HeroDB;
import db.User;
import receiver.NetworkReceiver;
import util.ActivityControl;
import util.AnalyzeData;
import util.HttpCallBackListener;
import util.HttpUtil;
import util.Music;

public class LogIn extends Activity{
	
	private EditText usernameEditText;
	private EditText passwordEditText;
	private Button loginButton; 
	private Button backButton;
	static  String username = "";
	private String password = "";
	static public String logInUsername;
	private Boolean result = false;
	List <User> userList = new ArrayList<User>();
	
	public void onCreate(Bundle savedInstanceState){
		
		ActivityControl.addActivity(this);
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);
        
        
        usernameEditText = (EditText)findViewById(R.id.edittext_loginname);
        passwordEditText = (EditText)findViewById(R.id.edittext_loginpassword);
        //���巵�ؼ�
        backButton = (Button)findViewById(R.id.button_loginback);
        backButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LogIn.this, Main.class);
				startActivity(intent);
			}      	
        });
        
        loginButton = (Button)findViewById(R.id.button_login);
        loginButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				
				username = usernameEditText.getText().toString();
				password = passwordEditText.getText().toString();
				
				if (NetworkReceiver.network == 1){
					HttpUtil.sendHttpGetRequest("http://10.0.2.2/user.json", new HttpCallBackListener(){

						@Override
						public void onFinish(String response) {
							userList = AnalyzeData.handleUserResponese(response);
							if(userList != null){
								//�ж��û����������Ƿ�ƥ��
								for (User user : userList){
									if (username.equals(user.getUsername()) && password.equals(user.getPassword())){
										result = true;
										
										//�������ݿ�
										HeroDB.getInstance(LogIn.this, "User");
										//����ǰ��¼��User�����������Ϣ�������ݿ⣬����û�����ֱ���޸����ݿ��еĶ�Ӧֵ�����ϴ���������
										HeroDB.saveUser(user);
										//��¼��ǰ��¼���û����˺�
										logInUsername = user.getUsername();
		
									}else{
										result = false;
									}
								}
							}else{
								result = false;
							}
							
						}

						@Override
						public void onError(Exception e) {
							// TODO �Զ����ɵķ������
							
						}
						
					});
				}else if(NetworkReceiver.network == 0){
					Toast.makeText(LogIn.this, "network is unavailable", Toast.LENGTH_SHORT).show();
				}
			}
        });
        
        
	}

	
    @Override
    public void onBackPressed()//��Back���ļ���
    { 
    	AlertDialog.Builder dialog = new AlertDialog.Builder(LogIn.this);
		dialog.setMessage("�Ƿ�Ҫ�˳���Ϸ");
		dialog.setCancelable(false);//ֻ�ܵ���Ի���
		dialog.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				Music.stopBackgroundMusic();//����������ڲ��ţ�ֹͣ����
				ActivityControl.finishAll();

			}
		});  
		dialog.setNegativeButton("ȡ��",new DialogInterface.OnClickListener()
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