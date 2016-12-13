package activity;


import com.hero.app.R;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import db.User;
import util.BaseActivity;
import util.HttpCallBackListener;
import util.HttpUtil;


public class RegisterActivity extends BaseActivity{
	
	
	private EditText userNameEditText;
	private EditText userPasswordEditText;
	private Button registerButton;
	private Button backButton;
	
	private String userName;
	private String userPassword;

	
	protected void onCreate(Bundle savedInstanceState) {
    	
		super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
       
        //���巵�ؼ�
        backButton = (Button)findViewById(R.id.button_registerback);
        backButton.setOnClickListener(new Listener());
        
        registerButton = (Button)findViewById(R.id.button_register);
        registerButton.setOnClickListener(new Listener());
        
        userNameEditText = (EditText)findViewById(R.id.edittext_registername);
        userNameEditText.addTextChangedListener(new EditTextListener());
        
        userPasswordEditText = (EditText)findViewById(R.id.edittext_registerpassword);
        userPasswordEditText.addTextChangedListener(new EditTextListener());
        
	}
 
        
	
	
	
	private class Listener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			
			if(v.getId() == R.id.button_registerback){
				
				Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
				startActivity(intent);
				
			}else if(v.getId() == R.id.button_register){
				
	            //��������������ע�ᣬ������״���ص�������
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");
				intent.putExtra("key", "registerbutton");
				sendBroadcast(intent);	
				
				userName = userNameEditText.getText().toString();
				userPassword = userPasswordEditText.getText().toString();
					
				//�û��������볤�ȶ���1-10�������û������ظ�
				if (userName.length() > 0 && userName.length() <= 10  && userPassword.length() > 0 && userPassword.length() <= 10){	
						
					User user = new User(userName, userPassword, "");
						
					//URL�ܹ���⴫�ݵ��û����Ƿ��ѱ�ע�ᣬ�����ע�ᣬ����ע��ʧ�ܣ�����û���δ��ע�ᣬ���û�����Ϣ��������������ݿ⣬����ע��ɹ�
					HttpUtil.sendHttpPostRequest("http://GuardLDW/index.php", user, new HttpCallBackListener(){

						@Override
						public void onFinish(String response) {
								
							//���ص���ע��ɹ�
							if(response.equals("")){
									
								Toast.makeText(RegisterActivity.this, "ע��ɹ�", Toast.LENGTH_SHORT);
	
								//��ת����¼����
								Intent intent = new Intent(RegisterActivity.this, LogInActivity.class);
								startActivity(intent);
								
							//���ص���ע��ʧ��
							}else{
									
								Toast.makeText(RegisterActivity.this, "ע��ʧ�ܣ�������ע��", Toast.LENGTH_SHORT);
								userNameEditText.setText("");
								userPasswordEditText.setText("");
							}
						}

						@Override
						public void onError(Exception e) {	
							
							Log.d("Register", "ע��ʱ���ִ���");
						}
					});
				}
			}	
		}
	}
	
	
	private class EditTextListener implements TextWatcher{

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterTextChanged(Editable s) {
			
			//����ʹ�ü�����Editable�������ʵʱ�õ��ı�������
			if(s.toString().equals("")){
				
				registerButton.setEnabled(false);
				registerButton.setBackgroundColor(android.graphics.Color.parseColor("#D8BFD8"));
				
			}else{
				
				registerButton.setEnabled(true);
				registerButton.setBackgroundColor(android.graphics.Color.parseColor("#70f3ff"));
			}
			
		}
		
		
	}

        

	
	
	
   


}