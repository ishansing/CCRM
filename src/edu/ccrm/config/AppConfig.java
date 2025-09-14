package edu.ccrm.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * This class acts as a central configuration holder for our application.
 * It's implemented as a Singleton, meaning there will only ever be one instance of it,
 * which is pretty useful for global settings like where our data lives.
 */
public class AppConfig {
    private static AppConfig instance; // The single instance of our configuration.
    private String applicationDataDirectory = "data"; // Default directory for application data.

    // The constructor is private to prevent direct instantiation from outside.
    private AppConfig() {}

    /**
     * Provides the global instance of the AppConfig. If it doesn't exist yet, it creates it.
     * This is the classic way to get hold of our configuration settings.
     * @return The singleton instance of AppConfig.
     */
    public static AppConfig getInstance() {
        if (instance == null) {
            // Double-checked locking could be used here for thread safety in a multi-threaded environment,
            // but for a simple CLI app, this is usually sufficient.
            instance = new AppConfig();
        }
        return instance;
    }

    /**
     * A simple method to simulate loading configuration settings.
     * For now, it just prints out the data folder, but in a real app, it might read from a file.
     * This method also ensures that the application's data directory exists.
     */
    public void loadConfig() {
        System.out.println("Loaded AppConfig. Application data will be stored in: " + applicationDataDirectory);
        try {
            // Let's make sure our data directory is actually there. If not, we'll create it.
            Files.createDirectories(Paths.get(applicationDataDirectory));
            System.out.println("Ensured data directory '" + applicationDataDirectory + "' exists.");
        } catch (IOException e) {
            System.err.println("Warning: Could not create data directory '" + applicationDataDirectory + "': " + e.getMessage());
            // It's not a fatal error, but good to know.
        }
    }

    public String getApplicationDataDirectory() {
        return applicationDataDirectory;
    }

    // We could add a setter for applicationDataDirectory if we wanted to make it configurable at runtime.
    // public void setApplicationDataDirectory(String applicationDataDirectory) {
    //     this.applicationDataDirectory = applicationDataDirectory;
    // }
}
