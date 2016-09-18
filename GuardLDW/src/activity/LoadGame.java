package activity;



import java.io.File;


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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import util.ActivityControl;
import util.Fold;
import util.Music;
import android.widget.AdapterView.OnItemClickListener;

public class LoadGame extends Activity{
	
	//��һ��Ӧ��ֻ����һ��
    public static String[] save = {"�浵1          XXXX.XX.XX.XX.XX.XX","�浵2          XXXX.XX.XX.XX.XX.XX","�浵3          XXXX.XX.XX.XX.XX.XX",
                             "�浵4          XXXX.XX.XX.XX.XX.XX","�浵5          XXXX.XX.XX.XX.XX.XX","�浵6          XXXX.XX.XX.XX.XX.XX",
                            };
    
	private ListView loadList;
	private Button backButton;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		
		ActivityControl.addActivity(this);
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.loadgame);
   	
        //���巵�ؼ�
        backButton = (Button)findViewById(R.id.button_loadgameback);
        backButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoadGame.this, Main.class);
				startActivity(intent);
			}      	
        });
        
        
        //��loadList����������ǰ����save�����ֵ
        Fold.checkSave(LoadGame.this);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoadGame.this, android.R.layout.simple_list_item_1, save);
        loadList = (ListView)findViewById(R.id.listview_loadgame);
        loadList.setAdapter(adapter);
      	loadList.setOnItemClickListener(new OnItemClickListener(){//ListView�ĵ��������Ӧ����
		
       		@Override
       		public void onItemClick(AdapterView<?> parent, View view, int position,
       				long id) {   			
       			if(position == 0){   //position�������е��±�
       				Main.i = Integer.parseInt(Fold.load("savei0", LoadGame.this));//���ַ���ʽ��MainActivity.iת��Ϊ���β���ȡ
       				Intent intent = new Intent(LoadGame.this, StartGame.class);
       				startActivity(intent);//��ʼ������Ϸ��ͨ��������Ϸ�Ĳ��֣�
       			}
       			else if(position == 1){   
       				Main.i = Integer.parseInt(Fold.load("savei1", LoadGame.this));
       				Intent intent = new Intent(LoadGame.this, StartGame.class);
       				startActivity(intent);
       			}
       			else if(position == 2){   
       				Main.i = Integer.parseInt(Fold.load("savei2", LoadGame.this));
       				Intent intent = new Intent(LoadGame.this, StartGame.class);
       				startActivity(intent);
       			}
       			else if(position == 3){   
       				Main.i = Integer.parseInt(Fold.load("savei3", LoadGame.this));
       				Intent intent = new Intent(LoadGame.this, StartGame.class);
       				startActivity(intent);
       			}
       			else if(position == 4){   
       				Main.i = Integer.parseInt(Fold.load("savei4", LoadGame.this));
       				Intent intent = new Intent(LoadGame.this, StartGame.class);
       				startActivity(intent);
       			}
       			else if(position == 5){   
       				Main.i = Integer.parseInt(Fold.load("savei5", LoadGame.this));
       				Intent intent = new Intent(LoadGame.this, StartGame.class);
       				startActivity(intent);
       			}
       		}       		 
      	});
	}
	

	
    @Override
    public void onBackPressed()//��Back���ļ���
    { 
    	AlertDialog.Builder dialog = new AlertDialog.Builder(LoadGame.this);
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