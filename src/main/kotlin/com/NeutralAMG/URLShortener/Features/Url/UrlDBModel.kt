package com.NeutralAMG.URLShortener.Features.Url

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.Date

@Entity
class UrlDBModel(
    val code: String,
    var longUrl: String,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null


    val creationDate: Date = Date();


}
