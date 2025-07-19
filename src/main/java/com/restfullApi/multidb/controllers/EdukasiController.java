package com.restfullApi.multidb.controllers;

import com.restfullApi.multidb.repository.EdukasiJdbcRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/edukasi")
public class EdukasiController {

    private final EdukasiJdbcRepository edukasiJdbcRepository;

    public EdukasiController(EdukasiJdbcRepository edukasiJdbcRepository) {
        this.edukasiJdbcRepository = edukasiJdbcRepository;
    }

    @GetMapping("data")
    public List<Map<String, Object>> getTableData() {
        return edukasiJdbcRepository.getAllData("users");
    }

}
