package be.kdg.fraudedetection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories(basePackages = "be.kdg.fraudedetection.dal.neo4j")
public class FraudeDetectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FraudeDetectionApplication.class, args);
	}
}
