package com.example.tarea_payphone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Random;

public class Comercial extends AppCompatActivity {

    EditText numtelef;
    String codigoPais;
    EditText identificación;
    EditText monto;
    EditText impuesto;
    EditText referencia;
    Button btnGenerar;
    int idClienteTransaccion;


    Double MontoTotal, subtotal, impuestopagar;
    int MontoTotalEntero, subtotalEntero, ImpuestoPagarEntero;
    double iva=0.12;//Establecemos el iva aplicacado en Ecuador

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comercial);

        numtelef=(EditText) findViewById(R.id.txtphoneNumber);
        codigoPais="593";
        identificación=(EditText) findViewById(R.id.txtclienteid);
        monto=(EditText) findViewById(R.id.txtmonto);
        referencia=(EditText) findViewById(R.id.txtref);
        btnGenerar=(Button) findViewById(R.id.btngenerar);
        Bundle bundle = this.getIntent().getExtras();
        monto.setText(bundle.getString("Monto"));
        referencia.setText("Compra de ropa en ShopWomen");
        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplication(), "Holaa", Toast.LENGTH_SHORT).show();
                pagar();
            }
        });


    }




    public void pagar()
    {
        String URL="https://pay.payphonetodoesposible.com/api/Sale";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        double Ivita= iva+1;
        MontoTotal=Double.parseDouble(monto.getText().toString());//monto que el usuario ingreso
        subtotal= MontoTotal/Ivita;
        impuestopagar=MontoTotal-subtotal;
        MontoTotalEntero=(int) (MontoTotal*100); //amount
        subtotalEntero=(int) Math.round(subtotal*100); //amountWithTax
        ImpuestoPagarEntero=(int) Math.round(impuestopagar*100); //tax
        Random rn = new Random();
        idClienteTransaccion=rn.nextInt(10000);
        JSONObject Informacion = new JSONObject();


        try{
            Informacion.put("phoneNumber", numtelef.getText().toString());
            Informacion.put("countryCode", codigoPais);
            Informacion.put("clientUserId", identificación.getText().toString());
            Informacion.put("reference", referencia.getText().toString());
            Informacion.put("responseUrl", URL);
            Informacion.put("amount", MontoTotalEntero);
            Informacion.put("amountWithTax", subtotalEntero);
            Informacion.put("amountWithoutTax", 0);
            Informacion.put("tax", ImpuestoPagarEntero);
            Informacion.put("clientTransactionId", String.valueOf(idClienteTransaccion));
            Toast.makeText(Comercial.this, Informacion.toString(), Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonR = new JsonObjectRequest(Request.Method.POST, URL, Informacion, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(Comercial.this, "La transacción fue realizada con Exito: ", Toast.LENGTH_LONG).show();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError ex) {
                Toast.makeText(Comercial.this, "!Ups¡ Ocurrio un error\n" + ex.getMessage(), Toast.LENGTH_LONG).show();

                System.out.println(ex.toString());
            }
        }) {
            @Override
            public HashMap<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> HMap = new HashMap<>();
                HMap.put("Content-Type", "application/json");
                HMap.put("Accept", "application/json");
                HMap.put("Authorization", "Bearer 5nB3_vvMTjQ053YnUKjID2I0zFzh2dc5cQj6MGJ7u8uoDTl2ZGzK_83s6MwABk9gU_L303D0wXVCShYjQeI_IaT_DUxgT2tIe9SesvLh7qPlSmGhvtkkSH20aseAfN5529uMY6u-MeoZwwunXkZk3Z0CpqIZpK0mjqQr6SXqywpA4XhVZSaJhscWBlAr1XBeDby4IvDo3TpYLTFNh9XYl6GTuQ0kmT8hkjEtg98QkHZh_geJmhd3uqMk2U1R9wRSABTzbegk6iEP9x8cZ0ftps-PPCAs7dpTRJUCXljcfqKv8vw4QrwR7AyDmkhoKx_l6GPkiBKwgz7JF05QdL10FfXyocQ");
                return HMap;
            }
        };

        requestQueue.add(jsonR);



    }




}