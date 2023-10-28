package com.nabila.storyapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nabila.storyapp.data.model.UserModel
import com.nabila.storyapp.data.repository.StoryRepository
import kotlinx.coroutines.launch
import java.io.File

class MainViewModel(private val repository: StoryRepository) : ViewModel() {

    val listStories = repository.listStories

    val detail = repository.detail

    fun login(email: String, password: String) = repository.login(email, password)

    fun register(name: String, email: String, password: String) = repository.register(name, email, password)

    fun getStories(token: String) = repository.getAllStories(token)

    fun getDetailStory(token: String, id: String) = repository.getDetailStory(token, id)

    fun uploadImage(token: String, file: File, description: String) = repository.uploadImage(token, file, description)

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}