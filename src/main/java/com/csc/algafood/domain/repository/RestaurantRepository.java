package com.csc.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csc.algafood.domain.model.Restaurant;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>
				, RestaurantRepositoryCustom
				, JpaSpecificationExecutor<Restaurant>{
	
//	@Query("select distinct r from Restaurant r join r.cookery left join fetch r.paymentMethods")
//	public List<Restaurant> findAll();

	public List<Restaurant> findByDeliveryFeeBetween(BigDecimal min, BigDecimal max);
	
	public Optional<Restaurant> findFistByName(String name);
	
	List<Restaurant> findTop2ByNameContaining(String name);
	
	int countByCookeryId(Long id);
	
	@Query("from Restaurant where name like %:name% and cookery.id = :id")
	List<Restaurant> findByNameLikeCaseSensitive(String name, @Param("id") Long cookeryId);
	
	List<Restaurant> find(String name, BigDecimal minFee, BigDecimal maxFee);
	
}
       