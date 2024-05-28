package com.neirasphere.ecosphere.domain.usecase.map

import com.neirasphere.ecosphere.domain.model.MapData
import com.neirasphere.ecosphere.domain.repository.MapRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapUseCaseInteract @Inject constructor(
    private val mapRepository: MapRepository
) : MapUseCase{
    override val tpsData: MutableList<MapData> = mapRepository.tpsData

    override fun getAllTpsData(): Flow<List<MapData>> = mapRepository.getAllTpsData()

    override fun getDetailTps(tpsId: Long): MapData = mapRepository.getDetailTps(tpsId)
}