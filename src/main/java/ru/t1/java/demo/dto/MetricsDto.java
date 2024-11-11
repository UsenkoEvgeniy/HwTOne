package ru.t1.java.demo.dto;

import java.io.Serializable;

public record MetricsDto(long elapsedTime, String methodName, Object[] args) implements Serializable {
}
