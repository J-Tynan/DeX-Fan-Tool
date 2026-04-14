package com.dexfan.tool

import android.util.Log
import java.io.File
import java.io.IOException
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class ProbeLogResult(
    val savedFileName: String? = null,
    val errorMessage: String? = null,
)

object ProbeLogRetention {
    fun trim(logDirectory: File, maxFiles: Int): Int {
        if (maxFiles < 1 || !logDirectory.exists()) {
            return 0
        }

        val files = logDirectory.listFiles { file -> file.isFile }
            ?.sortedWith(compareByDescending<File> { it.lastModified() }.thenByDescending { it.name })
            ?: return 0

        if (files.size <= maxFiles) {
            return 0
        }

        var deletedCount = 0

        files.drop(maxFiles).forEach { file ->
            if (file.delete()) {
                deletedCount += 1
            }
        }

        return deletedCount
    }
}

class ProbeLogger(
    private val filesDirectory: File,
    private val clock: Clock = Clock.systemDefaultZone(),
    private val maxLogFiles: Int = DEFAULT_MAX_LOG_FILES,
) {
    fun logSnapshot(snapshot: UsbProbeSnapshot, report: String): ProbeLogResult {
        logSnapshotState(snapshot)
        Log.i(LOG_TAG, report)

        return try {
            val logDirectory = File(filesDirectory, logDirectoryName)
            if (!logDirectory.exists() && !logDirectory.mkdirs()) {
                throw IOException("Unable to create ${logDirectory.absolutePath}")
            }

            val timestamp = fileTimestampFormatter.format(Instant.now(clock).atZone(clock.zone))
            val logFile = File(logDirectory, "usb-probe-$timestamp.txt")
            logFile.writeText(buildLogFileContents(report))

            val deletedCount = ProbeLogRetention.trim(logDirectory, maxLogFiles)
            if (deletedCount > 0) {
                Log.i(LOG_TAG, "Trimmed $deletedCount old probe log(s).")
            }

            Log.i(LOG_TAG, "Saved probe report to ${logFile.absolutePath}")
            ProbeLogResult(savedFileName = logFile.name)
        } catch (error: IOException) {
            Log.e(LOG_TAG, "Failed to save probe report.", error)
            ProbeLogResult(errorMessage = error.message ?: error.javaClass.simpleName)
        }
    }

    private fun buildLogFileContents(report: String): String {
        val generatedAt = displayTimestampFormatter.format(Instant.now(clock).atZone(clock.zone))

        return buildString {
            appendLine("Generated at: $generatedAt")
            appendLine("Log tag: $LOG_TAG")
            appendLine()
            appendLine(report)
        }
    }

    private fun logSnapshotState(snapshot: UsbProbeSnapshot) {
        when {
            !snapshot.usbManagerAvailable -> Log.w(LOG_TAG, "USB manager unavailable on this device.")
            !snapshot.hasDevices -> Log.i(LOG_TAG, "USB enumeration completed with no visible devices.")
            else -> Log.i(LOG_TAG, "USB enumeration completed with ${snapshot.devices.size} device(s).")
        }
    }

    companion object {
        const val logDirectoryName: String = "probe-logs"

        private const val LOG_TAG = "DeXFanTool"
        private const val DEFAULT_MAX_LOG_FILES = 20

        private val fileTimestampFormatter =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS").withZone(ZoneId.systemDefault())

        private val displayTimestampFormatter =
            DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.systemDefault())
    }
}