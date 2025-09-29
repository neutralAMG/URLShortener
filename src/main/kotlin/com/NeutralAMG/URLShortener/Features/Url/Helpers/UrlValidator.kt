package com.NeutralAMG.URLShortener.Features.Url.Helpers

import com.NeutralAMG.URLShortener.Common.Util.AppResult

object UrlValidator {
    fun validate(url: String?): AppResult<Nothing> {
        println(url)
        if (url.isNullOrEmpty()) {

            return AppResult.Error("The url cant be null or empty")
        }

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            return AppResult.Error("String is not a valid url")
        }
        //Implement the ip validator

        return AppResult.SuccessWithoutResult("Url is Valid")
    }
}