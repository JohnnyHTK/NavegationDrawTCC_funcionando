package com.example.martinsj.navegationdraw;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * Created by martinsj on 06/06/2017.
 */


public class Tela1 extends Fragment {


    private ImageButton button;
    private EditText editText;



    View MyView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        MyView = inflater.inflate(R.layout.tela1, container, false);


        editText = (EditText) MyView.findViewById(R.id.editText);
        button = (ImageButton) MyView.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text20r = editText.getText().toString();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {




                    Context context = getContext();




                    BitMatrix bitMatrix = multiFormatWriter.encode(text20r, BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    Intent intent = new Intent(context, QrActivity.class);
                    intent.putExtra("pic", bitmap);
                    context.startActivity(intent);



                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });


        return MyView;


    }

}
