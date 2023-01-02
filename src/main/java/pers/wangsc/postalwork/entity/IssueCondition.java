package pers.wangsc.postalwork.entity;

import jakarta.persistence.*;

@Entity
public class IssueCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 32)
    private String name;

    public IssueCondition() {
    }

    public IssueCondition(Integer id){
        this.id=id;
    }

    public IssueCondition(Integer id, String name) {
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
