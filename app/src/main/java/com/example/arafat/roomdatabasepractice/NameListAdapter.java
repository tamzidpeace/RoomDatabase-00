package com.example.arafat.roomdatabasepractice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NameListAdapter extends RecyclerView.Adapter<NameListAdapter.NameViewHolder> {


    private List<Name> mName;
    private final LayoutInflater mLayoutInflater;

    public class NameViewHolder extends RecyclerView.ViewHolder {

        public TextView  mFirstName, mLastName;

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);

            mFirstName = itemView.findViewById(R.id.first_name);
            mLastName = itemView.findViewById(R.id.last_name);
        }
    }

    public NameListAdapter(Context context) {

        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.single_item, viewGroup, false);
        NameViewHolder nameViewHolder = new NameViewHolder(view);
        return nameViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {

        if (mName != null) {
            Name current = mName.get(position);
            holder.mFirstName.setText(current.getFirst_name());
            holder.mLastName.setText(current.getLast_name());
        }


    }

    void setName(List<Name> name) {
        mName = name;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        //System.out.println(mName.size());
        if (mName != null)
            return mName.size();
        else
            return 0;

    }

}
