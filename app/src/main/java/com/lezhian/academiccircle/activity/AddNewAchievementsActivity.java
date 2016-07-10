package com.lezhian.academiccircle.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lezhian.academiccircle.R;
import com.lezhian.academiccircle.mvp.presenter.IPresenter;
import com.lezhian.academiccircle.utils.UIUtils;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.zhl.cbdialog.CBDialogBuilder;

import butterknife.Bind;

/**
 * 新增成果的界面
 */
public class AddNewAchievementsActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.tv_left)
    TextView mTvLeft;
    @Bind(R.id.tv_middle)
    TextView mTvMiddle;
    @Bind(R.id.tv_right)
    TextView mTvRight;
    @Bind(R.id.et_type)
    MaterialEditText mEtType;
    @Bind(R.id.iv_expand)
    ImageView mIvExpand;
    @Bind(R.id.et_title)
    MaterialEditText mEtTitle;
    @Bind(R.id.et_publish_year)
    MaterialEditText mEtPublishYear;
    @Bind(R.id.et_author)
    MaterialEditText mEtAuthor;
    @Bind(R.id.et_achievement_link)
    MaterialEditText mEtAchievementLink;
    @Bind(R.id.btn_ok)
    Button mBtnOk;

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        mTvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvMiddle.setText(UIUtils.getString(R.string.addNewAchievements));
        mTvRight.setVisibility(View.GONE);

        mIvExpand.setOnClickListener(this);

    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_new_achievements;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_expand:
                //选择列表的show和Hide
                new CBDialogBuilder(this)
                        .setTouchOutSideCancelable(true)
                                //.showCancelButton(true)
                        .showConfirmButton(true)
                        .setTitle("选择成果类型")
                        .setConfirmButtonText("取消")
                                //.setCancelButtonText("取消")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setItems(new String[]{"论文", "专著","国家基金","专利"}, new CBDialogBuilder.onDialogItemClickListener() {

                            @Override
                            public void onDialogItemClick(CBDialogBuilder.DialogItemAdapter ItemAdapter,
                                                          Context context, CBDialogBuilder dialogbuilder, Dialog dialog,
                                                          int position) {
                                Toast.makeText(AddNewAchievementsActivity.this, "postition:" + position, Toast.LENGTH_SHORT).show();
                                //TODO 保存选中设置
                                // dialog.dismiss();
                            }
                        })
                        .create().show();

                break;
        }

    }
}
