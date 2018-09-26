package com.example.keiti.aboutkings;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int MENU_NAME = Menu.FIRST + 1;
    public static final int MENU_TEXT = Menu.FIRST + 2;

    private List<King> kings = (new Kings()).getKings();
    private ListView view;
    private EditText from, to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.list_kings);
        from = findViewById(R.id.txt_from);
        to = findViewById(R.id.txt_to);

        disable(from);
        disable(to);

        view.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, kings));
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                update(position);
            }
        });
        // Context menu has to registered to programs ListView component
        registerForContextMenu(view);
    }

    // Creates menu, ours is simple with only two menu items
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    menu.add(Menu.NONE, MENU_NAME, Menu.NONE, "Kings");
    menu.add(Menu.NONE, MENU_TEXT, Menu.NONE, "Description");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        King king = kings.get(menuInfo.position);

        switch (item.getItemId()) {
            case MENU_NAME:
                Toast.makeText(this, king.getKing(), Toast.LENGTH_SHORT).show();
                return true;
            case MENU_TEXT:
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

    private void disable(EditText view) {
        view.setKeyListener(null);
        view.setEnabled(false);
    }

    private void update(int position) {
        int a = kings.get(position).getFrom();
        int b = kings.get(position).getTo();

        from.setText(a == 0 ? "" : "" + a);
        to.setText(b == 9999 ? "" : "" + b);
    }

    public void next(View view) {
        Intent nextPage = new Intent(MainActivity.this, SelectKings.class);
        startActivity(nextPage);
    }
}
