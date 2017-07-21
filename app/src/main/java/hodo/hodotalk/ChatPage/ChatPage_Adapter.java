package hodo.hodotalk.ChatPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import hodo.hodotalk.R;

/**
 * Created by mjk on 2017. 7. 21..
 */

public class ChatPage_Adapter extends ArrayAdapter<ChatPage_ChatData>
{

    public ChatPage_Adapter(Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ChatLayout chatLayout;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.chatpage_list,null);

            chatLayout = new ChatLayout();
            chatLayout.mTxtUserName = convertView.findViewById(R.id.txt_userName);
            chatLayout.mTxtMsg = convertView.findViewById(R.id.txt_message);
            chatLayout.mTxtTime =convertView.findViewById(R.id.txt_time);

            convertView.setTag(chatLayout);

        }else{
            chatLayout = (ChatLayout)convertView.getTag();
        }
        ChatPage_ChatData chatPage_chatData = getItem(position);
        chatLayout.mTxtUserName.setText(chatPage_chatData.userName);
        chatLayout.mTxtMsg.setText(chatPage_chatData.message);
        chatLayout.mTxtTime.setText(chatPage_chatData.time);
        return super.getView(position, convertView, parent);
    }

    private class ChatLayout {
        private TextView mTxtUserName;
        private TextView mTxtMsg;
        private TextView mTxtTime;

    }
}
