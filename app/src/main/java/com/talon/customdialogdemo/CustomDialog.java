package com.talon.customdialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Talon on 2017/2/28.
 */

public class CustomDialog extends Dialog implements View.OnClickListener {
    private Button yes;//确定按钮
    private Button no;//取消按钮
    private TextView titleTv;//消息标题文本
    private TextView messageTv;//消息提示文本
    private String titleStr;//从外界设置的title文本
    private String messageStr;//从外界设置的消息文本
    //确定文本和取消文本的显示内容
    private String yesStr, noStr;

    private onBtnOnclickListener btnOnclickListener;//确定取消按钮被点击了的监听器

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param noStr
     * @param okStr
     * @param btnOnclickListener
     */
    public void setBtnOnclickListener(String noStr, String okStr, onBtnOnclickListener btnOnclickListener) {
        if (noStr != null) {
            this.noStr = noStr;
        }
        if (okStr != null) {
            this.yesStr = okStr;
        }
        this.btnOnclickListener = btnOnclickListener;
    }

    public CustomDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_exercise_sure_dialog_layout);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(this);
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(this);
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (titleStr != null) {
            titleTv.setText(titleStr);
        }
        if (messageStr != null) {
            messageTv.setText(messageStr);
        }
        //如果设置按钮的文字
        if (yesStr != null) {
            yes.setText(yesStr);
        }
        if (noStr != null) {
            no.setText(noStr);
        }
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);
        titleTv = (TextView) findViewById(R.id.title);
        messageTv = (TextView) findViewById(R.id.message);
    }

    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        titleStr = title;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     */
    public void setMessage(String message) {
        messageStr = message;
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */

    public interface onBtnOnclickListener {

        public void onNoClick();

        public void onOkClick();
    }

    @Override
    public void onClick(View v) {
        if (btnOnclickListener != null) {
            switch (v.getId()) {
                case R.id.no:
                    btnOnclickListener.onNoClick();
                    break;
                case R.id.yes:
                    btnOnclickListener.onOkClick();
                    break;
            }
        }
    }
}
