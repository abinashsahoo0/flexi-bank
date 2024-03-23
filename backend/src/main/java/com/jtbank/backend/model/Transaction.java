package com.jtbank.backend.model;

import com.jtbank.backend.constant.TransactionMode;
import com.jtbank.backend.model.helper.Auditing;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionMode mode;
    private LocalDateTime timestamp;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_sl_no")
    private  Account account;
}
