package com.microservice.quotes.repositories;

import com.microservice.quotes.models.QuoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteModel, Long> {


}
