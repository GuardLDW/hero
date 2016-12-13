package activity;


import com.hero.app.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import util.ActivityControl;
import util.BaseActivity;
import util.Fold;
import util.Music;

public class MainActivity extends BaseActivity{

	
	private Button enterButton;
	private Button loadButton;
	private Button setButton;
	private Button houseButton;
	private Button exitButton;
	private Button registerButton;
	private Button logInButton;
	private Button specialButton;
	
	//��¼�����е�λ��
	public static int i = 0;
	
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        enterButton = (Button)findViewById(R.id.button_entergame);
        enterButton.setOnClickListener(new Listener());
        
        loadButton = (Button)findViewById(R.id.button_loadgame);
        loadButton.setOnClickListener(new Listener());
        
        setButton = (Button)findViewById(R.id.button_setgame);
        setButton.setOnClickListener(new Listener());
        
        houseButton = (Button)findViewById(R.id.button_housegame);
        houseButton.setOnClickListener(new Listener());
        
        exitButton = (Button)findViewById(R.id.button_exitgame);
        exitButton.setOnClickListener(new Listener());
        
        registerButton = (Button)findViewById(R.id.button_mainregister);
        registerButton.setOnClickListener(new Listener());
        
        logInButton = (Button)findViewById(R.id.button_mainlogin);
        logInButton.setOnClickListener(new Listener());
        
        specialButton = (Button)findViewById(R.id.button_special);
        specialButton.setOnClickListener(new Listener());
        
        
        //������ʼ�ļ�,ֻ������һ�Σ������п��ƣ�
        Fold.build(MainActivity.this);
        
        //��ʼ����������
        if(Fold.load("music", MainActivity.this).equals("1")){
        	
        	Music.startBackgroundMusic(MainActivity.this);
        	
        }else{
        	
        	Music.stopBackgroundMusic();
        }
        
        //���ؽű���ֻ������һ�Σ������п��ƣ�
        Fold.saveScript(MainActivity.this);

	}
	
	
	public boolean onCreateOptionsMenu(Menu menu){
		
        //MenuInflater class is used to instantiate menu XML files into Menu objects  
        MenuInflater inflater = getMenuInflater();  
        //��xml�����������inflate����
        inflater.inflate(R.menu.main, menu);  
        return true;  
    }  
	
	
	public boolean onOptionsItemSelected(MenuItem item){  
		 
        int item_id = item.getItemId();  
  
        switch (item_id){
        
           case R.id.menu_license:
        	   
        	   Intent intent = new Intent(MainActivity.this, LicenseActivity.class);
        	   startActivity(intent);
        	   break;   
        }  
        return true;  
    } 
	
	
	
	private class Listener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			
			if(v.getId() == R.id.button_entergame){
				
				i = 0;
				Intent intent = new Intent(MainActivity.this, StartGameActivity.class);
				startActivity(intent);
				
			}else if(v.getId() == R.id.button_loadgame){
				
				Intent intent = new Intent(MainActivity.this, LoadGameActivity.class);
				startActivity(intent);
				
			}else if(v.getId() == R.id.button_setgame){
				
				Intent intent = new Intent(MainActivity.this, SetActivity.class);
				startActivity(intent);	

				
			}else if(v.getId() == R.id.button_housegame){
				
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");
				intent.putExtra("key", "house");
				sendBroadcast(intent);
				
			}else if(v.getId() == R.id.button_mainregister){
				
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");	
				intent.putExtra("key", "register");
				sendBroadcast(intent);
				
			}else if(v.getId() == R.id.button_mainlogin){
				
				Intent intent = new Intent("com.hero.app.receiver.checknetwork");	
				intent.putExtra("key", "login");
				sendBroadcast(intent);
				
			}else if(v.getId() == R.id.button_special){
				
				if(Fold.load("gaonai", MainActivity.this).equals("1") && Fold.load("shixin", MainActivity.this).equals("1")){        	
					Intent intent = new Intent(MainActivity.this, SpecialActivity.class);
					startActivity(intent);
				}else{
					Toast.makeText(MainActivity.this, "����ͨ��������·", Toast.LENGTH_SHORT).show();
				}
				
			}else if(v.getId() == R.id.button_exitgame){
				
				//����������ڲ��ţ�ֹͣ����
				Music.stopBackgroundMusic();
				ActivityControl.finishAll();	
			}
			
		}

	}

}