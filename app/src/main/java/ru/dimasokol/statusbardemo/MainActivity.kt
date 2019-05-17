package ru.dimasokol.statusbardemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private val ARG_DARK_THEME = "dark_theme"

    var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        var isDark = intent.getBooleanExtra(ARG_DARK_THEME, false)
        setTheme(if (!isDark) R.style.AppThemeLight_Transparent else R.style.AppThemeDark_Transparent)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val rootLayout: View = findViewById<View>(R.id.root_layout)
        rootLayout.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        rootLayout.setOnApplyWindowInsetsListener { v, insets ->
            val top = insets.systemWindowInsetTop
            toolbar?.setPaddingRelative(0, top, 0, 0)
            insets.replaceSystemWindowInsets(0, 0, 0, 0)
        }

        val light: RadioButton = findViewById(R.id.radio_light)
        light.isChecked = !isDark

        var dark: RadioButton = findViewById(R.id.radio_dark)
        dark.isChecked = isDark

        light.setOnCheckedChangeListener { buttonView, isChecked ->
            val myIntent = intent
            myIntent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
            myIntent.putExtra(ARG_DARK_THEME, !isChecked)
            finish()
            startActivity(myIntent)
        }
    }
}
