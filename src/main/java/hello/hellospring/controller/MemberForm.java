package hello.hellospring.controller;

public class MemberForm {
    //이 멤버변수 name은 createMemberForm 8라인의 name = "name" <- 이 쌍따옴표 안에 네임하고 매칭
    //어떻게 알고 매칭 ?
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
