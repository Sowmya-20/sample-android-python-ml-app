package com.example.phishing_websites;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.VolleyError;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class MainActivity2 extends AppCompatActivity {
    EditText eturl;
    Button btn;
    String surl;
    TextView tvres;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        eturl = findViewById(R.id.url);
        btn = findViewById(R.id.btnsubmit);
        tvres = findViewById(R.id.result);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        /**awesomeValidation.addValidation(this, R.id.url, "((http|https)://)(www.)?” \n" +
                "+ “[a-zA-Z0-9@:%._\\\\+~#?&//=]{2,256}\\\\.[a-z]” \n" +
                "+ “{2,6}\\\\b([-a-zA-Z0-9@:%._\\\\+~#?&//=]*)", R.string.validateurl);**/
        awesomeValidation.addValidation(this,R.id.url,"((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)",R.string.validateurl);

    }

    public void howToUseThisApp(View view)
    {
        new AlertDialog.Builder(this)
                .setTitle("URL format")
                .setMessage("1. The URL must start with either http or https and\n" +
                        "2. then followed by :// \n"+
                        "3. then it must contain www.\n "+
                        "4. and then followed by subdomain of length (2, 256)\n"+
                        "5. The last part contains top level domain like .com, .org etc.\n"+"Happy Reading :)")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void submitnvalidate(View view)
    {
        if (awesomeValidation.validate()) {

            surl = eturl.getText().toString();

            if (!TextUtils.isEmpty(surl)) {
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                final String url = "";
                JSONObject postParams = new JSONObject();
                try {
                    postParams.put("urlkey", surl);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postParams, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("On Response", "onResponse: " + response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("On Error", error.toString());
                        Toast.makeText(MainActivity2.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(jsonObjectRequest);

            } else {
                Toast.makeText(this, "check if entered url is in correct format ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}