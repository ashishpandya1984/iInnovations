package com.iInnovations.groupchat;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by apandya on 12/23/2017.
 */

class ChatListAdapter extends BaseAdapter implements ChildEventListener
{
    private static class ViewHolder{
        TextView authorName;
        TextView body;
        LinearLayout.LayoutParams params;
    }

    protected ArrayList<DataSnapshot> m_messageCollection;

    protected DatabaseReference m_database;

    protected String m_authorDisplayName;

    protected Context m_context;

    public ChatListAdapter(DatabaseReference dbReference, String authorDisplayName, Context context)
    {
        m_database = dbReference;
        m_authorDisplayName = authorDisplayName;
        m_messageCollection = new ArrayList<DataSnapshot>();
        m_context = context;

        m_database.addChildEventListener(this);
    }

    @Override
    public int getCount() {
        return m_messageCollection.size();
    }

    @Override
    public ChatMessage getItem(int position) {
        DataSnapshot snapshot = m_messageCollection.get(position);

        ChatMessage msg = new ChatMessage(snapshot.child("author").getValue().toString(), snapshot.child("message").getValue().toString());
        return msg;
        //return new ChatMessage("Miklo", "Hello everyone!");
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.chat_msg_row, parent, false);

            final ViewHolder holder = new ViewHolder();
            holder.authorName = (TextView) convertView.findViewById(R.id.author);
            holder.body = (TextView) convertView.findViewById(R.id.message);
            holder.params = (LinearLayout.LayoutParams) holder.authorName.getLayoutParams();
            convertView.setTag(holder);
        }

        final ChatMessage message = getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();

        boolean isMe = message.getAuthor().equals(m_authorDisplayName);
        setChatRowAppearance(isMe, holder);

        String author = message.getAuthor();
        holder.authorName.setText(author);

        String msg = message.getMessage();
        holder.body.setText(msg);

        return convertView;
    }

    private void setChatRowAppearance(boolean isItMe, ViewHolder holder) {

        if (isItMe)
        {
            holder.params.gravity = Gravity.END;
            holder.authorName.setTextColor(Color.GREEN);
            holder.body.setBackgroundResource(R.drawable.bubble2);
        }
        else
        {
            holder.params.gravity = Gravity.START;
            holder.authorName.setTextColor(Color.BLUE);
            holder.body.setBackgroundResource(R.drawable.bubble1);
        }

        holder.authorName.setLayoutParams(holder.params);
        holder.body.setLayoutParams(holder.params);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s)
    {
        m_messageCollection.add(dataSnapshot);
        notifyDataSetChanged();
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
