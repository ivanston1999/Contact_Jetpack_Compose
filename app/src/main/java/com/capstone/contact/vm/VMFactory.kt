package com.capstone.contact.vm
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import com.capstone.contact.access.Repo


class VMFactory(private val repo: Repo) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("Error")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeVM::class.java) -> HomeVM(repo) as T
            modelClass.isAssignableFrom(DetailVM::class.java) -> DetailVM(repo) as T
            modelClass.isAssignableFrom(FavoriteVM::class.java) -> FavoriteVM(repo) as T
            else -> throw IllegalArgumentException("Error: ${modelClass.name}")
        }
    }
}
