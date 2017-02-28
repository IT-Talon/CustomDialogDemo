package com.talon.customdialogdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDialog();
    }

    private void initDialog() {
        customDialog = new CustomDialog(this);
        customDialog.setTitle("提示");
        customDialog.setMessage("确定退出应用?");
        customDialog.setBtnOnclickListener("取消", "确定", new CustomDialog.onBtnOnclickListener() {
            @Override
            public void onNoClick() {
                customDialog.dismiss();
            }

            @Override
            public void onOkClick() {
                customDialog.dismiss();
                MainActivity.super.onBackPressed();
            }
        });
    }

    public void onClick(View view) {
        if (customDialog != null) {
            customDialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        if (customDialog.isShowing()) {
            customDialog.dismiss();
        } else {
            customDialog.show();
        }
    }
}
