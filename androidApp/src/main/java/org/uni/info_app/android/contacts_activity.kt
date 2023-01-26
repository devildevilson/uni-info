package org.uni.info_app.android

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity


class contacts_activity: base_activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // здесь еще добавится нижний бар с кнопками соцсетей

    setContent {
      MyApplicationTheme() {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          Scaffold(
            topBar = { ui_utility.additional_top_appbar(this, getString(R.string.activity_contacts)) },
            bottomBar = { bottom_bar(this) }
          ) {
            main_surface(this, it)
          }
        }
      }
    }
  }

  @Composable
  fun main_surface(activity: ComponentActivity, padding: PaddingValues) {
    // что тут? сначала 3 крупные кнопки
    // затем 3 кнопки с телефонами списком (с разделителями)
    // затем еще 3 кнопки но их потом

    Box(
      Modifier
        .fillMaxSize()
        .padding(padding)
    ) {
      Column(
        modifier = Modifier
          .align(Alignment.TopCenter)
          .verticalScroll(rememberScrollState())
          .padding(20.dp, 40.dp),
        //horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Row(
          //verticalAlignment = Alignment.CenterVertically,
          //horizontalArrangement = Arrangement.spacedBy(40.dp)
        ) {
          feedback_button_item(activity, painterResource(R.drawable.ic_baseline_language_32), getString(R.string.go_to_site)) {
            // стартуем браузер с ссылкой
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            startActivity(browserIntent)
          }

          feedback_button_item(activity, painterResource(R.drawable.ic_baseline_contact_mail_32), getString(R.string.feedback_text)) {
//            val intent = Intent(Intent.ACTION_SEND)
//            intent.type = "message/rfc822"
//            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("andrewlemeshev@gmail.com"))
//            startActivity(Intent.createChooser(intent, "Send Email"))
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("mailto:andrewlemeshev@gmail.com") // ?subject=" + Uri.encode(subject) + "&body=" + Uri.encode(body)
            startActivity(intent)
          }

          feedback_button_item(activity, painterResource(R.drawable.ic_baseline_mail_32), getString(R.string.feedback_media_text)) {
//            val intent = Intent(Intent.ACTION_SEND)
//            intent.type = "message/rfc822"
//            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("andrewlemeshev@gmail.com"))
//            startActivity(Intent.createChooser(intent, "Send Email"))
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("mailto:andrewlemeshev@gmail.com") // ?subject=" + Uri.encode(subject) + "&body=" + Uri.encode(body)
            startActivity(intent)
          }
        }

        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 40.dp),
          contentAlignment = Alignment.Center
        ) {
          Surface(
            modifier = Modifier
              .fillMaxWidth(1.0f),
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colors.surface
          ) {
            Box(
              contentAlignment = Alignment.TopCenter
            ) {
              Surface(
                modifier = Modifier.fillMaxWidth(0.87f),
              ) {
                Box(
                  //contentAlignment = Alignment.CenterStart
                ) {
                  Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                  ) {
                    phone_button_item(activity, "tel:9999", "9999", activity.getString(R.string.contacts_phone_info1));

                    Divider(color = Color.Black, thickness = 1.dp)

                    phone_button_item(activity, "tel:+77476104485", "+7(747)610 44 85", activity.getString(R.string.contacts_phone_info2));

                    Divider(color = Color.Black, thickness = 1.dp)

                    phone_button_item(activity, "tel:+77476104485", "+7(747)610 44 85", activity.getString(R.string.contacts_phone_info3));
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

@Composable
fun bottom_bar(a: ComponentActivity) {
  // тут область с несколькими кнопками соц сетей
  BottomAppBar() {
    Box(Modifier.fillMaxSize(), Alignment.Center) {
      Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Box(Modifier.fillMaxHeight(), Alignment.Center) {
          Text(a.getString(R.string.contacts_write_us))
        }

        small_contact_icon(R.drawable.telegram_icon) {
          start_messenger_or_market(a, "https://t.me/andrewlemeshev123", "org.telegram.messenger")
        }
        small_contact_icon(R.drawable.whatsapp_icon) {
          start_messenger_or_market(a, "whatsapp://send?phone=+77476104485", "com.whatsapp")
        }
      }
    }
  }
}

fun start_messenger_or_market(a: ComponentActivity, uri_intent: String, package_name: String) {
  try {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri_intent))
    intent.setPackage(package_name)
    a.startActivity(intent)
  } catch (e: Exception) {
    try {
      a.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$package_name")))
    } catch(e: ActivityNotFoundException) {
      a.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$package_name")))
    }
  }
}

@Composable
fun small_contact_icon(icon_id: Int, on_click: () -> Unit) {
  Box(Modifier.fillMaxHeight(), Alignment.Center) {
    Image(
      painterResource(icon_id),
      "",
      Modifier
        .fillMaxHeight(0.6f)
        .aspectRatio(1.0f)
        .clickable(onClick = on_click),
      contentScale = ContentScale.Fit
    )
  }
}

@Composable
fun RowScope.feedback_button_item(a: ComponentActivity, icon: Painter, info_text: String, on_click: () -> Unit) {
  Column(
    modifier =  Modifier.weight(1.0f),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    Button(
      modifier = Modifier.size(70.dp),
      shape = CircleShape,
      colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
      contentPadding = PaddingValues(0.dp),
      onClick = on_click,
    ) {
      Icon(
        modifier = Modifier.size(50.dp),
        painter = icon,
        contentDescription = ""
      )
    }
    Text(text = info_text, textAlign = TextAlign.Center)
  }
}

@Composable
fun phone_button_item(a: ComponentActivity, uri: String, phone: String, info: String) {
  Button(
    modifier = Modifier
      .fillMaxWidth()
      .height(75.dp),
    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
    shape = RoundedCornerShape(0.dp),
    elevation = null,
    onClick = {
      a.startActivity(Intent(Intent.ACTION_DIAL).setData(Uri.parse(uri)))
    }
  ) {
    Box(Modifier.fillMaxWidth(), Alignment.CenterStart) {
      Row(
        //verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(1.0f),
          shape = RoundedCornerShape(10.dp),
          color = MaterialTheme.colors.primary
        ) {
          Icon(
            modifier = Modifier
              .fillMaxHeight()
              .aspectRatio(1.0f),
            imageVector = Icons.Filled.Phone,
            contentDescription = "",
          )
        }

        phone_info(phone, info)
      }
    }
  }
}

@Composable
fun phone_info(phone: String, info: String) {
  Box(Modifier.fillMaxHeight(), Alignment.CenterStart) {
    Column(
      Modifier.padding(10.dp, 0.dp),
      //verticalArrangement = Arrangement.Center
    ) {
      Text(text = phone, fontSize = 17.sp, color = MaterialTheme.colors.primary)
      Text(text = info, fontSize = 13.sp)
    }
  }
}