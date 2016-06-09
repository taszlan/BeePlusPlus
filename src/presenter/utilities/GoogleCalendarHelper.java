package presenter.utilities;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.*;
import com.google.api.services.calendar.model.Event;
import general.Settings;
import model.*;
import org.joda.time.Minutes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by atticus on 5/12/16.
 */
public class GoogleCalendarHelper {

    private com.google.api.services.calendar.Calendar service;
    List<Event> items;
    general.Settings settings;

    public GoogleCalendarHelper(){
        settings = new Settings();
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

    public Event pushEventAndReturnResponse(InternalEvent internalEvent) throws IOException{
        Event googleEvent = new Event()
                .setSummary(internalEvent.getSummary())
                .setDescription(internalEvent.getDescription());

        DateTime startDateTime = new DateTime(internalEvent.getStartDate().toDate());
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Europe/Warsaw");

        googleEvent.setStart(start);

        DateTime endDateTime = new DateTime(internalEvent.getEndDate().toDate());
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Europe/Warsaw");
        googleEvent.setEnd(end);

        List<String> listOfEmails = settings.getListOfEmails();
        List<EventAttendee> listOfAttendees = new ArrayList<>();
        for(String s : listOfEmails){
            listOfAttendees.add(new EventAttendee().setEmail(s));
        }
        googleEvent.setAttendees(listOfAttendees);

        EventReminder[] reminderOverrides = new EventReminder[]{
                new EventReminder().setMethod("email").setMinutes(Minutes.minutesBetween(internalEvent.getNotificationDate(), internalEvent.getStartDate()).getMinutes()),
        };

        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));

        googleEvent.setReminders(reminders);

        String calendarId = "primary";

        return service.events().insert(calendarId, googleEvent).execute();
    }

    public void pushEvent(InternalEvent internalEvent){
        Event googleEvent = new Event()
                .setSummary(internalEvent.getSummary())
                .setDescription(internalEvent.getDescription());

        DateTime startDateTime = new DateTime(internalEvent.getStartDate().toDate());
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Europe/Warsaw");

        googleEvent.setStart(start);

        DateTime endDateTime = new DateTime(internalEvent.getEndDate().toDate());
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Europe/Warsaw");
        googleEvent.setEnd(end);

        List<String> listOfEmails = settings.getListOfEmails();
        List<EventAttendee> listOfAttendees = new ArrayList<>();
        for(String s : listOfEmails){
            listOfAttendees.add(new EventAttendee().setEmail(s));
        }
        googleEvent.setAttendees(listOfAttendees);

        EventReminder[] reminderOverrides = new EventReminder[]{
                new EventReminder().setMethod("email").setMinutes(Minutes.minutesBetween(internalEvent.getNotificationDate(), internalEvent.getStartDate()).getMinutes()),
        };

        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));

        googleEvent.setReminders(reminders);

        String calendarId = "primary";

        try {
            googleEvent = service.events().insert(calendarId, googleEvent).execute();
            System.out.printf("Event created: %s\n", googleEvent.getHtmlLink());
            System.out.println("Event googleId: " + googleEvent.getId());
        } catch (IOException e){
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
                new EventAttendee().setEmail("beekeeper1@examlpe.com"),
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
