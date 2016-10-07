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
            .accept(MediaType.parseMediaType(EXPECTED_CONTENT_TYPE)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(EXPECTED_CONTENT_TYPE))
            .andExpect(jsonPath("$.status", is(OperationStatus.SUCCESS.name())))
            .andExpect(jsonPath("$.message", containsString("sended")));
    }

    @Test
    public void shouldNotSendMailTest() throws Exception {
        mockMvc.perform(get("/mail/test@test/Hello")
            .accept(MediaType.parseMediaType(EXPECTED_CONTENT_TYPE)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(EXPECTED_CONTENT_TYPE))
            .andExpect(jsonPath("$.status", is(OperationStatus.ERROR.name())))
            .andExpect(jsonPath("$.message", containsString("Not proper format")));
    }

    @Test
    public void sendSmsTest() throws Exception {
        mockMvc.perform(get("/sms/500100200/Hello")
            .accept(MediaType.parseMediaType(EXPECTED_CONTENT_TYPE)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(EXPECTED_CONTENT_TYPE))
            .andExpect(jsonPath("$.status", is(OperationStatus.SUCCESS.name())))
            .andExpect(jsonPath("$.message", containsString("sended")));
    }

    @Test
    public void shouldNotSendSmsTest() throws Exception {
        mockMvc.perform(get("/sms/222/Hello")
            .accept(MediaType.parseMediaType(EXPECTED_CONTENT_TYPE)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(EXPECTED_CONTENT_TYPE))
            .andExpect(jsonPath("$.status", is(OperationStatus.ERROR.name())))
            .andExpect(jsonPath("$.message", containsString("Not proper format")));
    }
}