package model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name= "folders")
public class Folder {
    @Id
    @Column(name = "id")
    //@GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    public Folder(String name){this.name = name;}

    protected Folder(){}

    private Folder(UUID id, String name){
        this.id = id;
        this.name = name;
    }

    public static Folder hydrate(UUID id, String name){return new Folder(id, name);}

    public UUID getId(){return id;}

    public String getName(){return name;}
}
