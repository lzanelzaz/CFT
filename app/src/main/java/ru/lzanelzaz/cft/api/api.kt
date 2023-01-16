package ru.lzanelzaz.cft.api

import javax.inject.Singleton
import retrofit2.http.GET
import retrofit2.http.Path
import ru.lzanelzaz.cft.model.BinInfo

@Singleton
interface ApiService {
    @GET("{bin}")
    suspend fun getBinInfo(
        @Path("bin") bin: Int
    ): BinInfo
}