package tech.flag.agregadordeinvestimentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AccountStockId {

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "stock_id")
    private String stockId;

    public AccountStockId() {
    }

    public AccountStockId(Long accountId, String stockId) {
        this.accountId = accountId;
        this.stockId = stockId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }
}
