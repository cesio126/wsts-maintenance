package br.com.rio.maintenance.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.rio.maintenance.dataprovider.mapper.request.VehicleDataProviderMapperRequest;
import br.com.rio.maintenance.usecase.domain.request.VehicleDomainRequest;
import br.com.rio.maintenance.usecase.domain.response.VehicleDomainResponse;
import br.com.rio.maintenance.usecase.gateway.IVehicleGateway;

@Component
public class VehicleUseCase {

	@Autowired
	private IVehicleGateway gateway;

    public List<VehicleDomainResponse> getVehicle(String vin, String field, int page) {
    	if(StringUtils.isEmpty(vin))
            return gateway.getAll(createPageRequest(field, page));
    	else
    		return gateway.getVehicleByVin(vin, createPageRequest(field, page));
    }
    
    public VehicleDomainResponse create(VehicleDomainRequest request) {
    	return gateway.create(VehicleDataProviderMapperRequest.toDomain(request));
    }
    
    @SuppressWarnings("deprecation")
	private Pageable createPageRequest(String field, int page) {
        return new PageRequest(page, 
                10, 
                new Sort(Sort.Direction.DESC, field)
        );
    }
}