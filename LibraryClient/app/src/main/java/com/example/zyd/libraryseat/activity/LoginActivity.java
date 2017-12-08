package com.example.zyd.libraryseat.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zyd.libraryseat.R;
import com.example.zyd.libraryseat.common.CommonRequest;
import com.example.zyd.libraryseat.common.CommonResponse;
import com.example.zyd.libraryseat.http.ResponseHandler;
import com.example.zyd.libraryseat.util.LogUtil;

import static com.example.zyd.libraryseat.common.CommonUrl.login_url;

public class LoginActivity extends BaseActivity {

    private Button stuLogin;
    private Button adminLogin;
    private Button register;
    private TextView userName;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        stuLogin = (Button) findViewById(R.id.StuLogin);
        userName = (TextView) findViewById(R.id.UserName);
        password = (TextView) findViewById(R.id.password);
        register = (Button) findViewById(R.id.Register);

        stuLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonRequest commonRequest = new CommonRequest();
                commonRequest.addRequestParam("userName", userName.getText().toString());
                commonRequest.addRequestParam("password", password.getText().toString());

                sendHttpPostRequest(login_url, commonRequest, new ResponseHandler() {
                    @Override
                    public void success(CommonResponse response) {
                            LogUtil.logErr(response.getResCode().toString());
                            if(response.getResCode().toString().equals("0")) {
                                Toast.makeText(getApplicationContext(), "登陆成功！", Toast.LENGTH_SHORT).show();
                                String UserId = response.getDataList().get(0).get("userId");
                                SharedPreferences mSharedPreference = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = mSharedPreference.edit();
                                editor.putString("userId", UserId);
                                editor.commit();
                                Intent intent = new Intent(LoginActivity.this,choose.class);
                                startActivity(intent);

                            }else if(response.getResCode().toString().equals("1")){
                                Toast.makeText(getApplicationContext(), "密码或输入账号出错!", Toast.LENGTH_LONG).show();
                            }
                        }

                    @Override
                    public void fail(String failCode, String failMsg) {
                        LogUtil.logErr(failMsg);
                    }
                });
            }

        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
