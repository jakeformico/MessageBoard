package group.project.messageboard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.web.servlet.ResultActions;

import group.project.messageboard.controllers.PostController;
import group.project.messageboard.models.*;
import group.project.messageboard.services.PersonService;
import group.project.messageboard.services.PostService;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PostController.class, excludeAutoConfiguration = (SecurityAutoConfiguration.class))
public class PostTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PostService postService;

	@MockBean
	private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;

	@Test
	public void testGetAllPosts() throws Exception {
		List<Post> listOfPosts = new ArrayList<>();
		listOfPosts.add(new Post());

		given(postService.getAllPosts()).willReturn(listOfPosts);

		ResultActions response = mockMvc.perform(get("/api/post"));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.size()", is(listOfPosts.size())));
	}

	@Test
	public void testCreatePost() throws Exception {
		Post post = new Post();
		post.setDateOfEvent(LocalDate.parse("2023-04-13"));
		post.setDateOfExpiration(LocalDate.parse("2023-04-17"));
		
		given(postService.createPost(any(Post.class)))
			.willAnswer((invocation)-> invocation.getArgument(0));
		
		ResultActions response = mockMvc.perform(post("/api/post/create/{personId}", 1L)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(post)));

		response.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.approved", is(post.isApproved())))
			.andExpect(jsonPath("$.description", is(post.getDescription())))
			.andExpect(jsonPath("$.title", is(post.getTitle())))
            .andExpect(jsonPath("$.dateOfEvent", is(post.getDateOfEvent().toString())))
            .andExpect(jsonPath("$.dateOfExpiration", is(post.getDateOfExpiration().toString())));
	}

	@Test
	public void testUpdatePostById() throws Exception {
		long postId = 1L;
		Post savedPost = new Post();
        savedPost.setId(postId);
        savedPost.setApproved(false);
		savedPost.setDateOfEvent(LocalDate.parse("2023-04-13"));
		savedPost.setDateOfExpiration(LocalDate.parse("2023-04-17"));
		Post updatedPost = new Post();
        updatedPost.setId(postId);
        updatedPost.setApproved(true);
		updatedPost.setDateOfEvent(LocalDate.parse("2023-04-13"));
		updatedPost.setDateOfExpiration(LocalDate.parse("2023-04-17"));

		given(postService.getById(postId)).willReturn(savedPost);
		given(postService.updatePostById(anyLong(), any(Post.class)))
			.willAnswer((invocation)-> invocation.getArgument(1));

		ResultActions response = mockMvc.perform(put("/api/post/{id}", postId)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(updatedPost)));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.id", is(updatedPost.getId()), Long.class))
			.andExpect(jsonPath("approved", is(updatedPost.isApproved())));
	}

	@Test
	public void testDeletePost() throws Exception {
		long postId = 1L;
		willDoNothing().given(postService).deletePost(postId);

		ResultActions response = mockMvc.perform(delete("/api/post/{id}", postId));

		response.andExpect(status().isOk())
			.andDo(print());
	}
	
	@Test
	public void testGetById() throws Exception {
		long postId = 1L;
		Post post = new Post();
		post.setId(postId);
		post.setDateOfEvent(LocalDate.parse("2023-04-13"));
		post.setDateOfExpiration(LocalDate.parse("2023-04-17"));
		given(postService.getById(postId)).willReturn(post);

		ResultActions response = mockMvc.perform(get("/api/post/{id}", postId));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.approved", is(post.isApproved())))
			.andExpect(jsonPath("$.description", is(post.getDescription())))
			.andExpect(jsonPath("$.title", is(post.getTitle())))
            .andExpect(jsonPath("$.dateOfEvent", is(post.getDateOfEvent().toString())))
            .andExpect(jsonPath("$.dateOfExpiration", is(post.getDateOfExpiration().toString())));
	}

	@Test
	public void testGetCalendarView() throws Exception {
		List<Post> listOfPosts = new ArrayList<>();
		listOfPosts.add(new Post());

		given(postService.getCalendarView()).willReturn(listOfPosts);

		ResultActions response = mockMvc.perform(get("/api/post/calendarView"));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.size()", is(listOfPosts.size())));
	}

	@Test
	public void testApprovePostById() throws Exception {
		long postId = 1L;
		Post savedPost = new Post();
        savedPost.setId(postId);
        savedPost.setApproved(false);
		Post updatedPost = new Post();
        updatedPost.setId(postId);
        updatedPost.setApproved(true);

		given(postService.getById(postId)).willReturn(savedPost);
		given(postService.approvePostById(postId)).willReturn(updatedPost);

		ResultActions response = mockMvc.perform(put("/api/post/approve/{id}", postId));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.id", is(updatedPost.getId()), Long.class))
			.andExpect(jsonPath("$.approved", is(updatedPost.isApproved())));
	}

	@Test
	public void testRejectPostById() throws Exception {
		long postId = 1L;
		Post savedPost = new Post();
        savedPost.setId(postId);
        savedPost.setApproved(true);
		Post updatedPost = new Post();
        updatedPost.setId(postId);
        updatedPost.setApproved(false);

		given(postService.getById(postId)).willReturn(savedPost);
		given(postService.rejectPostById(anyLong(), anyString())).willReturn(updatedPost);

		ResultActions response = mockMvc.perform(put("/api/post/reject/{id}", postId));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.id", is(updatedPost.getId()), Long.class))
			.andExpect(jsonPath("$.approved", is(updatedPost.isApproved())));
	}

	@Test
	public void testApprovedPosts() throws Exception {
		List<Post> listOfPosts = new ArrayList<>();
		listOfPosts.add(new Post());

		given(postService.getApprovedPosts()).willReturn(listOfPosts);

		ResultActions response = mockMvc.perform(get("/api/post/approvedPosts"));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.size()", is(listOfPosts.size())));
	}

	@Test
	public void testPostStatusByPersonId() throws Exception {
		long personId = 1L;
		List<Post> listOfPosts = new ArrayList<>();
		listOfPosts.add(new Post());
		given(postService.getPostStatusByPersonId(personId)).willReturn(listOfPosts);

		ResultActions response = mockMvc.perform(get("/api/post/postStatusByPersonId/{personId}", personId));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.size()", is(listOfPosts.size())));
	}

	@Test
	public void testPostStatusByPostId() throws Exception {
		long postId = 1L;
		Post post = new Post();
		post.setId(postId);
		post.setApproved(false);
		post.setDateOfEvent(LocalDate.parse("2023-04-13"));
		post.setDateOfExpiration(LocalDate.parse("2023-04-17"));
		given(postService.getById(postId)).willReturn(post);
		
		ResultActions response = mockMvc.perform(get("/api/post/postStatusById/{postId}", postId));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(content().string("Waiting for approval"));
	}

	@Test
	public void testDenialReport() throws Exception {
		List<Post> listOfPosts = new ArrayList<>();
		listOfPosts.add(new Post());

		given(postService.denialReport()).willReturn(listOfPosts);

		ResultActions response = mockMvc.perform(get("/api/post/denialReport"));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.size()", is(listOfPosts.size())));
	}

	@Test
	public void testAddPostToCalendar() throws Exception {
		long postId = 1L;
		Post post = new Post();
		post.setId(postId);
		post.setApproved(false);
		post.setDateOfEvent(LocalDate.parse("2023-04-13"));
		post.setDateOfExpiration(LocalDate.parse("2023-04-17"));

		given(postService.approvePostById(postId)).willReturn(post);

		ResultActions response = mockMvc.perform(get("/api/post/addPostToCalendar/{postId}", postId));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(content().string("Successfully added post with ID: 1 to Calendar"));
	}

	@Test
	public void testRemovePostFromCalendar() throws Exception {
		long postId = 1L;
		Post post = new Post();
		post.setId(postId);
		post.setApproved(false);
		post.setDateOfEvent(LocalDate.parse("2023-04-13"));
		post.setDateOfExpiration(LocalDate.parse("2023-04-17"));

		given(postService.rejectPostById(anyLong(), anyString())).willReturn(post);

		ResultActions response = mockMvc.perform(get("/api/post/removePostFromCalendar/{postId}", postId));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(content().string("Successfully removed post with ID: 1 from Calendar"));
	}
}
