package presenter.utilities;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by atticus on 5/12/16.
 */
public class GoogleCalendarHelper {

    private com.google.api.services.calendar.Calendar service;
    List<Event> items;

    public GoogleCalendarHelper(){
        try {
            // Build a new authorized API client service.
            // Note: Do not confuse this class with the
            //   com.google.api.services.calendar.model.Calendar class.
            CalendarService calendarService = new CalendarService();
            service = calendarService.getCalendarService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pushTestEvent(){
        Event event = new Event()
                .setSummary("BPP Test Event")
                .setDescription("A chance to test bees");

        DateTime startDateTime = new DateTime(new org.joda.time.DateTime().plusHours(1).toDate());
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Europe/Warsaw");
        event.setStart(start);

        DateTime endDateTime = new DateTime(new org.joda.time.DateTime().plusHours(2).toDate());
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Europe/Warsaw");
        event.setEnd(end);

        //String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
        //event.setRecurrence(Arrays.asList(recurrence));

        EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail("beekeeper1@example.com"),
                new EventAttendee().setEmail("beekeeper2@example.com"),
        };
        event.setAttendees(Arrays.asList(attendees));

        EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);

        String calendarId = "primary";
        try {
            event = service.events().insert(calendarId, event).execute();
            System.out.printf("Event created: %s\n", event.getHtmlLink());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getEvents(){
        try {
            // List the next 10 events from the primary calendar.
            DateTime now = new DateTime(System.currentTimeMillis());
            Events events = service.events().list("primary")
                    .setMaxResults(10)
                    .setTimeMin(now)
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();
            items = events.getItems();
            if (items.size() == 0) {
                System.out.println("No upcoming events found.");
            } else {
                System.out.println("Upcoming events");
                for (Event event : items) {
                    DateTime start = event.getStart().getDateTime();
                    if (start == null) {
                        start = event.getStart().getDate();
                    }
                    System.out.printf("%s (%s)\n", event.getSummary(), start);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void deleteAllEvents(){
        try {
            for (Event e : items) {
                service.events().delete("primary", e.getId()).execute();
            }
            System.out.println("All events deleted");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
