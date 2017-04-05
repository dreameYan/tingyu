package com.lmy.login;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.example.exmtabbar.R;
import com.example.exmtabbar.activityitem.ThirdActivity;

/**
 * Created by limengyan on 2017/3/21.
 */
public class FragmentMobile extends Fragment{

    private View mMainView;
    private LinearLayout collapse;
    private EditText nikname_edtext;
    private EditText mobile_edtext;
    private EditText verify_edtext;
    private Button verify_bt;
    private Button signup_bt;
    private Context context;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity.getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView=inflater.inflate(R.layout.activity_signup,container,false);
        collapse=(LinearLayout)mMainView.findViewById(R.id.collapse);
        collapse.setVisibility(View.GONE);
        nikname_edtext = (EditText) mMainView.findViewById(R.id.name_edtext);
        mobile_edtext = (EditText) mMainView.findViewById(R.id.phone_edtxt);
        verify_edtext = (EditText) mMainView.findViewById(R.id.verify_edtxt);
        verify_bt=(Button)mMainView.findViewById(R.id.verify_bt);
        signup_bt = (Button) mMainView.findViewById(R.id.signup_bt);


        verify_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mobile = mobile_edtext.getText().toString();
                sendCode(mobile);
            }
        });
        signup_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nikname = nikname_edtext.getText().toString();
                final String verify = verify_edtext.getText().toString();
                //verify(verify);
                AVUser user = new AVUser();//final?
                user.setUsername(nikname);
                //user.setPassword(demoPassword);
                user.setMobilePhoneNumber(mobile_edtext.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e !=null) {
                            verify(verify);
                            Log.d("lmylmy", "注册成功");
                            startActivity(new Intent(context, ThirdActivity.class));


                        }else{
                            Log.d("lmylmy", "注册失败");
                        }
                    }
                });
            }
        });


        return mMainView;
    }

    private void verify(String verify) {
        AVOSCloud.verifyCodeInBackground(verify, mobile_edtext.getText().toString(),
                new AVMobilePhoneVerifyCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e==null){
                            Log.d("lmylmy","verify succeed");
                            Toast.makeText(context,"验证成功",Toast.LENGTH_LONG).show();
                        }else{
                            Log.d("lmylmy","verify failed");
                            Toast.makeText(context,"验证失败",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void sendCode(final String mobile) {
        new AsyncTask<Void,Void,Void>(){

            public boolean res;

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    AVOSCloud.requestSMSCode(mobile,"听鱼","用户注册",10);
                    res=true;
                }catch (AVException e){
                    Log.d("lmylmy","send code failed");
                    res=false;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (res){
                    Toast.makeText(context,"验证码已发送",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"获取验证码失败",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();


    }


}
