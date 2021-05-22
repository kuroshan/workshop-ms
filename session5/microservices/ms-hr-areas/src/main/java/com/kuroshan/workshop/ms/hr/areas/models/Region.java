package com.kuroshan.workshop.ms.hr.areas.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "REGIONS", schema = "HR", catalog = "")
public class Region {

  @Id
  @Column(name = "REGION_ID")
  private long regionId;

  @Basic
  @Column(name = "REGION_NAME")
  private String regionName;

}
