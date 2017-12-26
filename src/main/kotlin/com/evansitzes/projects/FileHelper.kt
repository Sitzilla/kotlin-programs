package com.evansitzes.projects

import java.io.File

class FileHelper {

    companion object {
        fun writeToFile(fileName: String, textArray: Array<String>) {
            for (text in textArray) {
                File(fileName).printWriter().use { out -> out.println(text) }
            }
        }
    }
}