package tech.flag.agregadordeinvestimentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.flag.agregadordeinvestimentos.dto.AccountStockResponseDto;
import tech.flag.agregadordeinvestimentos.dto.AssociateAccountStock;
import tech.flag.agregadordeinvestimentos.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable("accountId") Long accountId,
                                               @RequestBody AssociateAccountStock dto){
        accountService.associateStock(accountId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDto>> listStock(@PathVariable("accountId") Long accountId){
        var stock = accountService.listStocks(accountId);
        return ResponseEntity.ok(stock);
    }
}
