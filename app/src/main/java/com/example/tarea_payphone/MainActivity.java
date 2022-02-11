package com.example.tarea_payphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
        Button btnPagar;
        Button btnproduct1;
        Button btnproduct2;
        Button btnproduct3;
        Button btndelet1, btndelet2, btndelet3;

    //btnquitar1
        Double precioP1, precioP2, precioP3;
        int contador=0;
        Double totalpagar=0.0;
        TextView totalPrendas, totalpago;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPagar=(Button) findViewById(R.id.btnpagar);
        btnproduct1=(Button) findViewById(R.id.btnproduct1);
        btnproduct2=(Button) findViewById(R.id.btnproduct2);
        btnproduct3=(Button) findViewById(R.id.btnproduct3);
        totalPrendas=(TextView) findViewById(R.id.txttotalprendas);
        totalpago=(TextView) findViewById(R.id.lbltotoalpagar);
        btndelet1=(Button) findViewById(R.id.btnquitar1);
        btndelet2=(Button) findViewById(R.id.btnquitar2);
        btndelet3=(Button) findViewById(R.id.btnquitar3);

        precioP1=10.00;
        precioP2=15.00;
        precioP3=8.00;
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Holaa", Toast.LENGTH_SHORT).show();
                clic();


            }
        });

        btnproduct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador=contador+1;
                totalpagar=totalpagar+precioP1;
                totalPrendas.setText(String.valueOf(contador));
                totalpago.setText(String.valueOf(totalpagar));




            }
        });


        btnproduct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador=contador+1;
                totalpagar=totalpagar+precioP2;
                totalPrendas.setText(String.valueOf(contador));
                totalpago.setText(String.valueOf(totalpagar));




            }
        });


        btnproduct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador=contador+1;
                totalpagar=totalpagar+precioP3;
                totalPrendas.setText(String.valueOf(contador));
                totalpago.setText(String.valueOf(totalpagar));




            }
        });


        btndelet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador=contador-1;
                totalpagar=totalpagar-precioP1;
                totalPrendas.setText(String.valueOf(contador));
                totalpago.setText(String.valueOf(totalpagar));




            }
        });
        btndelet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador=contador-1;
                totalpagar=totalpagar-precioP2;
                totalPrendas.setText(String.valueOf(contador));
                totalpago.setText(String.valueOf(totalpagar));




            }
        });
        btndelet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador=contador-1;
                totalpagar=totalpagar-precioP3;
                totalPrendas.setText(String.valueOf(contador));
                totalpago.setText(String.valueOf(totalpagar));




            }
        });




    }

    public void clic (){
        Intent intent = new Intent(this, Comercial.class);
        Bundle b = new Bundle();
        b.putString("Monto", String.valueOf(totalpagar));
        intent.putExtras(b);
        startActivity(intent);
    }












}