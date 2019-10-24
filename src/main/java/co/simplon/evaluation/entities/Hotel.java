package co.simplon.evaluation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Hotel implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String photo;
    private int star; 
    private int phone;
    private boolean selected;
    private String address;
    @OneToMany(mappedBy = "hotel")
    private Collection<Room> room;
    @ManyToOne
    private  City city;
}
