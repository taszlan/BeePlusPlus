package model;

import com.j256.ormlite.field.DatabaseField;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by atticus on 5/12/16.
 */
public class Event implements HasID {

    @DatabaseField(generatedId = true, columnName = ID)
    private int internalId;
    @DatabaseField
    private int externalId;
    @DatabaseField
    private String title;
    @DatabaseField
    private String text;
    @DatabaseField
    private DateTime startingDate;
    @DatabaseField
    private DateTime endnigDate;
    @DatabaseField
    private DateTime reminderDate;
    @DatabaseField
    private boolean isSynchronized;

    public Event(String title, String text, DateTime startingDate, DateTime endnigDate, DateTime reminderDate) {
        this.title = title;
        this.text = text;
        this.startingDate = startingDate;
        this.endnigDate = endnigDate;
        this.reminderDate = reminderDate;
    }

    public int getInternalId() {
        return internalId;
    }

    public int getExternalId() {
        return externalId;
    }

    public void setExternalId(int externalId) {
        this.externalId = externalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DateTime getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(DateTime startingDate) {
        this.startingDate = startingDate;
    }

    public DateTime getEndnigDate() {
        return endnigDate;
    }

    public void setEndnigDate(DateTime endnigDate) {
        this.endnigDate = endnigDate;
    }

    public DateTime getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(DateTime reminderDate) {
        this.reminderDate = reminderDate;
    }

    public boolean isSynchronized() {
        return isSynchronized;
    }

    public void setSynchronized(boolean aSynchronized) {
        isSynchronized = aSynchronized;
    }

    @Override
    public String toString() {
        return "Event{" +
                "internalId=" + internalId +
                ", externalId=" + externalId +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", startingDate=" + startingDate +
                ", endnigDate=" + endnigDate +
                ", reminderDate=" + reminderDate +
                ", isSynchronized=" + isSynchronized +
                '}';
    }
}



