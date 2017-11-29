package com.banbara23.leanandroidarchitecturecomponents;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

public interface UserDao {

    @Insert(onConflict = REPLACE)
    void save(User user);

    @Query("SELECT * FROM user WHERE id = :userId")
    LiveData<User> load(String userId);
}
