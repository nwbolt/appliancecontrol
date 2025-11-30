package com.interview.service;

import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.interview.controller.SmartApplianceController;

public class YearlyUpdateService {

    //Configurable date and time when yearly update runs
    private static final Month UPDATE_MONTH = Month.JANUARY;
    private static final int UPDATE_DAY = 1;
    private static final LocalTime UPDATE_TIME = LocalTime.of(1, 0);

     private final SmartApplianceController controller;
     private final ZoneId timeZoneID;
     private final ScheduledExecutorService executor;


     private YearlyUpdateService(SmartApplianceController controller, ZoneId timeZoneID, ScheduledExecutorService executor) {
        this.controller = controller;
        this.timeZoneID = timeZoneID;
        this.executor = executor;
        }
        
    //Default constructor
    public YearlyUpdateService(SmartApplianceController controller) {
        this(controller, ZoneId.systemDefault(), Executors.newSingleThreadScheduledExecutor(backgroundUpdate -> {
            Thread thread = new Thread(backgroundUpdate);
            thread.setDaemon(true);
            thread.setName("Yearly-Update-Service-Thread");
            return thread;
        }));
    }

    //publicly accessible start method
     public void startService() {
        scheduleNext();
    }

    //publicly accessible shutdown method
     public void shutdownService() {
        executor.shutdownNow();
    }

    //schedules when running thread will execute the performYearlyUpdate in the future 
    private void scheduleNext() {
        ZonedDateTime now = ZonedDateTime.now(timeZoneID);
        ZonedDateTime nextUpdate = nextUpdateDelay(now);
        long delayMillis = java.time.Duration.between(now, nextUpdate).toMillis();

        executor.schedule(this::performYearlyUpdate, delayMillis, java.util.concurrent.TimeUnit.MILLISECONDS);
    }

    // performs update on applainces: turns them all off and schedules next update
    void performYearlyUpdate() {
        try {
            controller.turnAllOff();
            System.out.println("Yearly system update has completed.");
        } catch (Exception e) {
            e.printStackTrace();} finally {
            // Schedule next year
            scheduleNext(); 
        }
    }

    // Calcuates date for next update, if reference date has already passed, plans update for next year 
    ZonedDateTime nextUpdateDelay(ZonedDateTime from) {
        ZonedDateTime nextUpdateYear = from.withMonth(UPDATE_MONTH.getValue())
                                       .withDayOfMonth(UPDATE_DAY)
                                       .withHour(UPDATE_TIME.getHour())
                                       .withMinute(UPDATE_TIME.getMinute())
                                       .withSecond(0)
                                       .withNano(0);
        if (!nextUpdateYear.isAfter(from)) {
            nextUpdateYear = nextUpdateYear.plusYears(1);
        }
        return nextUpdateYear;
    }  
}
