package com.numus.budgee;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MusicaViewHolder>{

    private ArrayList<Transaction> data;

    public TransactionAdapter(ArrayList<Transaction> data) {
        this.data = data;
    }

    @Override
    public MusicaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MusicaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_musica, parent, false));
    }

    @Override
    public void onBindViewHolder(MusicaViewHolder holder, int position) {
        Transaction transaction = data.get(position);
        holder.tv_name.setText(transaction.getName());
        holder.tv_date.setText(transaction.getDate());

        Transaction.Category category = transaction.getCategory();
        switch (category){
            case PET:
                holder.cat_lay.setBackgroundColor(Color.parseColor("#091b59"));
                holder.cat_img.setImageResource(R.drawable.baseline_pets_24);
                break;
            case EXTRA:
                holder.cat_lay.setBackgroundColor(Color.parseColor("#4e94ff"));
                holder.cat_img.setImageResource(R.drawable.baseline_account_balance_wallet_24);
                break;
            case GROCERIES:
                holder.cat_lay.setBackgroundColor(Color.parseColor("#ef4c57"));
                holder.cat_img.setImageResource(R.drawable.baseline_shopping_cart_24);
                break;
        }

        if(transaction.getQuantity()>0){
            holder.tv_qty.setTextColor(Color.parseColor("#79d388"));
            holder.tv_qty.setText("+$" + Double.toString(transaction.getQuantity()));
        }else{
            holder.tv_qty.setTextColor(Color.parseColor("#e05a5a"));
            holder.tv_qty.setText("-$" + Double.toString(Math.abs(transaction.getQuantity())));
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MusicaViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name;
        TextView tv_qty;
        TextView tv_date;
        LinearLayout cat_lay;
        ImageView cat_img;

        public MusicaViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_qty = (TextView) itemView.findViewById(R.id.tv_qty);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            cat_lay = itemView.findViewById(R.id.categoryLay);
            cat_img = itemView.findViewById(R.id.img_cat);
        }
    }
}