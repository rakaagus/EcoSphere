package com.neirasphere.ecosphere.domain.usecase.map

import com.neirasphere.ecosphere.domain.model.MapData
import kotlinx.coroutines.flow.Flow

interface MapUseCase {
    val tpsData: MutableList<MapData>

    fun getAllTpsData(): Flow<List<MapData>>

    fun getDetailTps(tpsId: Long) : MapData
}