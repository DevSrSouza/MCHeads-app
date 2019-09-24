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
        //getHeads(HeadCategory.FOOD_AND_DRINKS)
        getDebugHeads()
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

    private fun getDebugHeads() {
        var i = 0
        fun create(name: String, uuid: String, imageUrl: String): Head {
            return Head(i++, name, uuid, "x", HeadCategory.HUMANS, imageUrl)
        }

        _heads.value = listOf(
            create("SrSouza", "b4bfc262-b8b7-4391-88c2-d2d31ae5d919", "https://visage.surgeplay.com/head/256/b4bfc262b8b7439188c2d2d31ae5d919"),
            create("Rezzus", "9b2a30ec-f8b3-4dfe-bf49-9c5c367383f8", "https://visage.surgeplay.com/head/256/9b2a30ec-f8b3-4dfe-bf49-9c5c367383f8"),
            create("Kevinkool", "e3fbbbd8-9f22-408a-af32-47ac06f9c3e7", "https://visage.surgeplay.com/head/256/e3fbbbd8-9f22-408a-af32-47ac06f9c3e7"),
            create("Thorlon", "d3be5ef3-db9b-48d3-a668-019bf3ce0495", "https://visage.surgeplay.com/head/256/d3be5ef3-db9b-48d3-a668-019bf3ce0495"),
            create("aPunch", "77c0db17-0437-4b83-a419-8d4cd32f0b87", "https://visage.surgeplay.com/head/256/77c0db17-0437-4b83-a419-8d4cd32f0b87"),
            create("Codename_B", "dba2f115-6cc2-401b-a9e9-b4d27f7c897d", "https://visage.surgeplay.com/head/256/dba2f115-6cc2-401b-a9e9-b4d27f7c897d"),
            create("Zumulus", "1b02d8ff-8fc8-4452-a386-cf54139d252e", "https://visage.surgeplay.com/head/256/1b02d8ff-8fc8-4452-a386-cf54139d252e"),
            create("_annabock_", "4e4e8e9f-6bcb-478b-a0ee-b167f8872f68", "https://visage.surgeplay.com/head/256/4e4e8e9f-6bcb-478b-a0ee-b167f8872f68"),
            create("Flummox_", "4eb551ff-04e0-44f8-abba-27ae7b64e7a1", "https://visage.surgeplay.com/head/256/4eb551ff-04e0-44f8-abba-27ae7b64e7a1"),
            create("BulldogLucas", "e06f0e3e-523c-4781-9c1b-4386aceb710a", "https://visage.surgeplay.com/head/256/e06f0e3e-523c-4781-9c1b-4386aceb710a")
        )

        _status.value = HeadApiRequestStatus.DONE
    }

    fun updateCategory(category: HeadCategory) {
        getHeads(category)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}