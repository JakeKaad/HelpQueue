package epicodus.com.helpqueue.ui;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import epicodus.com.helpqueue.R;
import epicodus.com.helpqueue.adapters.TicketAdapter;
import epicodus.com.helpqueue.models.Ticket;


public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String TICKETS = "TICKETS";

    private Ticket[] mTickets;
    @InjectView(R.id.queueButton) Button mQueueButton;
    @InjectView(R.id.questionButton) Button mQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        getTickets();
    }

    private void getTickets() {
        String ticketsUrl = "https://dazzling-inferno-9595.firebaseio.com/tickets.json";

        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(ticketsUrl).build();
            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Toast.makeText(MainActivity.this, "Unable to connect to Firebase", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onResponse(Response response) throws IOException {

                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mTickets = parseTickets(jsonData);
                            Log.v(TAG, mTickets[0].getQuestion());
                        } else {
                            Toast.makeText(MainActivity.this, "Unable to connect to Firebase", Toast.LENGTH_LONG).show();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "exception caught: ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "exception caught: ", e);
                    }
                }
            });
        } else {
            Toast.makeText(this, "Network unavailable", Toast.LENGTH_LONG).show();
        }
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }

    private Ticket[] parseTickets(String jsonData) throws JSONException {
        JSONObject objectData = new JSONObject(jsonData);
        JSONArray data = objectData.toJSONArray(objectData.names());

        Ticket[] tickets = new Ticket[data.length()];

        for (int i = 0; i < data.length(); i++) {
            JSONObject jsonTicket = data.getJSONObject(i);

            String student = jsonTicket.getString("student");
            String question = jsonTicket.getString("question");
            boolean open = jsonTicket.getBoolean("open");
            String language = jsonTicket.getString("language");
            Ticket ticket = new Ticket(student, question, open, language);
            tickets[i] = ticket;
        }

        return tickets;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        Log.v(TAG, Boolean.toString(networkInfo.isConnected()));
        return isAvailable;
    }

    @OnClick(R.id.queueButton)
    public void startTicketActivity(View view) {
        Intent intent = new Intent(this, TicketActivity.class);
        intent.putExtra("TICKETS", mTickets);
        startActivity(intent);
    }

    @OnClick(R.id.questionButton)
    public void startNewTicketActivity(View view) {
        Intent intent = new Intent(this, NewTicketActivity.class);
        startActivity(intent);
    }
}
