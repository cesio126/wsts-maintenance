package br.com.rio.maintenance.dataprovider.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.rio.maintenance.dataprovider.repository.entity.VehicleEntity;

@Repository
public interface IVehicleRepository extends PagingAndSortingRepository<VehicleEntity, Long> {
	public Page<VehicleEntity> findByVin(String vin, Pageable pageable);
	
	public Page<VehicleEntity> findAll(Pageable pageable);
}