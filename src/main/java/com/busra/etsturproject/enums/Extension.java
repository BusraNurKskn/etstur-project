package com.busra.etsturproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Extension {
    JPEG("jpeg"),
    JPG("jpg"),
    PNG("png"),
    DOCX("docx"),
    PDF("pdf"),
    XLSX("xlsx");

    private final String name;

    public static boolean isContains(String name) {
        return Arrays.stream(Extension.values())
                .anyMatch(extension -> extension.getName().equalsIgnoreCase(name));
    }
}
