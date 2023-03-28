// package group.project.messageboard;

// import java.util.Date;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import group.project.messageboard.data.PersonRepository;
// import group.project.messageboard.data.PostRepository;
// import group.project.messageboard.models.*;

// @Configuration
// public class DevelopmentConfig {

// @Bean
// public CommandLineRunner dataLoader(PostRepository postRepo, PersonRepository
// personRepo)
// {
// return new CommandLineRunner() {
// @Override
// public void run(String... args) throws Exception {
// Post c1 = new Post();
// c1.setTitle("Content title");
// c1.setApproved(false);
// c1.setCreatedAt(new Date());
// c1.setDescription("description");
// c1.setUploadedFile("example file.png");
// Post savedContent = postRepo.save(c1);

// Person p1 = new Person();
// p1.addContent(savedContent);
// p1.setEmail("example@baylor.edu");
// p1.setFirstName("John");
// p1.setLastName("117");

// personRepo.save(p1);
// }
// };
// }
// }