package com.banbara23.leanandroidarchitecturecomponents;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

/**
 * ViewModel
 */
public class UserProfileViewModel extends ViewModel {
    private LiveData<User> user;
    UserRepository userRepository;

    @Inject // UserRepository parameter is provided by Dagger 2
    public UserProfileViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void init(String userId) {
        if (this.user != null) {
            return;
        }
        user = userRepository.getUser(userId);
    }

    public LiveData<User> getUser() {
        return user;
    }
}

