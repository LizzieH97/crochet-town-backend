package org.crochetdata.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pattern")
public class Pattern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ElementCollection
    @CollectionTable(
        name = "pattern_step_images",
        joinColumns = @JoinColumn(name = "pattern_id")
    )
    @Column(name = "step_images")
    private List<String> stepImages = new ArrayList<>();



    // Rest of the class can remain the same as it already follows conventions
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getStepImages() {
        return stepImages;
    }

    public void setStepImages(List<String> stepImages) {
        this.stepImages = stepImages;
    }

    public Pattern() {}
}