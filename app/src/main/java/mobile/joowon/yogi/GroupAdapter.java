package mobile.joowon.yogi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    private List<String> groupList;
    private OnRenameClickListener renameListener;
    private OnEnterClickListener enterListener;

    // 인터페이스 정의
    public interface OnRenameClickListener {
        void onRenameClick(int position);
    }

    public interface OnEnterClickListener {
        void onEnterClick(int position);
    }

    // 생성자
    public GroupAdapter(List<String> groupList, OnRenameClickListener renameListener, OnEnterClickListener enterListener) {
        this.groupList = groupList;
        this.renameListener = renameListener;
        this.enterListener = enterListener;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        String groupName = groupList.get(position);
        holder.groupNameTextView.setText(groupName);

        holder.renameGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (renameListener != null) {
                    renameListener.onRenameClick(position);
                }
            }
        });

        holder.enterGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enterListener != null) {
                    enterListener.onEnterClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    static class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView groupNameTextView;
        Button renameGroupButton;
        Button enterGroupButton;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            groupNameTextView = itemView.findViewById(R.id.groupNameTextView);
            renameGroupButton = itemView.findViewById(R.id.renameGroupButton);
            enterGroupButton = itemView.findViewById(R.id.enterGroupButton);
        }
    }
}
