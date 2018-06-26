package tech.destinum.recorderis.Data.DAO

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import tech.destinum.recorderis.Data.Entities.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun newUser(user: User)

    @Query("select user_id from users order by user_id desc limit 1")
    fun getLastUser()

}