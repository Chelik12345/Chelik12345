package searchengine.model;
package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "page")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String path;

    @Column(columnDefinition = "INT", nullable = false)
    private int statusCode;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String content;

    // Getters and Setters
}
