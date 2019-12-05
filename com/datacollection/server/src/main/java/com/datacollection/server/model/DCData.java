package com.datacollection.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dc_data")
@JsonIgnoreProperties(value = {"timestamp"})
public class DCData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date timestamp;
    private Double carbonDioxide;
    private Double humidity;
    private Double temperature;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dataCollector", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DC dataCollector;

    public DCData() {

    }

    public DCData(Double carbonDioxide, Double humidity, Double temperature, DC dataCollector) {
        this.carbonDioxide = carbonDioxide;
        this.humidity = humidity;
        this.temperature = temperature;
        this.dataCollector = dataCollector;
    }

    public Long getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getCarbonDioxide() {
        return carbonDioxide;
    }

    public void setCarbonDioxide(Double carbonDioxide) {
        this.carbonDioxide = carbonDioxide;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public DC getDC() {
        return dataCollector;
    }

    public void setDC(DC dataCollector) {
        this.dataCollector = dataCollector;
    }
}
