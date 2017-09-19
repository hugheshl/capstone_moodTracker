package hugheshl.easyfeelin;

import java.util.Date;
import java.util.UUID;

public class Assign {

    private UUID mId;
    private String mTitle;
    private Date mDueDate;
    private String mLogs;
    private int mMood;

    public Assign() {
        this(UUID.randomUUID());
    }

    public Assign(UUID id) {
        mId = id;
        mDueDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDueDate;
    }

    public void setDate(Date date) {
        mDueDate = date;
    }

    public String getLogs() {
        return mLogs;
    }

    public void setLogs(String logs) {
        mLogs = logs;
    }

    public int getMood() {
        return mMood;
    }

    public void setMood(int mood) {
        mMood = mood;
    }
}
