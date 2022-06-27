package com.busra.etsturproject.controller;

import com.busra.etsturproject.dto.StorageResponse;
import com.busra.etsturproject.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public StorageResponse upload(@RequestParam("file") MultipartFile file,
                                  @RequestHeader("X-Authorization") String authorization) {
        return storageService.upload(file);
    }

    @GetMapping
    public List<StorageResponse> getAll(@RequestHeader("X-Authorization") String authorization) {
        return storageService.getAll();
    }

    @GetMapping("file")
    public byte[] getByteFile(@RequestParam("name") String name,
                              @RequestHeader("X-Authorization") String authorization) {
        return storageService.getByteFile(name);
    }

    @DeleteMapping
    public void delete(@RequestParam("name") String name,
                       @RequestHeader("X-Authorization") String authorization) {
        storageService.delete(name);
    }

}
