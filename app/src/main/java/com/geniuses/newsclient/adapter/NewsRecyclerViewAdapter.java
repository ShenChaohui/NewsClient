package com.geniuses.newsclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geniuses.newsclient.R;
import com.geniuses.newsclient.entity.NewsModel;
import com.geniuses.newsclient.util.ImageUtils;

import java.util.List;

/**
 * Created by Sch.
 * Date: 2019/4/28
 * description:
 */
public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<NewsModel> mNewsModels;

    private OnRecyclerItemClickListener mOnRecyclerItemClickListener;

    public NewsRecyclerViewAdapter(Context context, List<NewsModel> newsModels) {
        mContext = context;
        mNewsModels = newsModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycleview_news, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        NewsModel newsModel = mNewsModels.get(position);
        ImageUtils.loadNetResource(newsModel.getPic(), holder.mImageView);
        holder.mTextViewTime.setText(newsModel.getTime());
        holder.mTextViewTitle.setText(newsModel.getTitle());
        if (mOnRecyclerItemClickListener != null) {
            holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnRecyclerItemClickListener.onItemClick(v, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mNewsModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private ImageView mImageView;
        private TextView mTextViewTime;
        private TextView mTextViewTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = itemView.findViewById(R.id.ll_item_news);
            mImageView = itemView.findViewById(R.id.iv_item_news_pic);
            mTextViewTime = itemView.findViewById(R.id.tv_item_news_time);
            mTextViewTitle = itemView.findViewById(R.id.tv_item_news_title);
        }
    }

    /**
     * 处理item的点击事件,因为recycler没有提供单击事件,所以只能自己写了
     */
    public interface OnRecyclerItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        mOnRecyclerItemClickListener = onRecyclerItemClickListener;
    }
}
