package sec.project.config;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    public void init() {
        Account account = new Account();
        account.setUsername("petri");
        account.setMoneyCoins(100);
        account.setCreditCard("1000");
        account.setSocSecNumber("0000-000A");
        account.setPassword(passwordEncoder.encode("petri"));
        accountRepository.save(account);
        
        Account accountTwo = new Account();
        accountTwo.setUsername("eero");
        accountTwo.setMoneyCoins(100);
        accountTwo.setCreditCard("1001");
        accountTwo.setSocSecNumber("0001-000B");
        accountTwo.setPassword(passwordEncoder.encode("eero"));
        accountRepository.save(accountTwo);
        
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }
        
        return new org.springframework.security.core.userdetails.User(
        account.getUsername(),
        account.getPassword(),
        true,
        true,
        true,
        true,
        Arrays.asList(new SimpleGrantedAuthority("USER")));
        
    }
}
