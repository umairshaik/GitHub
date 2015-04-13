package com.bil.ams.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bil.ams.R;

public class MainMenuFragment extends Fragment {

	private static MainMenuFragment mainMenuFragment;

	public static MainMenuFragment getInstance() {
		if (mainMenuFragment == null) {
			mainMenuFragment = new MainMenuFragment();
		}
		return mainMenuFragment;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_menu_fragment, null);
	}
}
