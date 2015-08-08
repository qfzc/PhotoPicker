package me.iwf.photopicker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.R;
import me.iwf.photopicker.entity.PhotoDirectory;

/**
 * Created by donglua on 15/6/28.
 */
public class PopupDirectoryListAdapter extends BaseAdapter {

    private Context context;

    private List<PhotoDirectory> directories = new ArrayList<>();

    private LayoutInflater mLayoutInflater;
    private int mImageSize;

    public PopupDirectoryListAdapter(Context context, List<PhotoDirectory> directories) {
        this.context = context;
        this.directories = directories;

        mLayoutInflater = LayoutInflater.from(context);
        mImageSize = context.getResources().getDimensionPixelOffset(R.dimen.folder_cover_size);
    }


    @Override
    public int getCount() {
        return directories.size();
    }


    @Override
    public PhotoDirectory getItem(int position) {
        return directories.get(position);
    }


    @Override
    public long getItemId(int position) {
        return directories.get(position).hashCode();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_directory, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.bindData(directories.get(position));

        return convertView;
    }


    private class ViewHolder {

        public ImageView ivCover;
        public TextView tvName;

        public ViewHolder(View rootView) {
            ivCover = (ImageView) rootView.findViewById(R.id.iv_dir_cover);
            tvName = (TextView) rootView.findViewById(R.id.tv_dir_name);
        }

        public void bindData(PhotoDirectory directory) {
            Picasso.with(context)
                    .load(new File(directory.getCoverPath()))
                    .placeholder(R.drawable.default_error)
                    .error(R.drawable.default_error)
                    .resize(mImageSize, mImageSize)
                    .centerCrop()
                    .into(ivCover);

            tvName.setText(directory.getName());
        }
    }

}
