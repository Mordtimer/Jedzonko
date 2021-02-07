package com.example.jedzonko.view.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.jedzonko.R
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

interface Bitmap {
        fun getBitmapFromURL(src: String?): Bitmap? {
            return try {
                val url = URL(src)
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input: InputStream = connection.inputStream
                BitmapFactory.decodeStream(input)
            } catch (e: IOException) {
                null
            }
    }
}