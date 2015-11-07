package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ユーザーアカウントを作成するAPIを提供するコントローラーです。
 * 
 * @author backpaper0
 *
 */
@Controller
public class SignUpController {

    @Autowired
    AccountService service;

    /**
     * ユーザーアカウントの登録画面を表示します。
     * 
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String index() {
        return "signup";
    }

    /**
     * ユーザーアカウントを作成するAPIです。
     * 
     * @param userId ユーザーID
     * @return リダイレクト先
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@RequestParam String userId) {

        service.signUp(userId);

        return "redirect:/account/" + userId;
    }
}
