package com.iInnovations.groupchat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainChatActivity extends AppCompatActivity implements View.OnClickListener{

    // TODO: Add member variables here:
    private String mDisplayName;

    private ListView mChatListView;

    private EditText mInputText;

    private ImageButton mSendButton;

    DatabaseReference m_chatDatabase;

    ChatListAdapter m_dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        // TODO: Set up the display name and get the Firebase reference
        SharedPreferences localPrefs = getSharedPreferences(RegisterActivity.CHAT_PREFS, MODE_PRIVATE);
        mDisplayName = localPrefs.getString( RegisterActivity.DISPLAY_NAME_KEY, "Anoynemous");

        // Link the Views in the layout to the Java code
        mInputText = (EditText) findViewById(R.id.messageInput);
        mSendButton = (ImageButton) findViewById(R.id.sendButton);
        mChatListView = (ListView) findViewById(R.id.chat_list_view);

        // TODO: Add an OnClickListener to the sendButton to send a message
        mSendButton.setOnClickListener(this);

        // Getting the databse handle to store the chat data
        m_chatDatabase = FirebaseDatabase.getInstance().getReference();
        m_dataAdapter = new ChatListAdapter(m_chatDatabase.child("messages"), mDisplayName, this);

        mChatListView.setAdapter(m_dataAdapter);
    }

    // TODO: Retrieve the display name from the Shared Preferences


    private void sendMessage()
    {
        if(mInputText.getText().toString().trim().isEmpty())
            return;

        // TODO: Grab the text the user typed in and push the message to Firebase
        ChatMessage msg = new  ChatMessage(mDisplayName, mInputText.getText().toString());
        m_chatDatabase.child("messages").push().setValue(msg);

        mInputText.setText("");
    }

    // TODO: Override the onStart() lifecycle method. Setup the adapter here.


    @Override
    public void onStop() {
        super.onStop();

        // TODO: Remove the Firebase event listener on the adapter.

    }

    @Override
    public void onClick(View v)
    {
        sendMessage();
    }
}
