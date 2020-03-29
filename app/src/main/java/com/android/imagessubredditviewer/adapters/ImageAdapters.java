package com.android.imagessubredditviewer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.imagessubredditviewer.R;
import com.android.imagessubredditviewer.listeners.Listener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * ImageAdapter class for images
 * Set Images into ImageView
 */
public class ImageAdapters extends RecyclerView.Adapter<ImageAdapters.ViewHolder> {
    private ArrayList<String> arrayListImages;
    private Context context;
    private Listener listener;

    public ImageAdapters(ArrayList<String> arrayListImages, Context context, Listener listener) {
        this.arrayListImages = arrayListImages;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_adapter_recyclervoew, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        Picasso.with(context).load(arrayListImages.get(position)).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(arrayListImages.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_adapter);
        }
    }
}
