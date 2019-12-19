package developersancho.mvvm.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable


object ImageUtils {

    fun drawableToBitmap(drawable: Drawable?): Bitmap? {
        if (drawable == null) return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        if (drawable is BitmapDrawable) {
            if (drawable.bitmap != null) return drawable.bitmap
        }
        val bitmap: Bitmap =
            if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
            ) else Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

}