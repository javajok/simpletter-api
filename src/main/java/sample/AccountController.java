package sample;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

/**
 * ユーザーアカウントに関する操作を行うAPIを提供するコントローラーです。
 * 
 * @author backpaper0
 *
 */
@Controller
public class AccountController {

    @Autowired
    AccountService service;

    /**
     * 指定されたユーザーのアカウント情報を表示します。
     * 
     * @param model
     * @param userId ユーザーID
     * @return
     */
    @RequestMapping(value = "/account/{userId}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable String userId) {

        Account account = service.find(userId);

        model.addAttribute("userId", account.userId);

        return "account";
    }

    /**
     * 指定されたユーザーのアイコンを更新します。
     * 
     * @param userId ユーザーID
     * @param icon アイコンのデータ
     * @return リダイレクト先
     * @throws IOException
     */
    @RequestMapping(value = "/account/{userId}", method = RequestMethod.POST)
    public String update(@PathVariable String userId,
            @RequestParam MultipartFile icon) throws IOException {

        //TODO 画像の縮小

        service.update(userId, icon.getBytes());

        return "redirect:/account/" + userId;
    }

    /**
     * 指定されたユーザーのアイコンを返します。
     * 
     * @param userId ユーザーID
     * @return アイコンのデータ
     */
    @RequestMapping("/icon/{userId}")
    @ResponseBody
    public byte[] icon(@PathVariable String userId) {

        Account account = service.find(userId);

        return account.icon;
    }

    /**
     * 登録されているユーザーアカウントの一覧を表示します。
     * 
     * @param model
     * @return
     */
    @RequestMapping("/accounts")
    public String list(Model model) {
        List<Account> accounts = service.findAll();
        model.addAttribute("accounts", accounts);
        return "account_list";
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleAccountNotFound(AccountNotFoundException e) {
    }
}
