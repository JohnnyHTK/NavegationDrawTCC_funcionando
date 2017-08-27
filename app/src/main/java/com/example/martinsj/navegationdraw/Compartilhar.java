package com.example.martinsj.navegationdraw;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jonat on 23/08/2017.
 */

public class Compartilhar extends Fragment{
    View MyView;

    @Nullable
    @Override


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        MyView = inflater.inflate(R.layout.compartilhar,container, false);

            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

            share.putExtra(Intent.EXTRA_SUBJECT, "Qr4Share");
            share.putExtra(Intent.EXTRA_TEXT, "http://www.ecompo.com.br/novo/");

            startActivity(Intent.createChooser(share, "Compartilhar App Qr4Share!!"));

         return MyView;
    }
}
