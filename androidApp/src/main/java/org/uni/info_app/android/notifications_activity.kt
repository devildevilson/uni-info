package org.uni.info_app.android

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

class notifications_activity : base_activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      MyApplicationTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          Box(modifier = Modifier.fillMaxSize()) {
            Text(
              text = getString(R.string.activity_notification),
              textAlign = TextAlign.Center,
              modifier = Modifier.align(Alignment.Center)
            )
          }
        }
      }
    }

    var data = userdata_utility.load_userdata(applicationContext)
    if (data == null) {
      val intent = Intent(this, login_activity::class.java)
      intent.putExtra("title_id", R.string.activity_notification)
      startActivity(intent)

      data = userdata_utility.load_userdata(applicationContext)
      if (data == null) {
        // не смогли залогиниться
        finish()
      }
    }
  }
}

@Preview
@Composable
fun notifications_preview() {
  MyApplicationTheme {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colors.background
    ) {
      Box(modifier = Modifier.fillMaxSize()) {
        Text(
          text = "Notifications",
          textAlign = TextAlign.Center,
          modifier = Modifier.align(Alignment.Center)
        )
      }
    }
  }
}