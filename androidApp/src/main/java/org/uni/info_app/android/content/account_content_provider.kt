package org.uni.info_app.android.content

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.uni.info_app.android.R
import org.uni.info_app.android.content_provider_interface
import org.uni.info_app.android.userdata_utility
import java.io.FileNotFoundException

class account_content_provider(val activity: ComponentActivity) : content_provider_interface {
  override fun need_auth(): Boolean {
    // проверим есть ли файл на диске, а также верно ли читаются данные
    // но мне бы еще помимо этого понять нужна ли авторизация для показа этого контента
    // или возможно что то еще, наверное лучше тогда просто сделать несколько Boolean функций

    val data = userdata_utility.load_userdata(activity.applicationContext)
    return data == null
  }

  @Composable
  override fun get_content(paddings: PaddingValues) {
    val userdata = userdata_utility.load_userdata(activity.applicationContext) ?:
      throw FileNotFoundException("Could not load file ${userdata_utility.file_name}")

    Toast.makeText(activity, "LOGIN SUCCESSFULLY", Toast.LENGTH_SHORT).show()

    Box(Modifier.fillMaxSize()) {
      Text(
        text = activity.getString(R.string.nav_account),
        textAlign = TextAlign.Center,
        modifier = Modifier.align(Alignment.Center)
      )
    }
  }
}