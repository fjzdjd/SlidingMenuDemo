package com.hand.some.smd.ui.packageapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fortysevendeg.android.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.android.swipelistview.SwipeListView;
import com.hand.some.smd.R;
import com.hand.some.smd.ui.main.MainTimeLineActivity;
/**
 * @author D_HANDSOME(weibo)
 * Date : 13-7-22
 */
public class PackageAppFragment extends Fragment {

	private static final int REQUEST_CODE_SETTINGS = 0;
	private PackageAdapter adapter;
	private List<PackageItem> data;

	private SwipeListView swipeListView;

	private ProgressDialog progressDialog;
	
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
		View view = inflater.inflate(R.layout.content_packageapp_fragment, container,
				false);

		swipeListView = (SwipeListView) view.findViewById(R.id.package_swipeListView);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		data = new ArrayList<PackageItem>();
		adapter = new PackageAdapter(getActivity(), data);
		
		swipeListView.setSwipeListViewListener(new BaseSwipeListViewListener(){

			@Override
			public void onOpened(int position, boolean toRight) {
			}

			@Override
			public void onClosed(int position, boolean fromRight) {
			}

			@Override
			public void onListChanged() {
			}

			@Override
			public void onMove(int position, float x) {
			}

			@Override
			public void onStartOpen(int position, int action, boolean right) {
				Log.d("swipe", String.format("onStartOpen %d - action %d",
						position, action));
			}

			@Override
			public void onStartClose(int position, boolean right) {
				Log.d("swipe", String.format("onStartClose %d", position));
			}

			@Override
			public void onClickFrontView(int position) {
				Log.d("swipe", String.format("onClickFrontView %d", position));
			}

			@Override
			public void onClickBackView(int position) {
				Log.d("swipe", String.format("onClickBackView %d", position));
			}

			@Override
			public void onDismiss(int[] reverseSortedPositions) {
				for (int position : reverseSortedPositions) {
					data.remove(position);
				}
				adapter.notifyDataSetChanged();
			}
			
		});
		swipeListView.setAdapter(adapter);
		new ListAppTask().execute();
		
		progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage(getString(R.string.loading));
		progressDialog.setCancelable(false);
		progressDialog.show();
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQUEST_CODE_SETTINGS:
			
//			break;
//
//		default:
//			break;
		}
	}
	public int convertDpToPixel(float dp) {
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return (int) px;
	}
	
	public class ListAppTask extends AsyncTask<Void, Void, List<PackageItem>> {

		protected List<PackageItem> doInBackground(Void... args) {
			PackageManager appInfo = ((MainTimeLineActivity)getActivity()).getPackageManager();
			List<ApplicationInfo> listInfo = appInfo
					.getInstalledApplications(0);
			Collections.sort(listInfo,
					new ApplicationInfo.DisplayNameComparator(appInfo));

			List<PackageItem> data = new ArrayList<PackageItem>();

			for (int index = 0; index < listInfo.size(); index++) {
				try {
					ApplicationInfo content = listInfo.get(index);
					if ((content.flags != ApplicationInfo.FLAG_SYSTEM)
							&& content.enabled) {
						if (content.icon != 0) {
							PackageItem item = new PackageItem();
							item.setName(((MainTimeLineActivity)getActivity()).getPackageManager().getApplicationLabel(content).toString());
							item.setPackageName(content.packageName);
							item.setIcon(((MainTimeLineActivity)getActivity()).getPackageManager().getDrawable(
									content.packageName, content.icon, content));
							data.add(item);
						}
					}
				} catch (Exception e) {

				}
			}

			return data;
		}

		protected void onPostExecute(List<PackageItem> result) {
			data.clear();
			data.addAll(result);
			adapter.notifyDataSetChanged();
			if (progressDialog != null) {
				progressDialog.dismiss();
				progressDialog = null;
			}
			/*if (PreferencesManager.getInstance(
					SwipeListViewExampleActivity.this).getShowAbout()) {
//				AboutDialog logOutDialog = new AboutDialog();
//				logOutDialog.show(getSupportFragmentManager(), "dialog");
			}*/
		}
	}
}
