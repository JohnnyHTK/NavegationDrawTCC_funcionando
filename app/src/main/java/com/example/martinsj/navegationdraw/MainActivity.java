package com.example.martinsj.navegationdraw;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    final Context context = this;
//    private Button button;
//    private EditText editText;
private EditText editText;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


     /*   Button btnGerar = (Button) findViewById(R.id.button);

       btnGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text20r = editText.getText().toString();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {


                    BitMatrix bitMatrix=  multiFormatWriter.encode(text20r, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    Intent intent =  new Intent(context, com.example.martinsj.navegationdraw.Tela1.class);
                    intent.putExtra("pic",bitmap);
                    context.startActivity(intent);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentMenager= getFragmentManager();

        if (id == R.id.nav_home) {
            fragmentMenager.beginTransaction().replace(R.id.content_frame , new Home()).commit();


            // Handle the camera action
        } else if (id == R.id.nav_tela1) {
            fragmentMenager.beginTransaction().replace(R.id.content_frame , new Tela1()).commit();

            QrActivity qra = new QrActivity();
            final Button btnGerar = (Button) findViewById(R.id.button);

            btnGerar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Adicionando o botão de gerar qr code DENTRO da classe QR Activity
                    btnGerar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String text20r = editText.getText().toString();
                            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                            try {


                                BitMatrix bitMatrix=  multiFormatWriter.encode(text20r, BarcodeFormat.QR_CODE,200,200);
                                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                                Context context = null;
                                Intent intent =  new Intent(context, com.example.martinsj.navegationdraw.Tela1.class);
                                intent.putExtra("pic",bitmap);
                                context.startActivity(intent);
                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                        }



                    });
                }
            });




            // Handle the camera action
        } else if (id == R.id.nav_tela2) {
            fragmentMenager.beginTransaction().replace(R.id.content_frame , new Tela2()).commit();

        } else if (id == R.id.nav_tela3) {
            fragmentMenager.beginTransaction().replace(R.id.content_frame , new Tela3()).commit();

        }
        else if (id == R.id.nav_manage) {

        }
        else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
