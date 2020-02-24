package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "resume")
public class Resume {
    // TODO Implement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "resume_id")
    private Integer id;

    @Column (name = "user_id")
    private Integer userId;

    @Column (name = "description")
    private String description;

    @Column (name = "is_active")
    private Boolean isActive;
    // ToDo: no-arg constructor
    public Resume (){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;
        Resume resume = (Resume) o;
        return getId().equals(resume.getId()) &&
                getUserId().equals(resume.getUserId()) &&
                getDescription().equals(resume.getDescription()) &&
                Objects.equals(getIsActive(), resume.getIsActive());
    }

    @Override
    public int hashCode() {
        return 21;//Objects.hash(getId(), getUserId(), getDescription(), getIsActive());
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
