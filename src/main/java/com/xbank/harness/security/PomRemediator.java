package com.xbank.harness.security;

import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class PomRemediator {

    public boolean injectSanitizationDependency(String pomPath) {
        try {
            Path path = Paths.get(pomPath);
            if (!Files.exists(path)) {
                return false;
            }
            List<String> lines = Files.readAllLines(path);
            boolean hasDependency = lines.stream().anyMatch(l -> l.contains("owasp-java-html-sanitizer"));
            
            if (hasDependency) {
                return true; // Already remediated
            }
            
            // Find the end of <dependencies> block
            int dependenciesEndLine = -1;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains("</dependencies>")) {
                    dependenciesEndLine = i;
                    break;
                }
            }
            
            if (dependenciesEndLine != -1) {
                // Inject the dependency right before </dependencies>
                String dep = "        <dependency>\n" +
                             "            <groupId>com.googlecode.owasp-java-html-sanitizer</groupId>\n" +
                             "            <artifactId>owasp-java-html-sanitizer</artifactId>\n" +
                             "            <version>20240325.1</version>\n" +
                             "        </dependency>";
                lines.add(dependenciesEndLine, dep);
                Files.write(path, lines);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
