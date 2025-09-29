package com.NeutralAMG.URLShortener.Features.Url.Interfaces

import com.NeutralAMG.URLShortener.Features.Url.UrlDBModel
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface UrlRepository : CrudRepository<UrlDBModel, Long> {

    fun findByCode(code: String): Optional<UrlDBModel>
    fun existsByCode(code: String): Boolean
}