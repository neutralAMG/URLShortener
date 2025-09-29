package com.NeutralAMG.URLShortener.Common.Util

import com.sun.net.httpserver.Request
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Service
class BaseUrlService {

    fun getBaseUrl(): String{
      return ServletUriComponentsBuilder
          .fromCurrentContextPath().build().toUriString()
    }

}