package ru.lzanelzaz.cft.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BinDao {
    @Query("SELECT * FROM bin ORDER BY id DESC")
    suspend fun getAll(): List<Bin>

    @Insert
    suspend fun insertBin(bin: Bin)

//    @Query("DELETE FROM projects WHERE projectId = :projectId")
//    fun deleteProject(projectId: String)
}