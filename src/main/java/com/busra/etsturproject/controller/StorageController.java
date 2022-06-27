package com.busra.etsturproject.controller;

import com.busra.etsturproject.entity.StorageResponse;
import com.busra.etsturproject.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("storage")
@RequiredArgsConstructor
public class StorageController {

  private final StorageService storageService;

  @PostMapping
  public StorageResponse upload(@RequestParam("file") MultipartFile file) {

    return storageService.upload(file);
  }
  @GetMapping
  public List<StorageResponse> getAll(){
    return storageService.getAll();
  }

  @GetMapping("file")
  public byte[] getByteFile(@RequestParam("name") String name){
    return storageService.getByteFile(name);
  }

  @DeleteMapping
  public void delete(@RequestParam("name") String name){
    storageService.delete(name);
  }

}
