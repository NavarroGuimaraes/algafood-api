package com.csc.algafood.domain.repository;

import java.util.List;

import com.csc.algafood.domain.model.Permission;

public interface PermissionRepository {
	
	public List<Permission> findAll();
	public Permission findById(Long id);
	public Permission save(Permission entity);
	public void delete(Permission entity);

}
