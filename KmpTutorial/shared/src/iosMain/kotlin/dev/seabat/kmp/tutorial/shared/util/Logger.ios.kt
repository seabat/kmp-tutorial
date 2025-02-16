package dev.seabat.kmp.tutorial.shared.util

import platform.Foundation.NSLog

actual fun log(message: String) {
    NSLog(message)
}