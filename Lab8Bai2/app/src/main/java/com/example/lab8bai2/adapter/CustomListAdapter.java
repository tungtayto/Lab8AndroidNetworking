package com.example.lab8bai2.adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab8bai2.R;
import com.example.lab8bai2.fragment.HomeFragment;
import com.example.lab8bai2.model.Items;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private final Activity context;
    private List<Items> items;
    private HomeFragment homeFragment;

    public CustomListAdapter(Activity context, List<Items> items, HomeFragment homeFragment) {
        this.context = context;
        this.items = items;
        this.homeFragment = homeFragment;
    }

    static class ViewHolder {
        public TextView txtTitle;
        public ImageView imageViewDelete, imageViewDone;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        ViewHolder holder = null;
        if (rowView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_row, parent, false);
            holder.txtTitle = (TextView) rowView.findViewById(R.id.textView);
            holder.imageViewDone = (ImageView) rowView.findViewById(R.id.imageView_done);
            holder.imageViewDelete = (ImageView) rowView.findViewById(R.id.imageView_delete);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }
        final Items todoItem = items.get(position);
        holder.txtTitle.setText(todoItem.getTitle());
        boolean isDone = todoItem.isDone();
        if (isDone) {
            holder.imageViewDone.setImageResource(R.drawable.done_all);
            holder.txtTitle.setPaintFlags(holder.txtTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.imageViewDone.setImageResource(R.drawable.done);
            holder.txtTitle.setPaintFlags(holder.txtTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
        holder.imageViewDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (todoItem.isDone)
                    todoItem.isDone = false;
                else
                    todoItem.isDone = true;
                    homeFragment.updateTodoItemDetails(todoItem, position);
            }
        });
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeFragment.deleteTodoItem(position);
            }
        });
        return null;
    }
}
