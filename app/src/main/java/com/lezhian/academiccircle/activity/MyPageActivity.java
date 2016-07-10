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
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.adapter.cmn.MyFragmentPagerAdapter;
import com.lezhian.academiccircle.fragment.personPage.AchievementsFragment;
import com.lezhian.academiccircle.fragment.personPage.IntroductionFragment;
import com.lezhian.academiccircle.fragment.personPage.PublishFragment;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.Constants;
import com.lezhian.academiccircle.utils.FileUtil;
import com.lezhian.academiccircle.utils.LogUtils;
import com.lezhian.academiccircle.utils.UIUtils;
import com.lezhian.academiccircle.widget.RoundImageView;
import com.zhl.cbdialog.CBDialogBuilder;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;

/**
 * 我的主页的界面
 */
public class MyPageActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.tv_concern_count)
    TextView mTvConcernCount;
    @Bind(R.id.tv_be_concerned_count)
    TextView mTvBeConcernedCount;
    @Bind(R.id.tv_h_index_count)
    TextView mTvHIndexCount;
    @Bind(R.id.iv_headImage)
    RoundImageView mIvHeadImage;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_identity)
    TextView mTvIdentity;
    @Bind(R.id.tv_college_identity)
    TextView mTvCollegeIdentity;
    @Bind(R.id.tv_college_identity2)
    TextView mTvCollegeIdentity2;
    @Bind(R.id.tv_concern_button)
    TextView mTvConcernButton;
    @Bind(R.id.tv_modify_message)
    TextView mTvModifyMessage;
    @Bind(R.id.toolbar_tab)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    //调用照相机返回图片临时文件
    private File tempFile;
    private int type;

    private MyFragmentPagerAdapter mAdapter;
    private ArrayList<Fragment> mFragments;
     private  String[] mTitles = {UIUtils.getString(R.string.publish),
            UIUtils.getString(R.string.achievements), UIUtils.getString(R.string.introduction)};

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        createHeadImageTempFile(savedInstanceState);

        mTvLeft.setOnClickListener(this);
        mTvMiddle.setText(UIUtils.getString(R.string.myPage));
        mTvRight.setVisibility(View.GONE);
        mTvConcernButton.setOnClickListener(this);
        mTvModifyMessage.setOnClickListener(this);
        mIvHeadImage.setOnClickListener(this);



        mFragments = new ArrayList<>();
        mFragments.add( PublishFragment.getInstance());
        mFragments.add(AchievementsFragment.getInstance());
        mFragments.add(IntroductionFragment.getInstance());


        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);

        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_page;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_left:
                finish();
                break;
            case R.id.iv_headImage:
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

            case R.id.tv_modify_message:
                //修改信息的界面
                startActivities(ModifyMessageActivity.class);
                break;
            case R.id.tv_concern_button:
                //关注
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
