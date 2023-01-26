package org.uni.info_app.android

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.uni.info_app.get_security_file
import org.uni.info_app.simple_serialization

class login_activity : base_activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // вообще было бы неплохо передавать сюда информацию откуда мы пришли
    val loc_id = intent.getIntExtra("title_id", R.string.nav_account)
    val page_index = intent.getIntExtra("page_index", 0)

    setContent {
      MyApplicationTheme() {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          Scaffold(
            topBar = { ui_utility.additional_top_appbar(this,  getString(loc_id)) }
          ) {
            main_surface(this, it, page_index)
          }
        }
      }
    }
  }

}

// тут топбар другой, их я так понимаю всего два будет, надо вынести в отдельный файл

@Composable
fun main_surface(activity: ComponentActivity, padding: PaddingValues, page_index: Int) {
  var username by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }

  val top_padding = padding.calculateTopPadding()
  Box(
    modifier = Modifier
      //.padding(vertical = top_padding*2)
      .fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth(0.9f)
        .aspectRatio(1.0f),
      contentAlignment = Alignment.TopCenter
    ) {
      Text(text = activity.getString(R.string.sign_in_text), fontSize = 30.sp)
    }
    Box(
      modifier = Modifier
        .fillMaxWidth(0.9f)
        .aspectRatio(1.0f),
      contentAlignment = Alignment.Center,
    ) {
      Column(
        //modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center
      ) {
        TextField(
          value = username,
          onValueChange = { username = it },
          leadingIcon = {
            Icon(
              imageVector = Icons.Filled.Person,
              contentDescription = ""
            )
          },
          placeholder = { Text(text = activity.getString(R.string.username_placeholder)) }
        )
        TextField(
          value = password,
          onValueChange = { password = it },
          leadingIcon = {
            Icon(
              imageVector = Icons.Filled.Warning,
              contentDescription = ""
            )
          },
          placeholder = { Text(text = activity.getString(R.string.password_placeholder)) }
        )
        Button(
          // логинимся, сохраняем данные на диск, возвращаем обратно результат с индексом
          onClick = {
            // по идее делаем запрос к апи серверу, который займет понятное дело время
            // пока запрос идет надо поменять текст у кнопки, и после того как запрос вернул нам данные
            // только после этого завершаем активити
            if (username == "admin" && password == "admin") {
              Toast.makeText(activity, "LOGIN SUCCESSFULLY", Toast.LENGTH_SHORT).show()
              val file_content = simple_serialization.save(mapOf("username" to username, "password" to password))
              get_security_file().create(activity.applicationContext, userdata_utility.file_name, file_content)
              val intent = Intent()
              intent.putExtra("page_index", page_index)
              activity.setResult(RESULT_OK, intent)
              activity.finish()
            } else {
              Toast.makeText(activity, "LOGIN FAILED", Toast.LENGTH_SHORT).show()
            }
          },
        ) {
          Text(text = activity.getString(R.string.login_text))
        }
      }
    }
  }
}

@Preview
@Composable
fun default_login_page() {
  var username by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }

  val activity = ComponentActivity()

  MyApplicationTheme() {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colors.background
    ) {
      Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { ui_utility.additional_top_appbar(activity, "Account") }
      ) {
        val top_padding = it.calculateTopPadding()
        Box(
          modifier = Modifier
            //.padding(vertical = top_padding*2)
            .fillMaxSize(),
          contentAlignment = Alignment.Center,
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth(0.9f)
              .aspectRatio(1.0f),
            contentAlignment = Alignment.TopCenter
          ) {
            Text(text = "Sign In", fontSize = 30.sp)
          }
          Box(
            modifier = Modifier
              .fillMaxWidth(0.9f)
              .aspectRatio(1.0f),
            contentAlignment = Alignment.Center,
          ) {
            Column(
              //modifier = Modifier.fillMaxSize(),
              horizontalAlignment = Alignment.CenterHorizontally,
              //verticalArrangement = Arrangement.Center
            ) {
              TextField(
                value = username,
                onValueChange = { username = it },
                leadingIcon = {
                  Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = ""
                  )
                },
                placeholder = { Text(text = "placeholder") }
              )
              TextField(
                value = password,
                onValueChange = { password = it },
                leadingIcon = {
                  Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = ""
                  )
                },
                placeholder = { Text(text = "placeholder") }
              )
              Button(
                // логинимся, сохраняем данные на диск, возвращаем обратно результат с индексом
                onClick = { /*TODO*/ },
              ) {
                Text(text = "Login")
              }
            }
          }
        }
      }
    }
  }
}