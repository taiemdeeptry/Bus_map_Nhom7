package UIT.IE303.BUSMAP.chatbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import UIT.IE303.BUSMAP.R;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private final List<Message> messageList;

    public ChatAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.textMessage.setText(message.getText());

        // Lấy layout params để canh chỉnh vị trí
        ConstraintLayout.LayoutParams params =
                (LayoutParams) holder.textMessage.getLayoutParams();

        // Reset trước khi áp dụng
        params.startToStart = LayoutParams.UNSET;
        params.endToEnd = LayoutParams.UNSET;

        if (message.isUser()) {
            // Người dùng → bên phải
            params.endToEnd = LayoutParams.PARENT_ID;
            holder.textMessage.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            holder.textMessage.setBackgroundResource(R.drawable.chat_bubble_user);
        } else {
            // Bot → bên trái
            params.startToStart = LayoutParams.PARENT_ID;
            holder.textMessage.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            holder.textMessage.setBackgroundResource(R.drawable.chat_bubble_bot);
        }

        params.bottomMargin = 8;
        holder.textMessage.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            textMessage = itemView.findViewById(R.id.textMessage);
        }
    }
}
