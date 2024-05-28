package com.neirasphere.ecosphere.domain.repository

import com.neirasphere.ecosphere.domain.model.MapData
import kotlinx.coroutines.flow.Flow

interface MapRepository {

    val tpsData: MutableList<MapData>

    fun getAllTpsData(): Flow<List<MapData>>

    fun getDetailTps(tpsId: Long) : MapData

}