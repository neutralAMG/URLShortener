package com.NeutralAMG.URLShortener.Common.Util

import kotlin.random.Random

object CodeGenerator {
    const val keys: String = "1234567890qwertyuiopasdfghjklzxcvbnm"
    const val codeLength: Int = 10
    fun generateCode(): String = buildString {
        repeat ( codeLength ) {
           append(keys.random())
        }
    }
}