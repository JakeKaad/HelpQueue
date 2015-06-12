package epicodus.com.helpqueue.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import epicodus.com.helpqueue.R;
import epicodus.com.helpqueue.models.Ticket;

/**
 * Created by jake on 6/12/15.
 */
public class TicketAdapter extends BaseAdapter {

    private Context mContext;
    private Ticket[] mTickets;

    public TicketAdapter(Context context, Ticket[] tickets) {
        mContext = context;

        if (tickets == null) {
            mTickets = new Ticket[0];
        } else {
            mTickets = tickets;
        }

    }

    @Override
    public int getCount() {
        return mTickets.length;
    }

    @Override
    public Object getItem(int position) {
        return mTickets[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.ticket_list_item, null);
            holder = new ViewHolder();
            holder.timeLabel = (TextView) convertView.findViewById(R.id.timeLabel);
            holder.questionLabel = (TextView) convertView.findViewById(R.id.questionLabel);
            holder.deskLabel = (TextView) convertView.findViewById(R.id.deskLabel);
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Ticket ticket = mTickets[position];

        holder.iconImageView.setImageResource(ticket.getIconId());
        holder.timeLabel.setText("Asked at " + ticket.getFormattedTime());
        holder.deskLabel.setText("By " + ticket.getStudent());
        holder.questionLabel.setText(ticket.getQuestion());

        return  convertView;
    }

    private static class ViewHolder {
        TextView timeLabel;
        TextView deskLabel;
        TextView questionLabel;
        ImageView iconImageView;
    }
}
