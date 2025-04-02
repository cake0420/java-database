package com.cake7.database.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookLoans {
    private final UUID id;
    private final LocalDateTime loanDate;
    private final LocalDateTime dueDate;
    private final LocalDateTime returnDate;
    private final int penaltyUntil;
    private final boolean returnStatus;

    public BookLoans(UUID id, LocalDateTime loanDate, LocalDateTime dueDate,
                     LocalDateTime returnDate, int penaltyUntil, boolean returnStatus) {
        this.id = id;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.penaltyUntil = penaltyUntil;
        this.returnStatus = returnStatus;
    }

    public UUID getId() {
        return id;
    }
    public LocalDateTime getLoanDate() {
        return loanDate;
    }
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    public LocalDateTime getReturnDate() {
        return returnDate;
    }
    public int getPenaltyUntil() {
        return penaltyUntil;
    }
    public boolean isReturned() { // Boolean → boolean, 네이밍 개선
        return returnStatus;
    }
}
