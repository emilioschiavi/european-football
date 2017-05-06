package nf.co.emilianku.europeanfootbal.gui.leaguetable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nf.co.emilianku.domain.model.LeagueTableEntry;
import nf.co.emilianku.europeanfootbal.R;
import nf.co.emilianku.europeanfootbal.gui.BaseActivity;
import nf.co.emilianku.europeanfootbal.gui.leaguetable.viewmodels.LeagueTableViewModel;

/**
 * Created by emilio on 25.04.17.
 */

public class LeagueTableAdapter extends
        RecyclerView.Adapter<LeagueTableAdapter.ViewHolder> {

    // Define listener member variable
    private OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView tvPosition;
        public TextView tvTeamName;
        public TextView tvPoints;
        public ImageView tvFlag;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            tvPosition = (TextView) itemView.findViewById(R.id.tvPosition);
            tvTeamName = (TextView) itemView.findViewById(R.id.tvTeamName);
            tvPoints = (TextView) itemView.findViewById(R.id.tvPoints);
            tvFlag = (ImageView) itemView.findViewById(R.id.ivFlag);

            // Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

    // Store a member variable for the view models
    private final List<LeagueTableViewModel> leagueTableViewModelList;

    // Store the context for easy access
    private final Context mContext;

    // Pass in the view model array into the constructor
    public LeagueTableAdapter(Context context) {
        mContext = context;
        leagueTableViewModelList = new ArrayList<>();
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public LeagueTableAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.listitem_league_table, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(LeagueTableAdapter.ViewHolder viewHolder, int position) {

        View view = viewHolder.itemView;
        if (position % 2 == 0) {
            //view.setBackgroundColor(getContext().getResources().getColor(R.color.color_listitem_background_normal));
            view.setBackground(getContext().getResources().getDrawable(R.drawable.list_item_normal_gradient));
        }
        else {
            //view.setBackgroundColor(getContext().getResources().getColor(R.color.color_listitem_background_alternated));
            view.setBackground(getContext().getResources().getDrawable(R.drawable.list_item_alternate_gradient));
        }

        // Get the data model based on position
        LeagueTableViewModel leagueTableViewModel = leagueTableViewModelList.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.tvPosition;
        textView.setText(leagueTableViewModel.getPosition());
        ImageView imageView = viewHolder.tvFlag;
        ((BaseActivity)getContext()).loadImageIntoView(leagueTableViewModel.getCrestURI(), imageView);
        textView = viewHolder.tvTeamName;
        textView.setText(leagueTableViewModel.getTeamName());
        textView = viewHolder.tvPoints;
        textView.setText(String.valueOf(leagueTableViewModel.getPoints()));
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return leagueTableViewModelList.size();
    }

    public void beginPage() {
        leagueTableViewModelList.clear();
        notifyDataSetChanged();
    }

    public void addLeagueTableEntry(LeagueTableEntry leagueTableEntry) {
        leagueTableViewModelList.add(new LeagueTableViewModel(leagueTableEntry));
    }

    public void endPage() {
        notifyDataSetChanged();
    }

    public LeagueTableViewModel getItem(int position) {
        assert 0 <= position && position < leagueTableViewModelList.size();
        return leagueTableViewModelList.get(position);
    }
}