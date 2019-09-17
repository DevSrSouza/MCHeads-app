package br.com.devsrsouza.mcheads.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HomeViewModel : ViewModel() {

    val job = Job()
    val scope: CoroutineScope = CoroutineScope(Dispatchers.Main + job)

    init {

    }
}