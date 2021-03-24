package com.example.demo.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc =  MockMvcBuilders.standaloneSetup(HomeController.class)
                .alwaysExpect(MockMvcResultMatchers.status().isOk())
                .build();
    }
    @Test
    public void requestBodyParameterTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/rb")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("name", "kan")
                .param("age", "13")
        ).andExpect(status().isOk()).andDo(print());
    }
    @Test
    public void requestBodyBodyTest() throws Exception {
        String json = "{ \"name\":\"kan\", \"age\":\"13\" }";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/rb")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json)
        ).andExpect(status().isOk()).andDo(print());
    }
    @Test
    public void modelAttributeParameterTest() throws Exception {
        String json = "{ \"name\":\"kan\", \"age\":\"13\" }";
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ma")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("name", "kan")
                .param("age", "13")
        ).andExpect(status().isOk()).andDo(print());
    }
    @Test
    public void modelAttributeBodyTest() throws Exception {
        String json = "{ \"name\":\"kan\", \"age\":\"13\" }";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/ma")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json)
        ).andExpect(status().isOk()).andDo(print());
    }
}