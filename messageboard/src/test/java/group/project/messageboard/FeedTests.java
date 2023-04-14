package group.project.messageboard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
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

import group.project.messageboard.controllers.FeedController;
import group.project.messageboard.models.*;
import group.project.messageboard.services.FeedService;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@ExtendWith(SpringExtension.class)
@WebMvcTest(value = FeedController.class, excludeAutoConfiguration = (SecurityAutoConfiguration.class))
public class FeedTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FeedService feedService;

    @Autowired
    private ObjectMapper objectMapper;

	@Test
	public void testGetAll() throws Exception {
		List<Feed> listOfFeeds = new ArrayList<>();
		listOfFeeds.add(new Feed(1L, false, 3, LocalDate.parse("2023-04-13"), LocalDate.parse("2023-04-16")));

		given(feedService.getAll()).willReturn(listOfFeeds);

		ResultActions response = mockMvc.perform(get("/api/feed"));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.size()", is(listOfFeeds.size())));
	}

	@Test
	public void testCreate() throws Exception {
		Feed feed = new Feed();
		feed.setRolling(false);
		feed.setDuration(3L);
		feed.setCurrentDateTime(LocalDate.parse("2023-04-13"));
		feed.setShutoffDateTime(LocalDate.parse("2023-04-16"));

		given(feedService.create(any(Feed.class)))
			.willAnswer((invocation)-> invocation.getArgument(0));
		
		ResultActions response = mockMvc.perform(post("/api/feed/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(feed)));

		response.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.duration", is(feed.getDuration()), Long.class))
			.andExpect(jsonPath("$.rolling", is(feed.isRolling())))
			.andExpect(jsonPath("$.currentDateTime", is(feed.getCurrentDateTime().toString())))
			.andExpect(jsonPath("$.shutoffDateTime", is(feed.getShutoffDateTime().toString())));
	}

	@Test
	public void testUpdateById() throws Exception {
		long feedId = 1L;
		Feed savedFeed = new Feed(feedId, false, 3, LocalDate.parse("2023-04-13"), LocalDate.parse("2023-04-16"));
		Feed updatedFeed = new Feed(feedId, false, 3, LocalDate.parse("2023-04-13"), LocalDate.parse("2023-04-17"));

		given(feedService.getById(feedId)).willReturn(savedFeed);
		given(feedService.updateById(anyLong(), any(Feed.class)))
			.willAnswer((invocation)-> invocation.getArgument(1));

		ResultActions response = mockMvc.perform(put("/api/feed/{id}", feedId)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(updatedFeed)));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.shutoffDateTime", is(updatedFeed.getShutoffDateTime().toString())));
	}

	@Test
	public void testDelete() throws Exception {
		long feedId = 1L;
		willDoNothing().given(feedService).delete(feedId);

		ResultActions response = mockMvc.perform(delete("/api/feed/{id}", feedId));

		response.andExpect(status().isOk())
			.andDo(print());
	}
	
	@Test
	public void testGetById() throws Exception {
		long feedId = 1L;
		Feed feed = new Feed(feedId, false, 3, LocalDate.parse("2023-04-13"), LocalDate.parse("2023-04-16"));
		given(feedService.getById(feedId)).willReturn(feed);

		ResultActions response = mockMvc.perform(get("/api/feed/{id}", feedId));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.duration", is(feed.getDuration()), Long.class))
			.andExpect(jsonPath("$.rolling", is(feed.isRolling())))
			.andExpect(jsonPath("$.currentDateTime", is(feed.getCurrentDateTime().toString())))
			.andExpect(jsonPath("$.shutoffDateTime", is(feed.getShutoffDateTime().toString())));
	}

	@Test
	public void testStartFeed() throws Exception {
		long feedId = 1L;
		Feed savedFeed = new Feed(feedId, false, 3, LocalDate.parse("2023-04-13"), LocalDate.parse("2023-04-16"));
		Feed updatedFeed = new Feed(feedId, true, 3, LocalDate.parse("2023-04-13"), LocalDate.parse("2023-04-16"));

		given(feedService.getById(feedId)).willReturn(savedFeed);
		given(feedService.setRolling(feedId)).willReturn(updatedFeed);

		ResultActions response = mockMvc.perform(put("/api/feed/startFeed/1", feedId));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.id", is(updatedFeed.getId()), Long.class))
			.andExpect(jsonPath("$.rolling", is(updatedFeed.isRolling())));
	}

	@Test
	public void testAddPostToFeed() throws Exception {
		long feedId = 1L;
		long postId = 1L;
		Post post = new Post();
		post.setId(postId);
		Feed feed = new Feed(feedId, false, 3, LocalDate.parse("2023-04-13"), LocalDate.parse("2023-04-16"));
		feed.addContent(post);
		
		given(feedService.getById(feedId)).willReturn(feed);
		given(feedService.addPostToFeed(feedId, postId)).willReturn(feed);

		ResultActions response = mockMvc.perform(post("/api/feed/addPost/{feedId}/{postId}", feedId, postId));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.id", is(feed.getId()), Long.class))
			.andExpect(jsonPath("$.contentList.length()", is(1)));
	}

}
