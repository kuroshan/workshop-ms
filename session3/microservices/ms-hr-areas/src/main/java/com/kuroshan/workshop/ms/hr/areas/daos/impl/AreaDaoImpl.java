package com.kuroshan.workshop.ms.hr.areas.daos.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kuroshan.workshop.ms.hr.areas.daos.AreaDao;

import javax.persistence.*;

@Slf4j
@Repository
@Transactional(readOnly = true)
public class AreaDaoImpl implements AreaDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Tuple findDepartmentByIdCustom(long id) {
		Tuple t;

		TypedQuery<Tuple> query = entityManager
				.createQuery("SELECT d.departmentId as departmentId, d.departmentName as departmentName, "
						+ "l.locationId as locationId, l.streetAddress as streetAddress, "
						+ "c.countryId as countryId, c.countryName as countryName, "
						+ "r.regionId as regionId, r.regionName as regionName "
						+ "FROM Department d, Location l, Country c, Region r " + "WHERE d.departmentId = :id "
						+ "AND d.location.locationId = l.locationId " + "AND l.country.countryId = c.countryId "
						+ "AND c.region.regionId = r.regionId", Tuple.class);
		query.setParameter("id", id);

		try {
			t = query.getSingleResult();
		} catch (NoResultException ex) {
			log.error(ex.getLocalizedMessage());
			t = null;
		}
		return t;
	}

}
