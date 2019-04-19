package com.example.pritam.recyclerviewusinggridlayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>  {

    private Context context;
    private List<Photo> personUtils;

    public CustomRecyclerAdapter(Context context, List personUtils) {
        this.context = context;
        this.personUtils = personUtils;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(personUtils.get(position));

        Photo pu = personUtils.get(position);
        holder.title.setText(pu.getTitle ());
        new DownloadImageTask(holder.imageView).execute(personUtils.get(position).getThumbnailUrl ());
    }

    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView tm_url;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.pNametxt);
            tm_url = (TextView) itemView.findViewById(R.id.pJobProfiletxt);
            imageView =(ImageView) itemView.findViewById (R.id.userImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Photo cpu = (Photo) view.getTag();
                    Intent intent =new Intent (MainActivity.getAppContext (),SecondActivity.class);
                    intent.putExtra ("image",cpu.getThumbnailUrl ());
                    context.startActivity (intent);

                }
            });

        }
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
                ImageView bmImage;

                public DownloadImageTask(ImageView bmImage) {
                    this.bmImage = bmImage;
                }
                protected Bitmap doInBackground(String... urls) {
                    String urldisplay = urls[0];
                    Bitmap mIcon = null;
                    try {
                        InputStream in = new java.net.URL(urldisplay).openStream();
                        mIcon = BitmapFactory.decodeStream(in);
                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }
                    return mIcon;
                }

                protected void onPostExecute(Bitmap result) {
                    bmImage.setImageBitmap(result);
                }
            }
}
