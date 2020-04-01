package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity{
    private final static String TAG="wujiabao";

    private TextView tv_name;
    private TextView tv_contentinfo;
    private EditText eddsay;
    private ImageView botton_sendinfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG,"ChatActivity onCreate");
        setContentView(R.layout.activity_chatroom);

        tv_name=findViewById(R.id.tv_with_name);
        eddsay=findViewById(R.id.ed_say);
        tv_contentinfo=findViewById(R.id.tv_content_info);
        botton_sendinfo=findViewById(R.id.btn_send_info);

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            Bundle bundle =extras.getBundle("user");
            if(bundle!=null)
            {
                tv_name.setText("正在与"+bundle.getString("title")+"对话");

            }
        }

        eddsay.setText("");
        botton_sendinfo.setEnabled(false);

        eddsay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                botton_sendinfo.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        botton_sendinfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String message = eddsay.getText().toString();

                eddsay.setText("");
                tv_contentinfo.append(message+"\n");
                message = message.replace("你","我");
                message = message.replace("吗","");
                message = message.replace("?","");
                message = message.replace("？","");
                tv_contentinfo.append(message+"\n");
                botton_sendinfo.setEnabled(false);
            }
        });

    }

}
