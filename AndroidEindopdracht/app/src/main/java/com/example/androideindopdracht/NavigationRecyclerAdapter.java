package com.example.androideindopdracht;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.osmdroid.views.overlay.Polyline;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NavigationRecyclerAdapter extends RecyclerView.Adapter<NavigationRecyclerAdapter.ViewHolder> {

        private ArrayList<Route> localDataSet;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public static class ViewHolder extends RecyclerView.ViewHolder {
//            private final TextView textView;
            private TextView finishedRoute;
            private TextView dateNumber;
            private TextView avgSpeedNumber;
            private TextView distanceNumber;
            private TextView timeNumber;


            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

//                textView = (TextView) view.findViewById(R.id.textView);
                this.finishedRoute = view.findViewById(R.id.finishedRoute);
                this.dateNumber=view.findViewById(R.id.dateNumber);
                this.avgSpeedNumber=view.findViewById(R.id.avgSpeedNumber);
                this.distanceNumber=view.findViewById(R.id.distanceNumber);
                this.timeNumber=view.findViewById(R.id.timeNumber);

            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
        public NavigationRecyclerAdapter(ArrayList<Route> dataSet) {
            localDataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.last_route_item, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String date = format.format(localDataSet.get(position).getDate());
            format.applyPattern("HH:mm:ss");
            String time = format.format(localDataSet.get(position).getDate());

            int timePassed = (int) (localDataSet.get(position).getEndDate().getTime() - localDataSet.get(position).getDate().getTime());
            String timePassedString = String.format("%02d:%02d:%02d", timePassed/3600000, timePassed/60000, timePassed/1000);

            viewHolder.finishedRoute.setText(date);
            viewHolder.dateNumber.setText(time);
            viewHolder.distanceNumber.setText(String.format("%.0f", localDataSet.get(position).getDistance()) + " m");
            viewHolder.avgSpeedNumber.setText(String.format("%.2f", localDataSet.get(position).getDistance() / (timePassed/1000) * 3.6) + " km/h");
            viewHolder.timeNumber.setText(timePassedString);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
}
