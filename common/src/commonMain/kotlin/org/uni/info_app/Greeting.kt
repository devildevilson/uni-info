package org.uni.info_app

class Greeting {
  private val platform: Platform = getPlatform()

  fun greet(): String {

    return "Hello, ${platform.name}!"
  }
}