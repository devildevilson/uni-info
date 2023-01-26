package org.uni.info_app.android.content

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.uni.info_app.android.MyApplicationTheme
import org.uni.info_app.android.R
import org.uni.info_app.android.content_provider_interface

data class big_button_data(val icon: Painter, val text: String, val on_click: () -> Unit)

class services_content_provider(val activity: ComponentActivity) : content_provider_interface {
  override fun need_auth() = false

  @Composable
  override fun get_content(paddings: PaddingValues) {
    Box(Modifier.fillMaxSize()) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .verticalScroll(rememberScrollState())
          .padding(paddings)
      ) {
        pair_of_two_buttons(
          activity,
          big_button_data(painterResource(R.drawable.ic_baseline_article_32), "Услуга") {},
          big_button_data(painterResource(R.drawable.ic_baseline_mail_32), "Услуга") {}
        )

        pair_of_two_buttons(
          activity,
          big_button_data(painterResource(R.drawable.ic_baseline_calculate_32), "Услуга") {},
          big_button_data(painterResource(R.drawable.ic_baseline_contact_page_32), "Услуга") {}
        )

        pair_of_two_buttons(
          activity,
          big_button_data(painterResource(R.drawable.ic_baseline_language_32), "Услуга") {},
          big_button_data(painterResource(R.drawable.ic_baseline_article_32), "Услуга") {}
        )

        pair_of_two_buttons(
          activity,
          big_button_data(painterResource(R.drawable.ic_baseline_article_32), "Услуга") {},
          big_button_data(painterResource(R.drawable.ic_baseline_mail_32), "Услуга") {}
        )

        pair_of_two_buttons(
          activity,
          big_button_data(painterResource(R.drawable.ic_baseline_article_32), "Услуга") {},
          big_button_data(painterResource(R.drawable.ic_baseline_mail_32), "Услуга") {}
        )
      }
//      Text(
//        text = activity.getString(R.string.nav_services),
//        textAlign = TextAlign.Center,
//        modifier = Modifier.align(Alignment.Center)
//      )
    }
  }
}

@Composable
fun pair_of_two_buttons(activity: ComponentActivity, data1: big_button_data, data2: big_button_data) {
  Row(Modifier.fillMaxWidth()) {
    Box(Modifier.weight(1.0f).aspectRatio(1.0f)) {
      big_button(activity, data1)
    }

    Box(Modifier.weight(1.0f).aspectRatio(1.0f)) {
      big_button(activity, data2)
    }
  }
}

@Composable
fun big_button(activity: ComponentActivity, data: big_button_data) {
  Button(
    onClick = data.on_click,
    modifier = Modifier
      .padding(10.dp)
      .fillMaxSize(),
    shape = RoundedCornerShape(8.dp),
    elevation = null,
    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
    //contentPadding = PaddingValues(0.dp),
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Surface(
        modifier = Modifier
          //.size(70.dp)
          .fillMaxWidth(0.5f)
          .aspectRatio(1.0f)
        ,
        shape = CircleShape,
        color = MaterialTheme.colors.primary,
        //contentPadding = PaddingValues(0.dp),
        elevation = 3.dp
      ) {
        Box(contentAlignment = Alignment.Center) {
          Icon(
            modifier = Modifier.fillMaxSize(0.65f),
            painter = data.icon,
            contentDescription = ""
          )
        }
      }

      Box(Modifier.padding(10.dp)) {
        Text(text = data.text)
      }
    }
  }
}

@Preview
@Composable
fun preview_big_button() {
  MyApplicationTheme() {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colors.background
    ) {
      big_button(ComponentActivity(), big_button_data(painterResource(R.drawable.ic_baseline_article_32), "Справка") {})
    }
  }
}