package com.example.studybuddy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TaskManagerActivity extends AppCompatActivity {

    private LinearLayout taskListContainer;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager);

        taskListContainer = findViewById(R.id.taskListContainer);
        taskList = new ArrayList<>();

        // Adding example tasks for demonstration
        taskList.add(new Task("Math Assignment", "Complete the algebra exercises", "2024-12-10"));
        taskList.add(new Task("Science Project", "Prepare the biology model for submission", "2024-12-15"));

        populateTaskList();
    }

    private void populateTaskList() {
        taskListContainer.removeAllViews();

        for (Task task : taskList) {
            View taskView = getLayoutInflater().inflate(R.layout.task_item, taskListContainer, false);

            TextView titleText = taskView.findViewById(R.id.taskTitle);
            TextView descriptionText = taskView.findViewById(R.id.taskDescription);
            TextView dueDateText = taskView.findViewById(R.id.taskDueDate);

            titleText.setText(task.getTitle());
            descriptionText.setText(task.getDescription());
            dueDateText.setText(task.getDueDate());

            taskView.setOnClickListener(v -> showTaskDetails(task));

            taskListContainer.addView(taskView);
        }
    }

    private void showTaskDetails(Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View taskDetailView = getLayoutInflater().inflate(R.layout.task_detail, null);

        TextView titleText = taskDetailView.findViewById(R.id.taskDetailTitle);
        TextView descriptionText = taskDetailView.findViewById(R.id.taskDetailDescription);
        TextView dueDateText = taskDetailView.findViewById(R.id.taskDetailDueDate);

        titleText.setText(task.getTitle());
        descriptionText.setText(task.getDescription());
        dueDateText.setText(task.getDueDate());

        builder.setView(taskDetailView);

        builder.setNegativeButton("Delete", (dialog, which) -> {
            taskList.remove(task);
            populateTaskList();
        });

        builder.setPositiveButton("Close", (dialog, which) -> dialog.dismiss());

        builder.show();
    }
}
