package com.jpn.ohta.asynctask1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getボタンにリスナを割当
        Button getButton = (Button)findViewById(R.id.getButton);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.d(TAG, "getButton onClick: ");
                AsyncDoGet task = new AsyncDoGet(MainActivity.this);
                task.setOnCallBack(new AsyncDoGet.CallBackTask() {
                    @Override
                    public void CallBack(int result) {
                        super.CallBack(result);
                        TextView getTextView = (TextView)findViewById(R.id.getTextView);
                        getTextView.setText("答えは" + result);
                    }
                });

                // AsyncTaskの実行
                EditText getEditText = (EditText)findViewById(R.id.getEditText);
                String str = getEditText.getText().toString();
                if(str!=null && str.length()>0){
                    task.execute(str);
                }
            }
        });

        // postボタンにリスナを割当
        Button postButton = (Button)findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.d(TAG, "postButton onClick: ");
                AsyncDoPost task = new AsyncDoPost(MainActivity.this);
                task.setOnCallBack(new AsyncDoPost.CallBackTask() {
                    @Override
                    public void CallBack(int result) {
                        super.CallBack(result);
                        TextView postTextView = (TextView)findViewById(R.id.postTextView);
                        postTextView.setText("答えは" + result);
                    }
                });

                // AsyncTaskの実行
                EditText postEditText = (EditText)findViewById(R.id.postEditText);
                String str = postEditText.getText().toString();
                if(str!=null && str.length()>0){
                    task.execute(str);
                }
            }
        });
    }
}
