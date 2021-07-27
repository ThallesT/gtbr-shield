package com.gtbr.gtbrshield.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class StatusResource {

    @GetMapping("/status")
    public ResponseEntity<Boolean> getStatus() {
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
