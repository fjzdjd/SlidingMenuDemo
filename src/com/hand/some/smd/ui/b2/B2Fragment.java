package com.hand.some.smd.ui.b2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hand.some.smd.R;
/**
 * @author D_HANDSOME(weibo)
 * Date : 13-7-22
 */
public class B2Fragment extends Fragment {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.content_fragment_b2, container,
				false);

		return view;
	}
}
