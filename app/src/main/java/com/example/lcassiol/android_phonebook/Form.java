package com.example.lcassiol.android_phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Form extends AppCompatActivity {

    FormHelper formHelper;
    Contact contact;

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

        formHelper = new FormHelper(this);

        Intent intent = this.getIntent();
        this.contact = (Contact) intent.getSerializableExtra("selectedContact");

        if(this.contact != null){
            this.formHelper.insertOnForm(this.contact);
        }
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

                Contact contact = formHelper.getContactFromForm();
                ContactDAO contactDAO = new ContactDAO(Form.this);
                if(contact.getId() == null){
                    contactDAO.insertContact(contact);
                }else{
                    contactDAO.updateContact(contact);
                }

                contactDAO.close();

                finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
