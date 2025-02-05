package mobile.joowon.yogi;


import android.graphics.Color;

import androidx.core.content.ContextCompat;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

    private Button joinButton;
    private Button resultButton;
    private Button suggestButton; // 약속 제안하기 버튼 추가

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // 그룹 이름 표시
        TextView groupNameTextView = findViewById(R.id.groupName);
        String groupName = getIntent().getStringExtra("groupName");
        if (groupName != null) {
            groupNameTextView.setText(groupName);
        }

        // 참여하기 버튼
        joinButton = findViewById(R.id.join_button);
        joinButton.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.holo_red_dark));
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeTablePopup();
            }
        });

        // 결과보기 버튼 설정
        resultButton = findViewById(R.id.result_button);
        resultButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.brown));
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultPopup();
            }
        });

        // 약속 제안하기 버튼 설정
        suggestButton = findViewById(R.id.suggest_button);
        suggestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateSchedulePopup();
            }
        });

        // 날짜에 체리 아이콘 추가
        displayMockEvents();
    }


    // TimeTable 팝업 띄우기
    private void showTimeTablePopup() {
        Intent intent = new Intent(CalendarActivity.this, TimeTable.class);
        startActivityForResult(intent, 1);
    }

    // 스케줄 취합 결과 팝업 띄우기
    private void showResultPopup() {
        LayoutInflater inflater = getLayoutInflater();
        View popupView = inflater.inflate(R.layout.popup_schedule_results, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popupView);
        builder.setTitle("스케줄 취합결과");

        Button completeButton = popupView.findViewById(R.id.complete_button);
        completeButton.setOnClickListener(v -> {
            Toast.makeText(this, "투표가 완료되었습니다!", Toast.LENGTH_SHORT).show();

            // 새로운 팝업 띄우기
            showSecondPopup();
        });

        builder.show();
    }

    // 두 번째 팝업 메서드
    private void showSecondPopup() {
        LayoutInflater inflater = getLayoutInflater();
        View secondPopupView = inflater.inflate(R.layout.popup_second, null); // 새 팝업 레이아웃 파일

        AlertDialog.Builder secondBuilder = new AlertDialog.Builder(this);
        secondBuilder.setView(secondPopupView);
        secondBuilder.setTitle("최종 확정 알림");

        Button okButton = secondPopupView.findViewById(R.id.ok_button);
        okButton.setOnClickListener(v -> {
            Toast.makeText(this, "장소를 정해보세요!", Toast.LENGTH_SHORT).show();
            highlightConfirmedDate(); // 24일에 색상 적용
        });

        secondBuilder.show();
    }

    private void highlightConfirmedDate() {
        TextView day24 = findViewById(R.id.textView27);
        if (day24 != null) {
            day24.setBackgroundColor(Color.parseColor("#81B29A"));
            day24.setText(day24.getText() + "\n뒷풀이");
        }
    }





    // 약속 제안 팝업 띄우기
    private void showCreateSchedulePopup() {
        LayoutInflater inflater = getLayoutInflater();
        View popupView = inflater.inflate(R.layout.popup_create_schedule, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popupView);
        builder.setTitle("약속 제안하기");

        EditText scheduleName = popupView.findViewById(R.id.schedule_name);
        EditText dateRange = popupView.findViewById(R.id.date_range);
        EditText voteEndTime = popupView.findViewById(R.id.vote_end_time);
        Button submitButton = popupView.findViewById(R.id.submit_schedule);

        submitButton.setOnClickListener(v -> {
            String name = scheduleName.getText().toString();
            String range = dateRange.getText().toString();
            String endTime = voteEndTime.getText().toString();

            if (!name.isEmpty() && !range.isEmpty() && !endTime.isEmpty()) {
                Toast.makeText(this, "약속이 생성되었습니다!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("취소", null);
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            joinButton.setText("참여완료");
            joinButton.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.holo_green_dark));
        }
    }

    private void displayMockEvents() {
        // 예제: 3일에 약속이 있다고 가정
        TextView day3 = findViewById(R.id.textView4);
        day3.setBackgroundColor(Color.parseColor("#F4A261")); // 따뜻한 오렌지색
        day3.setText(day3.getText() + "\n중간회의");

        // 예제: 15일에 약속이 있다고 가정
        TextView day11 = findViewById(R.id.textView17);
        day11.setBackgroundColor(Color.parseColor("#E9967A")); // 다크 살몬 색상
        day11.setText(day11.getText() + "\n최종발표");

    }


}
