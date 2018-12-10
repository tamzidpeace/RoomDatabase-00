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
    private Context context;
    private final LayoutInflater mLayoutInflater;

    public class NameViewHolder extends RecyclerView.ViewHolder {

        public TextView mSerialNo, mFirstName, mLastName;

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);

            mSerialNo = itemView.findViewById(R.id.id_no);
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
    public void onBindViewHolder(@NonNull NameViewHolder nameViewHolder, int i) {

        if (mName != null) {
            Name findName = mName.get(i);
            String b = findName.getFirst_name();
            String c = findName.getLast_name();
            int a = findName.getId();
            String s = Integer.toString(a);

            nameViewHolder.mSerialNo.setText(s);
            nameViewHolder.mFirstName.setText(b);
            nameViewHolder.mLastName.setText(c);
        } else
            Toast.makeText(context, "No Word", Toast.LENGTH_SHORT).show();


    }

    public void setName(List<Name> name) {
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
