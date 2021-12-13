package com.example.td5;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private final List<Contact> mContacts;
    public ContactAdapter(List<Contact> contacts){
        mContacts = contacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = mContacts.get(position);

        TextView fnTextView = holder.fnTextView;
        fnTextView.setText(contact.getPrenom());

        TextView lnTextView = holder.lnTextView;
        lnTextView.setText(contact.getNom());

        ImageView imageView = holder.userImageView ;
        Glide.with(holder.itemView).load(contact.getImageUrl()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView fnTextView;
        public TextView lnTextView;
        public ImageView userImageView;

        public ViewHolder(View itemVeiw){
            super(itemVeiw);

            fnTextView = (TextView) itemVeiw.findViewById(R.id.prenom);
            lnTextView = (TextView) itemVeiw.findViewById(R.id.nom);
            userImageView = (ImageView) itemVeiw.findViewById(R.id.imageUtilisateur);
        }
    }
}
