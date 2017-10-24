package com.example.lcassiol.android_phonebook;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.InputStream;


public class Form extends AppCompatActivity {

    FormHelper formHelper;
    Contact contact;

    private Bitmap bitmap;
    private ImageView contactImageView;

    private String pathFilePhoto;
    private static final int PICK_PHOTO = 747;
    private boolean photoResource = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.formToolbar);

        contactImageView = (ImageView) findViewById(R.id.imgForm);

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

        final Button btnPhoto = formHelper.getButtonPhoto();

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertSourceImage();
            }
        });
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

    public void loadCamera(){
        photoResource = true;

        File imagePath = new File(getApplicationContext().getFilesDir(), "images");
        if(!imagePath.exists()){
            imagePath.mkdir();
        }
        File newFile = new File(imagePath, System.currentTimeMillis() + ".jpg");
        this.pathFilePhoto = newFile.getAbsolutePath();
        Uri contentUri = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".fileprovider", newFile);

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        if (contentUri != null) {
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            intentCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        }

        startActivityForResult(intentCamera,747);
    }

    public  void loadLibrary(){
        photoResource = false;

        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void alertSourceImage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Selecione a fonte da imagem:");
        builder.setPositiveButton("Câmera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loadCamera();
            }
        });

        builder.setNegativeButton("Biblioteca", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loadLibrary();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(photoResource){
            if(requestCode == PICK_PHOTO){
                if(resultCode == Activity.RESULT_OK){
                    formHelper.loadImage(this.pathFilePhoto);
                }else{
                    this.pathFilePhoto = null;
                }
            }
        }else{
            if(resultCode == -1){
                Uri imagemSel = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(imagemSel,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String caminhoFoto = cursor.getString(columnIndex);
                cursor.close();

                formHelper.loadImage(caminhoFoto);
            }
        }
    }


}
