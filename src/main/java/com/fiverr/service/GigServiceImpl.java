package com.fiverr.service;

import com.fiverr.model.Gig;
import com.fiverr.repository.GigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GigServiceImpl implements GigService {

    @Autowired
    private GigRepository gigRepository;

    @Override
    public Gig createGig(Gig gig) {
        return gigRepository.save(gig);
    }

    @Override
    public List<Gig> getAllGigs() {
        return gigRepository.findAll();
    }

    @Override
    public Optional<Gig> getGigById(Long id) {
        return gigRepository.findById(id);
    }

    @Override
    public List<Gig> searchGigs(String keyword) {
        return gigRepository.findByTitleContaining(keyword);
    }
}
