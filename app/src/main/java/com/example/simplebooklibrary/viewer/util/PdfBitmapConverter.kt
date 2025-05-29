package com.example.simplebooklibrary.viewer.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import androidx.core.graphics.createBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val READ_MODE = "r"
private const val BITMAP_X_START_POINT = 0f
private const val BITMAP_Y_START_POINT = 0f

interface IBitmapConverter {
    suspend fun pdfToBitmaps(
        contentUri: Uri,
        page: Int = 0,
        isDarkModeActive: Boolean = false
    ): List<Bitmap>
}

class PdfBitmapConverter(
    private val context: Context
) : IBitmapConverter {
    private var renderer: PdfRenderer? = null
    private var bookNumberOfPages: Int = 0

    @Suppress("TooGenericExceptionCaught")
    override suspend fun pdfToBitmaps(
        contentUri: Uri,
        page: Int,
        isDarkModeActive: Boolean
    ): List<Bitmap> {
        val startPage = page * 5

        var endPage = startPage + 5

        return withContext(Dispatchers.IO) {
            renderer?.close()
            context
                .contentResolver
                .openFileDescriptor(contentUri, READ_MODE)
                ?.use { descriptor ->
                    with(PdfRenderer(descriptor)) {
                        renderer = this
                        bookNumberOfPages = this.pageCount

                        if (bookNumberOfPages == 0) {
                            return@withContext emptyList()
                        }

                        if (endPage > bookNumberOfPages && bookNumberOfPages > 0) {
                            endPage = bookNumberOfPages
                        }

                        return@withContext (startPage until endPage).map { index ->
                            try {
                                openPage(index).use { page ->
                                    val bitmap = createBitmap(page.width * 3, page.height * 3)

                                    Canvas(bitmap).apply {
                                        drawColor(Color.WHITE)
                                        drawBitmap(
                                            bitmap,
                                            BITMAP_X_START_POINT,
                                            BITMAP_Y_START_POINT,
                                            null
                                        )
                                    }

                                    page.render(
                                        bitmap,
                                        null,
                                        null,
                                        PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY
                                    )

                                    if (isDarkModeActive) {
                                        return@map invertBitmapColors(bitmap)
                                    } else {
                                        return@map bitmap
                                    }
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                return@map createBitmap(100, 100)
                            }
                        }
                    }
                }
            return@withContext emptyList()
        }
    }

    private fun invertBitmapColors(bitmap: Bitmap): Bitmap {
        val invertMatrix = ColorMatrix(
            floatArrayOf(
                -1f, 0f, 0f, 0f, 255f,
                0f, -1f, 0f, 0f, 255f,
                0f, 0f, -1f, 0f, 255f,
                0f, 0f, 0f, 1f, 0f
            )
        )

        val paint = Paint().apply {
            colorFilter = ColorMatrixColorFilter(invertMatrix)
        }

        val canvas = Canvas(bitmap)
        canvas.drawBitmap(bitmap, BITMAP_X_START_POINT, BITMAP_X_START_POINT, paint)
        return bitmap
    }
}
