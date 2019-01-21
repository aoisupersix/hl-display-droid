package hl_display_droid.hykwlab.org.hl_display_droid

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.WebView

/**
 * 表示するディジタルサイネージ用WebアプリのURLを入れてください。
 */
const val SIGNAGE_URL = "https://aoisupersix.github.io/hl-signage/"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //フルスクリーン化
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        val view = window.decorView
        view.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
        setContentView(R.layout.activity_main)

        //WebViewにディスプレイを表示
        val webView = findViewById<WebView>(R.id.mainWebView)
        webView.loadUrl(SIGNAGE_URL)
        webView.settings.javaScriptEnabled = true

    }

    override fun onResume() {
        super.onResume()

        val view = window.decorView
        view.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if(event?.action == KeyEvent.ACTION_DOWN) {
            if(event.keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
                //終了
                finish()
            }else if(event.keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                //WebViewの更新をおこなう
                val webView = findViewById<WebView>(R.id.mainWebView)
                webView.reload()
            }
        }
        return false
    }
}
