package com.example.restdocs.post;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PostController.class)
@AutoConfigureRestDocs
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;
    private final String uri = "/posts";


    @Test
    @DisplayName("게시글 저장")
    void test01() throws Exception {
        Post mockPost = new Post();
        mockPost.setId(1L);
        mockPost.setTitle("Test Title");
        mockPost.setContent("Test Content");

        Mockito.when(postService.save(Mockito.any(Post.class))).thenReturn(mockPost);

        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(mockPost)))
            .andExpect(status().isCreated())
            .andDo(print())
            .andDo(document("post-v1-post-posts",
                requestFields(
                    fieldWithPath("id").description("The ID of the post"),
                    fieldWithPath("title").description("The title of the post"),
                    fieldWithPath("content").description("The content of the post")
                ),
                responseFields(
                    fieldWithPath("id").description("The ID of the created post"),
                    fieldWithPath("title").description("The title of the created post"),
                    fieldWithPath("content").description("The content of the created post")
                )
            ));
    }

    @Test
    @DisplayName("게시글 조회")
    void test02() throws Exception {
        Long postId = 1L;
        Post post = new Post();
        post.setId(postId);
        post.setTitle("Test Title");
        post.setContent("Test Content");



        Mockito.when(postService.findById(postId)).thenReturn(post);

        mockMvc.perform(get(uri + "/{id}", postId))
            .andExpect(status().isOk())
            .andDo(document("get-v1-get-post",
                pathParameters(
                    parameterWithName("id").description("The ID of the post to retrieve")
                ),
                responseFields(
                    fieldWithPath("id").description("The ID of the retrieved post"),
                    fieldWithPath("title").description("The title of the retrieved post"),
                    fieldWithPath("content").description("The content of the retrieved post")
                )
            ));

    }

    @Test
    @DisplayName("게시글 삭제")
    void test03() throws Exception{
        Long postId = 1L;
        Mockito.doNothing().when(postService).deleteById(1L);

        mockMvc.perform(delete(uri+"/{id}",postId))
            .andExpect(status().isNoContent())
            .andDo(print())
            .andDo(document("delete-v1-delete-posts",
                pathParameters(
                    parameterWithName("id").description("The ID of the post to delete")
                )
            ));
    }

    @DisplayName("모든 게시글 조회")
    @Test
    void test04() throws Exception {
        List<Post> posts = Arrays.asList(
            new Post(1L, "title1", "content1"),
            new Post(2L, "title2", "content2")
        );

        Mockito.when(postService.findAll()).thenReturn(posts);

        mockMvc.perform(get(uri))
            .andExpect(status().isOk())
            .andDo(print())
            .andDo(document("get-v1-get-posts",
                responseFields(
                    fieldWithPath("[].id").description("The ID of the post"),
                    fieldWithPath("[].title").description("The title of the post"),
                    fieldWithPath("[].content").description("The content of the post")
                )
            ));
    }
}