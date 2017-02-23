package com.hfad.sdacourseapplication.listapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hfad.sdacourseapplication.MainActivity;
import com.hfad.sdacourseapplication.R;

public class ListAppActivity extends AppCompatActivity implements OnItemCheckStateChanged {


    private ToDoListAdapter toDoListAdapter;
    private String activityTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_app_layout);
        activityTitle = getSupportActionBar().getTitle().toString();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        toDoListAdapter = new ToDoListAdapter();
        recyclerView.setAdapter(toDoListAdapter);
        toDoListAdapter.setCheckListener(this);

        final EditText editText = (EditText) findViewById(R.id.todo_edit_text);
        Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() > 0) {
                    toDoListAdapter.addItem(editText.getText().toString());
                    editText.setText("");
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_app_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_items) {
            toDoListAdapter.deleteAllCheckedItems();
        } else if (item.getItemId() == R.id.back_from_list_app) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemCheckStateChanged(int checkedItemsCount) {
        if (checkedItemsCount > 0) {
            getSupportActionBar().setTitle("Zaznaczone elementy: " + checkedItemsCount);
        }
        else {
            getSupportActionBar().setTitle(activityTitle);
        }
    }
}
