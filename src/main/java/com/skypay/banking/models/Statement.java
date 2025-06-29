package com.skypay.banking.models;

import java.time.LocalDateTime;

public record Statement(int amount, LocalDateTime date) {}
