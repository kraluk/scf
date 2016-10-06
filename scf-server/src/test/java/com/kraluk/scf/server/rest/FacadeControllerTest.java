package com.kraluk.scf.server.rest;

import com.kraluk.scf.server.test.base.BaseControllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FacadeController.class)
public class FacadeControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGet404Test() throws Exception {
        mockMvc.perform(get("/")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(status().isNotFound());
    }
}