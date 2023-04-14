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

import group.project.messageboard.controllers.ReportController;
import group.project.messageboard.models.*;
import group.project.messageboard.services.ReportService;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ReportController.class, excludeAutoConfiguration = (SecurityAutoConfiguration.class))
public class ReportTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReportService reportService;

    @Autowired
    private ObjectMapper objectMapper;

	@Test
	public void testGetAll() throws Exception {
		List<Report> listOfReports = new ArrayList<>();
		listOfReports.add(new Report());

		given(reportService.getAll()).willReturn(listOfReports);

		ResultActions response = mockMvc.perform(get("/api/report"));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.size()", is(listOfReports.size())));
	}

	@Test
	public void testCreate() throws Exception {
		Report report = new Report();
		report.setDate(LocalDate.parse("2023-04-13"));

		given(reportService.create(any(Report.class)))
			.willAnswer((invocation)-> invocation.getArgument(0));
		
		ResultActions response = mockMvc.perform(post("/api/report/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(report)));

		response.andDo(print())
			.andExpect(status().isCreated())
            .andExpect(jsonPath("$.date", is(report.getDate().toString())));
	}

	@Test
	public void testUpdateById() throws Exception {
		long reportId = 1L;
		Report savedReport = new Report();
        savedReport.setId(reportId);
        savedReport.setDate(LocalDate.parse("2023-04-13"));
		Report updatedReport = new Report();
        updatedReport.setId(reportId);
        updatedReport.setDate(LocalDate.parse("2023-04-14"));

		given(reportService.getById(reportId)).willReturn(savedReport);
		given(reportService.updateById(anyLong(), any(Report.class)))
			.willAnswer((invocation)-> invocation.getArgument(1));

		ResultActions response = mockMvc.perform(put("/api/report/{id}", reportId)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(updatedReport)));

		response.andExpect(status().isOk())
			.andDo(print())
            .andExpect(jsonPath("$.id", is(updatedReport.getId()), Long.class))
			.andExpect(jsonPath("$.date", is(updatedReport.getDate().toString())));
	}

	@Test
	public void testDelete() throws Exception {
		long reportId = 1L;
		willDoNothing().given(reportService).delete(reportId);

		ResultActions response = mockMvc.perform(delete("/api/report/{id}", reportId));

		response.andExpect(status().isOk())
			.andDo(print());
	}
	
	@Test
	public void testGetById() throws Exception {
		long reportId = 1L;
		Report report = new Report();
        report.setId(reportId);
        report.setDate(LocalDate.parse("2023-04-13"));

		given(reportService.getById(reportId)).willReturn(report);

		ResultActions response = mockMvc.perform(get("/api/report/{id}", reportId));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.id", is(report.getId()), Long.class))
            .andExpect(jsonPath("$.date", is(report.getDate().toString())));
	}

}
