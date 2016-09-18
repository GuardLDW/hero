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
 * �ļ������ݵı��棬��ȡ�뽨��
 */



public class Fold {

	//������ʼ��¼���ݵ��ļ�,ֻ��Ҫ����һ��
    public static void build(Context context)
    {
   	 File file_gaonai = new File("/data/data/com.hero.app/files/gaonai");//��Ÿ�����
   	 File file_shixin = new File("/data/data/com.hero.app/files/shixin");//���ʫܰ��
   	 File file_newpage = new File("/data/data/com.hero.app/files/newpage");//�����ƪ��
   	 
   	 File file_music = new File("/data/data/com.hero.app/files/music");//��ű�������
   	 
   	 File file_userInfo = new File("/data/data/com.hero.app/file/userInfo");//���ģ����������û�����
   	 
   	 File file_save0 = new File("/data/data/com.hero.app/files/save0");//��Ŵ浵1
   	 File file_save1 = new File("/data/data/com.hero.app/files/save1");//��Ŵ浵2
   	 File file_save2 = new File("/data/data/com.hero.app/files/save2");//��Ŵ浵3
   	 File file_save3 = new File("/data/data/com.hero.app/files/save3");//��Ŵ浵4
   	 File file_save4 = new File("/data/data/com.hero.app/files/save4");//��Ŵ浵5
   	 File file_save5 = new File("/data/data/com.hero.app/files/save5");//��Ŵ浵6

   	
   	 File file_savei0 = new File("/data/data/com.hero.app/files/savei0");//��Ŵ浵i0��ֵ
   	 File file_savei1 = new File("/data/data/com.hero.app/files/savei1");//��Ŵ浵i1��ֵ
   	 File file_savei2 = new File("/data/data/com.hero.app/files/savei2");//��Ŵ浵i2��ֵ
   	 File file_savei3 = new File("/data/data/com.hero.app/files/savei3");//��Ŵ浵i3��ֵ
   	 File file_savei4 = new File("/data/data/com.hero.app/files/savei4");//��Ŵ浵i4��ֵ
   	 File file_savei5 = new File("/data/data/com.hero.app/files/savei5");//��Ŵ浵i5��ֵ

   	 
   	 if(!file_gaonai.exists())//����һ�κ��ļ�������������
   	 {
   		 save("gaonai", "0", context);//������ʼ�ļ�����ֵΪ0
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
	
	
	
	//���浽�ļ�    
    public static void save(String site, String text, Context context){
   	FileOutputStream out = null;//�����ļ����������
   	BufferedWriter writer = null;//��������д
   	try {
   		out = context.openFileOutput(site, Context.MODE_PRIVATE);//ͨ��openFileOutput�����õ��ļ����������
	    writer = new BufferedWriter(new OutputStreamWriter(out));
		writer.write(text);//������Ĳ���textд���ļ�
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
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
    
    
    //���ļ��ж�ȡ
    public static String load(String site, Context context)
    {
   	 FileInputStream in = null;//�����ļ�����������
   	 BufferedReader reader = null;//���������
   	 StringBuilder content = new StringBuilder();//�����ɱ��ַ�������content
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
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


    //�洢�ű���ֻ��Ҫ����һ��
    public static void saveScript(Context context){
    	File file = new File("/data/data/com.hero.app/shared_prefs/script");
    	//��ֻ֤����һ��
    	if (!file.exists()){
    	   	SharedPreferences.Editor editor = context.getSharedPreferences("script", Context.MODE_PRIVATE).edit();
        	editor.putString("0", "��һ�仰");
        	editor.putString("1", "�ڶ��仰");
        	editor.putString("2", "�����仰");
        	editor.putString("3", "�����仰");
        	editor.putString("4", "�����仰");
        	editor.putString("5", "�����仰");
        	editor.putString("6", "�����仰");
        	
        	editor.commit();
    	}
 
    	
    	
    	
    }

    
    public static String loadScript(Context context, String s){
    	SharedPreferences pref = context.getSharedPreferences("script", Context.MODE_PRIVATE); 
		return pref.getString(s, "�ű����س��ִ���");
    	
    }
    
    
    //���浵������ʾ������save0-5�ļ��б�����Ǵ浵1-6��ʱ�䣬��Ϊ0˵���д浵��¼
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