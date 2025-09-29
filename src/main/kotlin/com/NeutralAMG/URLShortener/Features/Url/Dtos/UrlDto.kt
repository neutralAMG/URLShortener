package com.NeutralAMG.URLShortener.Features.Url.Dtos

import java.util.Date

data class UrlDto(
    var id: Long?,
    var code: String,
    var longUrl: String,
    var creationTime: Date,

    ){

}