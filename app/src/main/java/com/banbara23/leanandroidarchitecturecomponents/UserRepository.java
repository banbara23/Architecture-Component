package com.banbara23.leanandroidarchitecturecomponents;

import android.arch.lifecycle.LiveData;

import java.io.IOException;
import java.util.concurrent.Executor;

import retrofit2.Response;

/**
 * リポジトリクラス
 */
public class UserRepository {
    private Webservice webservice;
    private final UserDao userDao;
    private final Executor executor;

    public UserRepository(Webservice webservice, UserDao userDao, Executor executor) {
        this.webservice = webservice;
        this.userDao = userDao;
        this.executor = executor;
    }

    public LiveData<User> getUser(String userId) {
        refreshUser(userId);
        return userDao.load(userId);
    }

    private void refreshUser(final String userId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                boolean userExists = userDao.hasUser(FRESH_TIMEOUT);
                if (!userExists) {
                    try {
                        Response<User> response = webservice.getUser(userId).execute();
                        userDao.save(response.body());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
