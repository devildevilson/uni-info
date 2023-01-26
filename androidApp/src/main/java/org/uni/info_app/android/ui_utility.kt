package org.uni.info_app.android

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.compose.runtime.Composable

object ui_utility {
  @Composable
  fun main_top_appbar(activity: ComponentActivity) {
    // две кнопки по бокам и по центру какое то изображение
//    Scaffold(
//      topBar = {
        TopAppBar {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              modifier = Modifier.weight(1.0f),
              horizontalArrangement = Arrangement.Start
            ) {
              Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = "",
                modifier = Modifier
                  .padding(10.dp)
                  .fillMaxHeight()
                  .aspectRatio(1f)
                  .clickable {
                    val intent = Intent(activity, contacts_activity::class.java)
                    activity.startActivity(intent)
                  }
              )
            }

            // fillMaxWidth от того что осталось? почему так?
            Row(
              modifier = Modifier.weight(1.0f),
              horizontalArrangement = Arrangement.End
            ) {
              Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "",
                modifier = Modifier
                  .padding(10.dp)
                  .fillMaxHeight()
                  .aspectRatio(1f)
                  .clickable {
                    val intent = Intent(activity, notifications_activity::class.java)
                    activity.startActivity(intent)
                  }
              )
            }
          }
        }
//      }
//    ) {
//
//    }
  }

  @Composable
  fun additional_top_appbar(activity: ComponentActivity, title: String) {
    // кнопка возврата, по центру надпись + возможно что то справа
    // как сделать возврат? activity.finish()?
    TopAppBar(
      //modifier = Modifier.height()
      //backgroundColor = Color.White
    ) {
      Box(modifier = Modifier.fillMaxSize()) {
        Box(
          //modifier = Modifier.fillMaxWidth(0.2f),
          contentAlignment = Alignment.CenterStart,
        ) {
          IconButton(
            onClick = { activity.finish() },
            modifier = Modifier
              .padding(10.dp)
              .fillMaxHeight()
              .aspectRatio(1f)
          ) {
            Icon(
              imageVector = Icons.Filled.ArrowBack,
              contentDescription = "",
              modifier = Modifier.fillMaxSize()
            )
          }
        }

        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center,
        ) {
          Text(text = title, fontSize = 23.sp)
        }
      }
    }
  }
}