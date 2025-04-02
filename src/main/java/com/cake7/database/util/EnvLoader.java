package com.cake7.database.util;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();  // `.env` 파일 자동 로드

    public static String get(String key) {
        return dotenv.get(key);
    }
}
