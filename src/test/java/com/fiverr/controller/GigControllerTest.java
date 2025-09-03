package com.fiverr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiverr.model.Gig;
import com.fiverr.service.GigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(GigController.class)
public class GigControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GigService gigService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateGig() throws Exception {
        Gig gig = new Gig();
        gig.setTitle("Test Gig");
        gig.setPrice(10.0);

        when(gigService.createGig(any(Gig.class))).thenReturn(gig);

        mockMvc.perform(post("/api/gigs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gig)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Gig"));
    }

    @Test
    public void testSearchGigs() throws Exception {
        Gig gig = new Gig();
        gig.setTitle("Test Gig");
        when(gigService.searchGigs("Test")).thenReturn(Collections.singletonList(gig));

        mockMvc.perform(get("/api/gigs/search")
                .param("keyword", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Gig"));
    }
}
