package com.lmy.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.example.exmtabbar.R;


/**
 * Created by limengyan on 2017/3/13.
 */

public class SignUpAct extends Activity {
    private EditText nikname_edtext;
    private EditText password_edtxt;
    private Button signup_bt;
    private Context context;
    private RelativeLayout phone_layout;
    private RelativeLayout verify_layout;
    private EditText mail_edtext;
    private EditText again;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        context=getApplicationContext();
        phone_layout=(RelativeLayout)findViewById(R.id.phone_layout);
        verify_layout=(RelativeLayout)findViewById(R.id.verify_layout);
        phone_layout.setVisibility(View.GONE);
        verify_layout.setVisibility(View.GONE);
        nikname_edtext = (EditText) findViewById(R.id.name_edtext);
        mail_edtext=(EditText)findViewById(R.id.mail_edtext);
        password_edtxt = (EditText) findViewById(R.id.password_edtxt);
        again=(EditText)findViewById(R.id.agin_password_edtxt);
        signup_bt = (Button) findViewById(R.id.signup_bt);
//        password_edtxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == R.id.signUp && actionId == EditorInfo.IME_NULL) {
//                    attemptRegist();
//                    return true;
//                }
//                return false;
//            }
//        });
        signup_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegist();
            }
        });

    }

    private void attemptRegist() {
        nikname_edtext.setError(null);
        mail_edtext.setError(null);
        password_edtxt.setError(null);
        String nikname = nikname_edtext.getText().toString();
        String mail =mail_edtext.getText().toString();
        String password = password_edtxt.getText().toString();
        String againPass =again.getText().toString();

//        View focusView=null;
//        Boolean cancel=false;
//
//        if (!TextUtils.isEmpty(password) && !isPassWordValid(password)) {
//            password_edtxt.setError("密码长度应大于6位");
//            focusView=password_edtxt;
//            cancel=true;
//        }
//
//        if(cancel){
//            focusView.requestFocus();
//        }

        if (password == againPass) {
            AVUser avUser = new AVUser();
            Log.d("lmylmy", "user" + nikname + "mail" + mail + "password" + password);
            avUser.setEmail(mail);
            avUser.setUsername(nikname);
            avUser.setPassword(password);
            avUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        //startActivity(new Intent(FragmentEmail.this, ThirdActivity.class));
                        Log.d("lmylmy", "success");
                        Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();

                    } else {
                        Log.d("lmylmy", "failed");
                        Log.d("lmylmy", e.toString());
                        Toast.makeText(SignUpAct.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else {
            Toast.makeText(SignUpAct.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isPassWordValid(String password) {
        return password.length() > 6;
    }
}
