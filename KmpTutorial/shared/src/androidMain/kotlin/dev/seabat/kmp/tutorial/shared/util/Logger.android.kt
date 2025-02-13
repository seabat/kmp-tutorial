package dev.seabat.kmp.tutorial.shared.util

actual fun log(message: String) {
    android.util.Log.d("KmpMultiModule", message)
}