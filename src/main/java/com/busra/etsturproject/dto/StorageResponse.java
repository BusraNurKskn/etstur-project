package com.busra.etsturproject.dto;

import com.busra.etsturproject.entity.Storage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageResponse {

    private String path;

    private Long size;

    private String name;

    private String extension;

    public static StorageResponse of(Storage storage) {
        StorageResponse response = new StorageResponse();
        response.setName(storage.getName());
        response.setPath(storage.getPath());
        response.setExtension(storage.getExtension());
        response.setSize(storage.getSize());
        return response;
    }
}
