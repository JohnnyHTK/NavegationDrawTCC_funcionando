package com.example.martinsj.navegationdraw;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by martinsj on 06/06/2017.
 */


public class Tela1 extends Fragment {

    View MyView;


    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String selectedFilePath;
    private String TEMP_FILENAME = "";
    Context cntx = null;
    private MyFTPClientFunctions ftpclient = new MyFTPClientFunctions();
    private Button btnLoginFtp, btnUploadFile;//, btnDisconnect, btnExit;
    private ProgressDialog pd;

    private ImageButton button, ivAttachment;
    private TextView editText;

    String[] permissions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.VIBRATE,
            Manifest.permission.RECORD_AUDIO,
    };

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(getActivity(), p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }


    private Handler handler = new Handler() {

            public void handleMessage(android.os.Message msg) {

            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            } else if (msg.what == 2) {
                Toast.makeText(getActivity(), "Uploaded Successfully!",
                        Toast.LENGTH_LONG).show();
            } else if (msg.what == 1) {
                Toast.makeText(getActivity(), "Connection Success!",
                        Toast.LENGTH_LONG).show();
            } else if (msg.what == -1) {
            Toast.makeText(getActivity(), "Connection Failed!",
                    Toast.LENGTH_LONG).show();
            } else if (msg.what == -2) {
            Toast.makeText(getActivity(), "Uploaded Failed!",
                    Toast.LENGTH_LONG).show();
            }

        }

    };

    private void showFileChooser() {


        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("file/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent, "Choose File to Upload.."), PICK_FILE_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_FILE_REQUEST) {
                if (data == null) {
                    //no data present
                    return;
                }

                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(null, selectedFileUri);
                Log.i(TAG, "Selected File Path:" + selectedFilePath);

                if (selectedFilePath != null && !selectedFilePath.equals("")) {
//                    Cifracesar cc = new Cifracesar();
//                    cc.encrypt(selectedFilePath);
                    editText.setText(selectedFilePath);
                } else {
//                    Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void connectToFTPAddress() {


        // aqui vai os valores de usuário: Host, nome de usuario e a senha.

//        final String host = "ftp.materiasbruno.hol.es";//edtHostName.getText().toString().trim();
//        final String username = "u582239733"; //edtUserName.getText().toString().trim();
//        final String password = "123456";//edtPassword.getText().toString().trim();

            final String host = "f19-preview.awardspace.net";
            final String username = "2630818_jonathan";
            final String password = "Controle5";
//        pd = ProgressDialog.show(MainActivity.this, "", "Connecting...",
//                true, false);

        new Thread(new Runnable() {
            public void run() {
                boolean status;
                status = ftpclient.ftpConnect(host, username, password, 21);
                if (status == true) {
                    Log.d(TAG, "Connection Sucess");
                    handler.sendEmptyMessage(1);
                    //System.out.println("");
                } else {
                    Log.d(TAG, "Connection failed");
                    handler.sendEmptyMessage(-1);
                }
            }
        }).start();
    }

    private boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }


    //________________________________________________________________________________________________________________
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        MyView = inflater.inflate(R.layout.tela1, container, false);

        checkPermissions();

        editText = (TextView) MyView.findViewById(R.id.editText);
        button = (ImageButton) MyView.findViewById(R.id.button);
        ivAttachment = (ImageButton) MyView.findViewById(R.id.ivAttachment);
        btnLoginFtp = (Button) MyView.findViewById(R.id.btnLoginFtp);
        btnUploadFile = (Button) MyView.findViewById(R.id.btnUploadFile);


        btnLoginFtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context cntx = null;
                if (isOnline(getActivity())) {
                    connectToFTPAddress();
                } else {
                    Toast.makeText(getActivity(),
                            "Verifique sua internet.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnUploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String textoQuebrado[] = selectedFilePath.split(Pattern.quote("/"));
                    int cont = 0;

                    for (int i = 0; i < textoQuebrado.length; i++) {
                        cont++;
                        if (cont == textoQuebrado.length) {
                            TEMP_FILENAME = textoQuebrado[cont - 1];
                        }
                    }
//                    pd = ProgressDialog.show(MainActivity.this, "", "Uploading...",
//                            true, false);
                    new Thread(new Runnable() {
                        public void run() {
                            boolean status;
                            status = ftpclient.ftpUpload(selectedFilePath,
                                    TEMP_FILENAME);

                            // colocar a variável do caminho do arquivo que deseja fazer o upload

                            if (status == true) {
                                Log.d(TAG, "Upload Sucess");
                                handler.sendEmptyMessage(2);
                            } else {
                                Log.d(TAG, "Upload failed");
                                handler.sendEmptyMessage(-2);
                            }
                        }
                    }).start();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        ivAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text20r = "ftp://2630818_jonathan:Controle@f19-preview.awardspace.net/"+TEMP_FILENAME;// aqui coloca o link do servidor ftp, onde está o arquivo
                Cifracesar cc = new Cifracesar();


                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {

                    if(TEMP_FILENAME!="") {
                        Context context = getActivity();


                        BitMatrix bitMatrix = multiFormatWriter.encode(cc.encrypt(text20r), BarcodeFormat.QR_CODE, 200, 200);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        Intent intent = new Intent(context, QrActivity.class);
                        intent.putExtra("pic", bitmap);
                        context.startActivity(intent);


                    }
                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }
        });


        return MyView;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do something
            }
            return;
        }
    }

}
