package br.com.devsrsouza.mcheads.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HeadDao {
    @Query("select * from heads")
    fun getHeads(): LiveData<List<DatabaseHead>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DatabaseHead)
}

@Database(entities = [DatabaseHead::class], version = 1)
@TypeConverters(Converter::class)
abstract class HeadsDatabase : RoomDatabase() {
    abstract val headDao: HeadDao

    companion object {
        private const val DATABASE_NAME = "heads"

        private lateinit var INSTANCE: HeadsDatabase

        fun getDatabase(context: Context): HeadsDatabase {
            synchronized(HeadsDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        HeadsDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}


