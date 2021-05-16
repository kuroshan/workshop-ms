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
@Table(name = "COUNTRIES", schema = "HR", catalog = "")
public class Country {

  @Id
  @Column(name = "COUNTRY_ID")
  private String countryId;

  @Basic
  @Column(name = "COUNTRY_NAME")
  private String countryName;

  @ManyToOne
  @JoinColumn(name = "REGION_ID", referencedColumnName = "REGION_ID")
  private Region region;

}
