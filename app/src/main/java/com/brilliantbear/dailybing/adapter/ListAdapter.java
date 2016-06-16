package com.brilliantbear.dailybing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brilliantbear.dailybing.R;
import com.brilliantbear.dailybing.bean.ImagesBean;
import com.brilliantbear.dailybing.utils.DateUtils;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Bear on 2016-6-15.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ImageViewHolder> {

    public interface OnItemClickListener {
        void onClick(View v, int position);
    }

    private Context context;
    private List<ImagesBean> images;
    private OnItemClickListener listener;

    public ListAdapter(Context context, List<ImagesBean> images) {
        this.context = context;
        this.images = images;
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_main, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        ImagesBean imagesBean = images.get(position);
        holder.tvTitle.setText(imagesBean.getCopyright());
        holder.tvDate.setText(DateUtils.changeDateFormat(imagesBean.getStartdate(), "yyyyMMdd", "yyyy年M月d日 E"));

        Glide.with(context).load(imagesBean.getUrl()).into(holder.ivImg);
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImg;
        TextView tvTitle;
        TextView tvDate;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }
}
