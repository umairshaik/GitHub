package com.bil.ams.utils;

import android.content.Context;

import com.bil.ams.R;
import com.bil.ams.customization.CustomDialog;
import com.bil.ams.customization.CustomDialogInterface;

public class Utils {

	private static CustomDialog mydialog;

	public static void showAlertDialog(final Context context, String msg,
			String negativeText) {

		mydialog = new CustomDialog(context, null, msg,
				context.getString(R.string.ok), negativeText,
				new CustomDialogInterface() {

					@Override
					public void onPositiveButtonClick() {
						// TODO Auto-generated method stub
						mydialog.dismiss();
					}

					@Override
					public void onNegativeButtonClick() {
						// TODO Auto-generated method stub
						mydialog.dismiss();
					}
				});
		mydialog.getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);
		mydialog.show();
	}

}
