package sec.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Account extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String username;
    private String password;
    //private String number;
    private Integer MoneyCoins;
    private String message;
    private String creditCard;
    private String socSecNumber;

    
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getCreditCard() {
        return this.creditCard;
    }

    public void setCreditCard(String card) {
        this.creditCard = card;
    }
    
    public String getSocSecNumber() {
        return this.socSecNumber;
    }

    public void setSocSecNumber(String socNumb) {
        this.socSecNumber = socNumb;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public Integer getMoneyCoins() {
        return MoneyCoins;
    }

    public void setMoneyCoins(int MoneyCoins) {
        this.MoneyCoins = MoneyCoins;
    }
    
    

}
