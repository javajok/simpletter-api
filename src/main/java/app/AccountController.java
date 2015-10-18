package app;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AccountController {

    @Autowired
    AccountService service;

    @RequestMapping(value = "/account/{userId}", method = RequestMethod.GET)
    String view(Model model, @PathVariable String userId) {

        //TODO アカウントの存在チェック

        model.addAttribute("userId", userId);

        return "account";
    }

    @RequestMapping(value = "/account/{userId}", method = RequestMethod.POST)
    public String update(@PathVariable String userId,
            @RequestParam MultipartFile icon) throws IOException {

        //TODO アカウントの存在チェック

        //TODO 画像の縮小

        service.update(userId, icon.getBytes());

        return "redirect:/account/" + userId;
    }

    @RequestMapping("/icon/{userId}")
    @ResponseBody
    public byte[] icon(@PathVariable String userId) {

        //TODO アカウントの存在チェック

        Account account = service.find(userId);

        return account.icon;
    }
}
