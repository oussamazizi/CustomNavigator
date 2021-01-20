package az.oussama.customNavigator

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import io.paperdb.Paper
import java.lang.Exception

class WebSearchDialog: AppCompatActivity() {

    fun ErrorAlert(context: Context){
        val error: AlertDialog.Builder = AlertDialog.Builder(context)
        error.setCancelable(true)
        error.setMessage("Invalid URL")
        error.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        var view = layoutInflater.inflate(R.layout.searchurl, null)
        var url = view.findViewById<View>(R.id.urlengine) as EditText
        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
        alert.setView(view)
        alert.setCancelable(false)
        alert.setPositiveButton(
            "SEARCH"
        ) { dialog, whichButton ->
            run {
                //GlassObject.url.value = url.text.toString()
                try {
                    MainActivity.webView!!.loadUrl(Uri.parse(url.text.toString()).toString())
                    Paper.book().write("navURL", url.text.toString())
                } catch (e: Exception){
                    ErrorAlert(this@WebSearchDialog)
                }
                this@WebSearchDialog.finish()
            }
        }
        alert.setNegativeButton(
            "CANCEL"
        ) { dialog, whichButton ->
            run {
                this@WebSearchDialog.finish()
            }
        }
        alert.show()
        // Show the popup dialog
        //showDialog(0)
    }

}
