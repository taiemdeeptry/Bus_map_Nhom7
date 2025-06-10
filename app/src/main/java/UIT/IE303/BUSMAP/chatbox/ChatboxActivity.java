package UIT.IE303.BUSMAP.chatbox;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import UIT.IE303.BUSMAP.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatboxActivity extends AppCompatActivity {
    private EditText editTextMessage;
    private Button buttonSend;
    private GeminiApi api;
    private List<Message> messageList = new ArrayList<>();
    private ChatAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatbox);

        // Sử dụng Retrofit Singleton
        api = RetrofitClient.getClient().create(GeminiApi.class);

        // Khởi tạo giao diện
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        recyclerView = findViewById(R.id.recyclerView);

        // Khởi tạo danh sách tin nhắn & adapter
        adapter = new ChatAdapter(messageList);
        recyclerView.setAdapter(adapter);

        // Đảm bảo tin nhắn mới xuất hiện ngay bên dưới
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        // Xử lý sự kiện click nút Gửi
        buttonSend.setOnClickListener(v -> {
            String message = editTextMessage.getText().toString();
            if (!message.isEmpty()) {
                sendMessage(message);
                editTextMessage.setText(""); // Xóa nội dung sau khi gửi
            }
        });
    }


    private void sendMessage(String message) {
        // Thêm tin nhắn người dùng vào giao diện
        messageList.add(new Message(message, true)); // Hiển thị tin nhắn của người dùng
        adapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.scrollToPosition(messageList.size() - 1);

        // Giữ API Key trong mã nguồn theo yêu cầu
        String apiKey = "AIzaSyBHZULdkEr4xS7RqQcDIfSlq4CjH4T0CVE"; // Google Gemini API Key
        List<ChatPart> parts = new ArrayList<>();
        parts.add(new ChatPart(message)); // Gửi tin nhắn từ người dùng lên API
        List<ChatContent> contents = new ArrayList<>();
        contents.add(new ChatContent(parts));
        ChatRequest request = new ChatRequest(contents);
        Call<ResponseBody> call = api.sendMessage(apiKey, request);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String botReplyJson = response.body().string(); // Đọc phản hồi API

                        // Chuyển đổi JSON thành đối tượng
                        JSONObject jsonResponse = new JSONObject(botReplyJson);

                        // Lấy nội dung tin nhắn từ API
                        JSONArray candidates = jsonResponse.getJSONArray("candidates");
                        if (candidates.length() > 0) {
                            JSONObject content = candidates.getJSONObject(0).getJSONObject("content");
                            JSONArray parts = content.getJSONArray("parts");
                            String botMessage = parts.getJSONObject(0).getString("text"); // Gán giá trị đúng

                            // Hiển thị tin nhắn bot
                            messageList.add(new Message(botMessage, false)); // Thêm tin nhắn bot vào danh sách
                            adapter.notifyItemInserted(messageList.size() - 1);
                            recyclerView.scrollToPosition(messageList.size() - 1);

                            System.out.println("Tin nhắn bot: " + botMessage); // Kiểm tra Logcat
                        }
                    } catch (IOException | JSONException e) {
                        System.out.println("Lỗi xử lý JSON: " + e.getMessage());
                    }
                } else {
                    System.out.println("API bị lỗi! Mã: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ChatboxActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
