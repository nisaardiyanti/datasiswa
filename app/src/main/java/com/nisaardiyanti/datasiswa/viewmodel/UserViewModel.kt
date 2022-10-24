package com.nisaardiyanti.datasiswa.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nisaardiyanti.datasiswa.data.UserDatabase
import com.nisaardiyanti.datasiswa.repository.UserRepository
import com.nisaardiyanti.datasiswa.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(
            //validasi mengambil database User
            application
        ).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        //function adduser
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User){
        //fungsi updateUser
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
            //merupakan repository updateUser

        }
    }

    fun deleteUser(user: User){
        //fungsi deleteUser
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
            //merupakan repository deleteAllUsers
        }
    }

    fun deleteAllUsers(){
        //function deleteAllUsers
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
            //merupakan repository deleteAllUsers
        }
    }

}