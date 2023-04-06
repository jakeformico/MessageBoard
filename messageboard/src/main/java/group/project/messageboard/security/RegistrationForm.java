package group.project.messageboard.security;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import group.project.messageboard.models.Person;
import group.project.messageboard.models.PersonRole;

@Data
public class RegistrationForm {

  private String firstName;
    private String lastName;
    private String email;
    private String password;
    private PersonRole personRole;
  
  public Person toPerson(PasswordEncoder passwordEncoder) {
    return new Person(firstName, lastName, email, passwordEncoder.encode(password), personRole);
  }
  
}
