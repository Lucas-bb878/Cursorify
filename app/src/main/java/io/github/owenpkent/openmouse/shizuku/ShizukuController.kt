
package io.github.owenpkent.openmouse.shizuku

import android.content.pm.PackageManager
import rikka.shizuku.Shizuku

object ShizukuController {

    private const val REQUEST_CODE = 1001

    fun isShizukuRunning(): Boolean {
        return Shizuku.pingBinder()
    }

    fun hasPermission(): Boolean {
        return isShizukuRunning() &&
            Shizuku.checkSelfPermission() ==
            PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission() {
        if (!isShizukuRunning()) {
            return
        }

        if (!hasPermission()) {
            Shizuku.requestPermission(REQUEST_CODE)
        }
    }
}

