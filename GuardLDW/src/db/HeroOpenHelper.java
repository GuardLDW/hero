package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class HeroOpenHelper extends SQLiteOpenHelper{

	public static String CREATE_USER = "create table User(" + "user_username text primary key," + "user_password text" + "user_comment text" + "user_like integer)" + "user_schedule text"; 
	
	
	public HeroOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER);//���û���
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}

}
