package com.lluengo.bci.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="phones")
public class Phone {
    @Id
    @GeneratedValue
    private Long id;
    private long number;
    private int cityCode;
    private String countryCode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
