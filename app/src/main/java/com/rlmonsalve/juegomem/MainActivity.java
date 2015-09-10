package com.rlmonsalve.juegomem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.os.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAcelSensor;
    private SharedPreferences mSharedPreferences;
    private String skey1 = "keyParametroIntentos";
    private String skey2 = "keyParametroPuntaje";
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, botonCancel, botonHist, botonInfo;
    private boolean check;
    private int v1, v2, puntaje, intentos, mayor, ultimo;
    private long LastUpdate;
    private List<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check=false;
        v1=0;
        v2=0;
        puntaje=0;
        ultimo=0;
        list=randomize();
        b1 = (Button) findViewById(R.id.button1); int size = b1.getHeight(); b1.setWidth(size);
        b2 = (Button) findViewById(R.id.button2); b2.setHeight(size);
        b3 = (Button) findViewById(R.id.button3); b3.setHeight(size);
        b4 = (Button) findViewById(R.id.button4); b4.setHeight(size);
        b5 = (Button) findViewById(R.id.button5); b5.setHeight(size);
        b6 = (Button) findViewById(R.id.button6); b6.setHeight(size);
        b7 = (Button) findViewById(R.id.button7); b7.setHeight(size);
        b8 = (Button) findViewById(R.id.button8); b8.setHeight(size);
        b9 = (Button) findViewById(R.id.button9); b9.setHeight(size);
        b10 = (Button) findViewById(R.id.button10); b10.setHeight(size);
        b11 = (Button) findViewById(R.id.button11); b11.setHeight(size);
        b12 = (Button) findViewById(R.id.button12); b12.setHeight(size);
        b13 = (Button) findViewById(R.id.button13); b13.setHeight(size);
        b14 = (Button) findViewById(R.id.button14); b14.setHeight(size);
        b15 = (Button) findViewById(R.id.button15); b15.setHeight(size);
        b16 = (Button) findViewById(R.id.button16); b16.setHeight(size);
        botonHist = (Button) findViewById(R.id.buttonHist);
        botonInfo = (Button) findViewById(R.id.buttonInfo);

        mSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        String text = mSharedPreferences.getString(skey1, "0");
        intentos=Integer.parseInt(text);
        intentos=intentos+1;
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(skey1, intentos + "");
        editor.commit();

        mSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        text = mSharedPreferences.getString(skey2, "0");
        mayor=Integer.parseInt(text);

        mSensorManager = (SensorManager) MainActivity.this.getSystemService(
                SENSOR_SERVICE);
        mAcelSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAcelSensor, SensorManager.SENSOR_DELAY_NORMAL);

        botonHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString(skey1, intentos + "");
                editor.commit();
                if (puntaje > mayor) {
                    editor.putString(skey2, puntaje + "");
                } else {
                    editor.putString(skey2, mayor + "");
                }
                editor.commit();
                startActivity(new Intent(MainActivity.this, Historial.class));
            }
        });

        botonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Informacion.class));
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=1) {
                    if (!check) {
                        check = true;
                        v1 = list.get(0);
                        b1.setText(v1 + "");
                        botonCancel = b1;
                        ultimo=1;
                    } else {
                        check = false;
                        v2 = list.get(0);
                        b1.setText(v2 + "");
                        ultimo=0;
                        if (v1 == v2) {
                            correcto(b1,botonCancel);
                            puntaje++;
                        } else {
                            incorrecto(b1, botonCancel);
                        }
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=2){
                    if(!check){
                        check=true;
                        v1=list.get(1);
                        b2.setText(v1+"");
                        botonCancel = b2;
                        ultimo=2;
                    }else {
                        check=false;
                        v2=list.get(1);
                        b2.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b2, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b2, botonCancel);
                        }
                    }
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=3){
                    if(!check){
                        check=true;
                        v1=list.get(2);
                        b3.setText(v1+"");
                        botonCancel = b3;
                        ultimo=3;
                    }else {
                        check=false;
                        v2=list.get(2);
                        b3.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b3, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b3, botonCancel);
                        }
                    }
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=4){
                    if(!check){
                        check=true;
                        v1=list.get(3);
                        b4.setText(v1 + "");
                        botonCancel = b4;
                        ultimo=4;
                    }else {
                        check=false;
                        v2=list.get(3);
                        b4.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b4, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b4, botonCancel);
                        }
                    }
                }
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=5){
                    if(!check){
                        check=true;
                        v1=list.get(4);
                        b5.setText(v1 + "");
                        botonCancel = b5;
                        ultimo=5;
                    }else {
                        check=false;
                        v2=list.get(4);
                        b5.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b5, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b5, botonCancel);
                        }
                    }
                }
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=6){
                    if(!check){
                        check=true;
                        v1=list.get(5);
                        b6.setText(v1 + "");
                        botonCancel = b6;
                        ultimo=6;
                    }else {
                        check=false;
                        v2=list.get(5);
                        b6.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b6, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b6, botonCancel);
                        }
                    }
                }
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=7){
                    if(!check){
                        check=true;
                        v1=list.get(6);
                        b7.setText(v1 + "");
                        botonCancel = b7;
                        ultimo=7;
                    }else {
                        check=false;
                        v2=list.get(6);
                        b7.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b7, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b7, botonCancel);
                        }
                    }
                }
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=8){
                    if(!check){
                        check=true;
                        v1=list.get(7);
                        b8.setText(v1 + "");
                        botonCancel = b8;
                        ultimo=8;
                    }else {
                        check=false;
                        v2=list.get(7);
                        b8.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b8, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b8, botonCancel);
                        }
                    }
                }
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=9) {
                    if(!check){
                        check=true;
                        v1=list.get(8);
                        b9.setText(v1 + "");
                        botonCancel = b9;
                        ultimo=9;
                    }else {
                        check=false;
                        v2=list.get(8);
                        b9.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b9, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b9, botonCancel);
                        }
                    }
                }

            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=10) {
                    if(!check){
                        check=true;
                        v1=list.get(9);
                        b10.setText(v1 + "");
                        botonCancel = b10;
                        ultimo=10;
                    }else {
                        check=false;
                        v2=list.get(9);
                        b10.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b10, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b10, botonCancel);
                        }
                    }
                }
            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=11) {
                    if(!check){
                        check=true;
                        v1=list.get(10);
                        b11.setText(v1 + "");
                        botonCancel = b11;
                        ultimo=11;
                    }else {
                        check=false;
                        v2=list.get(10);
                        b11.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b11, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b11, botonCancel);
                        }
                    }
                }

            }
        });

        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=12) {
                    if(!check){
                        check=true;
                        v1=list.get(11);
                        b12.setText(v1 + "");
                        botonCancel = b12;
                        ultimo=12;
                    }else {
                        check=false;
                        v2=list.get(11);
                        b12.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b12, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b12, botonCancel);
                        }
                    }
                }
            }
        });

        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=13) {
                    if(!check){
                        check=true;
                        v1=list.get(12);
                        b13.setText(v1 + "");
                        botonCancel = b13;
                        ultimo=13;
                    }else {
                        check=false;
                        v2=list.get(12);
                        b13.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b13, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b13, botonCancel);
                        }
                    }
                }
            }
        });

        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=14) {
                    if(!check){
                        check=true;
                        v1=list.get(13);
                        b14.setText(v1 + "");
                        botonCancel = b14;
                        ultimo=14;
                    }else {
                        check=false;
                        v2=list.get(13);
                        b14.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b14, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b14, botonCancel);
                        }
                    }
                }
            }
        });

        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=15) {
                    if(!check){
                        check=true;
                        v1=list.get(14);
                        b15.setText(v1 + "");
                        botonCancel = b15;
                        ultimo=15;
                    }else {
                        check=false;
                        v2=list.get(14);
                        b15.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b15, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b15,botonCancel);
                        }
                    }
                }
            }
        });

        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ultimo!=16) {
                    if(!check){
                        check=true;
                        v1=list.get(15);
                        b16.setText(v1+"");
                        botonCancel = b16;
                        ultimo=16;
                    }else {
                        check=false;
                        v2=list.get(15);
                        b16.setText(v2+"");
                        ultimo=0;
                        if(v1==v2){
                            correcto(b16, botonCancel);
                            puntaje++;
                        }else{
                            incorrecto(b16,botonCancel);
                        }
                    }
                }
            }
        });

    }

    private List<Integer> randomize() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);

        Collections.shuffle(list);

        return list;
    }

    private void reinicio() {
        b1.setText("");
        b1.setEnabled(true);
        b2.setText("");
        b2.setEnabled(true);
        b3.setText("");
        b3.setEnabled(true);
        b4.setText("");
        b4.setEnabled(true);
        b5.setText("");
        b5.setEnabled(true);
        b6.setText("");
        b6.setEnabled(true);
        b7.setText("");
        b7.setEnabled(true);
        b8.setText("");
        b8.setEnabled(true);
        b9.setText("");
        b9.setEnabled(true);
        b10.setText("");
        b10.setEnabled(true);
        b11.setText("");
        b11.setEnabled(true);
        b12.setText("");
        b12.setEnabled(true);
        b13.setText("");
        b13.setEnabled(true);
        b14.setText("");
        b14.setEnabled(true);
        b15.setText("");
        b15.setEnabled(true);
        b16.setText("");
        b16.setEnabled(true);
        list=randomize();
    }

    public void correcto(final Button b1, final Button b2){
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                b1.setEnabled(false);
                b2.setEnabled(false);
            }
        },500);
    }

    private void incorrecto(final Button b1, final Button b2) {
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                b1.setText("");
                b2.setText("");
                b1.setEnabled(true);
                b2.setEnabled(true);
            }
        },500);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String text = mSharedPreferences.getString(skey1, "0");
        intentos=Integer.parseInt(text);
        text = mSharedPreferences.getString(skey2, "0");
        mayor=Integer.parseInt(text);
        mSensorManager.registerListener(this, mAcelSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(skey1, intentos + "");
        editor.commit();
        if(puntaje>mayor){
            editor.putString(skey2, puntaje+"");
        }else {
            editor.putString(skey2, mayor+"");
        }
        editor.commit();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(skey1, intentos + "");
        editor.commit();
        if(puntaje>mayor){
            editor.putString(skey2, puntaje+"");
        }else {
            editor.putString(skey2, mayor+"");
        }
        editor.commit();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y *y + z * z) / (SensorManager.GRAVITY_EARTH);
        long actualTime = event.timestamp;
        if(accelationSquareRoot >=50) {
            if(actualTime - LastUpdate < 200) {
                return;
            }
            LastUpdate = actualTime;
            reinicio();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
