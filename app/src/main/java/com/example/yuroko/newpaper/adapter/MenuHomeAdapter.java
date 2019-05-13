package com.example.yuroko.newpaper.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
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

public class MenuHomeAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private List<MenuHomePaper> menuHomePapers;
    private OnitemClickListener onitemClickListener;
    public MenuHomeAdapter(Context context,List<MenuHomePaper> menuHomePapers)
    {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.menuHomePapers = menuHomePapers;

    }
    int firstitem_menu = 1;
    int enditem_menu =2;
    int item_menu = 3;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == item_menu)
       {
            View itemview = inflater.inflate(R.layout.item_paper,viewGroup,false);
            return new Viewholder(itemview);
        }
        else if(i == enditem_menu){
            View itemview = inflater.inflate(R.layout.item_bottomloadmore,viewGroup,false);
            return new Moreholder(itemview);
        }
        else {
            View itemview = inflater.inflate(R.layout.item_first_paper,viewGroup,false);
            return new Viewholder(itemview);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final MenuHomePaper menuHomePaper = menuHomePapers.get(i);

        if(getItemViewType(i) == item_menu)
        {
            Glide.with(context).load(menuHomePaper.getThumbnail()).into( ((Viewholder) viewHolder).imvthumbnail);
            ((Viewholder) viewHolder).txttitle.setText(menuHomePaper.getTitle());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onitemClickListener.OnitemClicked(menuHomePaper);
                }
            });

        }
        else if (getItemViewType(i) == firstitem_menu)
        {
            Glide.with(context).load(menuHomePaper.getThumbnail()).into(((Viewholder) viewHolder).imvfirstthumbnail);
            ((Viewholder) viewHolder).txtfirsttitle.setText(menuHomePaper.getTitle());
            ((Viewholder) viewHolder).txtfirstdescription.setText(menuHomePaper.getDescription());


            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onitemClickListener.OnitemClicked(menuHomePaper);

                }
            });
        }
        else {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     onitemClickListener.OnitemMoreClicked(menuHomePaper);
                }
            });

        }


    }

    @Override
    public int getItemViewType(int position) {
        if(position ==0) return firstitem_menu;
        else if (position == menuHomePapers.size()-1) return enditem_menu;
        else {
            return item_menu;
        }

    }

    @Override
    public int getItemCount() {
        return menuHomePapers.size();
    }

    public void setOnitemClickListener (OnitemClickListener onitemClickListener)
    {
        this.onitemClickListener = onitemClickListener;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView imvthumbnail;
        private TextView txttitle;
        private ImageView imvfirstthumbnail;
        private TextView txtfirsttitle;
        private TextView txtfirstdescription;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imvthumbnail = itemView.findViewById(R.id.imvthumbnail);
            txttitle = itemView.findViewById(R.id.txttitle);
            imvfirstthumbnail = itemView.findViewById(R.id.imvfirstthumbnail);
            txtfirsttitle = itemView.findViewById(R.id.firstTitle);
            txtfirstdescription = itemView.findViewById(R.id.firstdescription);
        }
    }
    public class Moreholder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        public Moreholder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearLoadMore);
        }
    }

    public interface OnitemClickListener{
        void OnitemClicked(MenuHomePaper menuHomePaper);
        void OnitemMoreClicked(MenuHomePaper menuHomePaper);
    }
}
