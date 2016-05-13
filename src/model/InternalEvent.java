package model;

import com.j256.ormlite.field.DatabaseField;
import org.joda.time.DateTime;

/**
 * Created by atticus on 5/12/16.
 */
public class InternalEvent implements HasID {

    @DatabaseField(generatedId = true, columnName = ID)
    private int internalId;
    @DatabaseField
    private String googleCalendarId;
    @DatabaseField
    private String summary;     //Tytuł wydarzenia
    @DatabaseField
    private String description; //Opis
    @DatabaseField
    private DateTime startDate;
    @DatabaseField
    private DateTime endDate;
    @DatabaseField
    private DateTime notificationDate;
    @DatabaseField
    private boolean isSynchronized;

    public InternalEvent(String summary, String description, DateTime startDate, DateTime endDate, DateTime notificationDate) {
        this.summary = summary;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notificationDate = notificationDate;
    }

    public int getInternalId() {
        return internalId;
    }

    public String getGoogleCalendarId() {
        return googleCalendarId;
    }

    public void setGoogleCalendarId(String googleCalendarId) {
        this.googleCalendarId = googleCalendarId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public DateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(DateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public boolean isSynchronized() {
        return isSynchronized;
    }

    public void setSynchronized(boolean isSynchronized) {
        this.isSynchronized = isSynchronized;
    }

    @Override
    public String toString() {
        return "InternalEvent{" +
                "internalId=" + internalId +
                ", googleCalendarId=" + googleCalendarId +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", notificationDate=" + notificationDate +
                ", isSynchronized=" + isSynchronized +
                '}';
    }
}



