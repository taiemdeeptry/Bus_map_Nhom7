package UIT.IE303.BUSMAP.chatbox;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ChatRequest {
    @SerializedName("contents")
    private List<ChatContent> contents;

    public ChatRequest(List<ChatContent> contents) {
        this.contents = contents != null ? contents : List.of(); // Tránh lỗi NullPointerException
    }

    public List<ChatContent> getContents() {
        return contents;
    }
}

class ChatContent {
    @SerializedName("parts")
    private List<ChatPart> parts;

    public ChatContent(List<ChatPart> parts) {
        this.parts = parts != null ? parts : List.of();
    }

    public List<ChatPart> getParts() {
        return parts;
    }
}

class ChatPart {
    @SerializedName("text")
    private String text;

    public ChatPart(String text) {
        this.text = text != null ? text : ""; // Đảm bảo không null
    }

    public String getText() {
        return text;
    }
}
