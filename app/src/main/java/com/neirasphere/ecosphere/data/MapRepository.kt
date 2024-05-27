package com.neirasphere.ecosphere.data

import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.domain.model.MapData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MapRepository {
    private val tpsData = mutableListOf<com.neirasphere.ecosphere.domain.model.MapData>()

    init {
        if (tpsData.isEmpty()){
            DataSource.tpsMapData().forEach {
                tpsData.add(it)
            }
        }
    }

    fun getAllTpsData() : Flow<List<com.neirasphere.ecosphere.domain.model.MapData>> = flowOf(tpsData)

    fun getDetailTps(tpsId: Long) : com.neirasphere.ecosphere.domain.model.MapData {
        return tpsData.first {
            it.id == tpsId
        }
    }

    companion object{
        @Volatile
        private var instance: MapRepository? = null

        fun getInstance(): MapRepository =
            instance ?: synchronized(this){
                MapRepository().apply {
                    instance = this
                }
            }
    }
}