package org.uni.info_app.android.content

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.uni.info_app.android.R
import org.uni.info_app.android.brief_info_activity
import org.uni.info_app.android.content_provider_interface
import org.uni.info_app.android.login_activity


class home_content_provider(val activity: ComponentActivity) : content_provider_interface {
  override fun need_auth() = false

  @Composable
  override fun get_content(paddings: PaddingValues) {
    val random_news = listOf(
      "Ключевые показатели университета на 1 января 2023",
      "Новый онлайн-сервис для обучающихся в университете",
      "Нововведения 2023 года",
      "Как работает Соглашение о сотрудничестве университетов",
      "Могут ли студенты получить карту других банков?",
      "Услуги университета - в онлайн"
    )

    val about_buttons_string = listOf(
      R.string.home_brief_info,
      R.string.home_key_metrics
    )

    Box(
      Modifier
        .fillMaxSize()
        .padding(paddings))
    {
      Column(
        Modifier
          .fillMaxWidth()
          .verticalScroll(rememberScrollState())
          .padding(10.dp, 20.dp)) {
        Row(
          Modifier
            .height(125.dp)
            .horizontalScroll(rememberScrollState()),
          Arrangement.SpaceEvenly
        ) {
          random_news.forEachIndexed { index, text ->
            article_button_item(index, text) {}
          }
        }

        Box(Modifier.padding(10.dp)) {
          Text(activity.getString(R.string.home_popular_services), fontSize = 30.sp)
        }

        Surface(
          modifier = Modifier.fillMaxWidth(1.0f),
          shape = RoundedCornerShape(10.dp),
          color = MaterialTheme.colors.surface
        ) {
          Box(Modifier.fillMaxWidth(1.0f), Alignment.TopCenter) {
            Column(Modifier.fillMaxWidth(0.9f)) {
              pop_service_button_item("Услуга") {}

              Divider(color = Color.Black, thickness = 1.dp)

              pop_service_button_item("Услуга") {}
            }
          }
        }

        Box(Modifier.padding(10.dp)) {
          Text(activity.getString(R.string.home_about_university), fontSize = 30.sp)
        }

        Row(
          Modifier
            .height(175.dp)
            .horizontalScroll(rememberScrollState()),
          Arrangement.SpaceEvenly
        ) {
          about_buttons_string.forEachIndexed { index, s_id ->
            big_about_button(index, activity.getString(s_id)) {
              if (s_id == R.string.home_brief_info) {
                activity.startActivity(Intent(activity, brief_info_activity::class.java))
              }
            }
          }
        }

        Box(Modifier.padding(10.dp)) {
          Text(activity.getString(R.string.home_university_on_map), fontSize = 30.sp)
        }

        big_map_button() {
          val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.google.com/maps/place/%D0%9C%D0%B5%D0%B4%D0%B8%D1%86%D0%B8%D0%BD%D1%81%D0%BA%D0%B8%D0%B9+%D1%83%D0%BD%D0%B8%D0%B2%D0%B5%D1%80%D1%81%D0%B8%D1%82%D0%B5%D1%82+%D0%90%D1%81%D1%82%D0%B0%D0%BD%D0%B0/@51.1815178,71.4161697,14z/data=!4m5!3m4!1s0x424587329e743275:0xc9b5b211a27335c2!8m2!3d51.1816297!4d71.4164221")
          )
          intent.setPackage("com.google.android.apps.maps")
          activity.startActivity(intent)
        }
      }
//      Text(
//        text = activity.getString(R.string.nav_home),
//        textAlign = TextAlign.Center,
//        modifier = Modifier.align(Alignment.Center)
//      )
    }
  }
}

val maximum_text_symbols_count = 35

fun cut_text(text: String): String {
  if (text.length <= maximum_text_symbols_count) return text
  val mod_text = text.substring(0, maximum_text_symbols_count-3)
  return "$mod_text..."
}

//a: ComponentActivity,
@Composable
fun article_button_item(index: Int, text: String, on_click: () -> Unit) {
  val final_text = cut_text(text)
  val even = index % 2 == 0
  val stroke_color = if (even) Color.Red else Color.Green

  Button(
    modifier = Modifier
      .fillMaxHeight()
      .aspectRatio(1.0f)
      .padding(5.dp),
    shape = RoundedCornerShape(10.dp),
    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
    border = BorderStroke(3.dp, stroke_color),
    onClick = on_click
  ) {
    Box(Modifier.fillMaxSize(), Alignment.Center) {
      Text(text = final_text, textAlign = TextAlign.Center)
    }
  }
}

@Composable
fun pop_service_button_item(info: String, on_click: () -> Unit) {
  Button(
    modifier = Modifier
      .fillMaxWidth()
      .height(75.dp),
    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
    shape = RoundedCornerShape(0.dp),
    elevation = null,
    onClick = on_click
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

        Box(
          Modifier
            .fillMaxSize()
            .padding(10.dp), Alignment.CenterStart) {
          Text(info) //, textAlign = TextAlign.Center
        }
      }
    }
  }
}

@Composable
fun big_about_button(index: Int, info: String, on_click: () -> Unit) {
  val even = index % 2 == 0
  val button_color = if (even) Color.Red else Color.Green

  Button(
    modifier = Modifier
      .fillMaxHeight()
      .aspectRatio(1.0f)
      .padding(5.dp),
    shape = RoundedCornerShape(10.dp),
    colors = ButtonDefaults.buttonColors(backgroundColor = button_color),
    onClick = on_click
  ) {
    Box(Modifier.fillMaxSize(), Alignment.Center) {
      Text(text = info, textAlign = TextAlign.Center, color = Color.White)
    }
  }
}

@Composable
fun big_map_button(on_click: () -> Unit) {
  Image(
    modifier = Modifier
      .height(125.dp)
      .fillMaxWidth()
      .clickable(onClick = on_click),
    painter = painterResource(R.drawable.big_map_med_uni),
    contentDescription = "",
    contentScale = ContentScale.Crop
  )
}