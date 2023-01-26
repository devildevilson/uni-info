package org.uni.info_app

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.StandardCharsets

class android_security_file: security_file_interface {
  override fun is_exists(ctx: Any, file_name: String) : Boolean {
    if (ctx !is Context) throw ClassCastException("'ctx' variable must be Context type in android environment")
    val file = File(ctx.filesDir, file_name)
    return file.exists()
  }

  override fun create(ctx: Any, file_name: String, content: String) {
    if (ctx !is Context) throw ClassCastException("'ctx' variable must be Context type in android environment")
    // this is equivalent to using deprecated MasterKeys.AES256_GCM_SPEC
    val spec = KeyGenParameterSpec.Builder(
      MasterKey.DEFAULT_MASTER_KEY_ALIAS,
      KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    )
      .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
      .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
      .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
      .build()

    val master_key = MasterKey.Builder(ctx)
      .setKeyGenParameterSpec(spec)
      .build()

    // Create a file with this name or replace an entire existing file
    // that has the same name. Note that you cannot append to an existing file,
    // and the filename cannot contain path separators.
    val encrypted_file_write = EncryptedFile.Builder(
      ctx,
      File(ctx.filesDir, file_name),
      master_key,
      EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
    ).build()

    // тут видимо зададим данные сессии, что там должно быть?
    // наверное в виде username=... , token=...
    // загрузим оттуда данные при старте приложения
    val file_content = content.toByteArray(StandardCharsets.UTF_8)
    encrypted_file_write.openFileOutput().apply {
      write(file_content)
      flush()
      close()
    }
  }

  override fun load(ctx: Any, file_name: String) : String {
    if (ctx !is Context) throw ClassCastException("'ctx' variable must be Context type in android environment")
    if (!is_exists(ctx, file_name)) return ""

    // this is equivalent to using deprecated MasterKeys.AES256_GCM_SPEC
    val spec = KeyGenParameterSpec.Builder(
      MasterKey.DEFAULT_MASTER_KEY_ALIAS,
      KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    )
      .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
      .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
      .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
      .build()

    val master_key = MasterKey.Builder(ctx)
      .setKeyGenParameterSpec(spec)
      .build()

    val encrypted_file_read = EncryptedFile.Builder(
      ctx,
      File(ctx.filesDir, file_name),
      master_key,
      EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
    ).build()

    val input_stream = encrypted_file_read.openFileInput()
    val output_stream = ByteArrayOutputStream()
    var next_byte: Int = input_stream.read()
    while (next_byte != -1) {
      output_stream.write(next_byte)
      next_byte = input_stream.read()
    }

    return output_stream.toString("UTF-8")
  }
}

// не работают дженерики =(
actual fun get_security_file(): security_file_interface {
  return android_security_file()
}