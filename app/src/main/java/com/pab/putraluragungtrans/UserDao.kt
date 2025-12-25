package com.pab.putraluragungtrans

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getUserById(id: Int): User?

    @Query("SELECT * FROM User WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM User WHERE email = :email")
    fun getUserByEmailFlow(email: String): Flow<User?>

    @Update
    suspend fun updateUser(user: User)

    @Query("UPDATE User SET firstName = :firstName, lastName = :lastName, phoneNumber = :phoneNumber, nik = :nik WHERE email = :email")
    suspend fun updateUserProfile(firstName: String, lastName: String, phoneNumber: String, nik: String, email: String): Int

    @Query("UPDATE User SET password = :password WHERE email = :email")
    suspend fun updatePassword(password: String, email: String): Int

    @Delete
    suspend fun deleteUser(user: User)
}