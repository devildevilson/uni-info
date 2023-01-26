package org.uni.info_app.android

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

interface content_provider_interface {
  fun need_auth(): Boolean

  @Composable
  fun get_content(paddings: PaddingValues)
}