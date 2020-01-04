package com.example.easy.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@AllArgsConstructor
public class Table implements Serializable {
    private final Integer number;
}

