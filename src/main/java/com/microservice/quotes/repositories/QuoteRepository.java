package com.microservice.quotes.repositories;

import com.microservice.quotes.models.QuoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteModel, String> {

    List<QuoteModel> findAllBySymbolAndDate(String symbol, Date dateToSearch);

    @Query(value = "SELECT * FROM stock_data WHERE price IN (SELECT MAX(price) FROM stock_data WHERE symbol = ?1 AND trading_date = ?2);", nativeQuery = true)
    List<QuoteModel> findHighestPrice(String symbol, Date dateToSearch);

    @Query(value = "SELECT * FROM stock_data WHERE price IN (SELECT min(price) FROM stock_data WHERE symbol = ?1 AND trading_date = ?2);", nativeQuery = true)
    List<QuoteModel> findLowestPrice(String symbol, Date dateToSearch);

    @Query(value = "SELECT * FROM stock_data WHERE id IN (SELECT MAX(id) FROM stock_data WHERE symbol = ?1 AND trading_date =?2 GROUP BY symbol);", nativeQuery = true)
    QuoteModel findClosingPrice(String symbol, Date dateToSearch);

    @Query(value = "SELECT * FROM stock_data WHERE id IN (SELECT MIN(id) FROM stock_data WHERE symbol = ?1 AND trading_date = ?2 GROUP BY symbol);", nativeQuery = true)
    QuoteModel findOpeningPrice(String symbol, Date dateToSearch);

    @Query(value = "select sum(volume) from stock_data where symbol = ?1 AND tradiing_date = ?2", nativeQuery = true)
    Integer totalVolume(String symbol, Date dateToSearch);
}
