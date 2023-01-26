package org.uni.info_app.android

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.LocaleList
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import java.util.*

open class base_app: Application() {
  override fun attachBaseContext(new_base: Context) {
    super.attachBaseContext(set_locale(new_base, current_lang))
  }

  companion object {
    var current_lang = "en"

    fun set_locale(ctx: Context, language: String): Context {
      val locale = Locale(language)
      println("locale $locale")
      Locale.setDefault(locale)
      val configuration = ctx.resources.configuration
      configuration.setLayoutDirection(locale)
      configuration.setLocale(locale)
//    val localeList = LocaleList(locale)
//    LocaleList.setDefault(localeList)
//    configuration.setLocales(localeList)
      return ctx.createConfigurationContext(configuration)
    }
  }
}

open class base_activity: ComponentActivity() {
  override fun attachBaseContext(new_base: Context) {
    super.attachBaseContext(base_app.set_locale(new_base, base_app.current_lang))
  }
}