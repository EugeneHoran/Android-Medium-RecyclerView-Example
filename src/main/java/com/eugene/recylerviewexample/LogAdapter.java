package com.eugene.recylerviewexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private List<Log> mLog;
    private OnRecyclerViewItemClickListener<Log> itemClickListener;
    private OnRecyclerViewLongItemClickListener<Log> itemLongClickListener;
    private int itemLayout;

    public LogAdapter(List<Log> log, int itemLayout) {
        this.mLog = log;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Log item = mLog.get(position);
        holder.itemView.setTag(item);
        holder.text.setText(item.getItem());
    }

    @Override
    public int getItemCount() {
        return mLog.size();
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            Log model = (Log) view.getTag();
            itemClickListener.onItemClick(view, model);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (itemLongClickListener != null) {
            Log model = (Log) v.getTag();
            itemLongClickListener.onItemLongClick(v, model);
        }
        return true;
    }

    public void add(Log item) {
        mLog.add(item);
    }

    public void remove(Log item) {
        int position = mLog.indexOf(item);
        mLog.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.txtItem);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener<Log> listener) { // Click Lister
        this.itemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnRecyclerViewLongItemClickListener<Log> listener) { //Long Click Listener
        this.itemLongClickListener = listener;
    }

}