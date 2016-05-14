package presenter.utilities;

import model.InternalEvent;
import model.database.access.DatabaseAccessObjectFactory;
import model.database.access.interfaces.DatabaseAccessObject;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by atticus on 5/14/16.
 */
public class CalendarThread extends Thread{

    LinkedBlockingQueue<InternalEvent> linkedBlockingQueue;
    DatabaseAccessObject<InternalEvent> internalEventDao;
    GoogleCalendarHelper googleCalendarHelper;

    public CalendarThread(LinkedBlockingQueue<InternalEvent> linkedBlockingQueue, DatabaseAccessObject<InternalEvent> internalEventDao){
        this.linkedBlockingQueue = linkedBlockingQueue;
        this.internalEventDao = internalEventDao;
        this.googleCalendarHelper = new GoogleCalendarHelper();
    }

    @Override
    public void run() {
        try {
            InternalEvent internalEvent;
            while (true) {
                internalEvent = linkedBlockingQueue.take();
                if(internalEvent.isEndThread()){
                    return;
                }

                internalEventDao.create(internalEvent);
                googleCalendarHelper.pushEvent(internalEvent);
            }
        } catch (InterruptedException e){

        }
    }
}
