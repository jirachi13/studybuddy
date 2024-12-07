package com.example.studybuddy;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private LinearLayout notesListContainer;
    private List<Note> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        notesListContainer = findViewById(R.id.notesListContainer);
        notesList = new ArrayList<>();

        // Add a button to create a new note
        Button addNoteButton = findViewById(R.id.addNoteButton);
        addNoteButton.setOnClickListener(v -> showAddNoteDialog());

        // Populate with sample notes
        populateSampleNotes();
        populateNotesList();
    }

    private void populateSampleNotes() {
        notesList.add(new Note("Meeting Notes", "Discuss project milestones.", "12/09/2024"));
        notesList.add(new Note("Homework Notes", "Complete math exercises.", "12/10/2024"));
    }

    private void populateNotesList() {
        notesListContainer.removeAllViews();

        for (Note note : notesList) {
            View noteView = getLayoutInflater().inflate(R.layout.note_item, notesListContainer, false);

            TextView titleText = noteView.findViewById(R.id.noteTitle);
            TextView descriptionText = noteView.findViewById(R.id.noteDescription);
            TextView dueDateText = noteView.findViewById(R.id.noteDueDate);

            titleText.setText(note.getTitle());
            descriptionText.setText(note.getDescription());
            dueDateText.setText(note.getDueDate());

            noteView.setOnClickListener(v -> showNoteDetails(note));

            notesListContainer.addView(noteView);
        }
    }

    private void showAddNoteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_note, null);

        EditText noteTitleInput = dialogView.findViewById(R.id.noteTitleInput);
        EditText noteDescriptionInput = dialogView.findViewById(R.id.noteDescriptionInput);
        EditText noteDueDateInput = dialogView.findViewById(R.id.noteDueDateInput);

        builder.setView(dialogView);
        builder.setPositiveButton("Add", (dialog, which) -> {
            String title = noteTitleInput.getText().toString().trim();
            String description = noteDescriptionInput.getText().toString().trim();
            String dueDate = noteDueDateInput.getText().toString().trim();

            if (!title.isEmpty() && !description.isEmpty() && !dueDate.isEmpty()) {
                notesList.add(new Note(title, description, dueDate));
                populateNotesList();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void showNoteDetails(Note note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View detailView = getLayoutInflater().inflate(R.layout.note_detail, null);

        TextView titleText = detailView.findViewById(R.id.noteDetailTitle);
        TextView descriptionText = detailView.findViewById(R.id.noteDetailDescription);
        TextView dueDateText = detailView.findViewById(R.id.noteDetailDueDate);

        titleText.setText(note.getTitle());
        descriptionText.setText(note.getDescription());
        dueDateText.setText(note.getDueDate());

        builder.setView(detailView);
        builder.setPositiveButton("Close", null);
        builder.setNegativeButton("Delete", (dialog, which) -> {
            notesList.remove(note);
            populateNotesList();
        });
        builder.show();
    }
}
