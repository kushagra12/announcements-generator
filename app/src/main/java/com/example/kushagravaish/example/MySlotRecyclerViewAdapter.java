package com.example.kushagravaish.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kushagravaish.example.SlotFragment.OnListFragmentInteractionListener;

import java.util.List;


public class MySlotRecyclerViewAdapter extends RecyclerView.Adapter<MySlotRecyclerViewAdapter.ViewHolder> {

    private final List<AnnouncementSlot> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MySlotRecyclerViewAdapter(List<AnnouncementSlot> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_slot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTimeView.setText(mValues.get(position).timings);
        holder.mFirstGuyView.setText(mValues.get(position).firstGuy);
        holder.mSecondGuyView.setText(mValues.get(position).secondGuy);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTimeView;
        public final TextView mFirstGuyView;
        public final TextView mSecondGuyView;
        public AnnouncementSlot mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTimeView = (TextView) view.findViewById(R.id.text_view_slot_time);
            mFirstGuyView = (TextView) view.findViewById(R.id.text_view_slot_guy_one);
            mSecondGuyView = (TextView) view.findViewById(R.id.text_view_slot_guy_two);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTimeView.getText() + "'";
        }
    }
}
