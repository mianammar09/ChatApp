package com.imianammar.talknow.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imianammar.talknow.databinding.ItemContainerRecievedMessageBinding;
import com.imianammar.talknow.databinding.ItemContainerSentMessageBinding;
import com.imianammar.talknow.models.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<ChatMessage> chatMessages;
    private Bitmap recieverProfileImage;
    private final String senderId;

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECIEVED = 2;


    public void setRecieverProfileImage(Bitmap bitmap){
        recieverProfileImage = bitmap;
    }

    public ChatAdapter(List<ChatMessage> chatMessages, Bitmap recieverProfileImage, String senderId) {
        this.chatMessages = chatMessages;
        this.recieverProfileImage = recieverProfileImage;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_SENT){
            return new SendMessageViewHolder(ItemContainerSentMessageBinding.inflate(LayoutInflater
                    .from(parent.getContext()),
                    parent,
            false));
        }
        else{
            return new RecievedMessageViewHolder(
                    ItemContainerRecievedMessageBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent,
                            false)
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(getItemViewType(position) == VIEW_TYPE_SENT){
            ((SendMessageViewHolder) holder).setData(chatMessages.get(position));
        }
        else{
            ((RecievedMessageViewHolder) holder).setData(chatMessages.get(position), recieverProfileImage);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(chatMessages.get(position).senderId.equals(senderId)){
            return VIEW_TYPE_SENT;
        }
        else {
            return VIEW_TYPE_RECIEVED;
        }
    }

    static class SendMessageViewHolder extends RecyclerView.ViewHolder{
        private final ItemContainerSentMessageBinding binding;

        SendMessageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding){
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }
        void setData(ChatMessage chatMessage){
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
        }
    }

    static class RecievedMessageViewHolder extends RecyclerView.ViewHolder{

        private final ItemContainerRecievedMessageBinding binding;

        RecievedMessageViewHolder(ItemContainerRecievedMessageBinding itemContainerRecievedMessageBinding){
            super(itemContainerRecievedMessageBinding.getRoot());
            binding = itemContainerRecievedMessageBinding;
        }

        void setData(ChatMessage chatMessage, Bitmap recieverProfileImage){
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
            if (recieverProfileImage != null){
                binding.imageProfile.setImageBitmap(recieverProfileImage);
            }
        }

    }
}
