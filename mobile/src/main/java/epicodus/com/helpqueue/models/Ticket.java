package epicodus.com.helpqueue.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

import epicodus.com.helpqueue.R;

/**
 * Created by jake on 6/12/15.
 */
public class Ticket implements Parcelable{
    private String mStudent;
    private boolean mOpen;
    private long mCreatedAt;
    private String mQuestion;
    private String mLanguage;

    public Ticket(String student, String question, boolean open, String language) {
        mStudent = student;
        mQuestion = question;
        mLanguage = language;
        mOpen = open;
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

    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm");
        Date date = new Date(mCreatedAt);
        return formatter.format(date);
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public int getIconId() {
        return R.drawable.java;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mStudent);
        dest.writeLong(mCreatedAt);
        dest.writeInt((mOpen ? 1 : 0));
        dest.writeString(mQuestion);
        dest.writeString(mStudent);
    }

    private Ticket(Parcel in) {
        mStudent = in.readString();
        mCreatedAt = in.readLong();
        mOpen = in.readByte() == 1;
        mQuestion = in.readString();
        mStudent = in.readString();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel source) {
            return new Ticket(source);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };
}
