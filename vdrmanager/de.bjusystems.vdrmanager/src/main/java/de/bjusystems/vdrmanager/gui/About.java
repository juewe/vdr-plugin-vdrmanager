package de.bjusystems.vdrmanager.gui;

import de.bjusystems.vdrmanager.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.PackageInfo;
import android.text.SpannableString;
import android.text.util.Linkify;

public class About {

	public static void show(Activity activity){
		if(activity.isFinishing()){
			return;
		}

			String vi = "";
			PackageInfo pi = Utils.getPackageInfo(activity);
			if(pi != null){
				vi = "v"+pi.versionName;
			}
			//View view = activity.getLayoutInflater().inflate(R.layout.about, null);
			 final SpannableString s = new SpannableString(activity.getString(R.string.about_text,vi));
			    Linkify.addLinks(s, Linkify.ALL);
			new AlertDialog.Builder(activity)
			.setTitle(R.string.about_title)
			.setMessage(s)
			.setPositiveButton(android.R.string.ok, null)
			.setCancelable(false)
			.create()
			.show();
		}
	}

