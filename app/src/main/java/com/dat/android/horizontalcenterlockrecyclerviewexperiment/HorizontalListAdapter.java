package com.dat.android.horizontalcenterlockrecyclerviewexperiment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by Nguyen on 7/5/2016.
 */
public class HorizontalListAdapter extends RecyclerView.Adapter<HorizontalListAdapter.ViewHolder> {
    Context context;
    private List<String> mValues;

    public HorizontalListAdapter(Context context, List<String> mValues) {
        this.context = context;
        this.mValues = mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.horizontal_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mValues.get(position));
        Glide.with(holder.avatar.getContext())
            .load(Cheeses.getRandomCheeseDrawable())
            .fitCenter()
            .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public String getValueAt(int position) {
        return mValues.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.avatar)
        protected ImageView avatar;
        @Bind(R.id.name)
        protected TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
