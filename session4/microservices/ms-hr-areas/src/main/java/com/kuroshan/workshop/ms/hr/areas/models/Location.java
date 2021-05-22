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
@Table(name = "LOCATIONS", schema = "HR", catalog = "")
public class Location {

  @Id
  @Column(name = "LOCATION_ID")
  private long locationId;

  @Basic
  @Column(name = "STREET_ADDRESS")
  private String streetAddress;

  @Basic
  @Column(name = "POSTAL_CODE")
  private String postalCode;

  @Basic
  @Column(name = "CITY")
  private String city;

  @Basic
  @Column(name = "STATE_PROVINCE")
  private String stateProvince;

  @ManyToOne
  @JoinColumn(name = "COUNTRY_ID", referencedColumnName = "COUNTRY_ID")
  private Country country;

}
