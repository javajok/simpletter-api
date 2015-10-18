package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    @Autowired
    AccountService service;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String index() {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@RequestParam String userId) {

        service.signUp(userId);

        return "redirect:/account/" + userId;
    }
}
