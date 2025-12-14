package com.example.demo.Repository;

import com.example.demo.Models.PdfChunk;
import com.example.demo.Models.PdfData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface PdfRepo extends JpaRepository<PdfData, Long> {

}
