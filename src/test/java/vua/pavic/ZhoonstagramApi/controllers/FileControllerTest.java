package vua.pavic.ZhoonstagramApi.controllers;


import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void invalidImageRequest() throws Exception {

        MvcResult result = this.mockMvc.perform(get("/api/files/nonexistingimage.png")
        .contentType(MediaType.MULTIPART_FORM_DATA)
                .with(csrf())
                .with(user("miro@miro.com")
                        .password("password")
                        .roles("ADMIN","USER")))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsByteArray()).isNotEmpty();
    }
    @Test
    public void validImageRequest() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/files/york.png")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .with(csrf())
                .with(user("miro@miro.com")
                        .password("password")
                        .roles("ADMIN","USER")))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsByteArray()).isNotEmpty();
    }

    @Test
    public void uploadImageNotPigeon() throws Exception {
        String path = "src/test/resources/nonPigeon.jpg";
        File file = new File(path);
        MockMultipartFile result = new MockMultipartFile("file",
                "file.jpg","image/jpeg", new FileInputStream((file)));

        mockMvc.perform(multipart("/api/files")
                .file(result))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void uploadImageIsPigeon() throws Exception {
        String path = "src/test/resources/isPigeon.jpg";
        File file = new File(path);
        MockMultipartFile result = new MockMultipartFile("file",
                "isPigeon.jpg","image/jpeg", new FileInputStream((file)));

        mockMvc.perform(multipart("/api/files")
                .file(result))
                .andExpect(status().isOk());
    }
}
