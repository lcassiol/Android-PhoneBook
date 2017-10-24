package com.example.lcassiol.android_phonebook;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by lcassiol on 10/9/2017.
 */

public class FormHelper {

    private Contact contact;

    private EditText name;
    private EditText email;
    private EditText site;
    private EditText adress;
    private EditText phone;

    private ImageView image;

    private Button btnPhoto;

    public FormHelper(Form activity) {
        this.contact = new Contact();
        this.name = (EditText) activity.findViewById(R.id.nameForm);
        this.email = (EditText) activity.findViewById(R.id.emailForm);
        this.site = (EditText) activity.findViewById(R.id.siteForm);
        this.adress = (EditText) activity.findViewById(R.id.adressForm);
        this.phone = (EditText) activity.findViewById(R.id.phoneForm);
        this.image = (ImageView) activity.findViewById(R.id.imgForm);
        this.btnPhoto = (Button) activity.findViewById(R.id.btnPhoto);
    }

    public Button getButtonPhoto(){
        return btnPhoto;
    }

    public Contact getContactFromForm(){
        contact.setName(name.getText().toString());
        contact.setEmail(email.getText().toString());
        contact.setSite(site.getText().toString());
        contact.setAdress(adress.getText().toString());
        contact.setPhone(phone.getText().toString());

        contact.setPhoto((String) image.getTag());

        return contact;
    }

    public void insertOnForm(Contact myContact){

        name.setText(myContact.getName());
        email.setText(myContact.getEmail());
        site.setText(myContact.getSite());
        adress.setText(myContact.getAdress());
        phone.setText(myContact.getPhone());

        image.setTag(myContact.getPhoto());
        loadImage(myContact.getPhoto());

        this.contact = myContact;

    }

    public void loadImage(String photoFolder){
        if(photoFolder!= null){

            Bitmap imagePhoto = BitmapFactory.decodeFile(photoFolder);
            image.setImageBitmap(imagePhoto);
            image.setTag(photoFolder);
        }
    }
}
