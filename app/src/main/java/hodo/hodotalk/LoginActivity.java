package hodo.hodotalk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    EditText edtEmail, edtPasswd;
    Button btnLogin, btnCreate;

    private FirebaseAuth mAuth;
    ProgressDialog progDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtEmail = (EditText)findViewById(R.id.email);
        edtPasswd = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.email_sign_in_button);
        btnCreate = (Button)findViewById(R.id.email_join_button);

        progDialog = new ProgressDialog(LoginActivity.this);
        mAuth = FirebaseAuth.getInstance();
        //로그인 정보 확인

        if(mAuth.getCurrentUser() != null){
            Log.d(TAG, "Current User:" + mAuth.getCurrentUser().getEmail());
            // 만약 회원이라면 메인으로 이동한다.
            //GotoMainPage(true);
        } else {
            Log.d(TAG, "Log out State");
        }

        //로그인 버튼
        //이메일과 비밀번호가 맞는지 확인한다.
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                String passwd = edtPasswd.getText().toString().trim();
                Log.d(TAG, "Email:" + email + " Password:" + passwd);
                //이메일과 비밀번호를 확인하는 부분
                if(isValidEmail(email) && isValidPasswd(passwd)){
                    signinAccount(email, passwd);
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Check Email or Password",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        //회원 가입 버튼
        //이메일과 비밀번호가 맞는지 확인한다.
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                String passwd = edtPasswd.getText().toString().trim();
                Log.d(TAG, "Email:" + email + " Password:" + passwd);
                if(email != null && passwd != null) {

                    mAuth.createUserWithEmailAndPassword(email,passwd)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        GotoMainPage(false);
                                    } else {
                                        Toast.makeText(LoginActivity.this,
                                                "이메일 형식이 아닙니다",
                                                Toast.LENGTH_LONG).show();
                                        int asdasd =0;
                                        //보통 이메일이 이미 존재하거나, 이메일 형식이아니거나, 비밀번호가 6자리 이상이 아닐 때 발생 
                                    }
                                }
                            });
                }
            }
        });

    }
    //Password확인
    private boolean isValidPasswd(String str){
        if(str == null || TextUtils.isEmpty(str)){
            return false;
        } else {
            if(str.length() > 4)
                return true;
            else
                return false;
        }
    }
    //Email확인
    private boolean isValidEmail(String str){
        if(str == null || TextUtils.isEmpty(str)){
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(str).matches();
        }
    }

    private void signinAccount(String email, String passwd){
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setMessage("User Account Log in ....");
        progDialog.show();
        //로그내용
        mAuth.signInWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "Sing in Account:" + task.isSuccessful());
                                progDialog.dismiss();
                                if(task.isSuccessful()){
                                    Log.d(TAG, "Account Log in  Complete");
                                    Log.d(TAG, "Current User:" + mAuth.getCurrentUser().getEmail());
                                    // Go go Main
                                    GotoMainPage(true);
                                }else {
                                    Toast.makeText(LoginActivity.this,
                                            "Log In Failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
    }

    private void GotoMainPage(boolean login){
        //Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
        Intent intent;

        if(login == true)
            intent = new Intent(LoginActivity.this, MainActivity.class);

        else
            intent = new Intent(LoginActivity.this, JoinActivity.class);

        startActivity(intent);
        finish();
    }

}
