package com.microservice.quotes.repositories;

import com.microservice.quotes.models.QuoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteModel, Long> {

//    @Query(value = "SELECT * FROM stock_data WHERE symbol = ")
    String query = "SELECT MIN(price) AS low_price, MAX(price) AS high_price, SUM(volume) AS volume FROM stock_quotes WHERE symbol = 'AAPL' AND date LIKE '2018-06-22%';";

    @Query(value = "SELECT * FROM stock_data WHERE symbol = ?1 AND trading_date = ?2", nativeQuery = true)
    List<QuoteModel> findAllBySymbolAndDate(Long symbol, Date dateToSearch);

//    @Query(value = "SELECT MIN(price), MAX(price) FROM stock_data WHERE symbol = ?1", nativeQuery = true)
//    QuoteModel findMinAndMax(Long symbol);

//    @Query(value = "SELECT MIN(price), MAX(price), SUM(volume) FROM stock_data WHERE symbol = ?1", nativeQuery = true)
//    List<QuoteModel> findAllBySymbolAndDate(Long symbol, Date dateToSearch);

    List<QuoteModel> findAllByDateContaining(Date date);

    @Query(value = "SELECT * FROM stock_data WHERE symbol = ?1", nativeQuery = true)
    List<QuoteModel> findAllBySymbol(Long symbol);

}
