@file:Suppress("unused")

package com.cpf.baseproject.util

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and


/**
 * 利用java原生的摘要实现SHA256加密
 *
 * @param str 加密后的报文
 * @return
 */
fun sha256(str: String): String {
    val messageDigest: MessageDigest
    var encodeStr = ""
    try {
        messageDigest = MessageDigest.getInstance("SHA-256")
        messageDigest.update(str.toByteArray(charset("UTF-8")))
        encodeStr = byte2Hex(messageDigest.digest())
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
    }

    return encodeStr
}

/**
 * 将byte转为16进制
 *
 * @param bytes
 * @return
 */
private fun byte2Hex(bytes: ByteArray): String {
    val stringBuffer = StringBuffer()
    var temp: String?
    for (i in bytes.indices) {
        temp = Integer.toHexString((bytes[i] and 0xFF.toByte()).toInt())
        if (temp!!.length == 1) {
            //1得到一位的进行补0操作
            stringBuffer.append("0")
        }
        stringBuffer.append(temp)
    }
    return stringBuffer.toString()
}