package com.example.lcassiol.android_phonebook;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lcass on 10/9/2017.
 */

public class ContactAdapter extends BaseAdapter{

    private final List<Contact> contacs;
    private final Activity activity;

    public ContactAdapter(Activity activity,List<Contact> contacs) {
        this.contacs = contacs;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.contacs.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contacs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.contacs.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View line = convertView;
        Contact contact = contacs.get(position);
        Bitmap bitmap;

        if(line==null){
            line = this.activity.getLayoutInflater().inflate(R.layout.cel_contact,parent,false);
        }

        TextView name = (TextView) line.findViewById(R.id.nameCel);
        TextView email = (TextView) line.findViewById(R.id.emailCel);
        TextView site = (TextView) line.findViewById(R.id.siteCel);
        TextView phone = (TextView) line.findViewById(R.id.phoneCel);
        TextView adress = (TextView) line.findViewById(R.id.adressCel);

        ImageView photo = (ImageView) line.findViewById(R.id.imageCel);

        if(position%2 == 0){
            line.setBackgroundColor(activity.getResources().getColor(R.color.colorPar));
        }else{
            line.setBackgroundColor(activity.getResources().getColor(R.color.colorImpar));
        }


        name.setText(contact.getName());
        phone.setText(contact.getPhone());

        if(email != null){ email.setText(contact.getEmail()); }
        if(site != null){ site.setText(contact.getSite()); }
        if(adress != null){ adress.setText(contact.getAdress()); }

        if(contact.getPhoto() != null){
            bitmap = BitmapFactory.decodeFile(contact.getPhoto());
        }else{
            bitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.contato);
        }

        if(bitmap != null){
            bitmap = Bitmap.createScaledBitmap(bitmap,180,180,true);
        }

        photo.setImageBitmap(bitmap);



        return line;
    }
}
