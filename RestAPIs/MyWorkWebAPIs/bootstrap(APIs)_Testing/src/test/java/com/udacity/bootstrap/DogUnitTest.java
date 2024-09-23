package com.udacity.bootstrap;

import com.udacity.bootstrap.Services.DogService;
import com.udacity.bootstrap.Web.DogConroller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DogConroller.class)

public class DogUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    DogService dogService;

    @Test
    public void getAllDogs() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/dogs"))
                .andExpect(status().isOk());
                // .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              //  .andExpect(content().json("[]"));
        verify(dogService, times(1)).retrieveDogs();
    }
    @Test
    public void getBreedByID() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/6/breed"))
                .andExpect(status().isOk());
        verify(dogService, times(1)).retrieveDogBreedById(6L);
    }

}
