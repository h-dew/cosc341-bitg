package com.example.cosc341_bitg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class InboxActivity extends AppCompatActivity {

    // -----------------------------------------------------------------------
    // Data model
    // -----------------------------------------------------------------------

    public static class ChatPreview {
        public final String contactName;
        public final String lastMessage;
        public final String time;
        public final int unreadCount;

        public ChatPreview(String contactName, String lastMessage, String time, int unreadCount) {
            this.contactName = contactName;
            this.lastMessage = lastMessage;
            this.time        = time;
            this.unreadCount = unreadCount;
        }
    }

    // -----------------------------------------------------------------------
    // Hard-coded chat list
    // -----------------------------------------------------------------------

    private static final List<ChatPreview> ALL_CHATS = new ArrayList<>();

    static {
        ALL_CHATS.add(new ChatPreview("Mike",   "Sounds like a plan \uD83D\uDE0A",        "2:14 PM",   0));
        ALL_CHATS.add(new ChatPreview("Shauna", "Let me know when you're free!",          "11:30 AM",  2));
        ALL_CHATS.add(new ChatPreview("Nicole", "That was such a lovely walk.",           "Yesterday", 1));
    }

    // -----------------------------------------------------------------------
    // State
    // -----------------------------------------------------------------------

    private List<ChatPreview> displayedChats = new ArrayList<>(ALL_CHATS);
    private ChatAdapter adapter;

    // -----------------------------------------------------------------------
    // Activity
    // -----------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        // Greeting
        TextView tvGreeting = findViewById(R.id.tvGreeting);
        tvGreeting.setText(getGreeting());

        // List
        ListView listView = findViewById(R.id.listViewChats);
        adapter = new ChatAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            ChatPreview chat = displayedChats.get(position);
            Intent intent = new Intent(this, ConversationActivity.class);
            intent.putExtra(ConversationActivity.EXTRA_CONTACT_NAME, chat.contactName);
            startActivity(intent);
        });

        // Filter chips
        Chip chipAll    = findViewById(R.id.chipAll);
        Chip chipUnread = findViewById(R.id.chipUnread);
        Chip chipRecent = findViewById(R.id.chipRecent);

        chipAll.setOnClickListener(v -> {
            chipAll.setChecked(true);
            chipUnread.setChecked(false);
            chipRecent.setChecked(false);
            applyFilter("all");
        });

        chipUnread.setOnClickListener(v -> {
            chipUnread.setChecked(true);
            chipAll.setChecked(false);
            chipRecent.setChecked(false);
            applyFilter("unread");
        });

        chipRecent.setOnClickListener(v -> {
            chipRecent.setChecked(true);
            chipAll.setChecked(false);
            chipUnread.setChecked(false);
            applyFilter("recent");
        });
    }

    // -----------------------------------------------------------------------
    // Filtering
    // -----------------------------------------------------------------------

    private void applyFilter(String filter) {
        displayedChats.clear();
        switch (filter) {
            case "unread":
                for (ChatPreview c : ALL_CHATS)
                    if (c.unreadCount > 0) displayedChats.add(c);
                break;
            case "recent":
                // "Recent" = today's messages (time doesn't contain "Yesterday")
                for (ChatPreview c : ALL_CHATS)
                    if (!c.time.equalsIgnoreCase("Yesterday")) displayedChats.add(c);
                break;
            default:
                displayedChats.addAll(ALL_CHATS);
        }
        adapter.notifyDataSetChanged();
    }

    // -----------------------------------------------------------------------
    // Greeting helper
    // -----------------------------------------------------------------------

    private String getGreeting() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour < 12) return "Good morning";
        if (hour < 17) return "Good afternoon";
        return "Good evening";
    }

    // -----------------------------------------------------------------------
    // Adapter
    // -----------------------------------------------------------------------

    private class ChatAdapter extends ArrayAdapter<ChatPreview> {

        ChatAdapter() {
            super(InboxActivity.this, R.layout.item_chat, displayedChats);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_chat, parent, false);
            }

            ChatPreview chat = displayedChats.get(position);

            TextView tvAvatar  = convertView.findViewById(R.id.tvChatAvatar);
            TextView tvName    = convertView.findViewById(R.id.tvChatName);
            TextView tvPreview = convertView.findViewById(R.id.tvChatPreview);
            TextView tvTime    = convertView.findViewById(R.id.tvChatTime);
            MaterialCardView cvBadge  = convertView.findViewById(R.id.cvUnreadBadge);
            TextView tvUnread  = convertView.findViewById(R.id.tvUnreadCount);

            tvAvatar.setText(String.valueOf(chat.contactName.charAt(0)));
            tvName.setText(chat.contactName);
            tvPreview.setText(chat.lastMessage);
            tvTime.setText(chat.time);

            if (chat.unreadCount > 0) {
                cvBadge.setVisibility(View.VISIBLE);
                tvUnread.setText(String.valueOf(chat.unreadCount));
            } else {
                cvBadge.setVisibility(View.GONE);
            }

            return convertView;
        }

        @Override
        public int getCount() {
            return displayedChats.size();
        }
    }
}
