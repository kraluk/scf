package com.kraluk.scf.server.rest;

import com.kraluk.scf.server.test.base.BaseControllerTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MaintenanceController.class)
public class MaintenanceControllerTests extends BaseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void pingTest() throws Exception {
        mockMvc.perform(get("/maintenance/ping")
            .accept(MediaType.parseMediaType(EXPECTED_CONTENT_TYPE)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(EXPECTED_CONTENT_TYPE));
    }
}