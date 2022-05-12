package com.example.endproject.controller;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CalendarController extends BaseController {

    @FXML
    private BorderPane borderCalender;

    @FXML
    public void initialize() {
        //loadCalendarFXViewInBorderPaneCenter();
    }

    private void loadCalendarFXViewInBorderPaneCenter() {
        System.setProperty("calendarfx.developer","true");


        CalendarView calendarView = new CalendarView();
        Calendar birthdays = new Calendar("Birthdays");
        Calendar java = new Calendar("Java");

        birthdays.setStyle(Calendar.Style.STYLE1);
        java.setStyle(Calendar.Style.STYLE2);

        /*Entry<String> entry = new Entry<>("Java");
        Interval interval = new Interval();
        entry.setInterval(interval);*/

      /*  model.bookings.forEach(element -> {
            Entry<Booking> entry = new Entry<>(element.getCourse().getCourseName());
            Interval interval = new Interval(element.getDateTimeStart(), element.getDateTimeStart(),
                    ZoneId.systemDefault());
            entry.setInterval(interval);
            java.addEntries(entry);
        });
*/


        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        myCalendarSource.getCalendars().addAll(birthdays, java);

        calendarView.getCalendarSources().addAll(myCalendarSource);

        calendarView.setRequestedTime(LocalTime.now());

        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
        //developer console hinzufügen
        calendarView.setShowDeveloperConsole(true);
        borderCalender.setCenter(calendarView);
    }






}
