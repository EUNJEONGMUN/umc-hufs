package umc.crudproject.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @Autowired
    private HomeService homeService;

    @GetMapping("/home")
    public String start() {
        return "hello!";
    }

}
