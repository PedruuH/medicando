package com.medicando.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.medicando.R;
import com.medicando.model.Status;

import java.util.List;

public class AdapterDetalhado extends RecyclerView.Adapter<AdapterDetalhado.HolderDetalhado> {

    private List<Status> listaStatus;

    public AdapterDetalhado(List<Status> listaStatus) {
        this.listaStatus = listaStatus;
    }

    @NonNull
    @Override
    public HolderDetalhado onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_historico_detalhado, parent, false);
        return new HolderDetalhado(item);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDetalhado holder, int position) {
        Status status = listaStatus.get(position);
        holder.textStatus.setText(status.getStatusFase());
        holder.imageStatus.setImageResource(status.getStatusID());

    }

    @Override
    public int getItemCount() {
        return listaStatus != null ? listaStatus.size() : 0;
    }

    public class HolderDetalhado extends RecyclerView.ViewHolder {

        private TextView textStatus;
        private ImageView imageStatus;

        public HolderDetalhado(View itemView) {
            super(itemView);
            textStatus = itemView.findViewById(R.id.textStatus);
            imageStatus = itemView.findViewById(R.id.imageStatus);
        }
    }
}
