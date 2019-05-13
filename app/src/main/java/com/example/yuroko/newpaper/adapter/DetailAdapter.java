package com.example.yuroko.newpaper.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.icu.lang.UScript;
import android.provider.ContactsContract;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.yuroko.newpaper.R;
import com.example.yuroko.newpaper.entity.Description;
import com.example.yuroko.newpaper.entity.Detail;
import com.example.yuroko.newpaper.entity.MenuHomePaper;
import com.example.yuroko.newpaper.entity.Share;
import com.example.yuroko.newpaper.entity.Time;
import com.example.yuroko.newpaper.entity.Title;
import com.example.yuroko.newpaper.entity.Toprelated;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<Object> details;
    private OnitemClickLitsener onitemClickLitsener;
    private String name_category;
    private int font_size;

    public DetailAdapter(Context context, List<Object> details, String name_category,int font_size) {
        this.context = context;
        this.details = details;
        this.inflater = LayoutInflater.from(context);
        this.name_category = name_category;
        this.font_size = font_size;
    }

    int TITLE_ITEM = 1;
    int DESCRIPTION_ITEM = 2;
    int IMAGE_ITEM = 3;
    int TEXT_RIGHT_ITEM = 4;
    int TEXT_CENTER_ITEM = 5;
    int TEXT_LEFT_ITEM = 6;
    int VIDEO_ITEM = 7;
    int NEXT_ARTICLE = 8;
    int TOP_RELATED = 9;
    int SHARE = 10;
    int TIME = 11;
    int NULL = 0;


    @Override
    public int getItemViewType(int position) {
      //  Log.d("getItemViewType", "position " + position + " is " + details.get(position).getClass().getSimpleName());

        if (details.get(position) instanceof Title) {
            return TITLE_ITEM;
        } else if (details.get(position) instanceof  Time) {
            return TIME;
        }
        else if (details.get(position)instanceof Share) {
            return SHARE;
        } else if (details.get(position) instanceof Toprelated) {
            return TOP_RELATED;
        } else if (details.get(position) instanceof MenuHomePaper) {
            return NEXT_ARTICLE;
        } else if (details.get(position) instanceof Description) {
            return DESCRIPTION_ITEM;
        } else if (details.get(position) instanceof Detail) {

            Detail detail = (Detail) details.get(position);
            if (detail.getType().equals("image") == true)
                return IMAGE_ITEM;
            else if (detail.getAlign().equals("right") == true) {
                return TEXT_RIGHT_ITEM;
            } else if (detail.getAlign().equals("left") == true) {
                return TEXT_LEFT_ITEM;
            } else if (detail.getAlign().equals("center") == true) {
                return TEXT_CENTER_ITEM;
            } else {
                return VIDEO_ITEM;
            }

        } else {
//            Log.d("ClassCastException", details.get(position).toString());
            return NULL;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TITLE_ITEM) {
            View itemview = inflater.inflate(R.layout.item_title_detail, viewGroup, false);
            return new ViewTitleDetailHolder(itemview);
        } else if (i == DESCRIPTION_ITEM) {
            View itemview = inflater.inflate(R.layout.item_description_detail, viewGroup, false);
            return new ViewDescriptionDetailHolder(itemview);
        } else if (i == IMAGE_ITEM) {
            View itemview = inflater.inflate(R.layout.item_imv_detail, viewGroup, false);
            return new ViewImvDetailHolder(itemview);
        } else if (i == TEXT_LEFT_ITEM) {
            View itemview = inflater.inflate(R.layout.item_left_detail, viewGroup, false);
            return new ViewTxtleftDetailHolder(itemview);
        } else if (i == TEXT_RIGHT_ITEM) {
            View itemview = inflater.inflate(R.layout.item_right_detail, viewGroup, false);
            return new ViewTxtRightDetailHolder(itemview);
        } else if (i == TEXT_CENTER_ITEM) {
            View itemview = inflater.inflate(R.layout.item_center_detail, viewGroup, false);
            return new ViewTxtcenterDetailHolder(itemview);
        } else if (i == NEXT_ARTICLE) {
            View itemview = inflater.inflate(R.layout.item_next_article, viewGroup, false);
            return new ViewnextArticleholder(itemview);
        } else if (i == VIDEO_ITEM) {
            View itemview = inflater.inflate(R.layout.item_video_detail, viewGroup, false);
            return new ViewVideoDetailHolder(itemview);
        } else if (i == TOP_RELATED) {
            View itemview = inflater.inflate(R.layout.item_relatedtop, viewGroup, false);
            return new ViewRelatedTopHolder(itemview);
        } else if (i == SHARE) {
            View itemview = inflater.inflate(R.layout.item_share, viewGroup, false);
            return new ViewShareHolder(itemview);
        } else if( i == TIME)
        {
            View itemview = inflater.inflate(R.layout.item_date_time, viewGroup, false);
            return new ViewTimeHolder(itemview);
        }
        else {
            View itemview = inflater.inflate(R.layout.item_null, viewGroup, false);
            return new ViewNullHolder(itemview);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        Object o = details.get(i);
        int viewtype = getItemViewType(i);
        if (o instanceof Detail) {
            Typeface typeface = ResourcesCompat.getFont(context,R.font.sfprodisplayregular);
            final Detail detail = (Detail) o;
            if (viewtype == TEXT_LEFT_ITEM) {

                ((ViewTxtleftDetailHolder) viewHolder).txtleft_detail.setTextSize(font_size);
                if (detail.getAlign().compareTo("left") == 0 && detail.getType().compareTo("text") == 0) {
                    ((ViewTxtleftDetailHolder) viewHolder).txtleft_detail.setText(detail.getContent());
                    ((ViewTxtleftDetailHolder) viewHolder).txtleft_detail.setTypeface(typeface,Typeface.NORMAL);
                } else if (detail.getAlign().compareTo("left") == 0 && detail.getType().compareTo("text-bold") == 0) {
                    ((ViewTxtleftDetailHolder) viewHolder).txtleft_detail.setText(detail.getContent());
                    ((ViewTxtleftDetailHolder) viewHolder).txtleft_detail.setTypeface(typeface,Typeface.BOLD);
                }
                else {
                    ((ViewTxtleftDetailHolder) viewHolder) .txtleft_detail.setText(detail.getContent());
                    ((ViewTxtleftDetailHolder) viewHolder).txtleft_detail.setTypeface(typeface,Typeface.NORMAL);
                }
            } else if (viewtype == IMAGE_ITEM) {
                Glide.with(context).load(detail.getContent()).into(((ViewImvDetailHolder) viewHolder).imv_detail);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onitemClickLitsener.OnitemImageClicked(detail);
                    }
                });

            } else if (viewtype == TEXT_RIGHT_ITEM) {
                ((ViewTxtRightDetailHolder) viewHolder).txtright_detail.setTextSize(font_size);
                if (detail.getAlign().compareTo("right") == 0 && detail.getType().compareTo("text-bold") == 0) {

                    ((ViewTxtRightDetailHolder) viewHolder).txtright_detail.setText(detail.getContent());
                    ((ViewTxtRightDetailHolder) viewHolder).txtright_detail.setTypeface(typeface,Typeface.BOLD);

                } else if (detail.getAlign().compareTo("right") == 0 && detail.getType().compareTo("text-bold-italic") == 0) {
                    ((ViewTxtRightDetailHolder) viewHolder).txtright_detail.setText(detail.getContent());
                    ((ViewTxtRightDetailHolder) viewHolder).txtright_detail.setTypeface(typeface,Typeface.BOLD_ITALIC);

                }else {
                    ((ViewTxtRightDetailHolder) viewHolder).txtright_detail.setGravity(Gravity.RIGHT);
                    ((ViewTxtRightDetailHolder) viewHolder).txtright_detail.setText(detail.getContent());
                    ((ViewTxtRightDetailHolder) viewHolder).txtright_detail.setTypeface(typeface,Typeface.NORMAL);
                }
            } else if (viewtype == TEXT_CENTER_ITEM) {
                ((ViewTxtcenterDetailHolder) viewHolder).txtcenter_detail.setTextSize(font_size);
                if (detail.getAlign().compareTo("center") == 0 && detail.getType().compareTo("text-desc") == 0) {
                    ((ViewTxtcenterDetailHolder) viewHolder).txtcenter_detail.setText(detail.getContent());
                    ((ViewTxtcenterDetailHolder) viewHolder).txtcenter_detail.setGravity(Gravity.CENTER);
                    ((ViewTxtcenterDetailHolder) viewHolder).txtcenter_detail.setTypeface(typeface,Typeface.ITALIC);


                } else if (detail.getAlign().compareTo("center") == 0 && detail.getType().compareTo("text") == 0) {
                    ((ViewTxtcenterDetailHolder) viewHolder).txtcenter_detail.setText(detail.getContent());
                    ((ViewTxtcenterDetailHolder) viewHolder).txtcenter_detail.setGravity(Gravity.CENTER);
                    ((ViewTxtcenterDetailHolder) viewHolder).txtcenter_detail.setTypeface(typeface,Typeface.NORMAL);

                } else if (detail.getAlign().compareTo("center") == 0 && detail.getType().compareTo("text-bold-italic") == 0) {
                    ((ViewTxtcenterDetailHolder) viewHolder).txtcenter_detail.setText(detail.getContent());
                    ((ViewTxtcenterDetailHolder) viewHolder).txtcenter_detail.setGravity(Gravity.CENTER);
                    ((ViewTxtcenterDetailHolder) viewHolder).txtcenter_detail.setTypeface(typeface,Typeface.BOLD_ITALIC);
                }
                else {
                    ( (ViewTxtcenterDetailHolder) viewHolder).txtcenter_detail.setText(detail.getContent());
                    ((ViewTxtcenterDetailHolder) viewHolder).txtcenter_detail.setTypeface(typeface,Typeface.NORMAL);
                    ((ViewTxtcenterDetailHolder) viewHolder).txtcenter_detail.setGravity(Gravity.CENTER);
                }
            } else if (viewtype == VIDEO_ITEM) {
                Glide.with(context).load(detail.getImg()).into(((ViewVideoDetailHolder) viewHolder).imvvideo_detail);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onitemClickLitsener.OnitemVideoClicked(detail);
                    }
                });
            }

        } else if (viewtype == TOP_RELATED) {

            final Toprelated toprelated = (Toprelated) o;
            if (viewtype == TOP_RELATED) {
                ((ViewRelatedTopHolder) viewHolder).txttitlerelatedtop.setText("•" + " " + toprelated.getTitle());
                ((ViewRelatedTopHolder) viewHolder).txttitlerelatedtop.setTextSize(font_size);

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onitemClickLitsener.OnitemToprelatedClicked(toprelated);
                    }
                });
            }
        } else if (viewtype == NEXT_ARTICLE) {
            final MenuHomePaper menuHomePaper = (MenuHomePaper) o;
            ((ViewnextArticleholder) viewHolder).txtCategoryBottom.setText(name_category);
            ((ViewnextArticleholder) viewHolder).txtCategorynextarticle.setText(name_category);
            ((ViewnextArticleholder) viewHolder).txttitlenextarticle.setText(menuHomePaper.getTitle());
            Glide.with(context).load(menuHomePaper.getThumbnail()).into(((ViewnextArticleholder) viewHolder).imvthumbnailnextarticle);

            if (details.get(i - 1) instanceof Share) {
                ((ViewnextArticleholder) viewHolder).relativeNextArticle.setVisibility(View.VISIBLE);
            } else {
                ((ViewnextArticleholder) viewHolder).relativeNextArticle.setVisibility(View.GONE);
            }
            if (details.get(i - 1) instanceof MenuHomePaper && details.get(i - 2) instanceof Share) {
                ((ViewnextArticleholder) viewHolder).constrainCategorybottom.setVisibility(View.VISIBLE);
            } else {
                ((ViewnextArticleholder) viewHolder).constrainCategorybottom.setVisibility(View.GONE);
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onitemClickLitsener.OnitemNextArticleClicked(menuHomePaper);
                }
            });
        } else if (viewtype == TITLE_ITEM) {
            final Title title = (Title) o;
            ((ViewTitleDetailHolder) viewHolder).txttitle_detail.setText(title.getTitle());
            ((ViewTitleDetailHolder) viewHolder).txttitle_detail.setTextSize(font_size+14);

        } else if (viewtype == DESCRIPTION_ITEM) {
            final Description description = (Description) o;
            ((ViewDescriptionDetailHolder) viewHolder).txtdescription_detail.setText(description.getDescription());
            ((ViewDescriptionDetailHolder) viewHolder).txtdescription_detail.setTextSize(font_size);
        } else if(viewtype == TIME)
        {
            final Time time = (Time) o;

           long time_date = Long.parseLong(time.getTime());
           time_date =time_date *1000;
            Timestamp timestamp = new Timestamp(time_date);
            SimpleDateFormat dateonnewpaper = new SimpleDateFormat("MMMM, dd, yyyy",Locale.US);
            SimpleDateFormat timeonnewpaper = new SimpleDateFormat("mm:ss");
            ((ViewTimeHolder) viewHolder).txttime.setText("Posted on " + dateonnewpaper.format(timestamp) + " at "+timeonnewpaper.format(timestamp));
            ((ViewTimeHolder) viewHolder).txttime.setTextSize(font_size);
        }
        else if (viewtype == SHARE) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onitemClickLitsener.OnitemShareClicked();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public void setOnitemClickListener(OnitemClickLitsener onitemClickListener) {
        this.onitemClickLitsener = onitemClickListener;
    }


    public class ViewTitleDetailHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txttitldetail)
        TextView txttitle_detail;

        public ViewTitleDetailHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewDescriptionDetailHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtdescriptiondetail)
        TextView txtdescription_detail;

        public ViewDescriptionDetailHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewImvDetailHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imvdetail)
        ImageView imv_detail;

        public ViewImvDetailHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewTxtleftDetailHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtleftdetail)
        TextView txtleft_detail;

        public ViewTxtleftDetailHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

    public class ViewTxtcenterDetailHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtcenterdetail)
        TextView txtcenter_detail;

        public ViewTxtcenterDetailHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewTxtRightDetailHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtrightdetail)
        TextView txtright_detail;

        public ViewTxtRightDetailHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewVideoDetailHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imvvideodetail)
        ImageView imvvideo_detail;

        public ViewVideoDetailHolder(View itemView) {
            super(itemView);
            //  imvvideo_detail = itemView.findViewById(R.id.imvvideodetail);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewnextArticleholder extends RecyclerView.ViewHolder {
        @BindView
                (R.id.txtcategorynextarticle)
        TextView txtCategorynextarticle;
        @BindView
                (R.id.txttitleNextArticle)
        TextView txttitlenextarticle;
        @BindView(R.id.imvthumbnailNextArticle)
        ImageView imvthumbnailnextarticle;
        @BindView(R.id.ConstrainCategorybottom)
        ConstraintLayout constrainCategorybottom;
        @BindView(R.id.RelativeNextarticle)
        RelativeLayout relativeNextArticle;
        @BindView(R.id.txtcategorybottom)
        TextView txtCategoryBottom;

        public ViewnextArticleholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            /*txtCategorynextarticle = itemView.findViewById(R.id.txtcategorynextarticle);
            txttitlenextarticle = itemView.findViewById(R.id.txttitleNextArticle);
            imvthumbnailnextarticle = itemView.findViewById(R.id.imvthumbnailNextArticle);

            txtCategoryBottom = itemView.findViewById(R.id.txtcategorybottom);
            constrainCategorybottom = itemView.findViewById(R.id.ConstrainCategorybottom);
            relativeNextArticle = itemView.findViewById(R.id.RelativeNextarticle);*/
        }
    }

    public class ViewRelatedTopHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txttitlerelatedtop)
        TextView txttitlerelatedtop;

        public ViewRelatedTopHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewNullHolder extends RecyclerView.ViewHolder {
        public ViewNullHolder(View itemView) {
            super(itemView);
        }
    }

    public class ViewShareHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imvfb)
        ImageView imvfb;
        @BindView(R.id.imvpinterest)
        ImageView imvpinterest;
        @BindView(R.id.imvtwitter)
        ImageView imvtwitter;
        @BindView(R.id.imvplus)
        ImageView imvplus;

        public ViewShareHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewTimeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txttime)
        TextView txttime;

        public ViewTimeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnitemClickLitsener {
        void OnitemNextArticleClicked(MenuHomePaper menuHomePaper);

        void OnitemShareClicked();

        void OnitemVideoClicked(Detail detail);

        void OnitemToprelatedClicked(Toprelated toprelated);
        void OnitemImageClicked(Detail detail);
    }



}
