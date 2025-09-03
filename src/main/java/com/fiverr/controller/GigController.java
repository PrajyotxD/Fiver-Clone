package com.fiverr.controller;

import com.fiverr.model.Gig;
import com.fiverr.service.GigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gigs")
public class GigController {

    @Autowired
    private GigService gigService;

    @PostMapping
    public Gig createGig(@RequestBody Gig gig) {
        return gigService.createGig(gig);
    }

    @GetMapping
    public List<Gig> getAllGigs() {
        return gigService.getAllGigs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gig> getGigById(@PathVariable Long id) {
        return gigService.getGigById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Gig> searchGigs(@RequestParam String keyword) {
        return gigService.searchGigs(keyword);
    }
}
