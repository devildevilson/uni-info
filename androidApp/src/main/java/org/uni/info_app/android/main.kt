package org.uni.info_app.android

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.uni.info_app.android.content.account_content_provider
import org.uni.info_app.android.content.home_content_provider
import org.uni.info_app.android.content.services_content_provider
import org.uni.info_app.android.content.settings_content_provider

// кредиты картинки для приложения
// <a href="https://www.flaticon.com/free-icons/study" title="study icons">Study icons created by Freepik - Flaticon</a>

enum class Page(val title_id: Int, val content: String, val icon: ImageVector, val create_provider: (activity: ComponentActivity) -> content_provider_interface) {
  HOME(R.string.nav_home,"Show only icon", Icons.Filled.Home, { activity: ComponentActivity -> home_content_provider(activity) }),
  SERVICES(R.string.nav_services,"Show icon with label", Icons.Filled.List, { activity: ComponentActivity -> services_content_provider(activity) }),
  ACCOUNT(R.string.nav_account,"Show icon with label", Icons.Filled.Person, { activity: ComponentActivity -> account_content_provider(activity) }),
  SETTINGS(R.string.nav_settings,"Show icon /Show the label only when selected", Icons.Filled.Settings, { activity: ComponentActivity -> settings_content_provider(activity) })
}

class main : base_activity() {
  var content_provider = mutableStateOf<content_provider_interface>(home_content_provider(this))

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val pages = Page.values()
    //content_provider.postValue(home_content_provider())

    // как сделать аккуратно другое активити и получение информации из него
    // + нужно сделать сохранение шифрованного файла на диск

    setContent {
      var selected_item by remember { mutableStateOf(0) }

      val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
          val data: Intent = it.data!!
          val page_index = data.extras!!.getInt("page_index")
          selected_item = page_index
          content_provider.value = pages[page_index].create_provider(this)
        }
      }

      MyApplicationTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          //GreetingView(Greeting().greet())
          //top_appbar()
          Scaffold(
            topBar = { ui_utility.main_top_appbar(this) },
            bottomBar = {
              bottom_navigation_bar(pages, this, selected_item, { selected_item = it }) { provider, index ->
                if (provider.need_auth()) {
                  // запускаем новое активити, в композе все по другому
                  val intent = Intent(this, login_activity::class.java)
                  intent.putExtra("page_index", index)
                  intent.putExtra("title_id", pages[index].title_id)
                  launcher.launch(intent)
                  false
                } else {
                  content_provider.value = provider
                  true
                }
              }
            }
          ) {
            content_provider.value.get_content(it)
          }
        }
      }
    }
  }
}

@Composable
fun GreetingView(text: String) {
  Text(text = text)
}

// как переходить?
// локализация? локализация делается через строки в xml
@Composable
fun bottom_navigation_bar(navs: Array<Page>, activity: ComponentActivity, selected_item: Int, index_trigger: (Int) -> Unit, change_provider: (provider: content_provider_interface, index: Int) -> Boolean) {
  //var selected_item by remember { mutableStateOf(0) }
  //val provider: content_provider_interface? by provider_live.observeAsState()

  Column(verticalArrangement = Arrangement.Bottom) {
    BottomNavigation() {
      navs.forEachIndexed { index, item ->
        BottomNavigationItem(
          label = { Text(stringResource(item.title_id)) },
          icon = { Icon(item.icon, contentDescription = "") },
          selected = selected_item == index,
          onClick = {
            val ret = change_provider(item.create_provider(activity), index)
            //provider_live.value = (item.create_provider())
            //if (ret) { selected_item = index }
            if (ret) index_trigger(index)
          },
          alwaysShowLabel = false
        )
      }
    }
  }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun top_appbar() {
  // две кнопки по бокам и по центру какое то изображение
  Scaffold(
    topBar = {
      TopAppBar {
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(0.5f),
            horizontalArrangement = Arrangement.Start
          ) {
            Icon(
              imageVector = Icons.Filled.Phone,
              contentDescription = "",
              modifier = Modifier
                .padding(10.dp)
                .fillMaxHeight()
                .aspectRatio(1f)
            )
          }

          // fillMaxWidth от того что осталось? почему так?
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
          ) {
            Icon(
              imageVector = Icons.Filled.Notifications,
              contentDescription = "",
              modifier = Modifier
                .padding(10.dp)
                .fillMaxHeight()
                .aspectRatio(1f)
            )
          }
        }
      }
    }
  ) {

  }
}

@Preview
@Composable
fun default_preview() {
  val pages = Page.values()
  val content_provider = remember { mutableStateOf<content_provider_interface>(home_content_provider(ComponentActivity())) }
  var selected_item by remember { mutableStateOf(0) }

  MyApplicationTheme {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colors.background
    ) {
      //GreetingView("Hello, Android!")
      //top_appbar()
      Scaffold(
        topBar = { ui_utility.main_top_appbar(ComponentActivity()) },
        bottomBar = {
          bottom_navigation_bar(pages, ComponentActivity(), selected_item, { selected_item = it }) { new_provider, index ->
            content_provider.value = new_provider
            true
          }
        }
      ) {
        content_provider.value.get_content(it)
      }
    }
  }
}
