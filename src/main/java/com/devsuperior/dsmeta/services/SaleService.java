package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	private final LocalDate CURRENTDATE = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> getSaleReport(String minDate, String maxDate, String name, Pageable pageable) {

		LocalDate startDate = (minDate == null ||minDate.isEmpty())
				? CURRENTDATE.minusYears(1L) : LocalDate.parse(minDate);

		LocalDate finalDate = (maxDate == null ||maxDate.isEmpty())
				? CURRENTDATE : LocalDate.parse(maxDate);

		return repository.searchSaleReport(startDate, finalDate, name, pageable);
	}

	public Page<SaleSummaryDTO> getSaleSummary(String minDate, String maxDate, Pageable pageable) {
		LocalDate startDate = (minDate == null || minDate.isEmpty())
				? CURRENTDATE.minusYears(1L) : LocalDate.parse(minDate);
		LocalDate finalDate = (maxDate == null || maxDate.isEmpty())
				? CURRENTDATE : LocalDate.parse(maxDate);
		return repository.searchSaleSummary(startDate,finalDate, pageable);
	}
}
