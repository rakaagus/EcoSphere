package com.neirasphere.ecosphere.data.repository

import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.domain.model.MapData
import com.neirasphere.ecosphere.domain.repository.MapRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
): MapRepository{
    override val tpsData = mutableListOf<MapData>()

    init {
        if (tpsData.isEmpty()){
            dataSource.tpsMapData().forEach {
                tpsData.add(it)
            }
        }
    }

    override fun getAllTpsData() : Flow<List<MapData>> = flowOf(tpsData)

    override fun getDetailTps(tpsId: Long) : MapData {
        return tpsData.first {
            it.id == tpsId
        }
    }

//    companion object{
//        @Volatile
//        private var instance: MapRepositoryImpl? = null
//
//        fun getInstance(): MapRepositoryImpl =
//            instance ?: synchronized(this){
//                MapRepositoryImpl().apply {
//                    instance = this
//                }
//            }
//    }
}