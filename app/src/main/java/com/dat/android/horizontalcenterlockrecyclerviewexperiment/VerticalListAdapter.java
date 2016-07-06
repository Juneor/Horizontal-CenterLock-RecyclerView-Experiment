package com.dat.android.horizontalcenterlockrecyclerviewexperiment;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.cheeseType.setText(mValues.get(position).getName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.horizontalList.setLayoutManager(linearLayoutManager);
        HorizontalListAdapter horizontalListAdapter =
            new HorizontalListAdapter(context, mValues.get(position).getCheese());
        holder.horizontalList.setAdapter(horizontalListAdapter);
      /*  int spacing = (int) context.getResources().getDimension(R.dimen.center_lock_padding);
        holder.horizontalList.addItemDecoration(new HorizontalSpaceItemDecoration(spacing));*/
        setupLockCenter(holder);
        holder.horizontalList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                holder.curPosition = holder.horizontalList.getScrollX();
                Log.d("newState",
                    "newState:" + recyclerView.getRight() + "  " + recyclerView.getTranslationX());
            }
        });
        if (holder.curPosition != -1) {
            holder.horizontalList.scrollToPosition(holder.curPosition);
        }
    }

    private void setupLockCenter(final ViewHolder holder) {
        Display display = ((MainActivity) context).getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        int center = size.x / 2;
        int padding = (int) context.getResources().getDimension(R.dimen.center_lock_padding);
        holder.horizontalList.setPadding(padding, 0, padding, 0);

        holder.horizontalList.addOnScrollListener(new CenterLockListener(center));
        // Log.d("Values:", "center - itemWidth - padding: " + center + " - " + itemWidth);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public CheeseListItem getItemAt(int position) {
        return mValues.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cheeseType)
        protected TextView cheeseType;
        @Bind(R.id.horizontal_list)
        protected RecyclerView horizontalList;
        int curPosition = -1;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int horizontalSpace;

        public HorizontalSpaceItemDecoration(int horizontalSpace) {
            this.horizontalSpace = horizontalSpace;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
            RecyclerView.State state) {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = horizontalSpace;
                outRect.right = 0; //dont forget about recycling...
            }
            if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1) {
                outRect.right = horizontalSpace;
                outRect.left = 0;
            }
        }
    }
}
