package com.umbrella.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NotesFragment.OnNoteClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.notes_titles, new NotesFragment()).commit();

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.navigation_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                getSupportFragmentManager().beginTransaction().replace(R.id.notes_titles, new InfoFragment()).addToBackStack(null).commit();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        getSupportFragmentManager().beginTransaction().replace(R.id.notes_titles, new NoteAddFragment()).addToBackStack(null).commit();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNoteClicked(Note note) {
        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);
        if (isLandscape) {
            getSupportFragmentManager().beginTransaction().replace(R.id.note_description_fragment, NotesDescriptionFragment.newInstance(note)).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.notes_titles, NotesDescriptionFragment.newInstance(note)).addToBackStack(null).commit();
        }
    }
}