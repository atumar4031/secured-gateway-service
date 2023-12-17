package com.attech.controller;


import com.attech.dto.NinjaRequest;
import com.attech.dto.NinjaResponse;
import com.attech.service.NinjaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninja")
@RequiredArgsConstructor
public class NinjaController {
    private final NinjaService ninjaService;

    @PostMapping
    public ResponseEntity<NinjaResponse> createNinja(@RequestBody NinjaRequest request){
        NinjaResponse response = ninjaService.createNinja(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NinjaResponse> updateNinja(@PathVariable long id, @RequestBody NinjaRequest request){
        NinjaResponse response = ninjaService.updateNinja(id, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<NinjaResponse>> findNinja(){
        List<NinjaResponse> response = ninjaService.findNinja();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NinjaResponse> deleteNinja(@PathVariable long id){
        NinjaResponse response = ninjaService.deleteNinja(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
