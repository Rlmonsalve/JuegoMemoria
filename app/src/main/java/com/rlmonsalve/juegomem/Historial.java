package com.rlmonsalve.juegomem;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Historial extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private String skey1 = "keyParametroIntentos";
    private String skey2 = "keyParametroPuntaje";
    private Button botonRegreso;
    private EditText num, sco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        num = (EditText) findViewById(R.id.editTextNum);
        sco = (EditText) findViewById(R.id.editTextSco);
        botonRegreso = (Button) findViewById(R.id.button);

        mSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        String text = mSharedPreferences.getString(skey1, "0");

        num.setText(text);
        num.setEnabled(false);

        mSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        text = mSharedPreferences.getString(skey2, "0");

        sco.setText(text);
        sco.setEnabled(false);

        botonRegreso.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }


}
