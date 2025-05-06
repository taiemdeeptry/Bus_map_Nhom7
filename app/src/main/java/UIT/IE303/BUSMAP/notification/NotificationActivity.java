package UIT.IE303.BUSMAP.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import UIT.IE303.BUSMAP.R;
import UIT.IE303.BUSMAP.Support;
import UIT.IE303.BUSMAP.component.Notification;

public class NotificationActivity extends AppCompatActivity {
//    Recycler View hiển thị list các notification
    RecyclerView rv_notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
//        set toolbar và set title là thông báo
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle(R.string.notification_text);

        initUI();
    }

//    Đổ data vào notification adapter
    private void initUI() {
        rv_notifications = findViewById(R.id.rv_notifications);
        NotificationAdapter adapter = new NotificationAdapter(this, getNotifications());
        rv_notifications.setAdapter(adapter);
        rv_notifications.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private List<Notification> getNotifications() {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification(
                "Xe buýt hoạt động dịp lễ",
                "Thông báo kế hoạch hoạt động của các tuyến xe buýt vào dịp lễ Giáng Sinh và Tết Dương lịch 2025",
                Support.stringToDate("21/12/2024 15:25", "dd/MM/yyyy HH:mm"),
                R.drawable.sun,
                R.layout.notification_detail_3));
        notifications.add(new Notification(
                "Tạm điều chỉnh lộ trình tuyến",
                "Ngày 13/12/2024, tạm thời điều chỉnh lộ trình các tuyến xe buýt qua khu vực đường Lý Thái Tổ thành phố Đồng Hới. Xem chi tiết thêm!",
                Support.stringToDate("12/12/2024 20:31", "dd/MM/yyyy HH:mm"),
                R.drawable.fireworks,
                R.layout.notification_detail_2));
        notifications.add(new Notification(
                "Khôi phục hoạt động 15 tuyến xe buýt",
                "Từ 09/11, TP.HCM khôi phục hoạt động thêm 15 tuyến buýt. Xem thêm chi tiết!",
                Support.stringToDate("07/11/2024 08:33", "dd/MM/yyyy HH:mm"),
                R.drawable.bus_notification,
                R.layout.notification_detail_1));
        return notifications;
    }
}