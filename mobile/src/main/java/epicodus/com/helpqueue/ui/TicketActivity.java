package epicodus.com.helpqueue.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import epicodus.com.helpqueue.R;
import epicodus.com.helpqueue.adapters.TicketAdapter;
import epicodus.com.helpqueue.models.Ticket;

public class TicketActivity extends ListActivity {

    private Ticket[] mTickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.TICKETS);
        mTickets = Arrays.copyOf(parcelables, parcelables.length, Ticket[].class);

        TicketAdapter adapter = new TicketAdapter(this, mTickets);
        setListAdapter(adapter);
    }

}
