package com.fiverr.service;

import com.fiverr.model.Gig;
import com.fiverr.repository.GigRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GigServiceTest {

    @InjectMocks
    private GigServiceImpl gigService;

    @Mock
    private GigRepository gigRepository;

    @Test
    public void testCreateGig() {
        Gig gig = new Gig();
        gig.setTitle("Test Gig");
        gig.setPrice(10.0);

        when(gigRepository.save(gig)).thenReturn(gig);

        Gig savedGig = gigService.createGig(gig);

        assertEquals("Test Gig", savedGig.getTitle());
        verify(gigRepository).save(gig);
    }

    @Test
    public void testSearchGigs() {
        Gig gig = new Gig();
        gig.setTitle("Test Gig");
        List<Gig> gigs = Collections.singletonList(gig);

        when(gigRepository.findByTitleContaining("Test")).thenReturn(gigs);

        List<Gig> foundGigs = gigService.searchGigs("Test");

        assertEquals(1, foundGigs.size());
        assertEquals("Test Gig", foundGigs.get(0).getTitle());
        verify(gigRepository).findByTitleContaining("Test");
    }
}
