package tech.flag.agregadordeinvestimentos.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.flag.agregadordeinvestimentos.dto.AccountStockResponseDto;
import tech.flag.agregadordeinvestimentos.dto.AssociateAccountStock;
import tech.flag.agregadordeinvestimentos.entity.AccountStock;
import tech.flag.agregadordeinvestimentos.entity.AccountStockId;
import tech.flag.agregadordeinvestimentos.repository.AccountRepository;
import tech.flag.agregadordeinvestimentos.repository.AccountStockRepository;
import tech.flag.agregadordeinvestimentos.repository.StockRepository;

import java.util.List;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private StockRepository stockRepository;
    private AccountStockRepository accountStockRepository;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
    }

    public void associateStock(Long accountId, AssociateAccountStock dto) {
        var account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository.findById(dto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entity = new AccountStock(
                id,
                account,
                stock,
                dto.quantity()
        );

        accountStockRepository.save(entity);
    }

    public List<AccountStockResponseDto> listStocks(Long accountId) {
        var account = accountRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return account.getAccountStocks()
                .stream()
                .map(as -> new AccountStockResponseDto(as.getStock().getStockId(), as.getQuantity(), 0.0))
                .toList();
    }
}
