
package io.github.owenpkent.openmouse

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.github.owenpkent.openmouse.service.MouseAccessibilityService
import io.github.owenpkent.openmouse.shizuku.ShizukuController
import rikka.shizuku.Shizuku

class MainActivity : AppCompatActivity() {

    private lateinit var statusText: TextView
    private lateinit var shizukuStatusText: TextView

    private val permissionListener =
        Shizuku.OnRequestPermissionResultListener { requestCode, grantResult ->
            if (requestCode == 1001) {
                updateShizukuStatus()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        shizukuStatusText = findViewById(R.id.shizukuStatusText)

        Shizuku.addRequestPermissionResultListener(permissionListener)

        findViewById<Button>(R.id.openSettingsButton).setOnClickListener {
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }

        findViewById<Button>(R.id.appSettingsButton).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        findViewById<Button>(R.id.refreshButton).setOnClickListener {
            updateStatus()
            updateShizukuStatus()
        }

        findViewById<Button>(R.id.shizukuButton).setOnClickListener {
            connectShizuku()
        }
    }

    override fun onResume() {
        super.onResume()
        updateStatus()
        updateShizukuStatus()
    }

    override fun onDestroy() {
        Shizuku.removeRequestPermissionResultListener(permissionListener)
        super.onDestroy()
    }

    private fun connectShizuku() {
        try {
            if (!ShizukuController.isShizukuRunning()) {
                shizukuStatusText.text =
                    "Shizuku não está em execução. Inicie o Shizuku primeiro."
                return
            }

            if (ShizukuController.hasPermission()) {
                updateShizukuStatus()
                return
            }

            shizukuStatusText.text = "Aguardando autorização do Shizuku..."
            ShizukuController.requestPermission()

        } catch (e: Exception) {
            shizukuStatusText.text =
                "Erro ao conectar ao Shizuku: ${e.message}"
        }
    }

    private fun updateShizukuStatus() {
        try {
            when {
                !ShizukuController.isShizukuRunning() -> {
                    shizukuStatusText.text = "Shizuku: não está em execução"
                }

                ShizukuController.hasPermission() -> {
                    shizukuStatusText.text = "Shizuku: conectado e autorizado"
                }

                else -> {
                    shizukuStatusText.text = "Shizuku: aguardando autorização"
                }
            }
        } catch (e: Exception) {
            shizukuStatusText.text = "Shizuku: erro ao verificar conexão"
        }
    }

    private fun updateStatus() {
        statusText.setText(
            if (isServiceEnabled()) {
                R.string.status_enabled
            } else {
                R.string.status_disabled
            }
        )
    }

    private fun isServiceEnabled(): Boolean {
        val expectedId =
            "$packageName/${MouseAccessibilityService::class.java.name}"

        val enabledServices = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
        ) ?: return false

        val splitter = TextUtils.SimpleStringSplitter(':')
        splitter.setString(enabledServices)

        while (splitter.hasNext()) {
            if (splitter.next().equals(expectedId, ignoreCase = true)) {
                return true
            }
        }

        return false
    }
}
