package hello.hellospring.domain;

//ORM 오브젝트 릴레이션널 디비 맵핑. 객체와 관계형 데이터베이스를 맵핑해주는 기술 => JPA

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "username") 만약 db의 컬럼 명이 name이 아니고 다른거라면 컬럼 어노테이션으로 해결
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
