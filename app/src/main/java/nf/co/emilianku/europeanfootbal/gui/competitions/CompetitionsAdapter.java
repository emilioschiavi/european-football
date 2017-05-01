package nf.co.emilianku.europeanfootbal.gui.competitions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nf.co.emilianku.domain.model.Competition;
import nf.co.emilianku.europeanfootbal.R;
import nf.co.emilianku.europeanfootbal.gui.competitions.viewmodels.CompetitionViewModel;

/**
 * Created by emilio on 25.04.17.
 */

public class CompetitionsAdapter extends
        RecyclerView.Adapter<CompetitionsAdapter.ViewHolder> {

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
        public TextView tvCaption;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            tvCaption = (TextView) itemView.findViewById(R.id.tvCaption);

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
    private final List<CompetitionViewModel> mCompetitionViewModels;

    // Store the context for easy access
    private final Context mContext;

    // Pass in the view model array into the constructor
    public CompetitionsAdapter(Context context) {
        mContext = context;
        mCompetitionViewModels = new ArrayList<>();
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public CompetitionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.listitem_competition, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(CompetitionsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        CompetitionViewModel competitionViewModel = mCompetitionViewModels.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.tvCaption;
        textView.setText(competitionViewModel.getCaption());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mCompetitionViewModels.size();
    }

    public void beginPage() {
        mCompetitionViewModels.clear();
        notifyDataSetChanged();
    }

    public void addCompetition(Competition competition) {
        mCompetitionViewModels.add(new CompetitionViewModel(competition));
        ////notifyItemInserted(mCompetitionViewModels.size() - 1);
    }

    public void endPage() {
        notifyDataSetChanged();
    }

    public CompetitionViewModel getItem(int position) {
        assert 0 <= position && position < mCompetitionViewModels.size();
        return mCompetitionViewModels.get(position);
    }
}