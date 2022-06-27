package com.busra.etsturproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "storage")
public class Storage {

  @Id @GeneratedValue private Long id;

  @Column(name = "path", nullable = false)
  private String path;

  @Column(name = "size", nullable = false)
  private Long size;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "extension", nullable = false)
  private String extension;
}
