package com.example.bin.note30;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    public static String username = "未定义";
    private Context mcontext = this;
    private Button login, register;
    private EditText userName, pwd;
    private CheckBox rememberPwd;
    DiaryDbAdapter myDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.reg);
        userName = (EditText) findViewById(R.id.username);
        pwd = (EditText) findViewById(R.id.pwd);
        rememberPwd = (CheckBox) findViewById(R.id.checkbox);
        SharedPreferences preferences = super.getSharedPreferences("info", Login.MODE_PRIVATE);
        userName.setText(preferences.getString("name", null));
        pwd.setText(preferences.getString("pwd", null));
        myDiary = new DiaryDbAdapter(this);
        myDiary.open();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userName.getText().toString();
                String pasw = pwd.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(Login.this, "用户名不能为空！！", Toast.LENGTH_LONG).show();
                } else if (pasw.equals("")) {
                    Toast.makeText(Login.this, "密码不能为空！！", Toast.LENGTH_LONG).show();
                } else {
                    int result = myDiary.login(userName.getText().toString(), pwd.getText().toString());
                    if (result == 1) {
/*                        Login login = new Login();
                        login.setUsername(name);*/
                        username = userName.getText().toString();
                        SharedPreferences preferences = getSharedPreferences("info", Login.MODE_PRIVATE);
                        SharedPreferences.Editor edit = preferences.edit();
                        if (rememberPwd.isChecked()) {
                            Toast.makeText(Login.this, "您选择了记住密码", Toast.LENGTH_LONG).show();
                            edit.putString("name", userName.getText().toString());
                            edit.putString("pwd", pwd.getText().toString());
                            edit.commit();
                        } else {
                            Toast.makeText(Login.this, "您取消了记住密码", Toast.LENGTH_LONG).show();
                            edit.putString("name", "");
                            edit.putString("pwd", "");
                            edit.commit();
                        }
                        Intent intent = new Intent(mcontext, MenuActivity.class);
//                        intent.putExtra("username", userName.getText().toString());
                        startActivityForResult(intent, 0);
                    } else {
                        Toast.makeText(Login.this, "用户名或密码错误！！！", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, Register.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("确定要退出程序?");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });
            builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
            return true;
        } else {

            return super.onKeyDown(keyCode, event);
        }
    }
}
