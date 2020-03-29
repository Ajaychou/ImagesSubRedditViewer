package com.android.imagessubredditviewer.utils

import android.graphics.drawable.Drawable
import java.io.IOException
import java.io.InputStream
import java.net.URL

/**
 * Utilities class
 */
object Util {
    /**
     * Convert url to drawable
     */
    @JvmStatic
    fun urlToBitmapCanverter(url: String?): Drawable? {
        var myUrl: URL? = null
        var drawable: Drawable? = null
        try {
            myUrl = URL(url)
            val inputStream = myUrl.content as InputStream
            drawable = Drawable.createFromStream(inputStream, null)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return drawable
    }
}