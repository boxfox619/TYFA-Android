package com.boxfox.appjam14application.view.card;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.boxfox.appjam14application.R;
import com.boxfox.appjam14application.data.RequestData;
import com.boxfox.appjam14application.data.RequestItem;
import com.boxfox.appjam14application.view.dialog.ItemListDialog;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by boxfox on 2017-12-16.
 */

public class RequestCardView extends LinearLayout {
    private RequestData requestData;
    private boolean flip;

    private CheckedChangeListener checkedChangeListener;

    private TextView tv_name, tv_subInfo, tv_price, tv_paymentType, tv_cost;
    private ImageView iv_checkBox, iv_profileImage;
    private LinearLayout itemList;
    private View priceInfo, layout_contentView, rootView, label_paymentType, btn_done;
    private boolean checked;


    public RequestCardView(Context context, boolean flip, RequestData data) {
        super(context);
        this.flip = flip;
        this.requestData = data;
        initView();
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.layout_card_request, this, false);
        iv_profileImage = view.findViewById(R.id.iv_profileImage);
        tv_name = view.findViewById(R.id.tv_name);
        tv_subInfo = view.findViewById(R.id.tv_subInfo);
        tv_price = view.findViewById(R.id.tv_price);
        tv_paymentType = view.findViewById(R.id.tv_paymentType);
        label_paymentType = view.findViewById(R.id.label_paymentType);
        tv_cost = view.findViewById(R.id.tv_cost);
        priceInfo = view.findViewById(R.id.layout_priceInfo);
        itemList = view.findViewById(R.id.layout_itemList);
        layout_contentView = view.findViewById(R.id.layout_contentView);
        iv_checkBox = view.findViewById(R.id.iv_checkBox);
        rootView = view.findViewById(R.id.rootView);
        btn_done = view.findViewById(R.id.btn_done);

