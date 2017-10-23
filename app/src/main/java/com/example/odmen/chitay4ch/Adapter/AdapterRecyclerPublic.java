package com.example.odmen.chitay4ch.Adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.odmen.chitay4ch.Groups.Group;
import com.example.odmen.chitay4ch.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by odmen on 11.10.2017.
 */

public class AdapterRecyclerPublic extends RecyclerView.Adapter<AdapterRecyclerPublic.ViewHolder> {
    List<Group> itemss;
    String[] menu = new String[]{"Удалить"};
    MyonClick click;

    public interface MyonClick {
        void click(Group group);
        void clickstart(Group group);
    }






    public AdapterRecyclerPublic(List<Group> items, MyonClick click) {
        this.itemss = items;
        this.click = click;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itempublic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Group item = itemss.get(position);
        holder.name.setText(item.getName());
        holder.idpublic.setText("@"+String.valueOf(item.getScreen_name()));
        Picasso.with(holder.image.getContext()).load(item.getPhoto_50()).into(holder.image);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setItems(menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        click.click(item);
                    }
                });
                builder.show();
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.clickstart(item);
            }
        });




    }

    @Override
    public int getItemCount() {
        return itemss.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView idpublic;
        private ImageView image;

        public ViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.namepublic);
            image = (ImageView) itemView.findViewById(R.id.imageViewgro);
            idpublic= (TextView) itemView.findViewById(R.id.name2);

            itemView.setLongClickable(true);

        }
    }
}
