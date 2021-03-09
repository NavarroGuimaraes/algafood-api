package com.csc.algafood.infrastructure.repository;

import static com.csc.algafood.infrastructure.specifications.restaurants.RestaurantSpecs.freeDeliveryFee;
import static com.csc.algafood.infrastructure.specifications.restaurants.RestaurantSpecs.nameLike;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.csc.algafood.domain.model.Restaurant;
import com.csc.algafood.domain.repository.RestaurantRepository;
import com.csc.algafood.domain.repository.RestaurantRepositoryCustom;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired @Lazy
	private RestaurantRepository repository;
	
	@Override
	public List<Restaurant> find(String name, BigDecimal minFee, BigDecimal maxFee) {
		
		var jpql = new StringBuilder();
		var parameters = new HashMap<String, Object>();
		jpql.append("from Restaurant where 0=0 ");
		
		if (StringUtils.hasLength(name)) {
			jpql.append("and name like :name ");
			parameters.put("name", "%"+name+"%");
		}

		if (minFee != null) {
			jpql.append("and deliveryFee >= :minFee ");
			parameters.put("minFee", minFee);
		}
		
		if (maxFee != null) {
			jpql.append("and deliveryFee <= :maxFee ");
			parameters.put("maxFee", maxFee);
		}
		
		TypedQuery<Restaurant> query = manager.createQuery(jpql.toString(), Restaurant.class);
		
		parameters.forEach((key, value) -> {
			query.setParameter(key, value);
		});

		return query.getResultList();
		
	}

	@Override
	public List<Restaurant> findUsingCriteria(String name, BigDecimal minFee, BigDecimal maxFee) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();//fabrica para construir elementos para fazermos consultas
		CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);		
		Root<Restaurant> root = criteria.from(Restaurant.class); //from Restaurante
		
		var predicates = new ArrayList<Predicate>();
		
		if (StringUtils.hasText(name)) {
			predicates.add(builder.like(root.get("name"), "%"+name+"%"));
		}
		
		if (minFee != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("deliveryFee"), minFee));
		}
		
		if (maxFee != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("deliveryFee"), maxFee));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Restaurant> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<Restaurant> findFreeDeliveryFee(String name) {
		return repository.findAll(
				freeDeliveryFee().and( nameLike(name) ) 
			);
	}
	
}
