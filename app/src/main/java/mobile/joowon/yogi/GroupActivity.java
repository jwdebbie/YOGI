package mobile.joowon.yogi;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;


import android.content.Intent;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupActivity extends AppCompatActivity {

    private RecyclerView joinedGroupsList;
    private GroupAdapter groupAdapter;
    private List<String> groupList;
    private Button createGroupButton, joinGroupButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        // 회원가입 화면에서 전달받은 데이터 가져오기
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        // 사용자 이름 환영 메시지 출력
        if (username != null) {
            Toast.makeText(this, "환영합니다, " + username + "님!", Toast.LENGTH_SHORT).show();
        }

        // RecyclerView 초기화
        joinedGroupsList = findViewById(R.id.joinedGroupsList);
        joinedGroupsList.setLayoutManager(new LinearLayoutManager(this));

        // 그룹 리스트 초기화 (예시 데이터)
        groupList = new ArrayList<>();
        groupList.add("모프 스터디");
        groupList.add("맛집 탐방");
        groupList.add("IT 학회");
        groupList.add("초등학교 동창모임");

        groupAdapter = new GroupAdapter(groupList, new GroupAdapter.OnRenameClickListener() {
            @Override
            public void onRenameClick(int position) {
                showEditGroupNamePopup(position); // 그룹 이름 변경 팝업 표시
            }
        }, new GroupAdapter.OnEnterClickListener() {
            @Override
            public void onEnterClick(int position) {
                enterGroup(position); // 그룹 입장 메서드 호출
            }
        });
        joinedGroupsList.setAdapter(groupAdapter);



        // 그룹 만들기 버튼 설정
        createGroupButton = findViewById(R.id.createGroupButton);
    createGroupButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_background_default));
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateGroupPopup();
            }
        });

// 그룹 참여하기 버튼 설정
        joinGroupButton = findViewById(R.id.joinGroupButton);
        joinGroupButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.button_background_default));
        joinGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJoinGroupPopup();
            }
        });


    }

    // 그룹 이름 변경 팝업 표시
    private void showEditGroupNamePopup(int position) {
        // 팝업 레이아웃 inflate
        LayoutInflater inflater = getLayoutInflater();
        View popupView = inflater.inflate(R.layout.popup_edit_group_name, null);

        // 팝업 내 요소 초기화
        EditText editGroupName = popupView.findViewById(R.id.editGroupName);
        Button saveGroupNameButton = popupView.findViewById(R.id.saveGroupNameButton);

        // AlertDialog로 팝업 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popupView);
        AlertDialog dialog = builder.create();

        // 저장 버튼 클릭 이벤트
        saveGroupNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newGroupName = editGroupName.getText().toString();
                if (!newGroupName.isEmpty()) {
                    groupList.set(position, newGroupName); // 이름 변경
                    groupAdapter.notifyItemChanged(position); // UI 업데이트
                    dialog.dismiss();
                    Toast.makeText(GroupActivity.this, "그룹 이름이 변경되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GroupActivity.this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    // 그룹 만들기 팝업 표시
    private void showCreateGroupPopup() {
        // 고유 그룹 코드 생성
        String groupCode = generateGroupCode();
        String groupName = "새로운 그룹 " + (groupList.size() + 1); // 새로운 그룹 이름 예시

        // 팝업 레이아웃을 inflate하여 커스텀 뷰로 사용
        LayoutInflater inflater = getLayoutInflater();
        View popupView = inflater.inflate(R.layout.popup_make_group, null);

        // 생성된 그룹 코드를 팝업의 TextView에 설정
        TextView groupCodeTextView = popupView.findViewById(R.id.groupCodeTextView);
        groupCodeTextView.setText(groupCode);

        // AlertDialog 빌더를 사용하여 팝업 구성
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popupView);
        builder.setTitle("그룹 만들기");

        // 복사 버튼 기능
        Button copyButton = popupView.findViewById(R.id.copyCodeButton);
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Group Code", groupCode);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(GroupActivity.this, "코드가 복사되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 완료 버튼 기능
        builder.setNegativeButton("완료하기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addGroupToList(groupName);
            }
        });

        builder.show();
    }

    // 랜덤 그룹 코드 생성
    private String generateGroupCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        while (code.length() < 6) {
            int index = (int) (random.nextFloat() * chars.length());
            code.append(chars.charAt(index));
        }
        return code.toString();
    }

    // 그룹 리스트에 새 그룹 추가
    private void addGroupToList(String groupName) {
        groupList.add(groupName);
        groupAdapter.notifyItemInserted(groupList.size() - 1);
        Toast.makeText(this, groupName + " 그룹이 추가되었습니다.", Toast.LENGTH_SHORT).show();
    }

    // 그룹 참여하기 팝업 표시
    private void showJoinGroupPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("그룹 참여하기");

        // 팝업 레이아웃을 inflate하여 사용
        LayoutInflater inflater = getLayoutInflater();
        View popupView = inflater.inflate(R.layout.popup_join_group, null); // 그룹 코드 입력 창을 포함한 레이아웃 사용
        builder.setView(popupView);

        // 그룹 코드 입력 필드 참조
        EditText groupCodeInput = popupView.findViewById(R.id.groupCodeInput);

        // 팝업의 참여 버튼 기능
        builder.setPositiveButton("참여하기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String groupCode = groupCodeInput.getText().toString();
                if (!groupCode.isEmpty()) {
                    addGroupToList("그룹 " + groupCode); // 그룹 리스트에 새 항목 추가
                    Toast.makeText(GroupActivity.this, "그룹 " + groupCode + "에 참여했습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GroupActivity.this, "그룹 코드를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 취소 버튼 추가
        builder.setNegativeButton("취소", null);
        builder.show();
    }

    private void enterGroup(int position) {
        String groupName = groupList.get(position);
        Intent intent = new Intent(GroupActivity.this, CalendarActivity.class); // 이동할 Activity 지정
        intent.putExtra("groupName", groupName); // 그룹 이름 전달
        startActivity(intent);
    }

}
