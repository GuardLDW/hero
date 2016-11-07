package activity;

import com.hero.app.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import util.BaseActivity;
import util.Fold;
import util.Music;

public class Set extends BaseActivity{
	
	private Button backButton;
	private RadioButton haveMusicRadioButton;
	private RadioButton noMusicRadioButton;
	private RadioGroup musicRadioGroup;
	

	protected void onCreate(Bundle savedInstanceState) {
    	
		super.onCreate(savedInstanceState);
        setContentView(R.layout.set);
        
        //定义返回键
        backButton = (Button)findViewById(R.id.button_setback);
        backButton.setOnClickListener(new Listener());
        
        haveMusicRadioButton = (RadioButton)findViewById(R.id.radiobutton_havemusic);
        noMusicRadioButton = (RadioButton)findViewById(R.id.radiobutton_nomusic);
        
        musicRadioGroup = (RadioGroup)findViewById(R.id.radiogroup_backgroundmusic);
        musicRadioGroup.setOnCheckedChangeListener(new CheckListener());
        
	}
        
	
	
	private class Listener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			
			Intent intent = new Intent(Set.this, Main.class);
			startActivity(intent);
		}
	}
	
	
	private class CheckListener implements RadioGroup.OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			
            if(checkedId == haveMusicRadioButton.getId()){ 
            	
            	Fold.save("music", "1", Set.this);//改变music文件中的值，将初始值0覆盖
            	Music.startBackgroundMusic(Set.this);
            	
            }else if(checkedId == noMusicRadioButton.getId()){
      
            	Fold.save("music", "0", Set.this);
            	Music.stopBackgroundMusic();
            } 
			
		}
		
		
	}


}