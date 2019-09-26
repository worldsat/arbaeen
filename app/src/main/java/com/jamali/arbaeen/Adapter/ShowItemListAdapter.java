package com.jamali.arbaeen.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jamali.arbaeen.Domain.ShowList;
import com.jamali.arbaeen.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShowItemListAdapter extends RecyclerView.Adapter<ShowItemListAdapter.ViewHolder> {

    private final ArrayList<ShowList> array_object;
    private int List;

    public ShowItemListAdapter(ArrayList<ShowList> result,Integer List) {

        this.array_object = result;
        this.List=List;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.title.setText((position+1) + " " + array_object.get(position).getSubject());

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