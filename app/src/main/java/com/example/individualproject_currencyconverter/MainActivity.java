package com.example.individualproject_currencyconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.EditText;
import java.util.ArrayList;
import android.app.Dialog;
import android.widget.Button;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.text.TextWatcher;
import android.text.Editable;
import com.google.android.material.internal.TextWatcherAdapter;
import android.widget.AdapterView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;
import com.android.volley.Response;
import org.json.JSONObject;
import org.json.JSONException;
import java.math.RoundingMode;
import java.math.BigDecimal;
import com.android.volley.VolleyError;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TextView convert_from_dropdown_menu, convert_to_dropdown_menu, conversion_rate;
    EditText edit_amount_to_convert_value;
    ArrayList<String> arraylist;
    Dialog from_dialog, to_dialog;
    Button conversion, exit;
    String convert_from_value, convert_to_value, conversion_value;
    String[] currency = {"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN",
            "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD", "BTC", "BTN", "BWP", "BYN", "BYR", "BZD",
            "CAD", "CDF", "CHF", "CLF", "CLP", "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CZK",
            "DJF", "DKK", "DOP", "DZD",
            "EGP", "ERN", "ETB", "EUR",
            "FJD", "FKP",
            "GBP", "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD",
            "HKD", "HNL", "HRK", "HTG", "HUF",
            "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK",
            "JEP", "JMD", "JOD", "JPY",
            "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT",
            "LAK", "LBP", "LKR", "LRD", "LSL", "LVL", "LYD",
            "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRO", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN",
            "NAD", "NGN", "NIO", "NOK", "NPR", "NZD",
            "OMR",
            "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG",
            "QAR",
            "RON", "RSD", "RUB", "RWF",
            "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD", "STD", "SVC", "SYP", "SZL",
            "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TWD", "TZS",
            "UAH", "UGX", "USD", "UYU", "UZS",
            "VEF", "VND", "VUV",
            "WST",
            "XAF", "XAG", "XCD", "XDR", "XOF", "XPF",
            "YER",
            "ZAR", "ZMK", "ZMW", "ZWL"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convert_from_dropdown_menu = findViewById(R.id.convert_from_dropdown_menu);
        convert_to_dropdown_menu = findViewById(R.id.convert_to_dropdown_menu);
        conversion_rate = findViewById(R.id.conversion_rate);
        conversion = findViewById(R.id.conversion);
        exit = findViewById(R.id.exit);

        edit_amount_to_convert_value = findViewById(R.id.edit_amount_to_convert_value);

        arraylist = new ArrayList<>();
        for(String i : currency) {
            arraylist.add(i);
        }

        convert_from_dropdown_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from_dialog = new Dialog(MainActivity.this);
                from_dialog.setContentView(R.layout.from_spinner);
                from_dialog.getWindow().setLayout(650, 800);
                from_dialog.show();

                EditText edittext = from_dialog.findViewById(R.id.edit_text);
                ListView listview = from_dialog.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arraylist);
                listview.setAdapter(adapter);

                edittext.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        convert_from_dropdown_menu.setText(adapter.getItem(position));
                        from_dialog.dismiss();
                        convert_from_value = adapter.getItem(position);
                    }
                });
            }
        });

        convert_to_dropdown_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to_dialog = new Dialog(MainActivity.this);
                to_dialog.setContentView(R.layout.to_spinner);
                to_dialog.getWindow().setLayout(650, 800);
                to_dialog.show();

                EditText edittext = to_dialog.findViewById(R.id.edit_text);
                ListView listview = to_dialog.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arraylist);
                listview.setAdapter(adapter);

                edittext.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        convert_to_dropdown_menu.setText(adapter.getItem(position));
                        to_dialog.dismiss();
                        convert_to_value = adapter.getItem(position);
                    }
                });
            }
        });

        conversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Double edit_amount_to_convert_value = Double.valueOf(MainActivity.this.edit_amount_to_convert_value.getText().toString());
                    getConversionRate(convert_from_value, convert_to_value, edit_amount_to_convert_value);
                }
                catch(Exception e) {

                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Close_MainActivity();
            }
        });
    }

    public String getConversionRate(String convert_from_value, String convert_to_value, Double edit_amount_to_convert_value) {
        RequestQueue requestqueue = Volley.newRequestQueue(this);
        String url = "https://free.currconv.com/api/v7/convert?q=" + convert_from_value + "_" + convert_to_value + "&compact=ultra&apiKey=1498408f1536e92250ee";

        StringRequest stringrequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonobject = null;
                try {
                    jsonobject = new JSONObject(response);
                    Double conversion_rate_value = round(((Double) jsonobject.get(convert_from_value + "_" + convert_to_value)), 2);
                    conversion_value = "" + round((conversion_rate_value * edit_amount_to_convert_value), 2);
                    conversion_rate.setText(conversion_value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestqueue.add(stringrequest);
        return null;
    }

    public static double round(double value, int currency) {
        if(currency < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(currency, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void Close_MainActivity() {
        MainActivity.this.finish();
        System.exit(0);
    }
}
