package com.example.atriox.segundaevaluacionmoviles;

import android.graphics.Bitmap;

/**
 * Created by Oscar Campos on 04/11/2017.
 */

public class Imagen {

    public String Ruta;
    public Bitmap Imagen;

    public Imagen(String R, Bitmap IMG){
        this.Ruta = R;
        this.Imagen = IMG;
    }

    public String getRuta() {
        return this.Ruta;
    }

    public void setRuta(String ruta) {
        this.Ruta = ruta;
    }

    public Bitmap getImagen() {
        return this.Imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.Imagen = imagen;
    }
}
