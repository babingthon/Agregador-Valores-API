package tech.flag.agregadordeinvestimentos.service;

import org.springframework.stereotype.Service;
import tech.flag.agregadordeinvestimentos.dto.CreateStockDto;
import tech.flag.agregadordeinvestimentos.entity.Stock;
import tech.flag.agregadordeinvestimentos.repository.StockRepository;

@Service
public class StockService {

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDto createStockDto) {
        var stock =  new Stock(
                createStockDto.stockId(),
                createStockDto.description()
        );

        stockRepository.save(stock);
    }
}
