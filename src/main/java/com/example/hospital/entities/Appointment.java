package com.example.hospital.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDateTime appointmentDate;
    private Double price;
    @OneToOne
    private AppointmentRequest appointmentRequest;
    @ManyToOne
    private AppointmentStatus appointmentStatus;
    @ManyToOne
    private ConsultingRoom consultingRoom;
    @ManyToOne
    private Person medic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public AppointmentRequest getAppointmentRequest() {
        return appointmentRequest;
    }

    public void setAppointmentRequest(AppointmentRequest appointmentRequest) {
        this.appointmentRequest = appointmentRequest;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public ConsultingRoom getConsultingRoom() {
        return consultingRoom;
    }

    public void setConsultingRoom(ConsultingRoom consultingRoom) {
        this.consultingRoom = consultingRoom;
    }

    public Person getMedic() {
        return medic;
    }

    public void setMedic(Person medic) {
        this.medic = medic;
    }
}
