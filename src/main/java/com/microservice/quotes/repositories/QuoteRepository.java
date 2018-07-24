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
    List<QuoteModel> findHighPriceForTheDay(String symbol, Date dateToSearch);

    @Query(value = "SELECT * FROM stock_data WHERE price IN (SELECT MIN(price) FROM stock_data WHERE symbol = ?1 AND trading_date = ?2);", nativeQuery = true)
    List<QuoteModel> findLowPriceForTheDay(String symbol, Date dateToSearch);

    @Query(value = "SELECT * FROM stock_data WHERE id IN (SELECT MAX(id) FROM stock_data WHERE symbol = ?1 AND trading_date =?2 GROUP BY symbol);", nativeQuery = true)
    QuoteModel findClosingPrice(String symbol, Date dateToSearch);

    @Query(value = "SELECT * FROM stock_data WHERE id IN (SELECT MIN(id) FROM stock_data WHERE symbol = ?1 AND trading_date = ?2 GROUP BY symbol);", nativeQuery = true)
    QuoteModel findOpeningPrice(String symbol, Date dateToSearch);

    @Query(value = "SELECT SUM(volume) FROM stock_data WHERE symbol = ?1 AND trading_date = ?2 GROUP BY symbol;", nativeQuery = true)
    Integer findTotalVolumeTradedForTheDay(String symbol, Date dateToSearch);

//    @Query(value = "select new com.microservice.quotes.models.EndOfDayModel(MAX(price), MIN(price), SUM(volume)) FROM QuoteModel quoteModel WHERE quoteModel.date = :date AND quoteModel.symbol = :stockId")
//    DailyStockModel findInfo(@Param("date")Date date, @Param("symbol") String stockId);
}
