package com.example.xyp.e_mail;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyp on 2018/3/31.
 */

public class MailTitleFragment extends Fragment {
    private boolean isTwoPane;
    private static final String TAG = "MailTitleFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mail_title_list_frg,container,false);

        RecyclerView mailTitleRecyclerView = (RecyclerView) view.findViewById((R.id.mail_title_list_recycler_view));
        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        mailTitleRecyclerView.setLayoutManager(layoutManager);
        MailAdapter adapter = new MailAdapter(getMail());
        mailTitleRecyclerView.setAdapter(adapter);
        return view;
    }
    private List<Mail> getMail(){
        String[] sender={"Apple","好大学在线","印象笔记"};
        String[] title={"進一步了解你的新 Apple 產品","《计算机网络》第三章习题已发布","限时优惠将于明天过期"};
        String[] summary={"報名預約你的免費 30 分鐘網上指導時段...",
                          "Hi， 各位同学，大家好!第三章第一次客观习题已发布，截止...",
                            "限时优惠将于明天过期.高级帐户 40%年费优惠折扣将于24小时内结束..."};
        String[] content = {"\n" +
                "\n" +
                "報名預約你的免費 30 分鐘網上指導時段。\n" +
                "訂單編號： xxxxxxxxxx\n" +
                "\n" +
                "感謝你最近在 Apple 購物。\n" +
                "\n" +
                "在這個度身制訂的互動指導時段中，你會與 Apple Specialist 透過電話對談，並同時在你的電腦上觀看影片。無論你是剛開始使用 Apple 產品，還是 Apple 的長期客戶，我們將助你進一步了解新的 Apple 產品。\n" +
                "\n" +
                "你的時段將會涵蓋以下主題和更多內容：\n" +
                "\n" +
                "實用貼士和技巧，還有最合你使用 Apple 產品的方法。\n" +
                "關於你的 AppleID、iCloud 和其他 Apple 服務的疑問和優點。\n" +
                "如有需要，可協助你完成設定。\n" +
                "如果你希望在澳門或香港參加網上指導時段：\n" +
                "\n" +
                "預約個人設定時段\n" +
                "如果你想要在中国大陆加入普通话在线会话：请注意，只有你居住在中国大陆境内时，才能获得普通话服务。\n" +
                "\n" +
                "安排会话\n" +
                "安排時段後，你將會收到一封確認電郵，內文附有指導時段的其他詳情。*\n" +
                "\n" +
                "如欲獲得技術支援服務，例如如何將數據傳輸到新裝置的操作方法，請瀏覽 Apple 支援服務。\n" +
                "\n" +
                "如果你已經跟我們的店內團隊預約網上指導時段，則可毋須理會此電郵。如果這是你送給別人的禮物，只需將這封電郵轉寄給對方，讓對方可以自行選擇時間。\n" +
                "\n" +
                "Apple 感謝你的惠顧。我們期待跟你相處的時間。\n" +
                "\n" +
                "網上購買 | 尋找零售店 | 800-908-988 | Apple Store App\n" +
                "*請注意，在指導時段中你需用到高速互聯網連線、電話（用來與我們通話）、電腦（用來顯示指導內容），當然還有你剛收到的新 Apple 產品。如果你的時段是為 iPhone 而設，請在時段開始前先激活手機。如果時段是為 Apple Watch 而設，請準備一部運行最新 iOS 版本的 iPhone 5s 或更新的裝置。\n" +
                "\n" +
                "Copyright © 2018 Apple Inc. 保留一切權利。\n" +
                "\n" +
                "私隱政策   |  使用條款   |  銷售條款\n" ,
                "Hi， xxx\n" +
                        "各位同学，大家好。\n" +
                        "\n" +
                        "第三章第一次客观习题已发布，截止4月4日，满分100分；第二次将于4月5日发布，截止4月7日，满分85分。\n" +
                        "\n" +
                        "第三章主观习题也已发布，截止日期4月8日；同学互评将于4月9日开始，截止日期4月10日。请各位同学按时完成测试、互评。\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "另，第二章客观习题、主观习题即将截止，没完成的同学请抓紧哦。",
                "限时优惠将于明天过期\n" +
                        "高级帐户 40%年费优惠折扣将于24小时内结束。 \n" +
                        "\n" +
                        "马上升级，随时随地在所有设备上查看存储的笔记资料。同时，每月还可享受超大上传空间及更多更强功能。 \n" +
                        "\n" +
                        "立即享受限时折扣，全面体验印象笔记的强大功能。"
                };
        String[] date={"3月3日","2月1日","1月30日"};
        int imageId = R.mipmap.mail;
        List<Mail> mailList = new ArrayList<>();
        for(int i = 0;i<3;i++)
            mailList.add(new Mail(title[i],sender[i],content[i],date[i],imageId,summary[i]));
        return mailList;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.mail_content_layout)!=null){
            isTwoPane = true;
        }else {
            isTwoPane = false;
        }
    }

    class MailAdapter extends RecyclerView.Adapter<MailAdapter.ViewHolder>{
        private List<Mail> mMailList;

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView mailTitleText;
            TextView senderText;
            TextView dateText;
            TextView summaryText;
            ImageView imageView;
            public ViewHolder(View view){
                super(view);
                mailTitleText = (TextView) view.findViewById(R.id.Title);
                senderText=(TextView)view.findViewById(R.id.Sender);
                dateText = (TextView)view.findViewById(R.id.date);
                summaryText = (TextView)view.findViewById(R.id.Summary);
                imageView = (ImageView) view.findViewById(R.id.Image);
            }

        }
        public  MailAdapter(List<Mail> mailList){
            mMailList=mailList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Mail mail = mMailList.get(holder.getAdapterPosition());
                    if(isTwoPane){
                        MailContentFragment mailContentFragment = (MailContentFragment) getFragmentManager().findFragmentById(R.id.mail_content_fragment);
                        mailContentFragment.refresh(mail.getTitle(),mail.getSender(),mail.getContent());

                    }else {
                        MailContentActivity.actionStart(getActivity(),mail.getTitle(),mail.getSender(),mail.getContent());
                    }
                }
            });
            return  holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Mail mail = mMailList.get(position);
            holder.mailTitleText.setText(mail.getTitle());
            holder.imageView.setImageResource(mail.getImage());
            holder.summaryText.setText(mail.getSummary());
            holder.dateText.setText(mail.getDate());
            holder.senderText.setText(mail.getSender());
        }

        @Override
        public int getItemCount() {
            return mMailList.size();
        }
    }
}
