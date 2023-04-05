package ru.clevertec.ecl.bean;

import java.io.Serializable;

public interface BaseEntity<K extends Serializable> {
    K getId();
}
