package mywiki.mou.mywiki.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mou on 2/2/2018.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "wiki.db";
    private static final int DATABASE_VERSION = 1;
    public static String TABLE_PAGE = "page_table";
    public static String V_ID = "_id";
    public static String V_TITLE = "title";
    public static String V_DESCRIPTION = "description";
    public static String V_PIC = "pic";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TPage.onCreate(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        TPage.onUpgrade(db);
    }
}
