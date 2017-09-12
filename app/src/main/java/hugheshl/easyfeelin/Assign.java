package hugheshl.easyfeelin;

import java.util.Date;
import java.util.UUID;

public class Assign {

    private UUID mId;
    private String mTitle;
    private Date mDueDate;
    private boolean mCompleted;
    private long mtotalTime;
    private String mLogs;

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

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    public long getTotalTime() {
        return mtotalTime;
    }

    public void setTotalTime(long totalTime) {
        mtotalTime = totalTime;
    }

    public String getLogs() {
        return mLogs;
    }

    public void setLogs(String logs) {
        mLogs = logs;
    }
}
