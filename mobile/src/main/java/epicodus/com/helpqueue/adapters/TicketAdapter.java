package epicodus.com.helpqueue.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import epicodus.com.helpqueue.models.Ticket;

/**
 * Created by jake on 6/12/15.
 */
public class TicketAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Ticket> mTickets;

    public TicketAdapter(Context context, ArrayList<Ticket> tickets) {
        mContext = context;
        mTickets = tickets;
    }

    @Override
    public int getCount() {
        return mTickets.size();
    }

    @Override
    public Object getItem(int position) {
        return mTickets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    private static class ViewHolder {

    }
}
