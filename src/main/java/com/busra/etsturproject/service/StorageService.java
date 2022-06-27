package com.busra.etsturproject.service;

import com.busra.etsturproject.dto.StorageResponse;
import com.busra.etsturproject.entity.Storage;
import com.busra.etsturproject.repository.StorageRepository;
import com.busra.etsturproject.validation.StorageValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;

    private final StorageValidator storageValidator;

    private final Path root = Paths.get("uploads");

    @PostConstruct
    public void createFolder() {
        try {
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            log.error("Folder couldn't created");
            throw new RuntimeException("Folder couldn't created");
        }
    }

    @Transactional
    public StorageResponse upload(MultipartFile file) {

        storageValidator.validate(FilenameUtils.getExtension(file.getOriginalFilename()));
        if (Files.exists(root.resolve(file.getOriginalFilename()))) {
            return update(file);
        }
        writeFile(file);
        Storage savedStorage = save(file);
        return StorageResponse.of(savedStorage);
    }

    @Transactional
    public StorageResponse update(MultipartFile file) {
        delete(file.getOriginalFilename());
        writeFile(file);
        Storage savedStorage = save(file);
        return StorageResponse.of(savedStorage);
    }

    public List<StorageResponse> getAll() {
        List<Storage> storageList = storageRepository.findAll();
        return storageList.stream().map(StorageResponse::of).collect(Collectors.toList());
    }

    @Transactional
    public void delete(String name) {

        Storage storage = findStorageByName(name);

        storageRepository.delete(storage);
        FileSystemUtils.deleteRecursively(new File(storage.getPath()));
    }

    public byte[] getByteFile(String name) {

        Storage storage = findStorageByName(name);
        Path path = Paths.get(storage.getPath());
        byte[] content;
        try {
            content = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content;
    }

    private Storage save(MultipartFile file) {

        Storage storage = new Storage();
        storage.setSize(file.getSize());
        storage.setName(file.getOriginalFilename());
        storage.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
        storage.setPath(this.root.resolve(file.getOriginalFilename()).toString());

        return storageRepository.save(storage);
    }

    private void writeFile(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            log.error("File couldn't upload");
            throw new RuntimeException(e);
        }
    }

    private Storage findStorageByName(String name) {

        return storageRepository
                .findByName(name)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }
}
