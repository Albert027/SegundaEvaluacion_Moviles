package com.example.atriox.segundaevaluacionmoviles;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Oscar Campos on 04/11/2017.
 */

public class Principal extends AppCompatActivity {
    private AdaptadorImagen adaptadorimagen;
    private ArrayList<Imagen> arrayList;
    private Button BtnAgregar;
    private int PICK_PHOTO_FOR_AVATAR=50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        BtnAgregar = (Button) findViewById(R.id.BtnAdd);

        arrayList = new ArrayList<>();

        adaptadorimagen = new AdaptadorImagen(this, arrayList);

        ListView lista = (ListView) findViewById(R.id.MostrarLstIMG);

        lista.setAdapter(adaptadorimagen);
        BtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

    }
    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
                if(bmp!=null) {
                    String ruta = data.getData().getPath();
                    arrayList.add(new Imagen(ruta,bmp));
                    adaptadorimagen.notifyDataSetChanged();
                }
            } catch (IOException e) {
                Toast.makeText(this,"Error loading image",Toast.LENGTH_SHORT);
                e.printStackTrace();
            }

        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

}