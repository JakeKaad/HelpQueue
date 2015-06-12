package epicodus.com.helpqueue.models;

import java.util.Date;

/**
 * Created by jake on 6/12/15.
 */
public class Ticket {
    private String mStudent;
    private boolean mOpen;
    private long mCreatedAt;
    private long mClosedAt;
    private String mQuestion;
    private String mLanguage;

    public Ticket(String student, String question, String language) {
        mStudent = student;
        mQuestion = question;
        mLanguage = language;
        mOpen = false;
        mCreatedAt = System.currentTimeMillis();
    }

    public String getStudent() {
        return mStudent;
    }

    public void setStudent(String student) {
        mStudent = student;
    }

    public boolean isOpen() {
        return mOpen;
    }

    public void setOpen(boolean open) {
        mOpen = open;
    }

    public long getCreatedAt() {
        return mCreatedAt;
    }

    public long getClosedAt() {
        return mClosedAt;
    }

    public void setClosedAt(long closedAt) {
        mClosedAt = closedAt;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getLanguage() {
        return mLanguage;
    }
}
