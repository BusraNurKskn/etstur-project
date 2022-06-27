package com.busra.etsturproject.validation;

import com.busra.etsturproject.entity.Extension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StorageValidator {

  public void validate(String extension) {
    if (!Extension.isContains(extension)) {
      log.error("Not valid extension");
      throw new RuntimeException("Not valid extension");
    }
  }
}
