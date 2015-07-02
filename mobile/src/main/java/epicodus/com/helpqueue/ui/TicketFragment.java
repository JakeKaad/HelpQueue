package epicodus.com.helpqueue.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import epicodus.com.helpqueue.R;
import epicodus.com.helpqueue.models.Ticket;

/**
 * Created by jake on 6/15/15.
 */
public class TicketFragment extends DialogFragment {

    static TicketFragment newInstance(Ticket ticket) {
        TicketFragment fragment = new TicketFragment();
        Bundle args = new Bundle();
        args.putParcelable("ticket", ticket);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Ticket ticket = getArguments().getParcelable("ticket");
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        TextView questionLabel = (TextView) view.findViewById(R.id.questionLabel);
        questionLabel.setText(ticket.getQuestion());

        return view;
    }
}
