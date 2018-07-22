package com.microservice.quotes.repositories;

import com.microservice.quotes.models.QuoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteModel, Long> {

    String query = "SELECT MIN(price) AS low_price, MAX(price) AS high_price, SUM(volume) AS volume FROM stock_quotes WHERE symbol = 'AAPL' AND date LIKE '2018-06-22%';";

//    @Query(value = "SELECT * FROM stock_data WHERE symbol = ?1 AND trading_date = ?2", nativeQuery = true)
//    List<QuoteModel> findAllBySymbolAndDate(Long symbol, Date dateToSearch);

    @Query(value = "SELECT price FROM stock_data WHERE symbol = ?1 AND trading_date = ?2", nativeQuery = true)
    QuoteModel findMinAndMax(Long symbol, Date dateToSearch);

    @Query(value = "SELECT * FROM stock_data WHERE symbol = ?1 AND trading_date = ?2", nativeQuery = true)
    List<QuoteModel> findAllBySymbolAndDate(String symbol, Date dateToSearch);

    List<QuoteModel> findAllByDateContaining(Date date);

    @Query(value = "SELECT * FROM stock_data WHERE symbol = ?1", nativeQuery = true)
    List<QuoteModel> findAllBySymbol(Long symbol);

//    @Query(
//            value = "SELECT MAX (volume) FROM stock_data WHERE symbol = ?1 AND trading_date = ?2"
//            countQuery = "SELECT count(volume) FROM stock_data WHERE symbol = ?1 AND trading_date = ?2",
//    )
//    List<QuoteModel> findTotalVolumeForDate(String stockId, Date dateToSearch);

//    @Query(value = "SELECT o.symbol, min(o.price) from stock_data o WHERE o.symbol = ?1 AND o.trading_date = ?2", nativeQuery = true)
//    List<QuoteModel> findAllBySymbolAndDate(Long symbol, Date dateToSearch);

}
