package com.example.lcassiol.android_phonebook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.formToolbar);

        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Editar Contato");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //toolbar.setNavigationIcon(R.drawable.image_back);
        }

        Contact myContact = new Contact();
        myContact.setName("Cassius Carvalho");
        myContact.setEmail("lcassiol");
        myContact.setSite("");
        myContact.setPhone("12345");
        myContact.setAdress("Aqui perto la longe");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.form_ok:

                finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
