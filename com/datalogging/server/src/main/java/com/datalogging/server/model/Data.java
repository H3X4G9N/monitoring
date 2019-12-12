package com.datalogging.server.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dl_data")
public class Data {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date timestamp;

    @Column(name = "carbon_dioxide", nullable = false)
    private Double carbonDioxide;

    @Column(name = "humidity", nullable = false)
    private Double humidity;

    @Column(name = "temperature", nullable = false)
    private Double temperature;

    @JoinColumn(name = "data_logger", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private DataLogger dataLogger;

    public Data() {

    }

    public Data(Double carbonDioxide, Double humidity, Double temperature, DataLogger dataLogger) {
        this.carbonDioxide = carbonDioxide;
        this.humidity = humidity;
        this.temperature = temperature;
        this.dataLogger = dataLogger;
    }

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
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

    public DataLogger getDataLogger() {
        return dataLogger;
    }

    public void setDataLogger(DataLogger dataCollector) {
        this.dataLogger = dataLogger;
    }
}
