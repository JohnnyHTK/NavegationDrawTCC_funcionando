package com.example.martinsj.navegationdraw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

/**
 * Created by jonat on 23/08/2017.
 */

public class Alertas {

    public void meuerro(Context context){
        Dialog meuerro = new Dialog(context);
        meuerro.requestWindowFeature(Window.FEATURE_NO_TITLE);

        meuerro.setContentView(R.layout.meuerro);
        meuerro.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        meuerro.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        meuerro.show();

    }



}
