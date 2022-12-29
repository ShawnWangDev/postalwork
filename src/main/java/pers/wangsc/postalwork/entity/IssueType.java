package pers.wangsc.postalwork.entity;

import jakarta.persistence.*;

@Entity(name = "issue_type")
public class IssueType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 16)
    private String name;

    public IssueType() {
    }

    public IssueType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "IssueType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
