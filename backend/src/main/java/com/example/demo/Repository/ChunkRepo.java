package com.example.demo.Repository;

import com.example.demo.Models.PdfChunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChunkRepo extends JpaRepository<PdfChunk,Long> {
    @Query(
            value = """
        SELECT *
        FROM pdf_chunk
        ORDER BY embedding <-> CAST(:embedding AS vector)
        LIMIT :limit
        """,
            nativeQuery = true
    )
    List<PdfChunk> findSimilarChunks(
            @Param("embedding") String embedding,
            @Param("limit") int limit
    );

    @Query(
            value = """
        SELECT *
        FROM pdf_chunk
        ORDER BY RANDOM()
        LIMIT :limit
        """,
            nativeQuery = true
    )
    List<PdfChunk> findRandomChunks(
            @Param("limit") int limit
    );


}
