package com.kraluk.scf.server.rest;

import com.kraluk.scf.server.model.enums.OperationStatus;
import com.kraluk.scf.server.test.base.BaseControllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FacadeController.class)
public class FacadeControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void sendMailTest() throws Exception {
        mockMvc.perform(get("/mail/test@test.pl/Hello")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.status", is(OperationStatus.SUCCESS.name())))
            .andExpect(jsonPath("$.message", containsString("sended")));
    }

    @Test
    public void sendSmsTest() throws Exception {
        mockMvc.perform(get("/sms/500100200/Hello")
            .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.status", is(OperationStatus.SUCCESS.name())))
            .andExpect(jsonPath("$.message", containsString("sended")));
    }
}