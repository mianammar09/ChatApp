package com.imianammar.talknow.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.imianammar.talknow.databinding.ItemContainerUserBinding;
import com.imianammar.talknow.listeners.UserListener;
import com.imianammar.talknow.models.User;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.userViewHolder>{
    private final List<User> users;
    private final UserListener userListener;

    public UsersAdapter(List<User> users , UserListener userListener) {
        this.users = users;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemContainerUserBinding itemContainerUserBinding =ItemContainerUserBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
                return new userViewHolder(itemContainerUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {

        holder.setUserData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class userViewHolder extends RecyclerView.ViewHolder{

        ItemContainerUserBinding binding;
        userViewHolder(ItemContainerUserBinding itemContainerUserBinding){
            super(itemContainerUserBinding.getRoot());
            binding = itemContainerUserBinding;
        }

        void setUserData(User user){
            binding.textName.setText(user.name);
            binding.textEmail.setText(user.email);
            binding.imageProfile.setImageBitmap(getUserImage(user.image));
            binding.getRoot().setOnClickListener(v -> userListener.onUserClicked(user));
        }
    }
    private Bitmap getUserImage(String encodedImage){
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

    }
}
