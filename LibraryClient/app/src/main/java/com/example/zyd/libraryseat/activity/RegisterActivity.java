package com.example.zyd.libraryseat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zyd.libraryseat.R;
import com.example.zyd.libraryseat.common.CommonRequest;
import com.example.zyd.libraryseat.common.CommonResponse;
import com.example.zyd.libraryseat.http.ResponseHandler;
import com.example.zyd.libraryseat.util.LogUtil;
import com.example.zyd.libraryseat.util.StringUtil;

import static com.example.zyd.libraryseat.common.CommonUrl.register_url;
import static com.example.zyd.libraryseat.util.StringUtil.isEmpty;

public class RegisterActivity extends BaseActivity {

    private EditText studentName;
    private EditText password;
    private EditText institute;
    private EditText studentId;
    private EditText repeatPassord;
    private Button sure;
    private Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        studentName = (EditText) findViewById(R.id.stuName);
        password = (EditText) findViewById(R.id.enterPassword);
        institute = (EditText) findViewById(R.id.insiture);
        repeatPassord = (EditText) findViewById(R.id.repeatPassword);
        studentId = (EditText) findViewById(R.id.userId);
        sure = (Button) findViewById(R.id.sure);
        back = (Button) findViewById(R.id.registerBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonRequest request = new CommonRequest();

                String StudentName = studentName.getText().toString();
                String Password = password.getText().toString();
                String Institute = institute.getText().toString();
                String RepeatPassword = repeatPassord.getText().toString();
                String StudentId = studentId.getText().toString();

                if(isEmpty(StudentId) && isEmpty(StudentName) && isEmpty(Password) && isEmpty(Institute)
                        && isEmpty(RepeatPassword)) {
                    if (Password.equals(RepeatPassword)) {

                        request.addRequestParam("studentName", StudentName);
                        request.addRequestParam("password", Password);
                        request.addRequestParam("studentId",StudentId);
                        request.addRequestParam("institute",Institute);
                        sendHttpPostRequest(register_url, request, new ResponseHandler() {
                            @Override
                            public void success(CommonResponse response) {
                                if(response.getResCode().equals("0")){
                                    Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void fail(String code, String msg) {

                            }
                        });
                    }else {
                        Toast.makeText(getApplicationContext(), "两次输入密码不相同！", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "注册信息不能留空！", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

}
