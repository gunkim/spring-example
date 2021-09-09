package dev.gunlog.app.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gunlog.app.dto.UserInfoResponseDto;
import dev.gunlog.app.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("/api/user 테스트")
    public void getUserInfoApiTest() throws Exception {
        String name = "gunkim";
        int age = 22;
        when(userService.selectUserInfo(any(String.class))).thenReturn(UserInfoResponseDto.builder()
                .name(name)
                .age(age)
                .build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/user")
                .param("username", name))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        UserInfoResponseDto dto = objectMapper.readValue(result.getResponse().getContentAsString(), UserInfoResponseDto.class);
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAge()).isEqualTo(age);
    }
}