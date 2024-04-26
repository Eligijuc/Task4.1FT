package com.example.third_assignment_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class DeleteNoteActivity extends AppCompatActivity {

    private ListView lvDeleteNotes;
    private ArrayAdapter<String> deleteListAdapter;
    private ArrayList<String> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        lvDeleteNotes = findViewById(R.id.lvDeleteNotes);
        notesList = retrieveNotes();
        deleteListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        lvDeleteNotes.setAdapter(deleteListAdapter);

        lvDeleteNotes.setOnItemClickListener((parent, view, position, id) -> {
            deleteNote(position);
            deleteListAdapter.notifyDataSetChanged();
        });
    }

    private ArrayList<String> retrieveNotes() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        return new ArrayList<>(sp.getStringSet("notes", new HashSet<>()));
    }

    private void deleteNote(int position) {
        notesList.remove(position);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet("notes", new HashSet<>(notesList));
        editor.apply();
    }
}
