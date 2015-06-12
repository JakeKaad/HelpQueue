package epicodus.com.helpqueue.ui;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.Firebase;

import java.util.ArrayList;

import epicodus.com.helpqueue.R;
import epicodus.com.helpqueue.models.Ticket;


public class MainActivity extends ListActivity {

    private ArrayList<Ticket> mTickets;

    Firebase DB = new Firebase("https://dazzling-inferno-9595.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
    }
}
