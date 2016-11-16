package com.aryaan.ankitha.interprocesscomapp1;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText messageBox;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageBox = (EditText)findViewById(R.id.message);
        status = (TextView)findViewById(R.id.status);
    }

    public void saveFile(View view){

        String text1 = messageBox.getText().toString();
        FileOutputStream fileOutputStream = null;

        try {
            File file = getFilesDir();
            fileOutputStream = openFileOutput("data.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(text1.getBytes());
            status.setTextColor(Color.GREEN);
            status.setText(text1+"\n Written to \n"+file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            status.setTextColor(Color.RED);
            status.setText(""+e);
        } catch (IOException e) {
            status.setTextColor(Color.RED);
            status.setText(""+e);
        }finally {
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    status.setTextColor(Color.RED);
                    status.setText(""+e);
                }
            }
        }
    }
}
