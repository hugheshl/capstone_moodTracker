package hugheshl.easyfeelin.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import hugheshl.easyfeelin.database.AssignDbSchema.AssignTable;

public class AssignBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "assignBase.db";

    public AssignBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + AssignTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                AssignTable.Cols.UUID + ", " +
                AssignTable.Cols.TITLE + ", " +
                AssignTable.Cols.DATE + ", " +
                AssignTable.Cols.COMPLETED + ", " +
                AssignTable.Cols.TOTALTIME + ", " +
                AssignTable.Cols.LOGS +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}