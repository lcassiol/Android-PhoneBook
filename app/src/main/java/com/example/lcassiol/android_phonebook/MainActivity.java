package com.example.lcassiol.android_phonebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Form.class);
                startActivity(intent);
            }
        });

        contactList = (ListView) findViewById(R.id.contactList);
        registerForContextMenu(contactList);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Contact selectContact = (Contact) contactList.getAdapter().getItem(info.position);

        final MenuItem itemCall = menu.add("Ligar");
        final MenuItem itemSMS = menu.add("SMS");
        final MenuItem itemSite = menu.add("Site");
        final MenuItem itemDelete = menu.add("Delete");

        itemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Apagar Contato")
                        .setMessage("Deseja Realmente Apagar " + selectContact.getName() + " ?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ContactDAO dao = new ContactDAO(MainActivity.this);
                                dao.deleteContact(selectContact);
                                dao.close();
                                loadList();
                            }
                        })
                        .setNegativeButton("Não", null).show();


                return false;
            }
        });

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected void onResume() {
        loadList();
        super.onResume();
    }

    private void loadList() {
        ContactDAO dao = new ContactDAO(this);
        List<Contact> contacs = dao.getList();
        dao.close();

        ContactAdapter adapter = new ContactAdapter(this,contacs);
        this.contactList.setAdapter(adapter);
    }
}
