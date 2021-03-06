package com.bil.ams.customization;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.bil.ams.R;

public class CustomDialog extends Dialog {

	public CustomDialogInterface myCustomDialogOnClickListener;
	private String mTitle;
	private String mPositiveButtonText;
	private String mNegativeButtonText;
	private String mMessage;

	public CustomDialog(Context context, String title, String message,
			String postiveButtonText, String negativeButtonText,
			CustomDialogInterface myclick) {
		super(context);

		myCustomDialogOnClickListener = myclick;
		mMessage = message;
		mPositiveButtonText = postiveButtonText;
		mNegativeButtonText = negativeButtonText;
		mTitle = title;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_alert);

		// set dialog Title here
		TextView tvTitle = (TextView) findViewById(R.id.tv_title);
		if (TextUtils.isEmpty(mTitle)) {
			tvTitle.setText(getContext().getString(R.string.alert));
		} else {
			tvTitle.setText(mTitle);
		}

		// set dialog msg here
		TextView tvMsg = (TextView) findViewById(R.id.tv_msg);
		if (TextUtils.isEmpty(mMessage)) {
			tvMsg.setVisibility(View.GONE);
		} else {
			tvMsg.setText(mMessage);
		}

		Button btnPositive = (Button) findViewById(R.id.btn_positive);
		if (TextUtils.isEmpty(mPositiveButtonText)) {
			btnPositive.setVisibility(View.GONE);
		} else {
			btnPositive.setText(mPositiveButtonText);
		}
		btnPositive.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				myCustomDialogOnClickListener.onPositiveButtonClick();
				// I am giving the click to the interface function which we need
				// to implements where we call this class

			}
		});

		Button btnNegative = (Button) findViewById(R.id.btn_negative);
		if (TextUtils.isEmpty(mNegativeButtonText)) {
			btnNegative.setVisibility(View.GONE);
		} else {
			btnNegative.setText(mNegativeButtonText);
		}
		btnNegative.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				myCustomDialogOnClickListener.onNegativeButtonClick();
				// I am giving the click to the interface function which we need
				// to implements where we call this class

			}
		});

	}

}