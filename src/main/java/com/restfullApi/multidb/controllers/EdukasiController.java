package com.restfullApi.multidb.controllers;

import com.restfullApi.multidb.repository.EdukasiJdbcRepository;
import com.restfullApi.multidb.utils.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/edukasi")
public class EdukasiController {

    private final EdukasiJdbcRepository edukasiJdbcRepository;

    public EdukasiController(EdukasiJdbcRepository edukasiJdbcRepository) {
        this.edukasiJdbcRepository = edukasiJdbcRepository;
    }

    @GetMapping("list")
    public ResponseEntity<?> getTableData() {
        try {
            List<Map<String, Object>> list = edukasiJdbcRepository.getAllData("users");
            System.out.println(list);
            return new ResponseEntity<>(new CustomResponse<>(list, 200, "data"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse<>(e.getMessage(), 200, "data"), HttpStatus. BAD_REQUEST);

        }
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> insertData() {
        try {

            return new ResponseEntity<>(new CustomResponse<>("test", 200, "data"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(new CustomResponse<>("test", 200, "data"), HttpStatus.BAD_REQUEST);
        }
    }


}
