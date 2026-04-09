package com.example.cosc341_bitg; // adjust to match your package name

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageButton;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ConversationActivity extends AppCompatActivity {

    public static final String EXTRA_CONTACT_NAME = "contact_name";

    // this is so java

    public static class Message {
        public final String text;
        public final boolean isOutgoing;  // true = sent by the user
        public final String time;

        public Message(String text, boolean isOutgoing, String time) {
            this.text       = text;
            this.isOutgoing = isOutgoing;
            this.time       = time;
        }
    }

    // i may or may not have gotten an LLM to write this part.....

    private static final Map<String, List<Message>> HISTORY = new HashMap<>();

    static {
        // Mike
        List<Message> mike = new ArrayList<>();
        mike.add(new Message("Hi there! I saw your profile — I love hiking too.", false, "Mon 10:02 AM"));
        mike.add(new Message("Oh that's wonderful! Which trails do you like?",     true,  "Mon 10:05 AM"));
        mike.add(new Message("Mostly the riverside paths. Easy on the knees!",     false, "Mon 10:07 AM"));
        mike.add(new Message("Ha! Same here. Maybe we could meet up sometime?",    true,  "Mon 10:10 AM"));
        mike.add(new Message("I'd really like that. How about this weekend?",      false, "Mon 10:12 AM"));
        mike.add(new Message("Saturday works great for me!",                        true,  "Mon 10:13 AM"));
        mike.add(new Message("Perfect. Let's meet at Riverside Park at 10 AM.",    false, "Mon 10:15 AM"));
        mike.add(new Message("Sounds like a plan 😊",                               true,  "2:14 PM"));
        HISTORY.put("Mike", mike);

        // Shauna
        List<Message> shauna = new ArrayList<>();
        shauna.add(new Message("Hello! I saw you like cooking. Favourite dish?",   false, "Tue 9:00 AM"));
        shauna.add(new Message("That's a tough one — probably a good lamb stew.",   true,  "Tue 9:15 AM"));
        shauna.add(new Message("Oh yum! I make a great one too.",                   false, "Tue 9:17 AM"));
        shauna.add(new Message("We'll have to compare recipes sometime 😄",         true,  "Tue 9:20 AM"));
        shauna.add(new Message("Absolutely! I also love gardening — do you?",      false, "Tue 9:22 AM"));
        shauna.add(new Message("Yes! Mostly vegetables. Tomatoes this year.",       true,  "Tue 9:25 AM"));
        shauna.add(new Message("Amazing. I'm growing herbs. Rosemary mostly.",     false, "Tue 9:27 AM"));
        shauna.add(new Message("Let me know when you're free!",                     false, "11:30 AM"));
        HISTORY.put("Shauna", shauna);

        // Nicole
        List<Message> nicole = new ArrayList<>();
        nicole.add(new Message("Hi! I noticed we're both in the same area.",       false, "Sun 2:00 PM"));
        nicole.add(new Message("Oh really? Whereabouts are you?",                   true,  "Sun 2:05 PM"));
        nicole.add(new Message("Just near the botanical gardens.",                  false, "Sun 2:06 PM"));
        nicole.add(new Message("That's so close to me! I walk there all the time.", true, "Sun 2:08 PM"));
        nicole.add(new Message("We should walk together sometime!",                 false, "Sun 2:10 PM"));
        nicole.add(new Message("I'd love that. Tomorrow morning?",                  true,  "Sun 2:12 PM"));
        nicole.add(new Message("Perfect. Meet at the east gate at 9?",             false, "Sun 2:13 PM"));
        nicole.add(new Message("See you then! 🌸",                                   true,  "Sun 2:14 PM"));
        nicole.add(new Message("That was such a lovely walk.",                      false, "Yesterday"));
        HISTORY.put("Nicole", nicole);
    }

    // to handle state

    private List<Message> messages;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        String contactName = getIntent().getStringExtra(EXTRA_CONTACT_NAME);
        if (contactName == null) contactName = "Unknown";

        // copy the hard-coded history into a mutable list so new messages can be added
        List<Message> seed = HISTORY.get(contactName);
        messages = seed != null ? new ArrayList<>(seed) : new ArrayList<>();

        // gotta access through the toolbar
        View toolbar          = findViewById(R.id.convToolbarLayout);
        TextView tvConvName   = toolbar.findViewById(R.id.tvConvName);
        TextView tvConvAvatar = toolbar.findViewById(R.id.tvConvAvatar);
        ImageButton btnBack   = toolbar.findViewById(R.id.btnBack);

        tvConvName.setText(contactName);
        tvConvAvatar.setText(String.valueOf(contactName.charAt(0)));
        btnBack.setOnClickListener(v -> finish());

        // message list
        ListView listView = findViewById(R.id.listViewMessages);
        adapter = new MessageAdapter();
        listView.setAdapter(adapter);
        listView.post(() -> listView.setSelection(adapter.getCount() - 1));

        // send
        TextInputEditText etInput = findViewById(R.id.etMessageInput);
        MaterialButton    btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> {
            String text = etInput.getText() != null ? etInput.getText().toString().trim() : "";
            if (TextUtils.isEmpty(text)) return;

            String time = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());
            messages.add(new Message(text, true, time));
            adapter.notifyDataSetChanged();
            etInput.setText("");
            listView.post(() -> listView.setSelection(adapter.getCount() - 1));
        });
    }

    // adapter

    private class MessageAdapter extends ArrayAdapter<Message> {

        MessageAdapter() {
            super(ConversationActivity.this, R.layout.item_message, messages);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_message, parent, false);
            }

            Message msg = messages.get(position);

            View    layoutIncoming  = convertView.findViewById(R.id.layoutIncoming);
            View    layoutOutgoing  = convertView.findViewById(R.id.layoutOutgoing);
            TextView tvIncoming     = convertView.findViewById(R.id.tvMessageIncoming);
            TextView tvOutgoing     = convertView.findViewById(R.id.tvMessageOutgoing);
            TextView tvTimeIncoming = convertView.findViewById(R.id.tvTimeIncoming);
            TextView tvTimeOutgoing = convertView.findViewById(R.id.tvTimeOutgoing);

            if (msg.isOutgoing) {
                layoutOutgoing.setVisibility(View.VISIBLE);
                layoutIncoming.setVisibility(View.GONE);
                tvOutgoing.setText(msg.text);
                tvTimeOutgoing.setText(msg.time);
            } else {
                layoutIncoming.setVisibility(View.VISIBLE);
                layoutOutgoing.setVisibility(View.GONE);
                tvIncoming.setText(msg.text);
                tvTimeIncoming.setText(msg.time);
            }

            return convertView;
        }
    }
}
