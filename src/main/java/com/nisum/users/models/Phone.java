package com.nisum.users.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "phones")
public class Phone {
  
  @Id
  private String id;
  
  private String number;
  
  @Column(name = "city_code")
  private String cityCode;
  
  @Column(name = "country_code")
  private String countryCode;

}
