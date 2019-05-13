package com.example.yuroko.newpaper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuroko.newpaper.R;
import com.example.yuroko.newpaper.entity.MenuHomePaper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private List<MenuHomePaper> searchs;
    private OnitemClickListener onitemClickListener ;

    public SearchAdapter(Context context,List<MenuHomePaper> searchs)
    {
       this.context = context;
        this.searchs = searchs;
        this.inflater = LayoutInflater.from(context);



    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview = inflater.inflate(R.layout.item_paper,viewGroup,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final MenuHomePaper search = searchs.get(position);
        ((ViewHolder) viewHolder).txttitlesearch.setText(search.getTitle());
        Glide.with(context).load(search.getThumbnail()).into(((ViewHolder) viewHolder).imvthumbnailsearch);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onitemClickListener.OnitemClicked(search);
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchs.size();
    }
    public void setOnitemClickListener (OnitemClickListener onitemClickListener)
    {
        this.onitemClickListener = onitemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txttitle)
        TextView txttitlesearch;
        @BindView(R.id.imvthumbnail)
        ImageView imvthumbnailsearch;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface OnitemClickListener
    {
        void OnitemClicked(MenuHomePaper search);
    }
    public interface OnLoadMoreListener{
        void onLoadmore(MenuHomePaper search);
    }

}
