package com.nisaardiyanti.datasiswa.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nisaardiyanti.datasiswa.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)
//insert digunakan untuk menambahkan data
    @Update
    suspend fun updateUser(user: User)
//update digunakan untuk mengubah data
    @Delete
    suspend fun deleteUser(user: User)
//delete digunakan untuk menghapus data
    @Query("DELETE FROM user_table")
    //query yang digunakan untuk menghapus data dari tabel user_table
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    //query untuk menampilkan data dari tabel user_tabel
    fun readAllData(): LiveData<List<User>>

}