package org.example.gotinka_travelling.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "guides")
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int GuideId;

    private String name;
    private String contact;
    private String languages; // Comma-separated: "English, Spanish, French"
    private Double rating;
    private Boolean available;
    private String imagePath;


    @OneToMany(mappedBy = "guide")
    private List<GuideTour> guideTours;
}