        if (flip) {
            initFilpMode();
            this.setOnClickListener(e -> openView());
        }
        initContents();
        initPaymentType();
        addView(view);
    }

    private void openView() {
        int maxHeight = (int) getResources().getDimension(R.dimen.cardview_open_height);
        ValueAnimator anim = ValueAnimator.ofInt(layout_contentView.getMeasuredHeight(), maxHeight);
        ValueAnimator anim2 = ValueAnimator.ofInt(((RelativeLayout.LayoutParams) priceInfo.getLayoutParams()).topMargin, (int) (getResources().getDimension(R.dimen.cardview_open_height) - priceInfo.getHeight() - getResources().getDimension(R.dimen.cardview_padding) * 2));
        ValueAnimator anim3 = ValueAnimator.ofInt(rootView.getPaddingLeft(), (int) getResources().getDimension(R.dimen.cardview_open_padding));
        ValueAnimator anim4 = ValueAnimator.ofInt(rootView.getPaddingTop(), (int) getResources().getDimension(R.dimen.cardview_open_padding));
        anim4.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            rootView.setPadding(rootView.getPaddingLeft(), val, rootView.getPaddingRight(), val);
        });
        anim3.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            rootView.setPadding(val, rootView.getPaddingTop(), val, rootView.getPaddingBottom());
        });
        anim2.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) priceInfo.getLayoutParams();
            params.topMargin = val;
            priceInfo.setLayoutParams(params);
        });
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = layout_contentView.getLayoutParams();
            layoutParams.height = val;
            layout_contentView.setLayoutParams(layoutParams);
        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                RequestCardView.this.setOnClickListener(e -> {
                });
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                iv_checkBox.setVisibility(VISIBLE);
                RequestCardView.this.setOnClickListener(e -> close());
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        Animation fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.iv_fade_in_on_open);
        iv_checkBox.startAnimation(fadeIn);
        anim4.start();
        anim3.start();
        anim2.start();
        anim.start();
    }

    private void close() {
        int targetHeight = (int) getResources().getDimension(R.dimen.cardview_close_height);
        ValueAnimator anim = ValueAnimator.ofInt(layout_contentView.getMeasuredHeight(), targetHeight);
        ValueAnimator anim2 = ValueAnimator.ofInt(((RelativeLayout.LayoutParams) priceInfo.getLayoutParams()).topMargin, 0);
        ValueAnimator anim3 = ValueAnimator.ofInt(rootView.getPaddingLeft(), (int) getResources().getDimension(R.dimen.cardview_close_padding));
        ValueAnimator anim4 = ValueAnimator.ofInt(rootView.getPaddingTop(), 0);
        anim4.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            rootView.setPadding(rootView.getPaddingLeft(), val, rootView.getPaddingRight(), val);
        });
        anim3.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            rootView.setPadding(val, rootView.getPaddingTop(), val, rootView.getPaddingBottom());
        });
        anim2.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) priceInfo.getLayoutParams();
            params.topMargin = val;
            priceInfo.setLayoutParams(params);
        });
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = layout_contentView.getLayoutParams();
            layoutParams.height = val;
            layout_contentView.setLayoutParams(layoutParams);
        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                iv_checkBox.setVisibility(INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                RequestCardView.this.setOnClickListener(e -> openView());
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        Animation fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.iv_fade_out_on_close);
        iv_checkBox.startAnimation(fadeOut);
        anim4.start();
        anim3.start();
        anim2.start();
        anim.start();
    }

    private void initFilpMode() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) priceInfo.getLayoutParams();
        params.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        priceInfo.setLayoutParams(params);
        iv_checkBox.setVisibility(INVISIBLE);

        ViewGroup.LayoutParams layoutParams = layout_contentView.getLayoutParams();
        layoutParams.height = (int) getResources().getDimension(R.dimen.cardview_close_height);
        layout_contentView.setLayoutParams(layoutParams);
    }

    private void initPaymentType() {
        tv_paymentType.setText(requestData.getPaymentType());
        switch (requestData.getPaymentType()) {
            case "먼저 결제":
                label_paymentType.setBackgroundColor(getResources().getColor(R.color.priceType_first_label));
                tv_paymentType.setBackgroundColor(getResources().getColor(R.color.priceType_first));
                break;
            case "대신 구매":
                label_paymentType.setBackgroundColor(getResources().getColor(R.color.priceType_second_label));
                tv_paymentType.setBackgroundColor(getResources().getColor(R.color.priceType_second));
                break;
            case "현금 결제":
                label_paymentType.setBackgroundColor(getResources().getColor(R.color.priceType_third_label));
                tv_paymentType.setBackgroundColor(getResources().getColor(R.color.priceType_third));
                break;
        }
    }

    private void initCheckbox() {
        iv_checkBox.setOnClickListener(e -> {
                    Animation fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.iv_fade_out);
                    iv_checkBox.startAnimation(fadeOut);

                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            iv_checkBox.setImageDrawable(getResources().getDrawable((!checked) ? R.mipmap.ic_checkbox_checked : R.mipmap.ic_checkbox_unchecked));
                            notifyCheckedChange();
                            Animation fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.iv_fade_in);
                            iv_checkBox.startAnimation(fadeIn);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                }
        );
    }

    private void initContents() {
        tv_name.setText(requestData.getName());
        tv_subInfo.setText(requestData.getSubInfo());
        tv_price.setText(String.valueOf(requestData.getPrice() + "원"));
        tv_cost.setText(String.valueOf(requestData.getCost()));

        Glide.with(getContext())
                .load(requestData.getProfileUrl())
                .into(iv_profileImage);

        initCheckbox();

        List<RequestItem> requestItems = requestData.getItemList();
        for (int i = 0; i < 3 && i < requestItems.size(); i++) {
            itemList.addView(new RequestItemView(getContext(), requestItems.get(i)));
        }
        if (requestItems.size() > 3) {
            MoreItemView view = new MoreItemView(getContext(), requestItems);
            itemList.addView(view);
        }
    }

    private void notifyCheckedChange() {
        this.checked = !checked;
        if (checkedChangeListener != null) {
            checkedChangeListener.onChange(checked);
        }
    }

    public void setCheckedChangeListener(CheckedChangeListener listener) {
        this.checkedChangeListener = listener;
    }

    public void setMyRequestMode() {
        iv_checkBox.setVisibility(INVISIBLE);
        btn_done.setVisibility(VISIBLE);
        rootView.setPadding(rootView.getPaddingLeft(), rootView.getPaddingTop(), rootView.getPaddingRight(), (int) getResources().getDimension(R.dimen.cardview_myRequestMode_padding_bottom));
    }

    public interface CheckedChangeListener {
        void onChange(boolean checked);
    }
}
