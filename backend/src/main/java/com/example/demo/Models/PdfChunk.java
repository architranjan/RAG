package com.example.demo.Models;

import com.example.demo.Converters.VectorConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "pdf_chunk")
public class PdfChunk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pdf_id", nullable = false)
    private PdfData pdf;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    @Column(columnDefinition = "vector(384)", nullable = false)
    @Convert(converter = VectorConverter.class)
    @JdbcTypeCode(SqlTypes.OTHER)
    private float[] embedding;



    public PdfChunk() {}

    public void setPdf(PdfData pdf) {
        this.pdf = pdf;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEmbedding(float[] embedding) {
        this.embedding = embedding;
    }

    public String getContent() {
        return content;
    }
}
