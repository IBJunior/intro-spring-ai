package com.example.introspringai.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class FileUtils {
    public static final String AUDIO_RESOURCES_PATH = "src/main/resources/audio";
    public static final String IMAGE_RESOURCES_PATH = "src/main/resources/images";

    public static String writeSoundBytesToGivenFile(byte[] bytes, String fileName) {
        Path directory = Paths.get(AUDIO_RESOURCES_PATH);
        Path filePath = directory.resolve(fileName);
        try {
            Files.write(filePath, bytes, StandardOpenOption.CREATE_NEW);
            System.out.printf("Saved %s to %s%n", fileName, AUDIO_RESOURCES_PATH);
            return fileName;
        } catch (IOException e) {
            throw new UncheckedIOException("Error writing audio to file", e);
        }
    }

    public static void saveImageToFile(String imageData) {
        Path directory = Paths.get(IMAGE_RESOURCES_PATH);
        String timestamp = getTimestamp();
        byte[] imageBytes = Base64.getDecoder().decode(imageData);
        String fileName = String.format("image_%s.jpeg", timestamp);
        Path filePath = directory.resolve(fileName);

        try {
            Files.write(filePath, imageBytes, StandardOpenOption.CREATE_NEW);
            System.out.printf("Saved %s to %s%n", fileName, AUDIO_RESOURCES_PATH);
        } catch (IOException e) {
            throw new UncheckedIOException("Error writing image to file", e);
        }
    }

    private static String getTimestamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public static String writeSoundBytesToFile(byte[] bytes) {
        String timestamp = getTimestamp();
        String fileName = String.format("audio_%s.mp3", timestamp);
        return writeSoundBytesToGivenFile(bytes, fileName);
    }
}
