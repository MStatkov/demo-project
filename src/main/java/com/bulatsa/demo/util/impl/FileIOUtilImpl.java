package com.bulatsa.demo.util.impl;

import com.bulatsa.demo.util.FileIOUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileIOUtilImpl implements FileIOUtil {
    @Override
    public List<String> readFileDataGetListOfStrings(String path) throws IOException {
        return Files.readAllLines(Paths.get(path))
                .stream()
                .filter(row -> !row.isEmpty())
                .collect(Collectors.toList());
    }

    @Override
    public String readFileDataGetString(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath))
                .stream()
                .filter(row -> !row.isEmpty())
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String readFileDataGetSimpleString(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    @Override
    public void write(String content, String filePath) throws IOException {
        Files.write(Paths.get(filePath), Collections.singleton(content), StandardCharsets.UTF_8);
    }
}
