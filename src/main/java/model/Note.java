package model;

import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "datetime")
    private LocalDateTime datetime;

    @Column(name = "content")
    private TextArea content;

    @ManyToOne
    @JoinColumn(name = "folders_id")
    private Folder folders_id;

    public Note(String title, TextArea content, LocalDateTime datetime, Folder folders_id){
        this.title = title;
        this.content = content;
        this.datetime = datetime;
        this.folders_id = folders_id;
    }

    protected Note() {

    }

    private Note(UUID id, String title, TextArea content, LocalDateTime datetime, Folder folders_id){
        this.id = id;
        this.title = title;
        this.content = content;
        this.datetime = datetime;
        this.folders_id = folders_id;
    }

    public static Note hydrate(UUID id,String title, TextArea content, LocalDateTime datetime, Folder folders_id){
        return new Note(id, title, content, datetime, folders_id);
    }

    public String getTitle() { return title; }

    public UUID getId() {
        return id;
    }

    public TextArea getContent() {
        return content;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public Folder getFolders_id() {
        return folders_id;
    }
}