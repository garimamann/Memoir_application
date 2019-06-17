package com.example.memoir.Activities;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.memoir.R;
import com.example.memoir.Utils.PermissionUtils;

public class SpeechToMemoActivity extends AppCompatActivity implements View.OnClickListener,PermissionUtils.PermissionHandlerListener{
    private Button mRecordButton;
    private EditText mMemo;
    private PermissionUtils mPermissionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_memo);

        mPermissionUtil = PermissionUtils.getInstance(this);

        mRecordButton=(Button)findViewById(R.id.activity_speech_to_memo_record_button);
        mMemo=(EditText) findViewById(R.id.activity_speech_to_memo_edit_text);

        mRecordButton.setOnClickListener(this);

        recordingPermission();


    }

    private void recordingPermission() {
        mPermissionUtil.setListener(this.getResources().getString(R.string.audio_record_permission), Manifest.permission.RECORD_AUDIO,1,this);

    }


    @Override
    public void onClick(View v) {


    }

    @Override
    public void onPermissionGranted(int requestCode, String permission) {

    }
}
