package net.qmsource.android.teaching.starterapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Simple application which shows some of elementary elements of Android
 * @author Tonda Novak
 *
 */

public class StarterAppActivity extends Activity {

	private static final String TAG = StarterAppActivity.class.getSimpleName();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	
        //logging tool
    	Log.i(TAG, "onCreate method started");
    	
    	//shared preferences is one of mechanisms of storing data
    	final SharedPreferences prefs  = PreferenceManager.getDefaultSharedPreferences(this);
    	
    	//loading layout from layout/main.xml
        setContentView(R.layout.main);
        
        //creating dialog (notice fluent api)
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.hello)+" "+prefs.getString("name", "stranger"))
        	   .setIcon(R.drawable.icon).setTitle(R.string.title)
        	   .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					Log.i(TAG, "dialog dismissed");
					
					dialog.dismiss();
				}
			}).create().show();
        
        //retrieving UI elements defined in xml layout
        final EditText editText = (EditText) findViewById(R.id.edittext);
        
        Button save = (Button) findViewById(R.id.savebutton);
        save.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				
				Log.i(TAG, "save button clicked");
				
				String name = editText.getText().toString();
				if (name.isEmpty()) {
					prefs.edit().putString("name", null).commit();
				} else {
					prefs.edit().putString("name", name).commit();
				}
				
				//user interaction element (eg. Toast, Notification etc...)
				Toast.makeText(StarterAppActivity.this, editText.getText(), Toast.LENGTH_SHORT).show();
				
			}
		});
        
    }
    
}