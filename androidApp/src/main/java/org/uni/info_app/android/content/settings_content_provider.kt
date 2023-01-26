package org.uni.info_app.android.content

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.res.Resources
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import org.uni.info_app.android.*
import org.uni.info_app.android.R
import java.util.*

// это пригодится когда я перепишу обновление языка
//fun set_locale(ctx: Context, resources: Resources, language: String): Context {
//  val locale = Locale(language)
//  Locale.setDefault(locale)
//  val config = resources.configuration
//  config.setLocale(locale)
//  config.setLayoutDirection(locale)
//  return ctx.createConfigurationContext(config)
//}

class settings_content_provider(val activity: ComponentActivity) : content_provider_interface {
  var content_provider = mutableStateOf<String?>(null)

  fun lang_id_to_locale(id: Int) = when(id) {
    R.string.lang_ru -> "ru"
    R.string.lang_en -> "en"
    R.string.lang_kk -> "kk"
    else -> "en"
  }

  override fun need_auth() = false

  @Composable
  override fun get_content(paddings: PaddingValues) {
    var popup_control by remember { mutableStateOf(false) }

    val lang_list = listOf(
      R.string.lang_ru,
      R.string.lang_kk,
      R.string.lang_en,
    )

    content_provider.value?.let {
      //val locale = Locale(it)
      //Locale.setDefault(locale)
      content_provider.value = null
      activity.finish()
      activity.startActivity(Intent(activity, main::class.java))
    }

    Box(Modifier.fillMaxSize()) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(0.dp, 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {

        Text(
          text = activity.getString(R.string.nav_settings),
          //textAlign = TextAlign.Center,
          //modifier = Modifier.align(Alignment.Center)
        )
        Button(
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(0.dp),
          onClick = { popup_control = !popup_control }
        ) {
          Box(modifier = Modifier.fillMaxWidth()) {
            Row(
              modifier = Modifier.align(Alignment.CenterStart),
            ) {
              Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = ""
              )
              Text(
                modifier = Modifier.padding(0.dp, 2.dp),
                text = activity.getString(R.string.change_lang)
              )
            }
            Icon(
              modifier = Modifier.align(Alignment.CenterEnd),
              imageVector = Icons.Filled.PlayArrow,
              contentDescription = ""
            )
          }
        }
      }

      if (popup_control) {
        Surface(
          color = Color.Black.copy(alpha = 0.6f),
          modifier = Modifier.fillMaxSize().clickable { popup_control = !popup_control }
        ) {
          Popup(
            alignment = Alignment.Center
          ) {
            Box(
              modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.4f),
            ) {
              Column(
                modifier = Modifier.background(color = MaterialTheme.colors.background)
              ) {
                lang_list.forEach { lang_str ->
                  Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(0.dp),
                    onClick = {
                      val locale_code = lang_id_to_locale(lang_str)
                      base_app.current_lang = locale_code
                      content_provider.value = locale_code
                    }
                  ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                      Row(
                        modifier = Modifier.align(Alignment.CenterStart),
                      ) {
                        Text(
                          text = activity.getString(lang_str)
                        )
                      }


                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}