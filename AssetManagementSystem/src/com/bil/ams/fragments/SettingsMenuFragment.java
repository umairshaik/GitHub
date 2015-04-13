package com.bil.ams.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.bil.ams.R;
import com.bil.ams.controller.SettingsController;
import com.bil.ams.models.Settings;
import com.bil.ams.utils.Utils;

public class SettingsMenuFragment extends Fragment implements OnClickListener {

	// options menu
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		inflater.inflate(R.menu.main_menu, menu);
		System.out.println("In menu");
		super.onCreateOptionsMenu(menu, inflater);
	}

	// onclick for menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.action_save:

			if (checkValueofText()) {
				updateSettingValuesToDB();

			} else {
				Utils.showAlertDialog(mActivity,
						getActivity().getString(R.string.enter_url_path), "");
			}

			// saving to datatbase

			return true;
		default:
			break;
		}

		return false;

	}

	private boolean checkValueofText() {

		String url_EditText = ((EditText) getActivity().findViewById(
				R.id.settings_editText_url)).getText().toString().trim();

		String path_EditText = ((EditText) getActivity().findViewById(
				R.id.settings_editText_path)).getText().toString().trim();

		System.out.println("URL PATH" + url_EditText + path_EditText);
		if (url_EditText.equals("") || path_EditText.equals("")) {

			System.out.println("URL PATH" + url_EditText + path_EditText
					+ "false");
			return false;
		}
		return true;

	}

	private static SettingsMenuFragment settingsMenuFragment;
	private Activity mActivity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = activity;

	}

	public static SettingsMenuFragment getInstance() {
		if (settingsMenuFragment == null) {
			settingsMenuFragment = new SettingsMenuFragment();
		}
		return settingsMenuFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.settings_menu_fragment, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		setHasOptionsMenu(true);

		final Button btnStartTime = (Button) view
				.findViewById(R.id.button_start_time);
		btnStartTime.setOnClickListener(this);

		final Button btnEndTime = (Button) view
				.findViewById(R.id.button_end_time);
		btnEndTime.setOnClickListener(this);

		final Button btnInterval = (Button) view
				.findViewById(R.id.button_interval);
		btnInterval.setOnClickListener(this);

		((CheckBox) view.findViewById(R.id.settings_checkBox_enable_scheduler))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {

							btnStartTime.setEnabled(true);
							btnStartTime.setTextColor(getResources().getColor(
									R.color.black));

							btnEndTime.setEnabled(true);
							btnEndTime.setTextColor(getResources().getColor(
									R.color.black));

							btnInterval.setEnabled(true);
							btnInterval.setTextColor(getResources().getColor(
									R.color.black));

						} else {
							btnStartTime.setEnabled(false);
							btnStartTime.setTextColor(getResources().getColor(
									R.color.hint_color_code));

							btnEndTime.setEnabled(false);
							btnEndTime.setTextColor(getResources().getColor(
									R.color.hint_color_code));

							btnInterval.setEnabled(false);
							btnInterval.setTextColor(getResources().getColor(
									R.color.hint_color_code));
						}

					}
				});

		retrieveSettingDetails();

	}

	// show time picker dialog
	private void showTimePickerDialog(View v) {
		DialogFragment newFragment = new TimePickerFragment(v);
		newFragment.show(mActivity.getFragmentManager(), "timePicker");
	}

	// when clicked on time interval
	private void onTimeIntervalButtonClicked(final View view) {
		final Dialog d = new Dialog(mActivity);
		d.setTitle("NumberPicker");
		d.setContentView(R.layout.layout_number_picker);
		Button b1 = (Button) d.findViewById(R.id.button1);
		final NumberPicker np = (NumberPicker) d
				.findViewById(R.id.numberPicker);
		np.setMaxValue(120);
		np.setMinValue(1);
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Button) view).setText(String.valueOf(np.getValue()));
				d.dismiss();
			}
		});

		d.show();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_start_time:
			showTimePickerDialog(v);
			break;

		case R.id.button_end_time:
			showTimePickerDialog(v);
			break;
		case R.id.button_interval:
			onTimeIntervalButtonClicked(v);

		default:
			break;
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		// updateSettingValuesToDB();
	}

	// retriving details of settings

	private void retrieveSettingDetails() {
		Settings settings = SettingsController
				.getSettingsFromDatabase(mActivity);

		if (settings != null) {

			// get Detiails of sync status
			CheckBox sync_battery_info = ((CheckBox) getView().findViewById(
					R.id.settings_checkBox_sync_status));

			if (settings.getSync_batter_status() == 1) {
				sync_battery_info.setChecked(true);

			} else {
				sync_battery_info.setChecked(false);
			}

			// geolocation status
			CheckBox geo_status = ((CheckBox) getView().findViewById(
					R.id.settings_checkBox_gps));

			if (settings.getGeo_status() == 1) {
				geo_status.setChecked(true);

			} else {
				geo_status.setChecked(false);
			}

			CheckBox enable_scheduler = ((CheckBox) getView().findViewById(
					R.id.settings_checkBox_enable_scheduler));

			if (settings.getmEnableScheduler() == 1) {
				enable_scheduler.setChecked(true);

			} else {
				enable_scheduler.setChecked(false);
			}

			CheckBox push_notification = ((CheckBox) getView().findViewById(
					R.id.settings_checkBox_push_notifications));

			if (settings.getmPush_notification() == 1) {
				push_notification.setChecked(true);
			} else {
				push_notification.setChecked(false);
			}

			CheckBox enable_financial_info = ((CheckBox) getView()
					.findViewById(
							R.id.settings_checkBox_financial_information_capture));

			if (settings.getmFinancial_info_capture() == 1) {
				enable_financial_info.setChecked(true);

			} else {
				enable_financial_info.setChecked(false);
			}

			CheckBox enable_contract_info = ((CheckBox) getView().findViewById(
					R.id.settings_checkBox_contract_information_capture));

			if (settings.getmContract_info_capture() == 1) {
				enable_contract_info.setChecked(true);

			} else {
				enable_contract_info.setChecked(false);
			}

			CheckBox geo_location = ((CheckBox) getView().findViewById(
					R.id.settings_checkBox_gps));

			if (settings.getmGeo_location() == 1) {
				geo_location.setChecked(true);

			} else {
				geo_location.setChecked(false);
			}

			((Button) getView().findViewById(R.id.button_start_time))

			.setText(settings.getmStart_time());

			((Button) getView().findViewById(R.id.button_end_time))
					.setText(settings.getmEnd_ime());

			((Button) getView().findViewById(R.id.button_interval))
					.setText(settings.getmSync_time());

			((EditText) getView().findViewById(R.id.settings_editText_url))
					.setText(settings.getmUrl());

			((EditText) getView().findViewById(R.id.settings_editText_path))
					.setText(settings.getmPath());

		}

	}

	// insert setting values into db
	private void updateSettingValuesToDB() {

		Settings settings = new Settings();

		// checking sync_battery_status
		if (((CheckBox) getView().findViewById(
				R.id.settings_checkBox_sync_status)).isChecked()) {
			settings.setSync_batter_status(1);
		} else {
			settings.setSync_batter_status(0);
		}

		// checking sync_geo_status
		if (((CheckBox) getView().findViewById(R.id.settings_checkBox_gps))
				.isChecked()) {
			settings.setGeo_status(1);
		} else {
			settings.setGeo_status(0);
		}

		// checking if enable scheduler cb checked or not

		if (((CheckBox) getView().findViewById(
				R.id.settings_checkBox_enable_scheduler)).isChecked()) {
			settings.setmEnableScheduler(1);
		} else {
			settings.setmEnableScheduler(0);
		}

		// start time
		settings.setmStart_time(((Button) getView().findViewById(
				R.id.button_start_time)).getText().toString());
		// end time

		settings.setmEnd_time(((Button) getView().findViewById(
				R.id.button_end_time)).getText().toString());
		settings.setmEnd_time(((Button) getView().findViewById(
				R.id.button_end_time)).getText().toString());

		settings.setmSync_time(((Button) getView().findViewById(
				R.id.button_interval)).getText().toString());

		settings.setmSync_time(((Button) getView().findViewById(
				R.id.button_interval)).getText().toString());

		// checking if push notification cb checked or not

		if (((CheckBox) getView().findViewById(
				R.id.settings_checkBox_push_notifications)).isChecked()) {
			settings.setmPush_notification(1);
		} else {
			settings.setmPush_notification(0);
		}

		// checking geo location checkbox value

		if (((CheckBox) getView().findViewById(R.id.settings_checkBox_gps))
				.isChecked() == true) {
			settings.setmGeo_location(1);
		} else {
			settings.setmGeo_location(0);
		}

		// checking if financial info cb checked or not

		if (((CheckBox) getView().findViewById(
				R.id.settings_checkBox_financial_information_capture))
				.isChecked()) {
			settings.setmFinancial_info_capture(1);
		} else {
			settings.setmFinancial_info_capture(0);
		}

		// checking if contract info cb checked or not

		if (((CheckBox) getView().findViewById(
				R.id.settings_checkBox_contract_information_capture))
				.isChecked()) {
			settings.setmContract_info_capture(1);
		} else {
			settings.setmContract_info_capture(0);
		}

		// url
		settings.setmUrl(((EditText) getView().findViewById(
				R.id.settings_editText_url)).getText().toString());
		// path
		settings.setmPath(((EditText) getView().findViewById(
				R.id.settings_editText_path)).getText().toString());

		SettingsController.insertOrUpdateSettingsDataIntoDataBase(mActivity,
				settings);

		// pop up a tost to see if its working
		Toast.makeText(getActivity(), "Saved to DB", Toast.LENGTH_LONG).show();
	}
}
