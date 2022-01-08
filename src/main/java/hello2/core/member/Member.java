package hello2.core.member;

public class Member {

    private String name;
    private Long id;
    private Grade grade;


    public Member(String name, Long id, Grade grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
