package com.example.odmen.chitay4ch.Adapter;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.odmen.chitay4ch.Groups.Group;
import com.example.odmen.chitay4ch.R;
import com.example.odmen.chitay4ch.Users.Users;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by odmen on 11.11.2017.
 */

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.ViewHolder> {
    List<Users> itemss;
    String[] menu = new String[]{"Удалить"};
    UsMyonClick click;

    public interface UsMyonClick {
        void click(Users users);

        void clickstart(Users users);
    }


    public AdapterUsers(List<Users> items, UsMyonClick click) {
        this.itemss = items;
        this.click = click;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemuser, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Users item = itemss.get(position);
        holder.name.setText(item.getFirst_name()+" "+item.getLast_name());
        Picasso.with(holder.image.getContext()).load(item.getPhoto_200()).into(holder.image);

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
            name = (TextView) itemView.findViewById(R.id.usnamepublic);
            image = (ImageView) itemView.findViewById(R.id.usimageViewgro);
            idpublic = (TextView) itemView.findViewById(R.id.usname2);

            itemView.setLongClickable(true);

        }
    }
}
