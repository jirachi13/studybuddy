package com.example.studybuddy;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    private EditText taskTitleInput, taskDescriptionInput;
    private TextView dueDateTextView;
    private Button submitButton;
    private String dueDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskTitleInput = findViewById(R.id.taskTitleInput);
        taskDescriptionInput = findViewById(R.id.taskDescriptionInput);
        dueDateTextView = findViewById(R.id.dueDateTextView);
        submitButton = findViewById(R.id.submitButton);

        dueDateTextView.setOnClickListener(v -> openDatePicker());

        submitButton.setOnClickListener(v -> {
            String title = taskTitleInput.getText().toString().trim();
            String description = taskDescriptionInput.getText().toString().trim();

            if (title.isEmpty() || description.isEmpty() || dueDate.isEmpty()) {
                // Handle empty fields
                dueDateTextView.setError("All fields are required.");
                return;
            }

            // Return data to Task Manager (You could use SharedPreferences, Database, or Intent)
            Intent resultIntent = new Intent();
            resultIntent.putExtra("taskTitle", title);
            resultIntent.putExtra("taskDescription", description);
            resultIntent.putExtra("taskDueDate", dueDate);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());
    }

    private void openDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            dueDate = (month1 + 1) + "/" + dayOfMonth + "/" + year1;
            dueDateTextView.setText(dueDate);
        }, year, month, day);

        datePickerDialog.show();
    }
}
