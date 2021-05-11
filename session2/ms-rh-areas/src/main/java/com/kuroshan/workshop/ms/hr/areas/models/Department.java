package com.kuroshan.workshop.ms.hr.areas.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "DEPARTMENTS", schema = "HR", catalog = "")
public class Department {

  @Id
  @Column(name = "DEPARTMENT_ID")
  private long departmentId;

  @Basic
  @Column(name = "DEPARTMENT_NAME")
  private String departmentName;

  @ManyToOne
  @JoinColumn(name = "LOCATION_ID", referencedColumnName = "LOCATION_ID")
  private Location location;

}
