package com.bulatsa.demo.util;

import java.io.IOException;
import java.util.List;

public interface FileIOUtil {
    List<String> readFileDataGetListOfStrings(String path) throws IOException;

    String readFileDataGetString(String filePath) throws IOException;

    String readFileDataGetSimpleString(String filePath) throws IOException;

    void write(String content, String filePath) throws IOException;
}
