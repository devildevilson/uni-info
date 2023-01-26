package org.uni.info_app

class arm64_security_file: security_file_interface {
  override fun is_exists(ctx: Any, file_name: String): Boolean {
    return false
  }

  override fun create(ctx: Any, file_name: String, content: String) {

  }

  override fun load(ctx: Any, file_name: String): String {
    return ""
  }
}

actual fun get_security_file(): security_file_interface {
  return arm64_security_file()
}