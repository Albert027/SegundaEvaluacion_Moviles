package com.example.atriox.segundaevaluacionmoviles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Oscar Campos on 04/11/2017.
 */

public class AdaptadorImagen extends ArrayAdapter<Imagen> {
    private Animation Animacion_ZoomIn,Animacion_ZoomOut;
    Boolean   ZoomIN;
    public AdaptadorImagen(Context context, List<Imagen> objects) {
        super(context, 0, objects);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Imagen imagen = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lista_imagenes, parent, false);
        }
        TextView txtContador = (TextView) convertView.findViewById(R.id.ContadorIMG);
        TextView txtRuta = (TextView) convertView.findViewById(R.id.TxtRuta);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.VistaImagen);


        ZoomIN = true;
        Animacion_ZoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        Animacion_ZoomIn.setDuration(1000);
        Animacion_ZoomIn.setFillAfter(true);

        Animacion_ZoomOut = AnimationUtils.loadAnimation(getContext(),R.anim.zoom_out);
        Animacion_ZoomOut.setDuration(1000);
        Animacion_ZoomOut.setFillAfter(true);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ZoomIN) {
                    view.startAnimation(Animacion_ZoomIn);
                }
                else if (!ZoomIN){
                    view.startAnimation(Animacion_ZoomOut);
                }
                ZoomIN=!ZoomIN;
            }
        });
        txtContador.setText((position+1)+"");
        txtRuta.setText(imagen.Ruta);
        imageView.setImageBitmap(imagen.Imagen);
        return convertView;
    }
}
