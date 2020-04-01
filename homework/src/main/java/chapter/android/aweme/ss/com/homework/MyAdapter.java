package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import chapter.android.aweme.ss.com.homework.R;
import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NumberViewHolder>{
    private static final String TAG="MyAdapter";
    private int num_item;
    private final ListItemClickListener OnClickListener;
    private List<Message> message;
    private static int ViewHolderCount;

    public MyAdapter(int numListItems, List<Message> messages, ListItemClickListener listener) {
        num_item = numListItems;
        OnClickListener = listener;
        ViewHolderCount = 0;
        this.message = messages;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       Context context = viewGroup.getContext();
       int layoutId_ListItem = R.layout.im_list_item;
       LayoutInflater inflater = LayoutInflater.from(context);
       boolean attachtoparent = false;

       View view = inflater.inflate(layoutId_ListItem,viewGroup,attachtoparent);
       NumberViewHolder viewHolder = new NumberViewHolder(view);

       Log.d(TAG,"OnCreateViewHolder : number of ViewHolders created:" + ViewHolderCount);
       ViewHolderCount++;
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder,int position){
        Log.d(TAG,"OnBindViewHolder:#"+position);
        numberViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return num_item;
    }


    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private final CircleImageView avatarCircleImageView;
        private final ImageView noticeImageView;
        private final TextView titleTextView;
        private final TextView timeTextView;
        private final TextView despTextView;


        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarCircleImageView = itemView.findViewById(R.id.iv_avatar);
            noticeImageView = itemView.findViewById(R.id.robot_notice);
            titleTextView = itemView.findViewById(R.id.tv_title);
            timeTextView = itemView.findViewById(R.id.tv_time);
            despTextView = itemView.findViewById(R.id.tv_description);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            Message messages = message.get(position);
            titleTextView.setText(messages.getTitle());
            timeTextView.setText(messages.getTime());
            despTextView.setText(messages.getDescription());
            if (messages.isOfficial()) {
                noticeImageView.setVisibility(View.VISIBLE);
            } else {
                noticeImageView.setVisibility(View.INVISIBLE);
            }
            int icon = AvatarUtil.getAvatarFromInstance(messages.getIcon());
            avatarCircleImageView.setImageResource(icon);
        }

        @Override
        public void onClick(View v) {
            if (OnClickListener != null) {
                OnClickListener.onlistItemClick((String) titleTextView.getText());
            }
        }
    }


    public interface ListItemClickListener {
        void onlistItemClick(String title);
    }
}
