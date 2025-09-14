package edu.ccrm.util;

import java.nio.file.*;
import java.io.IOException;

/**
 * A utility class for performing directory backups. It can recursively copy
 * files and subdirectories from a source to a destination.
 * This is pretty crucial for data safety, you know?
 */
public class BackupUtility {

    /**
     * Backs up the contents of a source directory to a destination directory.
     * This operation is recursive, meaning it will copy all files and subdirectories.
     * @param sourceDirectoryPath The path to the directory that needs to be backed up.
     * @param destinationDirectoryPath The path to the directory where the backup should be stored.
     * @throws IOException If any I/O error occurs during the backup process.
     */
    public static void backupDirectory(Path sourceDirectoryPath, Path destinationDirectoryPath) throws IOException {
        // First things first, let's make sure the source directory actually exists and is a directory.
        if (!Files.exists(sourceDirectoryPath) || !Files.isDirectory(sourceDirectoryPath))
            throw new IOException("Source not found or not a directory: " + sourceDirectoryPath);

        // We need to make sure our target backup directory exists. If not, we'll create it.
        if (!Files.exists(destinationDirectoryPath)) {
            Files.createDirectories(destinationDirectoryPath);
        }

        // Now, let's walk through the source directory, copying everything over.
        // This uses a stream to process each file and directory found.
        Files.walk(sourceDirectoryPath)
            .forEach(currentSourcePath -> {
                try {
                    // Calculate the relative path of the current item from the source root.
                    Path relativePath = sourceDirectoryPath.relativize(currentSourcePath);
                    // Construct the full path for the item in the destination.
                    Path targetPath = destinationDirectoryPath.resolve(relativePath);

                    // If it's a directory, we just need to make sure it exists in the destination.
                    if (Files.isDirectory(currentSourcePath)) {
                        if (!Files.exists(targetPath))
                            Files.createDirectories(targetPath);
                    } else {
                        // If it's a file, we copy it over, replacing it if it already exists.
                        Files.copy(currentSourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    // If something goes wrong during copying, we'll print an error, but try to continue.
                    System.err.println("Oh no! Failed to copy: " + currentSourcePath + " - " + e.getMessage());
                }
            });
    }
}
