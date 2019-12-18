package com.example.easy.domain;

import lombok.Value;

import java.io.Serializable;

@Value
public class Table implements Serializable {
    private final Integer number;
}

