package com.example.simplebooklibrary

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.PorterDuffXfermode
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class PdfBitmapConverter(
    private val context: Context
) {
    var renderer: PdfRenderer? = null

    suspend fun pdfToBitmaps(contentUri: Uri, screenDensity: Int): List<Bitmap> {
        return withContext(Dispatchers.IO) {
            renderer?.close()

            context
                .contentResolver
                .openFileDescriptor(contentUri, "r")
                ?.use { descriptor ->
                    with(PdfRenderer(descriptor)) {
                        renderer = this

                        Log.d("MOJTAG", pageCount.toString())
                        return@withContext (0 until 5).map { index ->
                            async {
                                openPage(index).use { page ->
                                    val bitmap = Bitmap.createBitmap(
                                        page.width * 3,
                                        page.height * 3,
                                        Bitmap.Config.ARGB_8888
                                    )

                                    val paint = Paint().apply {
                                        xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)
                                    }

                                    val canvas = Canvas(bitmap).apply {
                                        drawColor(Color.WHITE)
                                        drawBitmap(
                                            bitmap,
                                            0f,
                                            0f,
                                            paint
                                        )
                                    }

                                    page.render(
                                        bitmap,
                                        null,
                                        null,
                                        PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY
                                    )

                                    bitmap
                                }
                            }
                        }.awaitAll()
                    }
                }
            return@withContext emptyList()
        }
    }
}