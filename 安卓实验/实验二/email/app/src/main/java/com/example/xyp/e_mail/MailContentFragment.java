package com.example.xyp.e_mail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xyp on 2018/3/31.
 */

public class MailContentFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mail_content_frag,container,false);
        return view;
    }
    public void refresh(String mailTile,String sender,String mailContent){
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView mailTitleText = (TextView) view.findViewById(R.id.mail_title);
        TextView senderText = (TextView) view.findViewById(R.id.sender);
        TextView mailContentText = (TextView) view.findViewById(R.id.mail_content);

        mailTitleText.setText(mailTile);
        mailContentText.setText(mailContent);
        senderText.setText(sender);
    }
}
