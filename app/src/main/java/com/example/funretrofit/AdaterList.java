package com.example.funretrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdaterList extends RecyclerView.Adapter<AdaterList.ListHolder> {

    private List<ResponseList> listData;
    private Context context;

    public AdaterList(List<ResponseList> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaterList.ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.raw_list, viewGroup, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaterList.ListHolder listHolder, int i) {
        listHolder.tvAlamat.setText(listData.get(i).getAlamat());
        listHolder.tvNama.setText(listData.get(i).getNama());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvAlamat;
        public ListHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvName);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
        }
    }
}
