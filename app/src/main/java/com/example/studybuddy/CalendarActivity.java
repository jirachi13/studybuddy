package com.example.studybuddy;

import android.os.Bundle;
import android.view.View; // Ensure this import is present
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private LinearLayout eventsListContainer;
    private TextView selectedDateTextView;

    private HashMap<String, List<Event>> eventsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        eventsListContainer = findViewById(R.id.eventsListContainer);
        selectedDateTextView = findViewById(R.id.selectedDateTextView);

        eventsMap = new HashMap<>();
        populateSampleEvents();

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = (month + 1) + "/" + dayOfMonth + "/" + year;
            selectedDateTextView.setText(selectedDate);
            populateEventsForDate(selectedDate);
        });
    }

    private void populateSampleEvents() {
        addEvent("12/09/2024", new Event("Math Assignment", "Complete algebra homework."));
        addEvent("12/10/2024", new Event("Science Project", "Submit biology model."));
    }

    private void addEvent(String date, Event event) {
        if (!eventsMap.containsKey(date)) {
            eventsMap.put(date, new ArrayList<>());
        }
        eventsMap.get(date).add(event);
    }

    private void populateEventsForDate(String date) {
        eventsListContainer.removeAllViews();

        if (eventsMap.containsKey(date)) {
            for (Event event : eventsMap.get(date)) {
                View eventView = getLayoutInflater().inflate(R.layout.event_item, eventsListContainer, false);

                TextView titleText = eventView.findViewById(R.id.eventTitle);
                TextView descriptionText = eventView.findViewById(R.id.eventDescription);

                titleText.setText(event.getTitle());
                descriptionText.setText(event.getDescription());

                eventsListContainer.addView(eventView);
            }
        } else {
            TextView noEventsText = new TextView(this);
            noEventsText.setText("No events for this date.");
            noEventsText.setTextColor(getResources().getColor(R.color.blue));
            noEventsText.setTextSize(16);
            eventsListContainer.addView(noEventsText);
        }
    }
}
