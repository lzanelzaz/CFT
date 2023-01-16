package ru.lzanelzaz.cft

import javax.inject.Inject
import javax.inject.Singleton
import ru.lzanelzaz.cft.api.ApiService
import ru.lzanelzaz.cft.db.Bin
import ru.lzanelzaz.cft.db.BinDao
import ru.lzanelzaz.cft.model.BinInfo

@Singleton
class AppRepository @Inject constructor(
    private val apiService: ApiService,
    private val binDao: BinDao
) {
    suspend fun getBinInfo(bin: Int): BinInfo {
        val response = apiService.getBinInfo(bin)
        response.bin = bin.toString()
        return response
    }

    suspend fun getAll(): List<BinInfo> = binDao.getAll().map { it.binInfo }

    suspend fun insertBin(binInfo: BinInfo) {
        binDao.insertBin(Bin(null, binInfo))
    }
}