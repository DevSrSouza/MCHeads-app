package br.com.devsrsouza.mcheads.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory(val application: Application, val debug: Boolean) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(application, debug) as T
        }
        throw IllegalArgumentException("Unable to construct HomeViewModel")
    }
}