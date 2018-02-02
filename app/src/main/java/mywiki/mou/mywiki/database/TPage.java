package mywiki.mou.mywiki.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mywiki.mou.mywiki.model.Page;
import mywiki.mou.mywiki.model.Terms;
import mywiki.mou.mywiki.model.Thumbnail;

import static mywiki.mou.mywiki.database.MySQLiteHelper.TABLE_PAGE;
import static mywiki.mou.mywiki.database.MySQLiteHelper.V_DESCRIPTION;
import static mywiki.mou.mywiki.database.MySQLiteHelper.V_ID;
import static mywiki.mou.mywiki.database.MySQLiteHelper.V_PIC;
import static mywiki.mou.mywiki.database.MySQLiteHelper.V_TITLE;

/**
 * Created by Mou on 2/02/2018.
 */

public class TPage {


    // function to be inserted in SQL lite helper
    public static void onCreate(SQLiteDatabase db) {
        String tbl = "CREATE TABLE IF NOT EXISTS " + TABLE_PAGE +
                "( " +
                V_ID + " INTEGER NOT NULL UNIQUE, " + // msg id
                V_TITLE + " TEXT," +
                V_DESCRIPTION + " TEXT," +
                V_PIC + " TEXT," +
                " PRIMARY KEY( " + V_ID + ")" + ");";
        db.execSQL(tbl);
    }

    // function to be inserted in SQL lite helper
    public static void onUpgrade(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAGE);
    }

    public static long insert(Page page) {
        long rowId = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(V_ID, page.getId());
            values.put(V_TITLE, page.getTitle());
            if (page.getTerms() != null && page.getTerms().getDescription() != null && page.getTerms().getDescription().size() > 0) {
                values.put(V_DESCRIPTION, page.getTerms().getDescription().get(0));
            }
            if (page.getThumbnail() != null) {
                values.put(V_PIC, page.getThumbnail().getSource());
            }
            rowId = MySQLiteInstance.getDb().insertWithOnConflict(TABLE_PAGE, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        } catch (Exception e) {
        }
        return rowId;
    }

    public static List<Page> getVisitedPages() {
        List<Page> pageList = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + TABLE_PAGE;
            Cursor cursor = MySQLiteInstance.getDb().rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    Page page = new Page();
                    page.setId(cursor.getLong(cursor.getColumnIndex(V_ID)));
                    page.setTitle(cursor.getString(cursor.getColumnIndex(V_TITLE)));
                    List<String> desc = new ArrayList<>();
                    desc.add(cursor.getString(cursor.getColumnIndex(V_DESCRIPTION)));
                    Terms terms = new Terms();
                    terms.setDescription(desc);
                    page.setTerms(terms);
                    Thumbnail thumbnail = new Thumbnail();
                    thumbnail.setSource(cursor.getString(cursor.getColumnIndex(V_PIC)));
                    page.setThumbnail(thumbnail);
                    pageList.add(page);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
        }

        return pageList;
    }

    public static long getVisitedPageNum() {
        long cnt = 0;
        try {
            cnt = DatabaseUtils.queryNumEntries(MySQLiteInstance.getDb(), TABLE_PAGE);
        } catch (Exception e) {

        }
        return cnt;
    }

}
