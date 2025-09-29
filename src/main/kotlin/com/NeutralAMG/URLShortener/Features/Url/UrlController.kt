package com.NeutralAMG.URLShortener.Features.Url

import com.NeutralAMG.URLShortener.Common.Util.AppResult
import com.NeutralAMG.URLShortener.Common.Util.BaseUrlService
import com.NeutralAMG.URLShortener.Features.Url.Dtos.CreateUrlDto
import com.NeutralAMG.URLShortener.Features.Url.Dtos.UrlDto
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/url")
class UrlController(val urlService: UrlService, val baseUrlService: BaseUrlService) {
    @PostMapping("/GenerateUrl")
    fun generateShortUrl(@RequestBody request: CreateUrlDto): AppResult<String> {
        println(request)
        return try {
            val result: AppResult<UrlDto> = urlService.saveEntity(request)

            when (result) {
                is AppResult.Success -> {
                    val baseUrl: String = baseUrlService.getBaseUrl()

                    val shortUrl: String = "${baseUrl}/url/${result.data.code}"

                    AppResult.Success("Here is the short url", shortUrl)
                }

                is AppResult.Error -> {
                    return AppResult.Error(result.message)
                }

                is AppResult.SuccessWithoutResult -> {
                    AppResult.SuccessWithoutResult("")
                }
            }


        } catch (e: Exception) {
            e.printStackTrace()
            AppResult.Error("Error while generating the short url")
        }


    }

    @GetMapping("/{code}")
    fun redirectToLongUrl(@PathVariable(value = "code") code: String, response: HttpServletResponse) {
        try {
            val result: AppResult<UrlDto> = urlService.findByCode(code)

            when (result) {
                is AppResult.Success -> {

                    println("Redirecting to: " + result.data.longUrl)
                    response.sendRedirect(result.data.longUrl)

                }

                is AppResult.Error -> {

                    response.sendRedirect("/")


                }

                is AppResult.SuccessWithoutResult -> {
                    response.sendRedirect("/")

                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
            response.sendRedirect("/")
        }

    }
}