package com.example.preferensis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextStr, editTextNum;
    Button btnSave, btnLoad;
    SharedPreferences sharedPreferences;
    final String SAVED_TEXT = "TEXT";
    final String SAVED_NUM = "NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextStr = (EditText) findViewById(R.id.editTextStr);
        editTextNum = (EditText) findViewById(R.id.editTextNum);

        btnSave =  findViewById(R.id.save);
        btnSave.setOnClickListener((View.OnClickListener) this);

        btnLoad =  findViewById(R.id.load);
        btnLoad.setOnClickListener((View.OnClickListener) this);
        loadData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                saveData();
                break;
            case R.id.load:
                loadData();
                break;
            default:
                break;
        }
    }
    void saveData() {
        sharedPreferences = getSharedPreferences("Mydata",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVED_TEXT, editTextStr.getText().toString());
        editor.putInt(SAVED_NUM, Integer.parseInt(editTextNum.getText().toString()));
        editor.commit();
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    void loadData() {
        sharedPreferences = getSharedPreferences("Mydata",MODE_PRIVATE);
        String savedText = sharedPreferences.getString(SAVED_TEXT, "");
        Integer savedNum = sharedPreferences.getInt(SAVED_NUM, 0);
        editTextStr.setText(savedText);
        editTextNum.setText(savedNum.toString());
        Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }
}