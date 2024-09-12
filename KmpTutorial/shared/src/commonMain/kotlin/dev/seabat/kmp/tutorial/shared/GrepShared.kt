package dev.seabat.kmp.tutorial.shared

fun grep(lines: List<String>, pattern: String, action: (String) -> Unit) {
    val regex = pattern.toRegex()
    return lines.filter(regex::containsMatchIn)
        .forEach { line ->
            action(line)
        }
}