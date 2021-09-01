package com.example.translate2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    Button finish_game;
    Button var1;
    Button var2;
    TextView rightorwrong1;
    TextView rightorwrong2;
    TextView rightorwrong3;
    TextView rightorwrong4;
    Button var3;
    Button var4;
    int ansi;
    TextView word_for_guessing;
    Button next_word;
    TextView points;
    int point = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //MainActivity.learn_words.get(0).from;
        show();

    }
    void show(){
        int max = MainActivity.learn_words.size();
        int min = 0;
        int random_number_word_for_tr = min + (int) (Math.random() * max);
        int random_number1 = min + (int) (Math.random() * max);
        while (random_number1 == random_number_word_for_tr){
            random_number1 = min + (int) (Math.random() * max);
        }
        int random_number2 = min + (int) (Math.random() * max);
        while (random_number2 == random_number_word_for_tr || random_number2 == random_number1){
            random_number2 = min + (int) (Math.random() * max);
        }
        int random_number3 = min + (int) (Math.random() * max);
        while (random_number3 == random_number_word_for_tr || random_number3 == random_number1
                || random_number3 == random_number2){
            random_number3 = min + (int) (Math.random() * max);
        }
        Random random = new Random();
        int[] arr = {random_number1, random_number2, random_number3, random_number_word_for_tr};
        int[] newArray = new int[arr.length];
        List<Integer> indexes = new ArrayList<>(arr.length);
        int count = 0;
        while (true) {
            int var = random.nextInt(arr.length);
            if (!indexes.contains(var)) {
                indexes.add(var);
                newArray[var] = arr[count++];
            }
            if (count == arr.length) {
                break;
            }
        }
        for (int i = 0; i < 4; i = i + 1){
            if(newArray[i] == random_number_word_for_tr){
                ansi = i;
            }
        }
        Log.d("ansi", ansi + "");
        final String word1 = MainActivity.learn_words.get(newArray[0]).to;
        final String word2 = MainActivity.learn_words.get(newArray[1]).to;
        final String word3 = MainActivity.learn_words.get(newArray[2]).to;
        final String word4 = MainActivity.learn_words.get(newArray[3]).to;
        final String wordfortranslation = MainActivity.learn_words.get(random_number_word_for_tr).from;
        final String rightword = MainActivity.learn_words.get(random_number_word_for_tr).to;


        var1 = findViewById(R.id.var1);
        var2 = findViewById(R.id.var2);
        var3 = findViewById(R.id.var3);
        var4 = findViewById(R.id.var4);
        points = findViewById(R.id.points);
        rightorwrong1 = findViewById(R.id.rightorwrong1);
        rightorwrong2 = findViewById(R.id.rightorwrong2);
        rightorwrong3 = findViewById(R.id.rightorwrong3);
        rightorwrong4 = findViewById(R.id.rightorwrong4);
        rightorwrong1.setText("");
        rightorwrong2.setText("");
        rightorwrong3.setText("");
        rightorwrong4.setText("");
        word_for_guessing = findViewById(R.id.word_for_guessing);
        var1.setText(word1);
        var2.setText(word2);
        var3.setText(word3);
        var4.setText(word4);
        word_for_guessing.setText(wordfortranslation);
        var1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        if(word1.equals(rightword)){
                            rightorwrong1.setText("true");
                            point += 1;
                            points.setText(point + "");
                        }else{
                            rightorwrong1.setText("false");
                            if (ansi == 1){
                                rightorwrong2.setText("true");
                            }
                            if (ansi == 2){
                                rightorwrong3.setText("true");
                            }
                            if (ansi == 3){
                                rightorwrong4.setText("true");
                            }
                        }
                        var1.setEnabled(false);
                        var2.setEnabled(false);
                        var3.setEnabled(false);
                        var4.setEnabled(false);
                    }


        });
        var2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(word2.equals(rightword)){
                    rightorwrong2.setText("true");
                    point += 1;
                    points.setText(point + "");
                }else{
                    rightorwrong2.setText("false");
                    if (ansi == 0){
                        rightorwrong1.setText("true");
                    }
                    if (ansi == 2){
                        rightorwrong3.setText("true");
                    }
                    if (ansi == 3){
                        rightorwrong4.setText("true");
                    }
                }
                var1.setEnabled(false);
                var2.setEnabled(false);
                var3.setEnabled(false);
                var4.setEnabled(false);
            }


        });
        var3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(word3.equals(rightword)){
                    rightorwrong3.setText("true");
                    point += 1;
                    points.setText(point + "");
                }else{
                    rightorwrong3.setText("false");
                    if (ansi == 0){
                        rightorwrong1.setText("true");
                    }
                    if (ansi == 1){
                        rightorwrong2.setText("true");
                    }
                    if (ansi == 3){
                        rightorwrong4.setText("true");
                    }
                }
                var1.setEnabled(false);
                var2.setEnabled(false);
                var3.setEnabled(false);
                var4.setEnabled(false);
            }


        });
        var4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(word4.equals(rightword)){
                    rightorwrong4.setText("true");
                    point += 1;
                    points.setText(point + "");
                }else{
                    rightorwrong4.setText("false");
                    if (ansi == 1){
                        rightorwrong2.setText("true");
                    }
                    if (ansi == 2){
                        rightorwrong3.setText("true");
                    }
                    if (ansi == 0){
                        rightorwrong1.setText("true");
                    }
                }
                var1.setEnabled(false);
                var2.setEnabled(false);
                var3.setEnabled(false);
                var4.setEnabled(false);
            }


        });
        next_word = findViewById(R.id.next_word);
        next_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                var1.setEnabled(true);
                var2.setEnabled(true);
                var3.setEnabled(true);
                var4.setEnabled(true);
                show();
            }
        });
        finish_game = findViewById(R.id.finish_game);
        finish_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

