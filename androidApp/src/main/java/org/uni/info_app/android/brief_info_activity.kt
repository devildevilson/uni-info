package org.uni.info_app.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class brief_info_activity: base_activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val article1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam aliquet elementum purus sed egestas. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut dignissim dolor non odio pharetra feugiat. Donec eleifend semper tellus, vitae finibus felis vestibulum et. Praesent consequat magna ligula, et gravida sem maximus id. Fusce venenatis, quam quis accumsan elementum, metus arcu consectetur eros, at tincidunt purus ante eget massa. Nulla ut aliquet sapien, eu blandit tellus. Sed quis imperdiet arcu, vel luctus arcu. Interdum et malesuada fames ac ante ipsum primis in faucibus. Duis facilisis nulla lectus, vitae rutrum sem scelerisque eget. Nullam at mi odio. Nam ultrices ullamcorper feugiat."
    val article2 = "Mauris sagittis odio ipsum, nec elementum odio placerat et. Duis ornare ligula nec accumsan convallis. Sed consequat diam a mauris accumsan ultrices. Suspendisse potenti. Phasellus facilisis elementum nisi, at ultricies felis ultrices nec. Aenean vel venenatis est. Etiam a enim luctus, sagittis nisl at, pretium ipsum. Cras volutpat augue ut euismod euismod. Pellentesque eu sapien quis sapien vehicula volutpat."
    val article3 = "Phasellus pellentesque tempor enim scelerisque posuere. Aliquam nulla nisl, maximus eu arcu nec, maximus consequat ligula. Aenean efficitur, orci quis consequat dictum, ante lectus condimentum lectus, a porttitor metus nisi et tellus. In accumsan, dolor sit amet ultricies convallis, magna elit vulputate quam, eu porttitor lorem mi pellentesque mauris. Vestibulum facilisis nunc sit amet ex auctor, in dignissim lectus convallis. Integer feugiat odio a ligula congue, vitae posuere erat consectetur. Ut aliquet eleifend lectus posuere tempor. Duis eget neque sed ante lobortis consequat eu id tortor. Etiam feugiat justo vitae pharetra feugiat. Vivamus egestas egestas nunc id iaculis. Nulla at sollicitudin eros, sed sagittis arcu. Duis iaculis nunc id augue fermentum venenatis. Suspendisse dictum est at lorem sagittis dictum."
    val article4 = "Pellentesque vulputate mi vehicula, pulvinar lectus eu, condimentum lacus. Quisque iaculis ultrices sapien in maximus. Integer sed euismod magna. Cras mollis rhoncus urna non varius. Duis at rhoncus enim. Suspendisse euismod pharetra velit id consequat. Praesent convallis, elit sit amet faucibus efficitur, ante urna congue risus, at aliquam dolor neque sed metus. Sed sollicitudin volutpat nibh, vel fermentum nisl lacinia efficitur. Cras mollis blandit odio, vitae mollis tortor posuere eget."
    val article5 = "Curabitur porta venenatis risus, at ultrices nibh luctus id. Nulla accumsan fermentum sapien, semper venenatis felis mollis vel. Aenean ligula ante, posuere a arcu quis, suscipit accumsan elit. Ut id scelerisque libero. Nunc congue scelerisque nulla. Ut commodo dictum dui, id egestas risus pulvinar venenatis. Nullam ornare nisi vel volutpat dictum."
    val article6 = "In aliquet tortor ultricies massa vulputate fringilla. Sed lorem ligula, maximus vitae nisi rhoncus, finibus elementum felis. Quisque vitae neque tristique, egestas erat ut, dignissim orci. Sed lacus elit, sollicitudin quis malesuada sit amet, condimentum non eros. Proin eu felis elit. Praesent non quam urna. Vivamus auctor facilisis nulla, sed iaculis neque. Duis tincidunt a lectus congue imperdiet. Donec sit amet lectus faucibus, egestas elit vel, venenatis lectus. Maecenas eget gravida ligula. Aliquam quis consectetur leo. Nullam luctus risus lectus, scelerisque rutrum leo euismod sed. Donec vel tortor velit. Mauris vulputate eros id bibendum pharetra. Pellentesque faucibus, augue id varius egestas, arcu nulla varius eros, ac pulvinar orci diam a justo."
    val article7 = "Nam efficitur et felis a tempus. Donec ipsum urna, euismod id rhoncus in, volutpat id ex. Fusce quam lorem, dignissim et tellus ac, porttitor molestie lorem. Donec condimentum maximus massa sed finibus. Praesent metus sapien, aliquet sit amet lacus et, mattis condimentum quam. Nullam dapibus eu eros quis congue. Pellentesque id risus id massa congue faucibus. In nec dui nec diam dictum laoreet. Praesent vitae felis urna. Quisque ut fringilla tellus. Maecenas elementum, est quis efficitur vestibulum, velit justo bibendum quam, sit amet dignissim tortor ipsum non orci. Morbi consectetur ex in ex fermentum, vel sollicitudin metus lacinia."

    setContent {
      MyApplicationTheme {
        Scaffold(topBar = { ui_utility.additional_top_appbar_variant(this, getString(R.string.home_brief_info)) }) {
          Box(Modifier.padding(it).fillMaxSize()) {
            Column(Modifier.verticalScroll(rememberScrollState()).padding(5.dp)) {
              title_and_article("Title1", article1)
              title_and_article("Title2", article2)
              title_and_article("Title3", article3)
              title_and_article("Title4", article4)
              title_and_article("Title5", article5)
              title_and_article("Title6", article6)
            }
          }
        }
      }
    }
  }

  @Composable
  fun title_and_article(title: String, article: String) {
    Column(Modifier.fillMaxWidth().padding(5.dp)) {
      Box(Modifier.fillMaxWidth(), Alignment.Center) {
        Text(title, fontSize = 20.sp, color = MaterialTheme.colors.primary)
      }

      Text(article, softWrap = true)
    }
  }
}