package com.bil.ams;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.bil.ams.controller.AMSController;
import com.bil.ams.customization.CustomDialog;
import com.bil.ams.customization.CustomDialogInterface;
import com.bil.ams.models.AMSUser;
import com.bil.ams.utils.Utils;

public class LoginActivity extends Activity {

	private AMSUser user;

	public CustomDialog mydialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AMSController.loadAFRDatabase(this);
		setContentView(R.layout.activity_login);

		((EditText) findViewById(R.id.password_edt))
				.setOnEditorActionListener(new OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_DONE) {
							onLoginButtonClicked(v);
						}
						return false;
					}
				});

	}

	// on exit button clicked give alert and exit application on user
	// confirmation
	public void onExitButtonClicked(View v) {
		alertDialogForExit();

	}

	// Login On click
	// Login On click navigate to menu/home screen
	public void onLoginButtonClicked(View V) {
		// ***Dummy****/
		 Intent intent = new Intent(this, MainMenuActivity.class);
		 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		 startActivity(intent);
		// // ***Dummy****/

		// Check if text is present in textfields

		// does text exits in Edittext
		if (TextUtils.isEmpty(((TextView) findViewById(R.id.login_edt))
				.getText().toString().trim())) {
			Toast.makeText(this, "Please Enter UserName", Toast.LENGTH_LONG)
					.show();
			return;
		}

		if (TextUtils.isEmpty(((TextView) findViewById(R.id.password_edt))
				.getText().toString().trim())) {
			Toast.makeText(this, "Please Enter Password", Toast.LENGTH_LONG)
					.show();
			return;
		}

		new AsyncTask<Void, Void, Boolean>() {

			private String password;
			private String loginID;

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				// getting login and password text
				loginID = ((EditText) findViewById(R.id.login_edt)).getText()
						.toString().trim();
				password = ((EditText) findViewById(R.id.password_edt))
						.getText().toString().trim();
			}

			@Override
			protected Boolean doInBackground(Void... params) {
				return checkUserLoginCredentials(loginID, password);
			}

			@Override
			protected void onPostExecute(Boolean result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				if (result) {
					navigateToApplicationMenuActivity();
				} else {
					alertDialogForInvalidCredentails();
				}

			}

		}.execute();

	}

	protected void alertDialogForInvalidCredentails() {
		// show alert dialog
		((EditText) findViewById(R.id.password_edt)).setText("");
		

		Utils.showAlertDialog(this, getString(R.string.invalid_user_name_pwd),
				"");
		// mydialog = new CustomDialog(this,
		// getString(R.string.invalid_user_name_pwd), null,
		// getString(R.string.ok), null, new CustomDialogInterface() {
		//
		// @Override
		// public void onPositiveButtonClick() {
		// mydialog.dismiss();
		// }
		//
		// @Override
		// public void onNegativeButtonClick() {
		// }
		// });
		// mydialog.getWindow().setBackgroundDrawableResource(
		// android.R.color.transparent);
		// mydialog.show();

	}

	protected void navigateToApplicationMenuActivity() {

		((EditText) findViewById(R.id.login_edt)).setText("");
		((EditText) findViewById(R.id.password_edt)).setText("");
		// navigating to menu page
		Intent intent = new Intent(this, MainMenuActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}

	protected Boolean checkUserLoginCredentials(String loginID, String password) {
		user = AMSController.getAMSUsersListFromDabatase(this, loginID,
				password);
		if (user == null) {
			return false;
		} else {
			return true;
		}

	}

	// alert for exiting application
	private void alertDialogForExit() {
		mydialog = new CustomDialog(this,
				getString(R.string.do_you_want_to_exit), null,
				getString(R.string.yes), getString(R.string.no),
				new CustomDialogInterface() {

					@Override
					public void onPositiveButtonClick() {
						// TODO Auto-generated method stub
						finish();
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
