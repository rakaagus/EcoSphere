package com.neirasphere.ecosphere.data

import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.model.MapData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MapRepository {
    private val tpsData = mutableListOf<MapData>()

    init {
        if (tpsData.isEmpty()){
            DataSource.tpsMapData().forEach {
                tpsData.add(it)
            }
        }
    }

    fun getAllTpsData() : Flow<List<MapData>> = flowOf(tpsData)

    fun getDetailTps(tpsId: Long) : MapData {
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