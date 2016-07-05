package com.dat.android.horizontalcenterlockrecyclerviewexperiment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dat.android.horizontalcenterlockrecyclerviewexperiment.model.CheeseListItem;
import java.util.List;

/**
 * Created by Nguyen on 7/5/2016.
 */
public class VerticalListAdapter extends RecyclerView.Adapter<VerticalListAdapter.ViewHolder> {
    Context context;
    private List<CheeseListItem> mValues;

    public VerticalListAdapter(Context context, List<CheeseListItem> mValues) {
        this.context = context;
        this.mValues = mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.vertical_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.horizontalList.setLayoutManager(linearLayoutManager);
        HorizontalListAdapter horizontalListAdapter =
            new HorizontalListAdapter(context, mValues.get(position).getCheese());
        holder.horizontalList.setAdapter(horizontalListAdapter);
        holder.horizontalList.addOnScrollListener(new CenterLockListener(0));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public CheeseListItem getItemAt(int position) {
        return mValues.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.horizontal_list)
        protected RecyclerView horizontalList;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
