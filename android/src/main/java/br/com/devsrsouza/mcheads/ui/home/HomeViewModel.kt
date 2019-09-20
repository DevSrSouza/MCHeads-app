package br.com.devsrsouza.mcheads.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.devsrsouza.mcheads.client.HeadApi
import br.com.devsrsouza.mcheads.common.Head
import br.com.devsrsouza.mcheads.common.HeadCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class HeadApiRequestStatus { LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {

    private var job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    //LiveData
    private val _status = MutableLiveData<HeadApiRequestStatus>()
    val status: LiveData<HeadApiRequestStatus> get() = _status

    private val _heads = MutableLiveData<List<Head>>()
    val heads: LiveData<List<Head>> get() = _heads

    init {
        getHeads(HeadCategory.FOOD_AND_DRINKS)
    }

    private fun getHeads(category: HeadCategory) {
        scope.launch {
            try {
                _status.value = HeadApiRequestStatus.LOADING

                _heads.value = HeadApi.service.searchCategory(category)

                _status.value = HeadApiRequestStatus.DONE
            } catch (e: Throwable) {
                _status.value = HeadApiRequestStatus.ERROR
                _heads.value = listOf()
            }
        }
    }

    fun updateCategory(category: HeadCategory) {
        getHeads(category)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}