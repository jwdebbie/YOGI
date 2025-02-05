package mobile.joowon.yogi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;

public class TimeTable extends AppCompatActivity {

    private GridLayout gridLayout; // GridLayout 참조
    private HashSet<String> selectedCells = new HashSet<>();
    private Button completeButton; // 완료 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        // GridLayout 초기화
        gridLayout = findViewById(R.id.gridlayout_timetable);
        completeButton = findViewById(R.id.complete_button);

        // GridLayout 내부 모든 TextView에 클릭 이벤트 설정
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            if (child instanceof TextView) {
                child.setTag(i + ""); // 셀에 고유 ID를 할당
                child.setOnClickListener(v -> handleCellClick((TextView) v));
            }
        }

        // 완료 버튼 클릭 이벤트
        completeButton.setOnClickListener(v -> sendResultsAndClose());
    }

    // 클릭 이벤트 처리
    private void handleCellClick(TextView cell) {
        String cellId = cell.getTag().toString();
        if (selectedCells.contains(cellId)) {
            // 선택 해제
            cell.setBackgroundColor(Color.TRANSPARENT);
            selectedCells.remove(cellId);
        } else {
            // 새로 선택
            cell.setBackgroundColor(Color.parseColor("#1E90FF")); // 새로운 파란색

            selectedCells.add(cellId);
        }
    }

    // 결과를 CalendarActivity로 전달하고 종료
    private void sendResultsAndClose() {
        // 새 Intent 객체 생성
        Intent resultIntent = new Intent();
        resultIntent.putStringArrayListExtra("selectedCells", new ArrayList<>(selectedCells));
        resultIntent.putExtra("joinStatus", "completed"); // 참여 완료 상태 전달
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
