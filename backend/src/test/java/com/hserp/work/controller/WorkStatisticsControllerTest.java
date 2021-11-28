package com.hserp.work.controller;

import com.hserp.work.service.WorkStatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = WorkStatisticsController.class)
class WorkStatisticsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WorkStatisticsService workStatisticsService;

    String url = "/api/v1/works/statistics";

    @Test
    void getThisMonthSummary() throws Exception {
        // given
        String url = this.url + "/monthly/this";

        // when // then
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getMonthlySummary() throws Exception {
        // given
        String url = this.url + "/monthly";

        // when // then
        mockMvc.perform(get(url)
                        .param("months[]", "2021-10")
                        .param("months[]", "2021-11"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}