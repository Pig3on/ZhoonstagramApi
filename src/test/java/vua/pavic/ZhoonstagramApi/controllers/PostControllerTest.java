package vua.pavic.ZhoonstagramApi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import vua.pavic.ZhoonstagramApi.model.Post;
import vua.pavic.ZhoonstagramApi.model.User;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PostControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private String obtainAccessToken(String username, String password) throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "zhoonstagram");
        params.add("username", username);
        params.add("password", password);

        ResultActions result
                = mockMvc.perform(post("/oauth/token")
                .params(params)
                .with(httpBasic("zhoonstagram","secret"))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    public void findAll() throws Exception {
        String accessToken = obtainAccessToken("miro@miro.com","password");
        this.mockMvc
                .perform(
                        get("/api/post")
                                .header("Authorization", "Bearer "+ accessToken)
                )
                .andExpect(status().isOk());
    }
    @Test
    public void invalidPostSave() throws Exception {
        String accessToken = obtainAccessToken("miro@miro.com","password");
        User user = new User();
        user.setEmail("miro@miro.com");
        Post post = new Post();
        post.setDescription("Somethjing");
       // post.setTitle("Something"); //no title
        post.setUser(user);
        this.mockMvc
                .perform(
                        post("/api/post")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(post))
                                .header("Authorization", "Bearer "+ accessToken)
                )
                .andExpect(status().isBadRequest());
    }
    @Test
    public void validPostSave() throws Exception {
        String accessToken = obtainAccessToken("miro@miro.com","password");
        User user = new User();
        user.setEmail("miro@miro.com");
        Post post = new Post();
        post.setDescription("Somethjing");
        post.setTitle("Something"); //no title
        post.setUser(user);
        this.mockMvc
                .perform(
                        post("/api/post")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(post))
                                .header("Authorization", "Bearer "+ accessToken)
                )
                .andExpect(status().isCreated());
    }


}
