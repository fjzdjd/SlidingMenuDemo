package com.hand.some.smd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hand.some.smd.ui.main.MainTimeLineActivity;
/**
 * @author D_HANDSOME(weibo)
 * Date : 13-7-22
 */
public class StartActivity extends Activity implements OnClickListener {

	private Button bStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		findViewById();
		setListener();

	}

	protected void findViewById() {
		// TODO Auto-generated method stub
		bStart = (Button) findViewById(R.id.b_start);
	}

	protected void setListener() {
		// TODO Auto-generated method stub
		bStart.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.b_start:
			startActivity(new Intent(getApplication(), MainTimeLineActivity.class));
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

}
