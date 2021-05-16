package com.kuroshan.workshop.ms.hr.areas.daos;

import javax.persistence.Tuple;

public interface AreaDao {

  Tuple findDepartmentByIdCustom(long id);

}
