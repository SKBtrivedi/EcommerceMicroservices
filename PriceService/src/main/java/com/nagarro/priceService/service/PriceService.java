package com.nagarro.priceService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.priceService.exception.ResourceNotFoundException;
import com.nagarro.priceService.model.Price;
import com.nagarro.priceService.repository.PriceRepository;

@Service
public class PriceService {

	private static final Logger logger = LoggerFactory.getLogger(PriceService.class);

	@Autowired
	private PriceRepository priceRepository;

	public Price getPriceByProductId(Long productId) {
		logger.debug("Fetching price for product ID: {}", productId);
		return priceRepository.findByProductId(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Price not found"));
	}

	public Price addOrUpdatePrice(Price price) {
		logger.debug("Adding price: {}", price);
		return priceRepository.save(price);
	}

	public void removePrice(Long productId) {
		logger.debug("Removing price for product ID: {}", productId);
		priceRepository.findByProductId(productId).ifPresent(priceRepository::delete);
		logger.debug("Price removed for product ID: {}", productId);
	}
}
