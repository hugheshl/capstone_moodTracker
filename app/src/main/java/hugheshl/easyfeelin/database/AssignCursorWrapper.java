package hugheshl.easyfeelin.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import hugheshl.easyfeelin.Assign;
import hugheshl.easyfeelin.database.AssignDbSchema.AssignTable;

import java.util.Date;
import java.util.UUID;

public class AssignCursorWrapper extends CursorWrapper {

    public AssignCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Assign getAssign() {
        String uuidString = getString(getColumnIndex(AssignTable.Cols.UUID));
        String title = getString(getColumnIndex(AssignTable.Cols.TITLE));
        long Date = getLong(getColumnIndex(AssignTable.Cols.DATE));
        String logs = getString(getColumnIndex(AssignTable.Cols.LOGS));
        int mood = getInt(getColumnIndex(AssignTable.Cols.MOOD));

        Assign assign = new Assign(UUID.fromString(uuidString));
        assign.setTitle(title);
        assign.setDate(new Date(Date));
        assign.setLogs(logs);
        assign.setMood(mood);

        return assign;
    }
}
