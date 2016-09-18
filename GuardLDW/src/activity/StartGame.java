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
				dialogButton.setBackgroundColor(Color.parseColor("#00FFFFFF"));//���ð�ť�ı�����ɫ			
			}        	
        });
        
        gamestartsetButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				saveButton.setEnabled(true);
				saveButton.setBackgroundColor(android.graphics.Color.parseColor("#7B68EE"));
				saveButton.setText("����浵");
				
				backButton.setEnabled(true);
				backButton.setBackgroundColor(android.graphics.Color.parseColor("#7B68EE"));
				backButton.setText("���ر��⻭��");
				
				fastButton.setEnabled(true);
				fastButton.setBackgroundColor(android.graphics.Color.parseColor("#7B68EE"));
				fastButton.setText("�����ѡ��");
				
				clearButton.setEnabled(true);
				clearButton.setBackgroundColor(android.graphics.Color.parseColor("#7B68EE"));
				clearButton.setText("����Ի���");
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
				//��loadList����������ǰ����save�����ֵ
		        Fold.checkSave(StartGame.this);			
				final ArrayAdapter<String> adapter = new ArrayAdapter<String>(StartGame.this, android.R.layout.simple_list_item_1, LoadGame.save);//�������д����Ŵ浵��ʽ
		       	final ListView loadList = (ListView)findViewById(R.id.listview_loadgame);//�б����
		       	loadList.setAdapter(adapter);//ʹ��������������ListView��ֵ
		       	loadList.setOnItemClickListener(new OnItemClickListener(){//������ListView�ĵ��������Ӧ����

		    	@Override
		    	public void onItemClick(AdapterView<?> parent, View view, int position,long id) 
		    	{	 
		    		long l = System.currentTimeMillis();
		    		Date date = new Date(l);
		    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    		final String str = dateFormat.format(date);
		    		
		    		
		    		if(position == 0)//position�������е��±�
		    		{   
		    			AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
		    			dialog.setMessage("���ȱ����ڴ浵1��ȷ�ϣ�");
		    			dialog.setCancelable(false);//ֻ�ܵ���Ի���
		    			dialog.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{
		    					//����ǰiֵ��浵ʱ�䱣�浽��Ӧ�ļ�
		    					Fold.save("save0", "�浵1           " + str, StartGame.this);
		    					Fold.save("savei0", String.valueOf(Main.i), StartGame.this);
		    					//�ܹ���ʱ��ʾ�浵ʱ��
		    					LoadGame.save[0] = "�浵1          " + str;
		    	    			loadList.setAdapter(adapter);//������������ 
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
	   			
		    		}
		    		else if(position == 1)
		    		{   
		    			AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
		    			dialog.setMessage("���ȱ����ڴ浵2��ȷ�ϣ�");
		    			dialog.setCancelable(false);
		    			dialog.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{
		    					Fold.save("save1", "�浵2           " + str, StartGame.this);
		    					Fold.save("savei1", String.valueOf(Main.i), StartGame.this);
		    	    			LoadGame.save[1] = "�浵2          " + str;
		    	    			loadList.setAdapter(adapter);//������������	 
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
		    		}
		    		else if(position == 2)//position�������е��±�
		    		{   
		    			AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
		    			dialog.setMessage("���ȱ����ڴ浵3��ȷ�ϣ�");
		    			dialog.setCancelable(false);//ֻ�ܵ���Ի���
		    			dialog.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{
		    					Fold.save("save2", "�浵3           " + str, StartGame.this);
		    					Fold.save("savei2", String.valueOf(Main.i), StartGame.this);
		    	    			LoadGame.save[2] = "�浵3          " + str;
		    	    			loadList.setAdapter(adapter);//������������
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
	   			
		    		}
		    		else if(position == 3)//position�������е��±�
		    		{   
		    			AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
		    			dialog.setMessage("���ȱ����ڴ浵4��ȷ�ϣ�");
		    			dialog.setCancelable(false);//ֻ�ܵ���Ի���
		    			dialog.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{
		    					Fold.save("save3", "�浵4           " + str, StartGame.this);
		    					Fold.save("savei3", String.valueOf(Main.i), StartGame.this);
		    	    			LoadGame.save[3] = "�浵4          " + str;
		    	    			loadList.setAdapter(adapter);//������������	 
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
	   			
		    		}
		    		else if(position == 4)//position�������е��±�
		    		{   
		    			AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
		    			dialog.setMessage("���ȱ����ڴ浵5��ȷ�ϣ�");
		    			dialog.setCancelable(false);//ֻ�ܵ���Ի���
		    			dialog.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{
		    					Fold.save("save4", "�浵5           " + str, StartGame.this);
		    					Fold.save("savei4", String.valueOf(Main.i), StartGame.this);
		    	    			LoadGame.save[4] = "�浵5          " + str;
		    	    			loadList.setAdapter(adapter);//������������ 
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
	   			
		    		}
		    		else if(position == 5)//position�������е��±�
		    		{   
		    			AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
		    			dialog.setMessage("���ȱ����ڴ浵6��ȷ�ϣ�");
		    			dialog.setCancelable(false);//ֻ�ܵ���Ի���
		    			dialog.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
		    			{
		    				@Override
		    				public void onClick(DialogInterface dialog, int which)
		    				{
		    					Fold.save("save5", "�浵6           " + str, StartGame.this);
		    					Fold.save("savei5", String.valueOf(Main.i), StartGame.this);
		    	    			LoadGame.save[5] = "�浵6          " + str;
		    	    			loadList.setAdapter(adapter);//������������ 
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
		    		}
		    }			
	    });
		       	Button backtogame = (Button)findViewById(R.id.button_loadgameback);//����˵�����浵����ķ��ؼ�
		       	backtogame.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						
						Intent intent = new Intent(StartGame.this, StartGame.class);
					    startActivity(intent);
					}    		
		       	});
				
			}
        	
        });
        
        //����ûʱ�����ȸ���iֵ���ضԻ������뱳��ͼƬ
        dialogButton.setText(Fold.loadScript(StartGame.this, String.valueOf(Main.i)));
        gameImage.setImageResource(R.drawable.game1);
        
 
        
        dialogButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				dialogButton.setBackgroundColor(Color.parseColor("#7fDA70D6"));//�����ť���ɵ�������Ի����Ч��	
				//���һ�ΰ�ťiֵ��1
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
    public void onBackPressed()//��Back���ļ���
    { 
    	AlertDialog.Builder dialog = new AlertDialog.Builder(StartGame.this);
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