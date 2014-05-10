package com.dogs.base;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class BaseActivity extends Activity {
	public void Toast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

}
