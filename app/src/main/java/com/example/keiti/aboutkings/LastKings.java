package com.example.keiti.aboutkings;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class LastKings extends AppCompatActivity {

    private List<King> kings;
    private ListView view;
    private ArrayAdapter<King> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_kings);

        view = findViewById(R.id.list_kings);
        reset();
        registerForContextMenu(view);
    }

    // Creates menu, ours is simple with only two menu items
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        new MenuInflater(this).inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        King king = kings.get(menuInfo.position);

        switch (item.getItemId()) {
            case R.id.king_menu:
                Toast.makeText(this, king.getKing(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.text_menu:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(king.getName());
                builder.setMessage(king.getText());
                builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return true;


        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset_menu:
                reset();
                return true;
            case R.id.remove_menu:
                remove();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void reset() {
        kings = new Kings().getKings();
        view.setAdapter(adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, kings));
    }

    public void remove() {
        SparseBooleanArray checkedItems = view.getCheckedItemPositions();
        if (checkedItems != null) {
            for (int i = checkedItems.size() - 1; i >= 0; --i) {
                if (checkedItems.valueAt(i)) {
                    King king = kings.get(checkedItems.keyAt(i));
                    adapter.remove(king);
                }
            }
            // removes ticks from checkboxes
            for (int i = 0; i < view.getCount(); i++) view.setItemChecked(i, false);
        }
    }
}
