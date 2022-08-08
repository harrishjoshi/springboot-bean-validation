package com.harxsh.springboot.mongo.validator.util;

import org.springframework.stereotype.Service;

@Service
public class StringUtil {

    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
