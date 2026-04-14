package com.dexfan.tool

import java.io.File
import java.nio.file.Files
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ProbeLogRetentionTest {
    @Test
    fun trimKeepsNewestFilesOnly() {
        val logDirectory = Files.createTempDirectory("probe-log-retention").toFile()

        try {
            val oldest = createLogFile(logDirectory, "oldest.txt", 1_000L)
            val middle = createLogFile(logDirectory, "middle.txt", 2_000L)
            val newest = createLogFile(logDirectory, "newest.txt", 3_000L)

            val deletedCount = ProbeLogRetention.trim(logDirectory, maxFiles = 2)

            assertEquals(1, deletedCount)
            assertFalse(oldest.exists())
            assertTrue(middle.exists())
            assertTrue(newest.exists())
        } finally {
            deleteRecursively(logDirectory)
        }
    }

    @Test
    fun trimDoesNothingWhenUnderLimit() {
        val logDirectory = Files.createTempDirectory("probe-log-retention").toFile()

        try {
            createLogFile(logDirectory, "only.txt", 1_000L)

            val deletedCount = ProbeLogRetention.trim(logDirectory, maxFiles = 2)

            assertEquals(0, deletedCount)
            assertEquals(1, logDirectory.listFiles()?.size)
        } finally {
            deleteRecursively(logDirectory)
        }
    }

    private fun createLogFile(directory: File, name: String, lastModified: Long): File {
        return File(directory, name).apply {
            writeText(name)
            setLastModified(lastModified)
        }
    }

    private fun deleteRecursively(file: File) {
        if (file.isDirectory) {
            file.listFiles()?.forEach(::deleteRecursively)
        }

        file.delete()
    }
}