package com.restfullApi.multidb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "siswa")

public class Siswa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 40)
    private Long id;


}
