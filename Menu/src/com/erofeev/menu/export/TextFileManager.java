package com.erofeev.menu.export;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextFileManager {

	private static final Logger loggerTextFileManager = LogManager.getLogger(TextFileManager.class);
	private final static Charset FILE_ENCODING = StandardCharsets.UTF_8;

	public void writeToFile(String fileName, String[] values) {

		Path path = Paths.get(fileName);
		try {
			if (!Files.exists(path)) {
				Files.createFile(path);
			}

		} catch (UnsupportedOperationException | IOException | SecurityException e) {
			loggerTextFileManager.warn("can't write to file " + e);
		}

		if (values != null && values.length > 0) {
			List<String> lines = Arrays.asList(values);
			try {
				Files.write(path, lines, FILE_ENCODING, StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public List<String> readFromFile(String fileName) {
		List<String> lines = null;
		try {
			Path path = Paths.get(fileName);
			lines = Files.readAllLines(path);
		} catch (InvalidPathException e) {
			loggerTextFileManager.warn("wrong path " + e);
		}

		catch (IOException e) {
			loggerTextFileManager.warn("Can't read file " + e);

		}
		return lines;
	}

}
