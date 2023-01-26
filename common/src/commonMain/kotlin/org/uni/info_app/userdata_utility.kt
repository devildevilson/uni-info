package org.uni.info_app.android

import org.uni.info_app.get_security_file
import org.uni.info_app.simple_serialization

data class userdata_t(val username : String, val password: String)

object userdata_utility {
  val file_name = "session_file.txt"

  fun make_user_data(fields : List<String>) : userdata_t? {
    var un = ""
    var p = ""
    for (field in fields) {
      val pair = field.split("=")
      if (pair[0] == "username") un = pair[1]
      if (pair[0] == "password") p = pair[1]
    }

    if (un == "" || p == "") return null
    return userdata_t(un, p)
  }

  fun make_user_data(map : Map<String, String>) : userdata_t? {
    val n = map["username"]
    val p = map["password"]
    if (n == null || p == null) return null
    return userdata_t(n, p)
  }

  fun load_userdata(ctx: Any) : userdata_t? {
    val file_man = get_security_file()
    val file_content = file_man.load(ctx, file_name)
    if (file_content == "") return null

    val map = simple_serialization.load(file_content)
    return make_user_data(map)

//    val fields = file_content.split("\n")
//    return make_user_data(fields)
  }
}