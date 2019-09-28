package br.com.devsrsouza.mcheads.ui.home

import android.app.Application
import androidx.lifecycle.*
import br.com.devsrsouza.mcheads.common.HeadCategory
import br.com.devsrsouza.mcheads.data.database.HeadsDatabase
import br.com.devsrsouza.mcheads.data.domain.Head
import br.com.devsrsouza.mcheads.data.repository.HeadsRepository
import kotlinx.coroutines.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    // Coroutines
    private var job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    // Repository
    private val database = HeadsDatabase.getDatabase(application)
    private val headsRepository = HeadsRepository(database)

    // LiveData
    private val allHeads = headsRepository.heads
    //private val allHeads = MutableLiveData<List<Head>>()

    private val _category = MutableLiveData<HeadCategory?>()
    val category: LiveData<HeadCategory?> get() = _category

    private val _heads: MediatorLiveData<List<Head>> = MediatorLiveData()
    val heads: LiveData<List<Head>> get() = _heads

    init {
        scope.launch {
            headsRepository.refreshHeads()
        }

        //getDebugHeads()

        _heads.addSource(allHeads) {
            val category = category.value

            _heads.value = if (category != null) {
                it.filter { it.category == category }
            } else it
        }
        _heads.addSource(category) { category ->
            val all = allHeads.value

            _heads.value = if (category != null)
                all?.filter { it.category == category }
            else all ?: emptyList()
        }
    }

    /*private fun getDebugHeads() {
        var i = 0
        fun create(name: String, uuid: String, category: HeadCategory, imageUrl: String): Head {
            return Head(i++, name, uuid, "x", category, imageUrl)
        }

        allHeads.value = listOf(
            create("SrSouza", "b4bfc262-b8b7-4391-88c2-d2d31ae5d919", HeadCategory.HUMANOID, "https://visage.surgeplay.com/head/256/b4bfc262b8b7439188c2d2d31ae5d919"),
            create("Rezzus", "9b2a30ec-f8b3-4dfe-bf49-9c5c367383f8", HeadCategory.HUMANOID,"https://visage.surgeplay.com/head/256/9b2a30ec-f8b3-4dfe-bf49-9c5c367383f8"),
            create("Kevinkool", "e3fbbbd8-9f22-408a-af32-47ac06f9c3e7", HeadCategory.PLANTS,"https://visage.surgeplay.com/head/256/e3fbbbd8-9f22-408a-af32-47ac06f9c3e7"),
            create("Thorlon", "d3be5ef3-db9b-48d3-a668-019bf3ce0495", HeadCategory.PLANTS,"https://visage.surgeplay.com/head/256/d3be5ef3-db9b-48d3-a668-019bf3ce0495"),
            create("aPunch", "77c0db17-0437-4b83-a419-8d4cd32f0b87", HeadCategory.HUMANOID,"https://visage.surgeplay.com/head/256/77c0db17-0437-4b83-a419-8d4cd32f0b87"),
            create("Codename_B", "dba2f115-6cc2-401b-a9e9-b4d27f7c897d", HeadCategory.HUMANOID,"https://visage.surgeplay.com/head/256/dba2f115-6cc2-401b-a9e9-b4d27f7c897d"),
            create("Zumulus", "1b02d8ff-8fc8-4452-a386-cf54139d252e", HeadCategory.PLANTS,"https://visage.surgeplay.com/head/256/1b02d8ff-8fc8-4452-a386-cf54139d252e"),
            create("_annabock_", "4e4e8e9f-6bcb-478b-a0ee-b167f8872f68", HeadCategory.PLANTS,"https://visage.surgeplay.com/head/256/4e4e8e9f-6bcb-478b-a0ee-b167f8872f68"),
            create("Flummox_", "4eb551ff-04e0-44f8-abba-27ae7b64e7a1", HeadCategory.HUMANOID, "https://visage.surgeplay.com/head/256/4eb551ff-04e0-44f8-abba-27ae7b64e7a1"),
            create("BulldogLucas", "e06f0e3e-523c-4781-9c1b-4386aceb710a", HeadCategory.MONSTERS,"https://visage.surgeplay.com/head/256/e06f0e3e-523c-4781-9c1b-4386aceb710a")
        )

    }*/

    // if null, all heads
    fun updateCategory(category: HeadCategory?) {
        if (_category.value == category) return

        _category.value = category
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}