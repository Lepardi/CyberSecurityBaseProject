package sec.project.controller;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Controller
public class SignupController {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/user";
    }
    

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String list(Authentication authentication, Model model) {

        Account account = accountRepository.findByUsername(authentication.getName());
        model.addAttribute("user", account);
        model.addAttribute("users", accountRepository.findAll());

        return "user";
    }

    
    @Transactional
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String transfer(Authentication authentication, @RequestParam String to, @RequestParam String message, @RequestParam Integer amount, Model model) {
        Account account = accountRepository.findByUsername(authentication.getName());
        Account accountTo = accountRepository.findByUsername(to);

        if (account.getMoneyCoins() >= amount && amount > 0) {
            account.setMoneyCoins(account.getMoneyCoins() - amount);
            accountTo.setMoneyCoins(accountTo.getMoneyCoins() + amount);
        }
        accountTo.setMessage(message);
        accountRepository.save(account);
        accountRepository.save(accountTo);

        model.addAttribute("user", account);
        model.addAttribute("users", accountRepository.findAll());
        
        return "user";
    }
    
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public String changePassword(Authentication authentication, @RequestParam String password) {
        Account account = accountRepository.findByUsername(authentication.getName());
        
        if (account == null) {
            return "redirect:/index";
        }

        account.setPassword(encoder.encode(password));
        accountRepository.save(account);

        return "redirect:/user";
    }
    
}
