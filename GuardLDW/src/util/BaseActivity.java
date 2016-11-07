package util;

import activity.Register;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

//�����super.onCreate(savedInstanceState)���Դ�ӡ����ǰʵ��������

public class BaseActivity extends Activity{
	
	
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		ActivityControl.addActivity(this);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Log.d("BaseActivity", getClass().getSimpleName());
		
	}
	
	
	protected void onDestroy(){
		
		super.onDestroy();
		ActivityControl.removeActivity(this);
	}
	
	
    //��Back���ļ���
    public void onBackPressed(){ 
    	
    	AlertDialog.Builder dialog = new AlertDialog.Builder(BaseActivity.this);
		dialog.setMessage("�Ƿ�Ҫ�˳���Ϸ");
		dialog.setCancelable(false);//ֻ�ܵ���Ի���
		dialog.setPositiveButton("ȷ��", new DialogInterface.OnClickListener(){
			
			@Override
			public void onClick(DialogInterface dialog, int which){
				
				Music.stopBackgroundMusic();//����������ڲ��ţ�ֹͣ����
				ActivityControl.finishAll();

			}
		});  
		
		
		dialog.setNegativeButton("ȡ��",new DialogInterface.OnClickListener(){
			
			@Override
			public void onClick(DialogInterface dialog, int which){ 
			
			}
		});
		
		dialog.show();
		return;		
		
    }

}