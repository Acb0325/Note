package com.example.bin.note30;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText editUserName, editpwd, edittel;
    RadioGroup rg1;
    RadioButton rb1;
    Button btn1;
    private Context context = this;
    DiaryDbAdapter myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editUserName = (EditText) findViewById(R.id.Rusername);
        editpwd = (EditText) findViewById(R.id.Rpwd);
        edittel = (EditText) findViewById(R.id.Rtel);
        rg1 = (RadioGroup) findViewById(R.id.rg1);
        btn1 = (Button) findViewById(R.id.register);
        myHelper = new DiaryDbAdapter(this);
        myHelper.open();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = editUserName.getText().toString();
                String pwd = editpwd.getText().toString();
                String tel = edittel.getText().toString();
                int selected = rg1.getCheckedRadioButtonId();
                rb1 = (RadioButton) findViewById(selected);
                String sex = rb1.getText().toString();
                if (myHelper.selectUser(userName) == 0) {
                    myHelper.insert(userName, sex, pwd, tel);
                    Toast.makeText(Register.this, "注册成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, Login.class);
                    startActivityForResult(intent, 0);
                } else
                    Toast.makeText(Register.this, "用户名已存在！！！", Toast.LENGTH_LONG).show();
            }
        });

    }
}
