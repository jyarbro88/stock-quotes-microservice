package com.microservice.quotes.repositories;

import com.microservice.quotes.models.QuoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteModel, String> {

    @Query(value = "SELECT * FROM stock_data WHERE price IN (SELECT MAX(price) FROM stock_data WHERE symbol = ?1 AND trading_date = ?2);", nativeQuery = true)
    List<QuoteModel> findHighestPrice(String symbol, Date dateToSearch);

    @Query(value = "select * from stock_data where price in (select min(price) from stock_data where symbol = ?1 AND trading_date = ?2);", nativeQuery = true)
    List<QuoteModel> findLowestPrice(String symbol, Date dateToSearch);

    @Query(value = "select * from stock_data where price in (select sum(volume) from stock_data where symbol = ?1 and trading_date = ?2);", nativeQuery = true)
    Integer findTotalVolume(String symbol, Date dateToSearch);


//    @Query(value = "select COUNT(volume) from stock_data where symbol = ?1 AND trading_date = ?2", nativeQuery = true)
//    Integer findTotalVolume(String symbol, Date dateToSearch);

//    Integer countAllByVolumeAndDateBetween(String symbol, Date date);

//    @Query(value = "(select MIN(price) from stock_data where symbol = ?1 and trading_date = ?2);", nativeQuery = true)
//    List<QuoteModel> findAllBySymbolAndDate(String symbol, Date dateToSearch);

    @Query(value = "SELECT * FROM stock_data WHERE symbol = ?1", nativeQuery = true)
    List<QuoteModel> findAllBySymbol(String symbol);
}
