package com.example.translate2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText translation;
    Button start;
    Button learn;
    Button add_to_dict;
    String response = "error";
    String transl = "";
    TextView words;
    static ArrayList <Word> learn_words = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        //System.out.println(learn_words);
        for (int i = 0; i < learn_words.size(); i+=1){
            String textf = words.getText().toString();
            words.setText("\n" + learn_words.get(i).from + " " + learn_words.get(i).to + textf);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //System.out.println(learn_words);
        words = findViewById(R.id.textwords);
        for (int i = 0; i < learn_words.size(); i+=1){
            String textf = words.getText().toString();
            words.setText("\n" + learn_words.get(i).from + " " + learn_words.get(i).to + textf);
        }
        editText = findViewById(R.id.editText);
        translation = findViewById(R.id.translation);
        learn = findViewById(R.id.learn);
        start = findViewById(R.id.button);
        //start.setVisibility(View.INVISIBLE);
        //start.setEnabled(false);
        add_to_dict = findViewById(R.id.add_to_dict);
        String text = "";
        words.setText(text);
        final Spinner spinnerlang = findViewById(R.id.spinnerlang);
        final Spinner spinnerlangtwo = findViewById(R.id.spinnerlangtwo);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String word = editText.getText().toString();
                final String langf = spinnerlang.getSelectedItem().toString();
                final String lang2f = spinnerlangtwo.getSelectedItem().toString();
                new Thread() {
                    public void run() {
                        try {
                            HttpURLConnection connection = (HttpURLConnection) new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20170425T145422Z.50255e37fd0f3be2.193e26f527cdd0bbf1653c5005ab887b18eb05ec&text=" + word + "&lang=" + langf + "-" + lang2f).openConnection();
                            Scanner in = new Scanner(connection.getInputStream());
                            response = in.nextLine();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Thread() {
                            @SuppressLint("SetTextI18n")
                            public void run() {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONArray array = jsonObject.getJSONArray("text");
                                    response = array.getString(0);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                translation.setText(response);
                            }
                        });
                    }
                }.start();
            }
        });
        add_to_dict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String textf = words.getText().toString();
                    final String word = editText.getText().toString();
                    transl = translation.getText().toString();
                    final String langf = spinnerlang.getSelectedItem().toString();
                    final String lang2f = spinnerlangtwo.getSelectedItem().toString();
                    words.setText("\n" + word + " " + transl + textf);
                    learn_words.add(new Word(langf, lang2f, word, transl));
            }
        });
        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        if (learn_words.size() >= 4){
                            startActivity(intent);}
                        else{

                        }
            }
        });
    }
}