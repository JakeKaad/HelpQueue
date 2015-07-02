package epicodus.com.helpqueue.ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import epicodus.com.helpqueue.R;
import epicodus.com.helpqueue.models.Ticket;

public class NewTicketActivity extends ActionBarActivity {

    @InjectView(R.id.studentLabel)  EditText mStudentLabel;
    @InjectView(R.id.languageLabel)  EditText mLanguageLabel;
    @InjectView(R.id.questionLabel)  EditText mQuestionLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ticket);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.helpButton)
    public void postTicketActivity(View view) {
        String students = mStudentLabel.getText().toString();
        String question = mQuestionLabel.getText().toString();
        String language = mLanguageLabel.getText().toString();

        Ticket ticket = new Ticket(students, question, true, language);
        createTicket(ticket);
    }

    private void createTicket(Ticket ticket) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String jsonString = "{ \"student\" : \"" + ticket.getStudent() + "\"," +
                "\"question\" : \"" +  ticket.getQuestion() + "\"," +
                "\"open\" : true," +
                "\"language\" : \"" + ticket.getLanguage() + "\"," +
                "\"createdAt\" : " + ticket.getCreatedAt() + "}";

        Log.v("REQUEST BODY", jsonString);
        Request request = new Request.Builder()
                .url("https://dazzling-inferno-9595.firebaseio.com/tickets.json")
                .post(RequestBody.create(mediaType, jsonString))
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                Intent intent = new Intent(NewTicketActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
