package com.csc.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "permission_groups")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Group {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//
	@Column
	private String name;
	
	@ManyToMany
	@JoinTable(name="group_permissions", 
			joinColumns = @JoinColumn(name="group_id")
			, inverseJoinColumns = @JoinColumn(name="permission_id"))
	private List<Permission> permissions = new ArrayList<>();

}
