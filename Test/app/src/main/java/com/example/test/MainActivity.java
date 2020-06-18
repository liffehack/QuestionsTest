package com.example.test;

import android.content.Intent;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Question[] question = new Question[10]; // Массив вопросов
    Button button1,button2,button3,button4,button5,button6; // Необходимые кнопки
    TextView myTextView;    // Текст вопроса
    TextView Num;           // Номер страницы
    TextView prav;          // Верный ответ
    int NumberList=1;       // Номер страницы (только int)
    int VernOtv=0;          // Кол-во верных ответов
    int i=0;                // Для счётчика
    ProgressBar pr1;
    CountDownTimer mCountDownTimer; // Таймер для бара
    boolean press=false;            // бул для бара
    String YourName ="";            // Имя пользователя

    // Чтение файла
    protected void ReadFile() throws IOException {
        Resources resources = getResources();
        InputStream is=resources.openRawResource(R.raw.data);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        String vopros;
        String answerA;
        String answerB;
        String answerC;
        String answerD;
        String otvet;
        int count=0;
        while (reader.ready())
        {
            vopros=reader.readLine();
            answerA=reader.readLine();
            answerB=reader.readLine();
            answerC=reader.readLine();
            answerD=reader.readLine();
            otvet=reader.readLine();
            question[count]=new Question(count+1,otvet+" , "+vopros,answerA,answerB,answerC,answerD,otvet);
            count++;
        }
    }

    // Запись результатов в файл
    void WriteFileSD() {
        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + "MyFiles");
        // создаем каталог
        sdPath.mkdirs();
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, "file.txt");
        try {
            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile,true));
            // пишем данные
            bw.write("Пользователь " + YourName + " набрал " + VernOtv + " баллов.\n");
            // закрываем поток
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Переход к след. вопросу
    void QuestionsRefresh()
    {
        // Далее следует логика перехода вопросов
        if(NumberList<=10) { myTextView.setText(question[NumberList - 1].getVopros());
            Num.setText("Вопрос:" + NumberList+" /10");
            prav.setText("Правильных ответов: " + VernOtv);
            button1.setText(question[NumberList - 1].getAnswerA());
            button2.setText(question[NumberList - 1].getAnswerB());
            button3.setText(question[NumberList - 1].getAnswerC());
            button4.setText(question[NumberList - 1].getAnswerD());
            i=0;
            ProgressBarRefresh();
        }else {
            //WriteResult();
            //cbdLog("Пользователь " + YourName + " набрал " + VernOtv + " баллов.\n");
            WriteFileSD();
            openActivity();
        }
    }

    // Метод обновления бара
    void ProgressBarRefresh()
    {
        mCountDownTimer=new CountDownTimer(10000,500) {

            @Override
            public void onTick(long millisUntilFinished) {
                if(press) {
                    pr1.setProgress(0);
                    int i=0;
                    NumberList++;
                    mCountDownTimer.cancel();
                    QuestionsRefresh();
                    press=false;

                } else {
                    i++;
                    pr1.setProgress((int) i * 100 / (10000 / 500));
                }

            }

            @Override
            public void onFinish() {
                NumberList++;
                QuestionsRefresh();
            }

        };
        mCountDownTimer.start();
    }

    // Инициализация
    protected void Init()
    {
        Intent intent = getIntent();
        YourName =intent.getStringExtra("YourName");
        //SetQuestion();

        try {
            ReadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        button1 = (Button)findViewById(R.id.button3);
        button2 = (Button)findViewById(R.id.button6);
        button3 = (Button)findViewById(R.id.button2);
        button4 = (Button)findViewById(R.id.button1);

        pr1=(ProgressBar) findViewById(R.id.progressBar2) ;
        myTextView = (TextView) findViewById(R.id.txt1); //Вопрос
        Num = (TextView) findViewById(R.id.textView3); //Номер теста
        prav=(TextView)findViewById(R.id.textView); //Кол-во  правильного ответа

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        myTextView.setText(question[NumberList - 1].getVopros());
        button1.setText(question[NumberList - 1].getAnswerA());
        button2.setText(question[NumberList - 1].getAnswerB());
        button3.setText(question[NumberList - 1].getAnswerC());
        button4.setText(question[NumberList - 1].getAnswerD());
        pr1.setProgress(i);
        ProgressBarRefresh();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                    if (question[NumberList - 1].getAnswerA().equals(question[NumberList - 1].getOtvet())) {
                        if (question[NumberList - 1].isRead() == false) {
                            VernOtv++;
                            question[NumberList - 1].setRead(true);
                        }
                    }
                press=true;
                break;
            case R.id.button6:

                    if (question[NumberList - 1].getAnswerB().equals(question[NumberList - 1].getOtvet())) {
                        if (question[NumberList - 1].isRead() == false) {
                            VernOtv++;
                            question[NumberList - 1].setRead(true);
                        }
                    }
                press=true;
                break;
            case R.id.button2:
                    if (question[NumberList - 1].getAnswerC().equals(question[NumberList - 1].getOtvet())) {
                        if (question[NumberList - 1].isRead() == false) {
                            VernOtv++;
                            question[NumberList - 1].setRead(true);
                        }

                    }
                    press=true;
                break;
            case R.id.button1:
                    if (question[NumberList - 1].getAnswerD().equals(question[NumberList - 1].getOtvet())) {
                        if (question[NumberList - 1].isRead() == false) {
                            VernOtv++;
                            question[NumberList - 1].setRead(true);
                        }
                    }
                press=true;
                break;

        }
    }

    // Открытие нового активити
    void openActivity()
    {
        Intent intent = new Intent(MainActivity.this, Result.class);
        intent.putExtra("otvet", VernOtv);
        startActivity(intent);
        finish();
    }
}
