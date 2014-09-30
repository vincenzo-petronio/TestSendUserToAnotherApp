package it.localhost.app.mobile.testsendusertoanotherapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	private final static String GOOGLE_FB_ID = "104958162837";
	private final static String GOOGLE_TW_ID = "20536157";
	private Button btnFb, btnTw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// FACEBOOK
		btnFb = (Button) findViewById(R.id.button1);
		btnFb.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String uri = "";
				Intent intent;

				try {
					// try to open page in facebook native app.
					// http://stackoverflow.com/questions/5707722/what-are-all-the-custom-url-schemes-supported-by-the-facebook-iphone-app/5707825#5707825
					uri = "fb://page/" + GOOGLE_FB_ID;
					intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
					Log.i("[TestSendUserToAnotherApp]", uri);
					startActivity(intent);
				} catch (ActivityNotFoundException ex) {
					// facebook native app isn't available, use browser.
					uri = "http://touch.facebook.com/pages/x/" + GOOGLE_FB_ID;
					intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
					Log.i("[TestSendUserToAnotherApp]", uri);
					startActivity(intent);
				}
			}
		});

		// TWITTER
		btnTw = (Button) findViewById(R.id.button2);
		btnTw.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String uri = "";
				Intent intent;

				try {
					// try to open page in twitter native app.
					// https://twittercommunity.com/t/how-to-find-profile-link-from-user-id/17299
					// http://stackoverflow.com/questions/11105103/open-page-in-twitter-app-from-other-app-android/13843901#13843901
					uri = "twitter://user?user_id=" + GOOGLE_TW_ID;
					intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
					Log.i("[TestSendUserToAnotherApp]", uri);
					startActivity(intent);
				} catch (Exception e) {
					// twitter native app isn't available, use browser.
					uri = "https://twitter.com/intent/user?user_id="
							+ GOOGLE_TW_ID;
					intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
					Log.i("[TestSendUserToAnotherApp]", uri);
					startActivity(intent);
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
