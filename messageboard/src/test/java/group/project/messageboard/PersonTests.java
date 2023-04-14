package group.project.messageboard;

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

import group.project.messageboard.controllers.PersonController;
import group.project.messageboard.models.*;
import group.project.messageboard.services.PersonService;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PersonController.class, excludeAutoConfiguration = (SecurityAutoConfiguration.class))
public class PersonTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;

	@Test
	public void testGetAllPersons() throws Exception {
		List<Person> listOfPersons = new ArrayList<>();
		listOfPersons.add(new Person("Michael", "Crowther", "Michael_Crowther1@baylor.edu", "password", PersonRole.Admin));

		given(personService.findAll()).willReturn(listOfPersons);

		ResultActions response = mockMvc.perform(get("/api/person"));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.size()", is(listOfPersons.size())));
	}

	@Test
	public void testCreatePerson() throws Exception {
		Person person = new Person("Michael", "Crowther", "Michael_Crowther1@baylor.edu", "password", PersonRole.Admin);
		
		given(personService.createPerson(any(Person.class)))
			.willAnswer((invocation)-> invocation.getArgument(0));
		
		ResultActions response = mockMvc.perform(post("/api/person/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(person)));

		response.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.firstName", is(person.getFirstName())))
			.andExpect(jsonPath("$.lastName", is(person.getLastName())))
			.andExpect(jsonPath("$.email", is(person.getEmail())))
			.andExpect(jsonPath("$.password", is(person.getPassword())));
	}

	@Test
	public void testUpdatePersonById() throws Exception {
		long personId = 1L;
		Person savedPerson = new Person("Michael", "Crowther", "Michael_Crowther@baylor.edu", "pass", PersonRole.Admin);
        savedPerson.setId(personId);
		Person updatedPerson = new Person("Mike", "Crowther", "Michael_Crowther1@baylor.edu", "password", PersonRole.Admin);
        updatedPerson.setId(personId);

		given(personService.getById(personId)).willReturn(savedPerson);
		given(personService.updatePersonById(personId, any(Person.class)))
			.willAnswer((invocation)-> invocation.getArgument(1));

		ResultActions response = mockMvc.perform(put("/api/person/{id}", personId)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(updatedPerson)));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.firstName", is(updatedPerson.getFirstName())))
            .andExpect(jsonPath("$.email", is(updatedPerson.getEmail())))
            .andExpect(jsonPath("$.password", is(updatedPerson.getPassword())));
	}

	@Test
	public void testDeletePerson() throws Exception {
		long personId = 1L;
		willDoNothing().given(personService).deletePerson(personId);

		ResultActions response = mockMvc.perform(delete("/api/person/{id}", personId));

		response.andExpect(status().isOk())
			.andDo(print());
	}
	
	@Test
	public void testGetById() throws Exception {
		long personId = 1L;
		Person person = new Person("Michael", "Crowther", "Michael_Crowther@baylor.edu", "pass", PersonRole.Admin);
		given(personService.getById(personId)).willReturn(person);

		ResultActions response = mockMvc.perform(get("/api/person/{id}", personId));

		response.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.firstName", is(person.getFirstName())))
            .andExpect(jsonPath("$.lastName", is(person.getLastName())))
			.andExpect(jsonPath("$.email", is(person.getEmail())))
			.andExpect(jsonPath("$.password", is(person.getPassword())))
			.andExpect(jsonPath("$.personRole", is(person.getPersonRole().toString())));
	}

}
