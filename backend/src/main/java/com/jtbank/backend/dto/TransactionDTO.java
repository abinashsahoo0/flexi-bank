package com.jtbank.backend.dto;

import com.jtbank.backend.constant.TransactionMode;

import java.time.LocalDateTime;

public record TransactionDTO(
        LocalDateTime timestamp,
        TransactionMode mode,
        double balance
) {
}
