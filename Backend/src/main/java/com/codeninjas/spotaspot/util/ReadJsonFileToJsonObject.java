package com.codeninjas.spotaspot.util;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJsonFileToJsonObject {

    public static JSONObject read(String path) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(path)));
        return new JSONObject(content);
    }
}
