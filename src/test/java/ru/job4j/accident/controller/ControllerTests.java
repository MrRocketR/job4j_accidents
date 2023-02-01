package ru.job4j.accident.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accident.App;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccidentService accidentService;
    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser
    public void createAccountGetTest() throws Exception {
        this.mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAccident"));
    }

    @Test
    @WithMockUser
    public void loginGetTest() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser
    public void regGetTest() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

    @Test
    @WithMockUser
    public void whenCreateAccidentThenReturnDefaultMessage() throws Exception {
        String[] ids = {"1"};
        this.mockMvc.perform(post("/saveAccident")
                        .param("id", "1")
                        .param("name", "Машина-мотоцикл")
                        .param("text", "some text")
                        .param("address", "MSK")
                        .param("type.id", "1")
                        .param("rIds", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).addAccident(argument.capture(), eq(ids));
        assertThat(argument.getValue().getAddress()).isEqualTo("MSK");
    }

    @Test
    @WithMockUser
    public void whenEditAccidentThenReturnDefaultMessage() throws Exception {
        String[] ids = new String[0];
        this.mockMvc.perform(post("/updateAccident")
                        .param("id", "1")
                        .param("name", "Машина-мотоцикл")
                        .param("text", "some text")
                        .param("address", "MSK")
                        .param("type.id", "1")
                        .param("rIds", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).addAccident(argument.capture(), eq(ids));
        assertThat(argument.getValue().getAddress()).isEqualTo("MSK");
    }

    @Test
    @WithMockUser
    public void whenRegUserThenReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(post("/reg")
                        .param("username", "name")
                        .param("password", "123"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(userService).save(argument.capture());
        assertThat(argument.getValue().getUsername()).isEqualTo("name");
    }

}