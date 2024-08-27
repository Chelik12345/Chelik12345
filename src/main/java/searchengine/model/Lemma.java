package searchengine.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lema")
@Getter
@Setter
public class Lemma implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lemma;
    private int frequency;

    // Другие поля
    public int getFrequency() {
        return frequency;
    }
}