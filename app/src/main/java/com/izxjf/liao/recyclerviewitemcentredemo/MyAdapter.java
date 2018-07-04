package com.izxjf.liao.recyclerviewitemcentredemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * =======================================
 * 创建日期:2018/7/4 on 20:18
 * 作   者:张辽
 * 邮   箱:Zl13484407109@sina.com
 * 描   述:
 * =======================================
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyVideoHolder> {
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public MyVideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false);
        return new MyVideoHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyVideoHolder holder, final int position) {

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, holder.itemLayout);
                }
            }
        });
        holder.mContent.setText("当前条目 ======" + position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyVideoHolder extends RecyclerView.ViewHolder {

        private TextView mContent;
        private View itemLayout;

        public MyVideoHolder(View itemView) {
            super(itemView);
            mContent = itemView.findViewById(R.id.content);
            itemLayout = itemView.findViewById(R.id.item_layout);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View itemLayout);
    }
}
