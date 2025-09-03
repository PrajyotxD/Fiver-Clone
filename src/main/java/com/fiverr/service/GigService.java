package com.fiverr.service;

import com.fiverr.model.Gig;

import java.util.List;
import java.util.Optional;

public interface GigService {
    Gig createGig(Gig gig);
    List<Gig> getAllGigs();
    Optional<Gig> getGigById(Long id);
    List<Gig> searchGigs(String keyword);
}
