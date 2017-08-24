package com.example.martinsj.navegationdraw;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.example.martinsj.navegationdraw.R.id.webView;

/**
 * Created by jonat on 22/08/2017.
 */

public class Tela4 extends Fragment{
    View MyView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        MyView = inflater.inflate(R.layout.tela4,container, false);


        // webview página da ecompo
       WebView wv = (WebView) MyView.findViewById(R.id.webView);

        String url="http://ecompo.com.br/novo/";

        //carregar a página no webview xml
        wv.loadUrl(url);

        // se url diferente de vazio e começar com http:// ele carrega
        wv.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null && url.startsWith("http://")) {
                    view.getContext().startActivity(
                            new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else {
                    return false;
                }
            }
        });

        return MyView;
    }
}
