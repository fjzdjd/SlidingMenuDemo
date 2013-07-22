
package com.hand.some.smd.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.hand.some.smd.R;
import com.hand.some.smd.ui.b1.B1Fragment;
import com.hand.some.smd.ui.b2.B2Fragment;
import com.hand.some.smd.ui.b3.B3Fragment;
import com.hand.some.smd.ui.packageapp.PackageAppFragment;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
/**
 * @author D_HANDSOME(weibo)
 * Date : 13-7-22
 */
public class MainTimeLineActivity extends SlidingFragmentActivity {

	private SlidingMenu mSlidingMenu;
	private Button b1, b2, b3, b4;
	private B1Fragment mB1;
	private B2Fragment mB2;
	private B3Fragment mB3;
	private PackageAppFragment mPackageAppFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_content);

		// 菜单
		setBehindContentView(R.layout.frame_menu);

		initSlidingMenu();
		initContent();
		findViewById();
	}
	/////////////////
	private void findViewById() {
		b1 = (Button) findViewById(R.id.menu_01);
		b2 = (Button) findViewById(R.id.menu_02);
		b3 = (Button) findViewById(R.id.menu_03);
		b4 = (Button) findViewById(R.id.menu_04);
		
		b1.setOnClickListener(onClickListener);
		b2.setOnClickListener(onClickListener);
		b3.setOnClickListener(onClickListener);
		b4.setOnClickListener(onClickListener);
	}
	private View.OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.menu_01:
				mB1 = new B1Fragment();
				changeContent(mB1);
//				hideContent(mB2);
				Toast.makeText(getApplication(), R.string.one, Toast.LENGTH_LONG).show();
				break;
			case R.id.menu_02:
				mB2 = new B2Fragment();
				changeContent(mB2);
				Toast.makeText(getApplication(), R.string.two, Toast.LENGTH_LONG).show();
				break;
			case R.id.menu_03:
				mB3 = new B3Fragment();
				changeContent(mB3);
				Toast.makeText(getApplication(), R.string.three, Toast.LENGTH_LONG).show();
				break;
			case R.id.menu_04:
				mPackageAppFragment = new PackageAppFragment();
				changeContent(mPackageAppFragment);
				Toast.makeText(getApplication(), R.string.four, Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		}
	};

	///////////////////////
	/** 初始化菜单 */
	private void initSlidingMenu() {
		// TODO Auto-generated method stub
		mSlidingMenu = getSlidingMenu();
		// mSlidingMenu.setBehindOffsetRes(resID)//
		mSlidingMenu.setBehindOffset(60);
		mSlidingMenu.setShadowWidth(20);
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);// 手势范围
		mSlidingMenu.setShadowDrawable(R.drawable.shadow);
	}
	/** 初始化显示内容，进入时主页 **/
	private void initContent() {
		mB2 = new B2Fragment();
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.add(R.id.sliding_content, mB2);
		transaction.commit();
	}

	/** 内容模块修改 */
	private void changeContent(Fragment fragment) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.sliding_content, fragment);
		transaction.commit();
		showContent();
	}
	/** 内容模块隐藏 */
	private void hideContent(Fragment fragment) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.hide(fragment);
		transaction.commit();
//		showContent();
	}

	/** 显示内容模块 */
	public void showContent() {
		mSlidingMenu.showContent();
	}
}
