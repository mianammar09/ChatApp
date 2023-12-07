package com.imianammar.talknow.listeners;

import com.imianammar.talknow.models.User;

public interface UserListener {

    default void onUserClicked(User user){

    }
}
