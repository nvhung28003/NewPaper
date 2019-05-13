package com.example.yuroko.newpaper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuroko.newpaper.R;
import com.example.yuroko.newpaper.entity.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Category> categories;
    private LayoutInflater layoutInflater;
    private OnitemClickListener onitemClickListener;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
        layoutInflater = LayoutInflater.from(context);
    }

    int CATEGORY_TOP = 1;
    int DEVISION_CATEGORIES = 2;
    int CATEGORY_BOT = 3;

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 5) {
            return DEVISION_CATEGORIES;
        } else if (position == 1 || position == 2 || position == 3 || position == 4) {
            return CATEGORY_TOP;
        } else return CATEGORY_BOT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == CATEGORY_BOT) {
            View itemview = layoutInflater.inflate(R.layout.item_category, viewGroup, false);
            return new ViewHolder(itemview);
        } else if (i == CATEGORY_TOP) {
            View itemview = layoutInflater.inflate(R.layout.item_category, viewGroup, false);
            return new ViewHolder(itemview);
        } else {
            View itemview = layoutInflater.inflate(R.layout.item_division_categories, viewGroup, false);
            return new ViewDivisisonHolder(itemview);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final Category category = categories.get(position);
        int viewtype = getItemViewType(position);
        if (viewtype == CATEGORY_BOT) {


            if (category.getTitle().equals("Home")) {
                Glide.with(context).load(R.drawable.home).into(((ViewHolder) viewHolder).imvIconCategoryInMore);
            } else if (category.getIcon() == null || category.getIcon().equals("null")) {
                Log.d("getIcon", "getIcon CATEGORY_BOT null = " + category.getIcon());
                //Glide.with(context).load(R.drawable.hotfocus).into(((ViewHolder) viewHolder).imvIconCategoryInMore);
                ((ViewHolder) viewHolder).imvIconCategoryInMore.setImageResource(R.drawable.hotfocus);
            } else {
                Log.d("getIcon", "getIcon CATEGORY_BOT = " + category.getIcon());
                Glide.with(context).load(category.getIcon()).into(((ViewHolder) viewHolder).imvIconCategoryInMore);
            }

            ((ViewHolder) viewHolder).txtCategoryInMore.setText(category.getTitle());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onitemClickListener.OnitemClickedbot(category);
                }
            });
        } else if (viewtype == CATEGORY_TOP) {
            if (category.getTitle().equals("Home")) {
                Glide.with(context).load(R.drawable.home).into(((ViewHolder) viewHolder).imvIconCategoryInMore);
            }
            /*else {
                Glide.with(context).load(category.getIcon()).into(((ViewHolder) viewHolder).imvIconCategoryInMore);
            }*/
            else if (category.getIcon() == null|| category.getIcon().equals("null")) {
                Log.d("getIcon", "getIcon CATEGORY_TOP = " + category.getIcon());
                //Glide.with(context).load(R.drawable.hotfocus).into(((ViewHolder) viewHolder).imvIconCategoryInMore);
                ((ViewHolder) viewHolder).imvIconCategoryInMore.setImageResource(R.drawable.hotfocus);
            } else {
                Log.d("getIcon", "getIcon CATEGORY_TOP = " + category.getIcon());
                Glide.with(context).load(category.getIcon()).into(((ViewHolder) viewHolder).imvIconCategoryInMore);
            }
            ((ViewHolder) viewHolder).txtCategoryInMore.setText(category.getTitle());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onitemClickListener.OnitemClickedtop(category);
                }
            });
        } else {
            ((ViewDivisisonHolder) viewHolder).txtdivisionCategories.setText(category.getTitle());

        }

    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setOnItemClickListener(OnitemClickListener onItemClickListener) {
        this.onitemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imvIconCategoryInMore;
        private TextView txtCategoryInMore;

        public ViewHolder(View itemView) {
            super(itemView);
            imvIconCategoryInMore = itemView.findViewById(R.id.imvIconCategoryInMore);
            txtCategoryInMore = itemView.findViewById(R.id.txtCategoryInMore);
        }
    }

    public class ViewDivisisonHolder extends RecyclerView.ViewHolder {
        private TextView txtdivisionCategories;

        public ViewDivisisonHolder(View itemView) {
            super(itemView);
            txtdivisionCategories = itemView.findViewById(R.id.txtdivisionCategories);
        }
    }

    public interface OnitemClickListener {
        void OnitemClickedtop(Category category);

        void OnitemClickedbot(Category category);
    }
}
