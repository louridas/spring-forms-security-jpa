package sfsj;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class WebController extends WebMvcConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @RequestMapping(value="/members", method=RequestMethod.GET)
    public String showMembers(Model model) {
	Iterable<User> users = userRepository.findAll();
	model.addAttribute("members", users);
        return "members";
    }

    @RequestMapping(value={"/", "/login"}, method=RequestMethod.GET)
    public String showLogin() {
	return "login";
    }
    
    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String showForm(User user) {
        return "register";
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String checkPersonInfo(@Valid User user, 
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error(bindingResult.toString());
            return "register";
        }
        
        String plaintextPassword = user.getPlaintextPassword();
        List<Role> roles = roleRepository.findByName("user");
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode((plaintextPassword)));
       
        logger.info("About to save user...");
        userRepository.save(user);
        logger.info("Saved user.");
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken auth = 
        	new UsernamePasswordAuthenticationToken(userDetails, 
        		userDetails.getPassword(), userDetails.getAuthorities());
        
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "redirect:/results";
    }
    
}