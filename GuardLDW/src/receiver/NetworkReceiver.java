package receiver;

import activity.House;
import activity.LogIn;
import activity.Main;
import activity.Register;
import activity.Speical;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;


/*
 * 判断当前网络是否可用
 */


public class NetworkReceiver extends BroadcastReceiver{
	
	
	@Override
	public void onReceive(Context context, Intent intent) {//需要的参数放置在intent中的额外值中
		
		ConnectivityManager connectionManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);//getSystemService只有通过活动才能调用，得到系统服务类		
		NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();//得到NetworkInfo的实例
		
		String key = intent.getStringExtra("key");
		Log.d("what", key);
		if(networkInfo != null && networkInfo.isAvailable()){
			if (key.equals("house")){
				
				Intent intent1 = new Intent(context, House.class);
				intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent1);
			}else if(key.equals("register")){
				
				Intent intent2 = new Intent(context, Register.class);
				intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent2);
			}else if(key.equals("login")){
				
				Intent intent3 = new Intent(context, LogIn.class);
				intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent3);				
			}
		}
		else{
			Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
		}
	}

}

