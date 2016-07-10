package com.lezhian.academiccircle.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.Constants;
import com.lezhian.academiccircle.utils.FileUtil;
import com.lezhian.academiccircle.utils.LogUtils;
import com.lezhian.academiccircle.utils.UIUtils;
import com.lezhian.academiccircle.widget.RoundImageView;
import com.zhl.cbdialog.CBDialogBuilder;

import java.io.File;

import butterknife.Bind;

/**
 * 帐号管理界面
 */
public class AccountManageActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.iv_headImage)
    RoundImageView mIvHeadImage;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.tv_real_email_address)
    TextView mTvRealEmailAddress;
    @Bind(R.id.tv_real_identity)
    TextView mTvRealIdentity;
    @Bind(R.id.tv_real_area)
    TextView mTvRealArea;
    @Bind(R.id.tv_real_phone)
    TextView mTvRealPhone;
    @Bind(R.id.tv_real_password)
    TextView mTvRealPassword;

    //调用照相机返回图片临时文件
    private File tempFile;
    private int type;


    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        createHeadImageTempFile(savedInstanceState);
        mTvLeft.setOnClickListener(this);
        mTvMiddle.setText(UIUtils.getString(R.string.accountManage));
        mTvRight.setVisibility(View.GONE);

        mIvHeadImage.setOnClickListener(this);
        mTvRealIdentity.setOnClickListener(this);
        mTvRealArea.setOnClickListener(this);
        mTvRealPhone.setOnClickListener(this);
        mTvRealPassword.setOnClickListener(this);



    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_manage;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_left:
                finish();
                break;
            case R.id.iv_headImage:
              //更换头像
                new CBDialogBuilder(this)
                        .setTouchOutSideCancelable(true)
                                //.showCancelButton(true)
                        .showConfirmButton(true)
                        .setTitle("上传头像")
                        .setConfirmButtonText("取消")
                                //.setCancelButtonText("取消")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setItems(new String[]{"拍照", "从相册选择"}, new CBDialogBuilder.onDialogItemClickListener() {

                            @Override
                            public void onDialogItemClick(CBDialogBuilder.DialogItemAdapter ItemAdapter,
                                                          Context context, CBDialogBuilder dialogbuilder, Dialog dialog,
                                                          int position) {


                                Intent intent;
                                switch (position){
                                    case Constants.CAPTURE:
                                        type=1;
                                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                                        startActivityForResult(intent, Constants.REQUEST_CAPTURE);
                                        break;
                                    case Constants.PHOTOS:
                                        type=1;
                                        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(Intent.createChooser(intent, "请选择图片"), Constants.REQUEST_PICK);
                                        break;
                                }


                                dialog.dismiss();
                            }
                        })
                        .create().show();
                break;
            case R.id.tv_real_identity:
                //选择身份的界面
                break;
            case R.id.tv_real_area:
                //选择城市定位的界面
                startActivities(AreaLocationActivity.class);
                break;
            case R.id.tv_real_phone:
                //更换手机号的界面
                //startActivities();
                break;
            case R.id.tv_real_password:
                //更换密码的界面
                startActivities(ResetPasswordActivity.class);
                break;


        }
    }


    /**
     * 创建调用系统照相机待存储的临时文件
     *
     * @param savedInstanceState
     */
    private void createHeadImageTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("tempFile", tempFile);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case Constants.REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case Constants.REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case Constants.REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == Constants.RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = FileUtil.getRealFilePathFromUri(UIUtils.getContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);

                    LogUtils.d("Academic", bitMap.getByteCount() + ":");
                    if(type==1){
                        mIvHeadImage.setImageBitmap(bitMap);
                    }

                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......

                }
                break;
        }

    }

    /**
     * 打开截图界面
     *
     * @param uri
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(UIUtils.getActivity(), ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        startActivityForResult(intent, Constants.REQUEST_CROP_PHOTO);
    }


}
