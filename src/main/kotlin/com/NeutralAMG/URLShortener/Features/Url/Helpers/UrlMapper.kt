package com.NeutralAMG.URLShortener.Features.Url.Helpers

import com.NeutralAMG.URLShortener.Features.Url.Dtos.UrlDto
import com.NeutralAMG.URLShortener.Features.Url.UrlDBModel

object UrlMapper {

    fun mapToDto(urlDBModel: UrlDBModel): UrlDto =
        UrlDto(urlDBModel.id, urlDBModel.code, urlDBModel.longUrl, urlDBModel.creationDate);

    fun mapToModel(urlDto: UrlDto): UrlDBModel =
        UrlDBModel(urlDto.code, urlDto.longUrl)
}