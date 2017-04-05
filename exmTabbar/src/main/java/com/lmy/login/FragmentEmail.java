package com.lmy.login;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.exmtabbar.activityitem.ThirdActivity;

/**
 * Created by limengyan on 2017/3/21.
 */
public class FragmentEmail extends Fragment{

    private View mainView;
    private EditText nikname_edtext;
    private EditText password_edtxt;
    private Button signup_bt;
    private Context context;
    private RelativeLayout phone_layout;
    private RelativeLayout verify_layout;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity.getApplicationContext();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.activity_signup, container, false);
        phone_layout=(RelativeLayout)mainView.findViewById(R.id.phone_layout);
        verify_layout=(RelativeLayout)mainView.findViewById(R.id.verify_layout);
        phone_layout.setVisibility(View.GONE);
        verify_layout.setVisibility(View.GONE);
        nikname_edtext = (EditText) mainView.findViewById(R.id.name_edtext);
        password_edtxt = (EditText) mainView.findViewById(R.id.password_edtxt);
        signup_bt = (Button) mainView.findViewById(R.id.signup_bt);
        password_edtxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.signUp && actionId == EditorInfo.IME_NULL) {
                    attemptRegist();
                    return true;
                }
                return false;
            }
        });
        signup_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegist();
            }
        });
        return mainView;
    }


    private void attemptRegist() {
        nikname_edtext.setError(null);
        password_edtxt.setError(null);
        String nikname = nikname_edtext.getText().toString();
        String password = password_edtxt.getText().toString();

        View focusView=null;
        Boolean cancel=false;

        if(!TextUtils.isEmpty(password) && !isPassWordValid(password)){
            password_edtxt.setError("密码长度应大于6位");
            focusView=password_edtxt;
            cancel=true;

        }

        if(cancel){
            focusView.requestFocus();
        }

        AVUser avUser=new AVUser();
        avUser.setEmail(nikname);
        avUser.setPassword(password);
        avUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e==null) {
                    //startActivity(new Intent(FragmentEmail.this, ThirdActivity.class));
                    Toast.makeText(context,"注册成功",Toast.LENGTH_SHORT).show();

                }else{
                    //Toast.makeText(FragmentEmail.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean isPassWordValid(String password) {
        return password.length() > 6;
    }



}
