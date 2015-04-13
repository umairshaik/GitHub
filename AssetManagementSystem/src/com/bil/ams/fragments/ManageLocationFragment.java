package com.bil.ams.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bil.ams.R;

public class ManageLocationFragment extends Fragment {

	private static ManageLocationFragment manageLocationFragment;

	public static ManageLocationFragment getInstance() {
		if (manageLocationFragment == null) {
			manageLocationFragment = new ManageLocationFragment();
		}
		return manageLocationFragment;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.manage_location_fragment, null);
	}

}
