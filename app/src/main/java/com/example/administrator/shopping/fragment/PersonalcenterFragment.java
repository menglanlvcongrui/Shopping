package com.example.administrator.shopping.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.shopping.Dialog.CleanDialog;
import com.example.administrator.shopping.Dialog.ExitDialog;
import com.example.administrator.shopping.R;
import com.example.administrator.shopping.activity.LoginActivity;
import com.example.administrator.shopping.activity.MyOrderActivity;
import com.example.administrator.shopping.activity.ReceiverAddressActivity;
import com.example.administrator.shopping.activity.ResetPasswordActivity;
import com.example.administrator.shopping.activity.UserAgreementActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class PersonalcenterFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout my_order;
    private RelativeLayout goods_address;
    private RelativeLayout reset_password;
    private RelativeLayout user_agreement;
    private RelativeLayout clear_cache;
    private RelativeLayout esc;
    TextView photo, picture, cancel;
    ImageView head_change;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personalcenter, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        my_order = (RelativeLayout) view.findViewById(R.id.my_order);
        goods_address = (RelativeLayout) view.findViewById(R.id.goods_address);
        reset_password = (RelativeLayout) view.findViewById(R.id.reset_password);
        user_agreement = (RelativeLayout) view.findViewById(R.id.user_agreement);
        clear_cache = (RelativeLayout) view.findViewById(R.id.clear_cache);
        esc = (RelativeLayout) view.findViewById(R.id.esc);
        head_change = (ImageView) view.findViewById(R.id.head_change);
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从Sd中找头像，转换成Bitmap
        if (bt != null) {
            Drawable drawable = new BitmapDrawable(toRoundBitmap(bt));// 转换成drawable
            head_change.setImageDrawable(drawable);
        } else {
            /**
             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
        }

        head_change.setOnClickListener(this);
        my_order.setOnClickListener(this);
        goods_address.setOnClickListener(this);
        reset_password.setOnClickListener(this);
        user_agreement.setOnClickListener(this);
        clear_cache.setOnClickListener(this);
        esc.setOnClickListener(this);
    }

    CleanDialog cleanDialog;
    ExitDialog exitDialog;
    Dialog headDialog;
    Intent intent = null;
    private Bitmap head;// 头像Bitmap
    private static String path = "/sdcard/TongGou/";// sd路径

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_order:
                intent = new Intent(getActivity(), MyOrderActivity.class);
                break;
            case R.id.goods_address:
                intent = new Intent(getActivity(), ReceiverAddressActivity.class);
                break;
            case R.id.reset_password:
                intent = new Intent(getActivity(), ResetPasswordActivity.class);
                break;
            case R.id.user_agreement:
                intent = new Intent(getActivity(), UserAgreementActivity.class);
                break;
            case R.id.clear_cache:
                startCleanDialog();
                break;
            case R.id.esc:
                startExitDialog();
                break;
            case R.id.head_change:
                showHeaddialog();
                break;
            case R.id.photo:
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                headDialog.dismiss();
                break;
            case R.id.picture:
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                headDialog.dismiss();
                break;
            case R.id.cancel:
                headDialog.dismiss();
                break;
        }
        if (intent != null) {
            startActivity(intent);
            intent = null;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    if (data.getExtras()==null){
                        return;
                    }
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);// 保存在SD卡中
                        head_change.setImageBitmap(toRoundBitmap(head));// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 调用系统的裁剪
     *
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    /**
     * 保存图片到本地
     * @param mBitmap
     */
    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void showHeaddialog() {
        headDialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_head, null);
        //初始化控件
        initDialogView(inflate);
        //将布局设置给Dialog
        headDialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = headDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 40;//设置Dialog距离底部的距离
        lp.width = getResources().getDisplayMetrics().widthPixels - 40;
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        headDialog.show();//显示对话框
    }

    private void initDialogView(View inflate) {
        photo = (TextView) inflate.findViewById(R.id.photo);
        picture = (TextView) inflate.findViewById(R.id.picture);
        cancel = (TextView) inflate.findViewById(R.id.cancel);
        picture.setOnClickListener(this);
        photo.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }
    /**
     * 把bitmap转成圆形
     * */
    public Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int r = 0;
        // 取最短边做边长
        if (width < height) {
            r = width;
        } else {
            r = height;
        }
        // 构建一个bitmap
        Bitmap backgroundBm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // new一个Canvas，在backgroundBmp上画图
        Canvas canvas = new Canvas(backgroundBm);
        Paint p = new Paint();
        // 设置边缘光滑，去掉锯齿
        p.setAntiAlias(true);
        RectF rect = new RectF(0, 0, r, r);
        // 通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        // 且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r / 2, r / 2, p);
        // 设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, p);
        return backgroundBm;
    }
    //清除缓存弹出的dialog
    public void startCleanDialog() {

        cleanDialog = new CleanDialog(getActivity());//cleanDialog.setTitle("选择分享应用");
        cleanDialog.setYesOnclickListener("清除", new CleanDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                cleanDialog.dismiss();
            }
        });

        cleanDialog.setNoOnclickListener("取消", new CleanDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                cleanDialog.dismiss();
            }
        });
        cleanDialog.show();
    }

    //退出账户弹出的dialog
    public void startExitDialog() {
        exitDialog = new ExitDialog(getActivity());

        exitDialog.setYesOnclickListener("确定", new ExitDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                exitDialog.dismiss();
                Intent intentexit = new Intent();
                intentexit.setClass(getContext(), LoginActivity.class);
                startActivity(intentexit);
            }
        });

        exitDialog.setNoOnclickListener("取消", new ExitDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                exitDialog.dismiss();
            }
        });
        exitDialog.show();
    }
}
