package mobile.joowon.yogi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private EditText idEditText, passwordEditText;
    private Button enterButton;
    private TextView signupTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idEditText = findViewById(R.id.idEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        enterButton = findViewById(R.id.enterButton);
        signupTextView = findViewById(R.id.signupTextView);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (id.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "ID와 PW를 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    // 로그인 성공 시 GroupActivity로 이동
                    Intent intent = new Intent(MainActivity.this, GroupActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 화면으로 이동
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
