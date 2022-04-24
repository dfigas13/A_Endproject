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
    private BorderPane borderPane;

    private void loadFXMLInBorderPaneCenter(String fxmlPath) throws IOException {

        Parent newCenterRoot = FXMLLoader.load(getClass().getResource(fxmlPath));
        borderPane.setCenter(newCenterRoot);
    }

    private void loadCalendarFXViewInBorderPaneCenter() {

        CalendarView calendarView = new CalendarView();
        Calendar birthdays = new Calendar("Birthdays");
        Calendar holidays = new Calendar("Holidays");

        birthdays.setStyle(Calendar.Style.STYLE1);
        holidays.setStyle(Calendar.Style.STYLE2);

        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        myCalendarSource.getCalendars().addAll(birthdays, holidays);

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
        calendarView.setShowDeveloperConsole(true);

        borderPane.setCenter(new CalendarView());
    }

    @FXML
    private void initialize() {

        // loadCalendarFXViewInBorderPaneCenter();

        model.pathForDetailViewProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue != null) {
                    try {
                        loadFXMLInBorderPaneCenter(newValue);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
