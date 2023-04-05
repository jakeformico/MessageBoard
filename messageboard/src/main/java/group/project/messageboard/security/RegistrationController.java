package group.project.messageboard.security;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import group.project.messageboard.repositories.PersonRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {
  
  private PersonRepository personRepo;
  private PasswordEncoder passwordEncoder;

  public RegistrationController(
      PersonRepository personRepo, PasswordEncoder passwordEncoder) {
    this.personRepo = personRepo;
    this.passwordEncoder = passwordEncoder;
  }
  
  @GetMapping
  public String registerForm() {
    return "registration";
  }
  
  @PostMapping
  public String processRegistration(RegistrationForm form) {
    personRepo.save(form.toPerson(passwordEncoder));
    return "redirect:/login";
  }

}
