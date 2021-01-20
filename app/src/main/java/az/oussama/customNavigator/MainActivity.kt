package az.oussama.customNavigator

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.paperdb.Paper

class MainActivity : AppCompatActivity() {

    companion object {
        var webView: WebView? = null
    }

    private var nav_webview: WebView? = null
    private var quit: ImageView? = null
    private var urlsearch: ImageView? = null
    private var navUrl: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        Paper.init(this)
        nav_webview = findViewById(R.id.nav_webview)
        quit = findViewById(R.id.quit)
        urlsearch = findViewById(R.id.urlsearch)
        navUrl = findViewById(R.id.navUrl)

        nav_webview!!.settings.javaScriptEnabled = true
        var url = Paper.book().read<String>("navURL", "https://github.com/oussamazizi")
        nav_webview!!.loadUrl(url)
        webView = nav_webview
        quit!!.setOnClickListener { this.finish() }
        urlsearch!!.setOnClickListener {
            val intent = Intent(this, WebSearchDialog::class.java)
            startActivity(intent)
        }

        nav_webview!!.setWebViewClient(object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                // Here you can check your new URL.
                navUrl!!.text = url
                super.onPageStarted(view, url, favicon)
            }
        })

        quit!!.setOnClickListener { this.finish() }
    }
}