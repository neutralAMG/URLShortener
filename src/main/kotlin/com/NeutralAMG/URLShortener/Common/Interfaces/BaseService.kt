package com.NeutralAMG.URLShortener.Common.Interfaces

import com.NeutralAMG.URLShortener.Common.Util.AppResult

interface BaseService<Id,GetDto,SaveDto> {
    fun getAllEntities(): AppResult<List<GetDto>>
    fun getEntityById(id: Id): AppResult<GetDto>
    fun saveEntity(saveDto: SaveDto):  AppResult<GetDto>
    fun updateEntity(saveDto: SaveDto): AppResult<GetDto>
    fun deleteEntity(id: Id): AppResult<Nothing>
}