package dev.gunlog.app.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gunlog.app.domain.User;
import dev.gunlog.app.domain.UserRepository;
import dev.gunlog.app.dto.UserInfoResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("/api/user 테스트")
    public void getUserInfoApiTest() throws Exception {
        String name = "gunkim";
        int age = 22;
        userRepository.save(User.builder()
                .name(name)
                .age(age)
                .build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/user")
                .param("username", name))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"gunkim\",\"age\":22}"))
                .andDo(print())
                .andReturn();

        UserInfoResponseDto dto = objectMapper.readValue(result.getResponse().getContentAsString(), UserInfoResponseDto.class);
        assertThat(dto.getName(), is(equalTo(name)));
        assertThat(dto.getAge(), is(equalTo(age)));
    }
}