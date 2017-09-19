package hugheshl.easyfeelin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import hugheshl.easyfeelin.database.AssignBaseHelper;
import hugheshl.easyfeelin.database.AssignCursorWrapper;
import hugheshl.easyfeelin.database.AssignDbSchema;
import hugheshl.easyfeelin.database.AssignDbSchema.AssignTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static hugheshl.easyfeelin.database.AssignDbSchema.AssignTable.*;
import static hugheshl.easyfeelin.database.AssignDbSchema.AssignTable.Cols.*;

public class AssignLab {
    private static AssignLab sAssignLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static AssignLab get(Context context) {
        if (sAssignLab == null) {
            sAssignLab = new AssignLab(context);
        }
        return sAssignLab;
    }

    private AssignLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new AssignBaseHelper(mContext).getWritableDatabase();

    }

    public void addAssign(Assign c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(AssignTable.NAME, null, values);
    }

    public List<Assign> getAssigns() {
        List<Assign> assigns = new ArrayList<>();
        AssignCursorWrapper cursor = queryAssigns(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                assigns.add(cursor.getAssign());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return assigns;
    }

    public Assign getAssign(UUID id) {
        AssignCursorWrapper cursor = queryAssigns(
                AssignTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getAssign();
        } finally {
            cursor.close();
        }
    }

    public void deleteAssign(UUID assignId)
    {
        String uuidString = assignId.toString();
        mDatabase.delete(AssignTable.NAME, AssignTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    public void updateAssign(Assign assign) {
        String uuidString = assign.getId().toString();
        ContentValues values = getContentValues(assign);
        mDatabase.update(AssignTable.NAME, values,
                AssignTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private AssignCursorWrapper queryAssigns(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                AssignTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new AssignCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Assign assign) {
        ContentValues values = new ContentValues();
        values.put(UUID, assign.getId().toString());
        values.put(TITLE, assign.getTitle());
        values.put(DATE, assign.getDate().getTime());
        values.put(LOGS, assign.getLogs());
        values.put(MOOD, assign.getMood());
        return values;
    }


}
