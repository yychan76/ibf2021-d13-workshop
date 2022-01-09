package nus.edu.sg.workshop13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

// import third party library for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop13Application {
	private static final String DEFAULT_DATA_DIR = "./data";
	private static final Logger logger = LoggerFactory.getLogger(Workshop13Application.class);

	public static void main(String[] args) throws IOException {
		logger.info("Address Book App started");
		String dataDir;
		SpringApplication app = new SpringApplication(Workshop13Application.class);
		ApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List<String> optVals = appArgs.getOptionValues("dataDir");
		if (optVals == null || optVals.get(0) == null) {
			dataDir = DEFAULT_DATA_DIR;
			// TODO to strictly follow the workshop specs we should show error message and stop the app from running
			// logger.error("No data directory specified. Exiting...");
			// System.exit(1);
		} else {
			dataDir = optVals.get(0);
		}
		logger.info("Using data directory: {}", dataDir);
		Path dataPath = Paths.get(dataDir);
		if (!dataPath.toFile().exists()) {
			try {
				Files.createDirectories(dataPath);
			} catch (IOException e) {
				logger.error("Cannot create data directory: {} ", dataPath, e);
				System.exit(1);
			}
		}

		if (dataPath.toFile().exists()) {
			logger.info("Full path of data directory: {}", dataPath.toRealPath());
			// sets default environment properties which we can read with Environment API
			app.setDefaultProperties(
				Collections.singletonMap("config.dataDir", dataDir)
			);
		}

		app.run(args);
	}

}
