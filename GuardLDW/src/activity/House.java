package activity;

import java.util.ArrayList;
import java.util.List;
import com.hero.app.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import db.User;
import util.ActivityControl;
import util.BaseActivity;
import util.HttpCallBackListener;
import util.HttpUtil;
import util.Music;


public class House extends BaseActivity{
	
	
	private EditText commentEditText;
	private Button commentButton;
	private Button backButton;
	
	private String comment;
	private ListView commentListView;	

	private List<String> commentList;
	private ArrayAdapter<String> adapter; 

	
	protected void onCreate(Bundle savedInstanceState) {
		

		super.onCreate(savedInstanceState); 
        setContentView(R.layout.house);
        
        commentListView = (ListView)findViewById(R.id.listview_comment);    
        commentListView.setAdapter(adapter);
        commentListView.setOnItemLongClickListener(new LongListener());
        
        backButton = (Button)findViewById(R.id.button_housegameback);
        backButton.setOnClickListener(new Listener());
        
        commentButton = (Button)findViewById(R.id.button_comment);
        commentButton.setOnClickListener(new Listener());
        
        commentEditText = (EditText)findViewById(R.id.edittext_comment);
        
        commentList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(House.this, android.R.layout.simple_list_item_1, commentList);
	}
	
	
	private class Listener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			
			if(v.getId() == R.id.button_housegameback){
				
				Intent intent = new Intent(House.this, Main.class);
				startActivity(intent);
				
			}else if(v.getId() == R.id.button_comment){
				
                //有网络的情况允许注册，无网络状况回到主界面
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");
				intent.putExtra("key", "commentbutton");
				sendBroadcast(intent);	
				
				comment = commentEditText.getText().toString();
				commentListView.setAdapter(adapter);
				
				
				//假设能够提交到服务器并受到正确的返回信息
				commentList.add(comment);
				
				//用户名与密码应为当前登录账号的用户名与密码
				//。。。。。
				User user = new User("", "", comment);
				
				//将评论提交到服务器，如果成功将数据存入相应对象的服务器数据库，返回评论成功，如果失败，返回评论失败
				HttpUtil.sendHttpPostRequest("http://GuardLDW/index.php", user, new HttpCallBackListener(){

					@Override
					public void onFinish(String response) {
						
						//如果评论成功，将本地的listview同步更新
						if(response.equals("")){
							
							commentList.add(comment);
						
						//如果评论失败
						}else{
						
							Toast.makeText(House.this, "评论失败", Toast.LENGTH_SHORT);
						}
			
					}

					@Override
					public void onError(Exception e) {
						
						Log.d("House", "评论出错");
					}					
				});
			}
	
		}
	}
	
	
	private class LongListener implements OnItemLongClickListener{

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
			
	    	AlertDialog.Builder dialog = new AlertDialog.Builder(House.this);
			dialog.setMessage("删除？");
			dialog.setCancelable(false);//只能点击对话框
			dialog.setPositiveButton("确认", new DialogInterface.OnClickListener(){
				
				@Override
				public void onClick(DialogInterface dialog, int which){
					
					commentList.remove(position);
					commentListView.setAdapter(adapter);
					
				}
			});  
			
			
			dialog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
				
				@Override
				public void onClick(DialogInterface dialog, int which){ 
				
				}
			});
			
			dialog.show();
			return false;
		}
		
		
	}

}
