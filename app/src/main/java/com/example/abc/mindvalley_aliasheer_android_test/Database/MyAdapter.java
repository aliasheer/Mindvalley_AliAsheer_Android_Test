package com.example.abc.mindvalley_aliasheer_android_test.Database;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abc.mindvalley_aliasheer_android_test.Imageloader.ImageLoader;
import com.example.abc.mindvalley_aliasheer_android_test.R;

import java.util.List;

/**
 * Created by Ali Asheer on 12/11/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.FeedListRowHolder> {
    List<Model> feedItemList;
    ImageLoader imageLoader;
    private Context mContext;
    DatabaseHandler db ;

    public MyAdapter(Context context, List<Model> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
        imageLoader = new ImageLoader(mContext);
        db = new DatabaseHandler(mContext);
    }

    @Override
    public FeedListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item, null);
        FeedListRowHolder mh = new FeedListRowHolder(v,mContext);
        return mh;
    }

    @Override
    public void onBindViewHolder(final FeedListRowHolder feedListRowHolder, final int i) {
        final Model feedItem = feedItemList.get(i);
        feedListRowHolder.textViewer.setText(feedItem.getId());

        imageLoader.DisplayImage(feedItem.getUrls().getRegular(), feedListRowHolder.imageView);
        feedListRowHolder.card.setCardBackgroundColor(Color.parseColor(feedItem.getColor()));
    }

    public class FeedListRowHolder extends RecyclerView.ViewHolder {

        public TextView textViewer;
        public CardView card;
        public ImageView imageView;
        Context contxt;

        public FeedListRowHolder(View view, final Context contxt) {

            super(view);
            this.contxt = contxt;
            this.textViewer = (TextView) view.findViewById(R.id.title);
            this.imageView = (ImageView) view.findViewById(R.id.imageView1);
            this.card= (CardView) view.findViewById(R.id.card_view2);

        }
    }

    @Override
    public int getItemCount() {
        return feedItemList.size();
    }

}