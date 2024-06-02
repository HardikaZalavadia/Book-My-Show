package BMS.models;

import BMS.models.constant.PaymentType;
import BMS.models.constant.Paymentstatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Payment extends BaseModel{
    private String referenceId;
    private double amount;
    private LocalDateTime paymentTime;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Enumerated(EnumType.STRING)
    private Paymentstatus paymentstatus;
    @ManyToOne
    private Ticket ticket;


}
