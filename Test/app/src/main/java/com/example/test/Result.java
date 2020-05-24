package com.example.test;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// Для результата создадим новое активити
public class Result extends AppCompatActivity {

    // Текущий результат
    TextView txt;

    // История
    TextView txt2;

    // Поделиться
    Button btn;


    void ReadFileSD() {
        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + "MyFiles");
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, "file.txt");
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
            String str = "";
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                txt2.append(str+"\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод при запуске акттивити
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txt=(TextView)findViewById(R.id.textView2);
        txt2=(TextView)findViewById(R.id.textView5);
        txt2.setText("");
        btn=(Button)findViewById(R.id.button);
        int otv=0;
        int defaultvalue=0;
        Intent intent = getIntent();
        otv=intent.getIntExtra("otvet",defaultvalue);
        String txt1="Вы получили "+ otv + " баллов из 10";
        txt.setText(txt1);
        final String send=txt1;
        ReadFileSD();
        btn.setOnClickListener(v -> {
            //Неявный Intent
            Intent intent1 = new Intent();
            intent1.setAction(Intent.ACTION_SEND);
            intent1.putExtra(Intent.EXTRA_TEXT, send);
            intent1.setType("text/plain");
            startActivity(intent1);
        });
    }
}
