package com.restfullApi.multidb.controllers;

import com.restfullApi.multidb.utils.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sekolah")
public class SekolahController {
    @RequestMapping(value = "/index", method = RequestMethod.GET, produces = {"text/plain", "application/*"})
    ResponseEntity<?> listdata() {
        try {

            return new ResponseEntity<>(new CustomResponse<>(null, 400, "error Response"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse<>(e.getMessage(), 400, "error Response"), HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET, produces = {"text/plain", "application/*"})
    ResponseEntity<?> update(@PathVariable() Long id) {
        try {

            return new ResponseEntity<>(new CustomResponse<>(null, 400, "error Response"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse<>(e.getMessage(), 400, "error Response"), HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = {"text/plain", "application/*"})
    ResponseEntity<?> show(@PathVariable Long id) {
        try {

            return new ResponseEntity<>(new CustomResponse<>(null, 400, "error Response"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse<>(e.getMessage(), 400, "error Response"), HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET, produces = {"text/plain", "application/*"})
    ResponseEntity<?> delete() {
        try {
            return new ResponseEntity<>(new CustomResponse<>(null, 400, "error Response"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse<>(e.getMessage(), 400, "error Response"), HttpStatus.BAD_REQUEST);
        }
    }


}
