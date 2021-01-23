package com.example.decero.Service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.decero.Models.User;
import com.example.decero.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {


    List<User> listaUsuarios;

    public AdapterDatos(List<User> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.asignarDatos(listaUsuarios.get(position));
        Picasso.get().load(listaUsuarios.get(position).getAvatar_url()).into(holder.profile);

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        ImageView profile;
        TextView userName;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            profile = (ImageView) itemView.findViewById(R.id.profileImage);
            userName = (TextView) itemView.findViewById(R.id.userName);

        }

        public void asignarDatos(User user) {

            userName.setText(user.getLogin());


        }
    }
}
