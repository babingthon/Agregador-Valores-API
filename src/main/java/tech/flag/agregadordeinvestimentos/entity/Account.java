package tech.flag.agregadordeinvestimentos.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Entity
@Table(name = "tb_accounts")
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<AccountStock> getAccountStocks() {
        return accountStocks;
    }

    public void setAccountStocks(List<AccountStock> accountStocks) {
        this.accountStocks = accountStocks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    @PrimaryKeyJoinColumn
    private BillingAddress billingAddress;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "account")
    private List<AccountStock> accountStocks;

    public Account() {
    }

    public Account(List<AccountStock> accountStocks, String description, BillingAddress billingAddress, User user) {
        this.accountStocks = accountStocks;
        this.description = description;
        this.billingAddress = billingAddress;
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
