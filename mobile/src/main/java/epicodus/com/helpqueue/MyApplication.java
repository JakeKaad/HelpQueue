package epicodus.com.helpqueue;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import epicodus.com.helpqueue.ui.MainActivity;

/**
 * Created by jake on 7/8/15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "jxqUTRq7VnDg3OApyh22pUCZwSCGDadLR9cSNt2x", "2r7gUeOXuF818s5I1QUfnzYtnNHgN5MY35leiz7M");
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
//
//        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
