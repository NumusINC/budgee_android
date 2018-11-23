package com.numus.budgee;

import android.content.Context;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MusicaViewHolder>{


    private ArrayList<Transaction> data;
    Context context;
    DataManager dataManager;

    public TransactionAdapter(ArrayList<Transaction> data) {
        this.data = data;
    }

    @Override
    public MusicaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MusicaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_transaction, parent, false));
    }

    @Override
    public void onBindViewHolder(MusicaViewHolder holder, final int position) {
        Transaction transaction = data.get(position);
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        holder.tv_name.setText(transaction.getName());
        holder.tv_date.setText(transaction.getDate());

        dataManager = new DataManager(context);

        Transaction.Category category = transaction.getCategory();
        switch (category){
            case PET:
                holder.cat_lay.setBackgroundColor(Color.parseColor("#20CC97"));
                holder.img_cat.setImageResource(R.drawable.baseline_pets_24);
                break;
            case PAYROLL:
                holder.cat_lay.setBackgroundColor(Color.parseColor("#4e94ff"));
                holder.img_cat.setImageResource(R.drawable.baseline_account_balance_wallet_24);
                break;
            case GROCERIES:
                holder.cat_lay.setBackgroundColor(Color.parseColor("#ef4c57"));
                holder.img_cat.setImageResource(R.drawable.baseline_shopping_cart_24);
                break;
            case TAXES:
                holder.cat_lay.setBackgroundColor(Color.parseColor("#39374A"));
                holder.img_cat.setImageResource(R.drawable.baseline_domain_48);
                break;
            case FOOD:
                holder.cat_lay.setBackgroundColor(Color.parseColor("#FFA917"));
                holder.img_cat.setImageResource(R.drawable.baseline_fastfood_24);
                break;
            case FUN:
                holder.cat_lay.setBackgroundColor(Color.parseColor("#FFD103"));
                holder.img_cat.setImageResource(R.drawable.baseline_local_play_24);
                break;
        }

        if(transaction.getType().equals("in")){
            holder.tv_qty.setTextColor(Color.parseColor("#79d388"));
            holder.tv_qty.setText("+$" + formatter.format(transaction.getQuantity()));

        }else{
            holder.tv_qty.setTextColor(Color.parseColor("#e05a5a"));
            holder.tv_qty.setText("-$" + formatter.format(transaction.getQuantity()));
        }

        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(holder.tv_qty, 15, 17, 1,
                TypedValue.COMPLEX_UNIT_DIP);


        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Transaction " + data.get(position).getName() + " removed", Toast.LENGTH_SHORT).show();
                removeItem(data.get(position));
            }
        });

    }

    private void removeItem(Transaction infoData) {
        int currPosition = data.indexOf(infoData);
        notifyItemRemoved(currPosition);
        notifyDataSetChanged();
        data.remove(currPosition);
        dataManager.setTransactionArray(data);
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
        ImageView img_cat, img_edit;

        public MusicaViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_qty = itemView.findViewById(R.id.tv_qty);
            tv_date = itemView.findViewById(R.id.tv_date);
            cat_lay = itemView.findViewById(R.id.categoryLay);
            img_cat = itemView.findViewById(R.id.img_cat);
            img_edit = itemView.findViewById(R.id.edit);
        }
    }
}