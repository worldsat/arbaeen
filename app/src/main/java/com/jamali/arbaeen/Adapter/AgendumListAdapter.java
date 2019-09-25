package com.jamali.arbaeen.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jamali.arbaeen.Domain.MiddleList;
import com.jamali.arbaeen.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AgendumListAdapter extends RecyclerView.Adapter<AgendumListAdapter.ViewHolder> {

    private final ArrayList<MiddleList> array_object;

    public AgendumListAdapter(ArrayList<MiddleList> result) {

        this.array_object = result;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


    }

    @Override
    public int getItemCount() {
        return array_object == null ? 0 : array_object.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        private ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);

        }
    }
}