package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import activity.LoadGame;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


/*
 * 文件中数据的保存，读取与建立
 */



public class Fold {

	//建立初始记录数据的文件,只需要运行一次
    public static void build(Context context)
    {
   	 File file_gaonai = new File("/data/data/com.hero.app/files/gaonai");//存放高乃线
   	 File file_shixin = new File("/data/data/com.hero.app/files/shixin");//存放诗馨线
   	 File file_newpage = new File("/data/data/com.hero.app/files/newpage");//存放新篇章
   	 
   	 File file_music = new File("/data/data/com.hero.app/files/music");//存放背景音乐
   	 
   	 File file_userInfo = new File("/data/data/com.hero.app/file/userInfo");//存放模拟服务器的用户数据
   	 
   	 File file_save0 = new File("/data/data/com.hero.app/files/save0");//存放存档1
   	 File file_save1 = new File("/data/data/com.hero.app/files/save1");//存放存档2
   	 File file_save2 = new File("/data/data/com.hero.app/files/save2");//存放存档3
   	 File file_save3 = new File("/data/data/com.hero.app/files/save3");//存放存档4
   	 File file_save4 = new File("/data/data/com.hero.app/files/save4");//存放存档5
   	 File file_save5 = new File("/data/data/com.hero.app/files/save5");//存放存档6

   	
   	 File file_savei0 = new File("/data/data/com.hero.app/files/savei0");//存放存档i0的值
   	 File file_savei1 = new File("/data/data/com.hero.app/files/savei1");//存放存档i1的值
   	 File file_savei2 = new File("/data/data/com.hero.app/files/savei2");//存放存档i2的值
   	 File file_savei3 = new File("/data/data/com.hero.app/files/savei3");//存放存档i3的值
   	 File file_savei4 = new File("/data/data/com.hero.app/files/savei4");//存放存档i4的值
   	 File file_savei5 = new File("/data/data/com.hero.app/files/savei5");//存放存档i5的值

   	 
   	 if(!file_gaonai.exists())//运行一次后文件存在则不再运行
   	 {
   		 save("gaonai", "0", context);//建立初始文件，初值为0
   	 }
   	 
   	 if(!file_shixin.exists())
   	 {
   		 save("shixin", "0", context);
   	 }
   	 
   	 if(!file_newpage.exists())
   	 {
   		 save("newpage", "0", context);
   	 }
   	 
   	 if(!file_music.exists())
   	 {
   		 save("music", "0", context);
   	 }
   	 
   	 if(!file_save0.exists())
   	 {
   		 save("save0", "0", context);
   	 }
   	 
   	 if(!file_save1.exists())
   	 {
   		 save("save1", "0", context);
   	 }
   	 
   	 if(!file_save2.exists())
   	 {
   		 save("save2", "0", context);
   	 }
   	 
   	 if(!file_save3.exists())
   	 {
   		 save("save3", "0", context);
   	 }
   	
   	 if(!file_save4.exists())
   	 {
   		 save("save4", "0", context);
   	 }
   	 
   	 if(!file_save5.exists())
   	 {
   		 save("save5", "0", context);
   	 }
   	 

   	 if(!file_savei0.exists())
   	 {
   		 save("savei0", "0", context);
   	 }
   	 
   	 if(!file_savei1.exists())
   	 {
   		 save("savei1", "0", context);
   	 }
   	 
   	 if(!file_savei2.exists())
   	 {
   		 save("savei2", "0", context);
   	 }
   	 
   	 if(!file_savei3.exists())
   	 {
   		 save("savei3", "0", context);
   	 }
   	
   	 if(!file_savei4.exists())
   	 {
   		 save("savei4", "0", context);
   	 }
   	 
   	 if(!file_savei5.exists())
   	 {
   		 save("savei5", "0", context);
   	 }
   	 
   	 if(!file_userInfo.exists())
   	 {
   		 save("userInfo", "", context);
   	 }
   	 

    }
	
	
	
	//保存到文件    
    public static void save(String site, String text, Context context){
   	FileOutputStream out = null;//创建文件输出流对象
   	BufferedWriter writer = null;//创建缓冲写
   	try {
   		out = context.openFileOutput(site, Context.MODE_PRIVATE);//通过openFileOutput方法得到文件输出流对象
	    writer = new BufferedWriter(new OutputStreamWriter(out));
		writer.write(text);//将传入的参数text写入文件
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			}finally{  
           try {  
              if(writer != null){
               	writer.close();
               }
           } catch (Exception e) {  
               e.printStackTrace();  
           }  
       }     	
    }
    
    
    //从文件中读取
    public static String load(String site, Context context)
    {
   	 FileInputStream in = null;//创建文件输入流对象
   	 BufferedReader reader = null;//创建缓存读
   	 StringBuilder content = new StringBuilder();//创建可变字符串对象content
   	 try 
   	 {
			in = context.openFileInput(site);
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null){
				content.append(line);
			}			
		 } 
   	 catch (FileNotFoundException e) 
   	 {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{  
			if (reader != null){
           try {  
                	reader.close();
                }catch (Exception e) {  
                e.printStackTrace();  
            }  
			}
		}         
		return content.toString();   	 
    }


    //存储脚本，只需要运行一次
    public static void saveScript(Context context){
    	File file = new File("/data/data/com.hero.app/shared_prefs/script");
    	//保证只运行一次
    	if (!file.exists()){
    	   	SharedPreferences.Editor editor = context.getSharedPreferences("script", Context.MODE_PRIVATE).edit();
        	editor.putString("0", "第一句话");
        	editor.putString("1", "第二句话");
        	editor.putString("2", "第三句话");
        	editor.putString("3", "第三句话");
        	editor.putString("4", "第三句话");
        	editor.putString("5", "第三句话");
        	editor.putString("6", "第三句话");
        	
        	editor.commit();
    	}
 
    	
    	
    	
    }

    
    public static String loadScript(Context context, String s){
    	SharedPreferences pref = context.getSharedPreferences("script", Context.MODE_PRIVATE); 
		return pref.getString(s, "脚本加载出现错误");
    	
    }
    
    
    //将存档日期显示出来，save0-5文件中保存的是存档1-6的时间，不为0说明有存档记录
    public static void checkSave(Context context)
    {
  	 
      	if(!Fold.load("save0", context).equals("0")){
      		LoadGame.save[0] = Fold.load("save0", context);
      		}
      	if(!Fold.load("save1", context).equals("0"))
   	 {
   		 LoadGame.save[1] = Fold.load("save1", context);
   	 }
      	if(!Fold.load("save2", context).equals("0"))
   	 {
   		 LoadGame.save[2] = Fold.load("save2", context);
   	 }
      	if(!Fold.load("save3", context).equals("0"))
   	 {
   		 LoadGame.save[3] = Fold.load("save3", context);
   	 }
      	if(!Fold.load("save4", context).equals("0"))
   	 {
   		 LoadGame.save[4] = Fold.load("save4", context);
   	 }
      	if(!Fold.load("save5", context).equals("0"))
   	 {
   		 LoadGame.save[5] = Fold.load("save5", context);
   	 }

    
    }

}
