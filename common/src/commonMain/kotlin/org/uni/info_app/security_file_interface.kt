package org.uni.info_app

interface security_file_interface {
  fun is_exists(ctx: Any, file_name: String): Boolean
  fun create(ctx: Any, file_name: String, content: String)
  fun load(ctx: Any, file_name: String): String
}

expect fun get_security_file(): security_file_interface