package UIT.IE303.BUSMAP.chatbox;

public class Message {
    private String text;
    private boolean isUser; // Để phân biệt tin nhắn của người dùng và bot

    public Message(String text, boolean isUser) {
        this.text = text;
        this.isUser = isUser;
    }

    public String getText() {
        return text;
    }

    public boolean isUser() {
        return isUser;
    }
}
