package com.whalespottingjava.models.database;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "whale_sighting")
public class Sighting {
  private static final String ID_SEQUENCE = "whale_sighting_id_sequence";

  @Id
  @SequenceGenerator(name = ID_SEQUENCE, sequenceName = ID_SEQUENCE, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE)
  private Long id;

  @Column(nullable = false)
  private Long member_id;

  private Date date;
  private double latitude;
  private double longitude;
  private Boolean approved;

  public Sighting() {}

  public Sighting(Date date, double latitude, double longitude) {
    this.date = date;
    this.latitude = latitude;
    this.longitude = longitude;
    this.approved = false;
  }

  public Long getMemberId() {
    return member_id;
  }

  public void setMemberId(Long member_id) {
    this.member_id = member_id;
  }

  public Long getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public Boolean getApproved() {
    return approved;
  }

  public void setApproved(Boolean approved) {
    this.approved = approved;
  }
}
