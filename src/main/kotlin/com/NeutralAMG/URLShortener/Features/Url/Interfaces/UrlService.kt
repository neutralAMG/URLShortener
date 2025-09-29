package com.NeutralAMG.URLShortener.Features.Url.Interfaces

import com.NeutralAMG.URLShortener.Common.Interfaces.BaseService
import com.NeutralAMG.URLShortener.Common.Util.AppResult
import com.NeutralAMG.URLShortener.Features.Url.Dtos.CreateUrlDto
import com.NeutralAMG.URLShortener.Features.Url.Dtos.UrlDto
import com.NeutralAMG.URLShortener.Features.Url.UrlService

interface UrlService : BaseService<Long, UrlDto, CreateUrlDto> {
    fun findByCode(code: String?): AppResult<UrlDto>
}