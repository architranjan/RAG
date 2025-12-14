package com.example.demo.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pdf_data")
public class PdfData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private LocalDateTime uploadedAt = LocalDateTime.now();


    @OneToMany(
            mappedBy = "pdf",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PdfChunk> chunks;

    public PdfData() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
