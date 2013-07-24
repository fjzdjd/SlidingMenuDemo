package com.hand.some.smd.ui.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hand.some.smd.R;

public class TestFragment extends Fragment {

	private Button b;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.testfragment, container, false);
		b = (Button) view.findViewById(R.id.bt_two_f);
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		b.setOnClickListener(onClickListener);
	}
	private View.OnClickListener onClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.bt_two_f:
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				TestFragment02 tf = new TestFragment02();
				transaction.replace(R.id.sliding_content, tf);
				transaction.addToBackStack(null);
				transaction.commit();
				break;

			default:
				break;
			}
		}
	};

}
