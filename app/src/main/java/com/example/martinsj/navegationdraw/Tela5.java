package com.example.martinsj.navegationdraw;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jonat on 23/08/2017.
 */

public class Tela5 extends Fragment {
    View MyView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        MyView = inflater.inflate(R.layout.tela5,container, false);


        Button enviar = (Button) MyView.findViewById(R.id.enviar);

        enviar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                EditText nome = (EditText) MyView.findViewById(R.id.nome);
//                EditText email = (EditText) MyView.findViewById(R.id.email);
                EditText menssagem = (EditText) MyView.findViewById(R.id.menssagem);
                EditText subject = (EditText) MyView.findViewById(R.id.subject);
                final Alertas alertas = new Alertas();

                // quando não é colocado um dos campos e-mail, nome, assunto e menssagem da erro e chama a classe Alertas
                if (nome.getText().toString().equals("")
//                        || email.getText().toString().equals("")
                        || subject.getText().toString().equals("")
                        || menssagem.getText().toString().equals("")){

                    alertas.meuerro(v.getContext());


//                } else if (email.getText().toString().equals("")){
//                    email.setError("Error");
//                } else if (subject.getText().toString().equals("")){
//                    subject.setError("Error");
//                } else if (menssagem.getText().toString().equals("")){
//                    menssagem.setError("Error");
                } else {
                    Intent i = new Intent(Intent.ACTION_SENDTO);
                    i.setData(Uri.parse("mailto:"));
                    i.putExtra(Intent.EXTRA_EMAIL, new String[]{"tcc.ecompo@gmail.com"});// e-mail para onde será enviado
                    i.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());// pega o assunto do campo e joga no assunto do e-mail
                    i.putExtra(Intent.EXTRA_TEXT, "Nome: " + nome.getText().toString()+
//                            "\nE-mail: "+ email.getText().toString()+
                                    "\n\n\n"+ menssagem.getText().toString());



                    try {
                        startActivity(Intent.createChooser(i, "E-mail enviado"));

                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getContext(), "Nenhum aplicativo de e-mail encontrado", Toast.LENGTH_SHORT).show();
                    } catch (Exception ex) {
                        Toast.makeText(getContext(), "Erro inesperado" + ex.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        return MyView;
    }
}
