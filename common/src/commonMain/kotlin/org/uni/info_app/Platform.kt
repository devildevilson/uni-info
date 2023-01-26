package org.uni.info_app

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform