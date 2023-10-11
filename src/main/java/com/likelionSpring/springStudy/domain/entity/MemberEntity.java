package com.likelionSpring.springStudy.domain.entity;

import com.likelionSpring.springStudy.domain.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자
@Getter //알아서 getter를 만들어 준다. Setter, ToString 등등 다 되지만 Getter만 쓴다.
@Table(name = "member")
//@Where(clause = "is_deleted = false") //기본적으로 들어가게 하는 것
//@SQLDelete(sql = "udate member set is_delted = true, deleted_at = now()+30 where id = ?") //jpa에서 쓸 때 실제로 시행되는 것
//Where과 SQLDelete는 실제로 사용하지 않는다. 그 이유는 탈퇴하거나 존재하는 회원 전부 데려와야되는데 남아있는 애들만 조회가 되기 때문에
//실제 개발을 할 때 비즈니스 요구사항이 들어왔을 때 대응이 제대로 이루어지지 않을 수 있다.
 // 그냥 선언하는 경우가 많다! (MemberEntity인 경우 그냥 class Entity로... )
public class MemberEntity extends BaseTimeEntity {

    private static final Long MEMBER_INFO_RETENTION_PERIOD = 30L; //변수로 선언하는 방식
    //Getter Setter도 Annotation을 가져와야 한다.
    //아이디 생성 전략이래 저 GeneratedValue부분이... UUID는 table id를 uuid로 할때 사용.. 웬만하면 IDENTITY인가보다
    //캡슐화 특성으로 속성을 감춘다. -> method를 이용해서 접근을 해야 된다.
    //관계형 데이터베이스 -> 식별자를 두는 것
    //pk는 wrapper Class로 무조건 해야되고, 나머지 친구들은 속성을 보고 결정을 하기 때문.. String은 null이 되지만 int같은 원시타입은 null이 안된다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String nickname;

    //회원 닉네임을 업데이트하는 기능
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    //편지함과 1대 1연결
    @OneToOne(mappedBy = "member")
    private  BoxEntity box;

    //삭제 시 필요한 변수들!
    private boolean isDeleted;
    private LocalDateTime deletedAt;

    @Builder
    public MemberEntity(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public static MemberEntity createMember(String username, String password, String nickname){
        return new MemberEntity(username, password, nickname);
    }

    private void validateNickname(){
        if(this.nickname.length()>20){
            throw  new IllegalArgumentException("닉네임은 20자 이하여야 합니다");
        }
    }

    //delete와 관련된 method들을 만들어준다.
    public void softDelete() {
        this.isDeleted = true; //DB에는 삭제가 안되었지만 삭제가 되도록 인식하게 해주는 것
        this.deletedAt = LocalDateTime.now().plusDays(MEMBER_INFO_RETENTION_PERIOD); //30일동안 보관하도록 하는 것 plusDays라는 method가 내재되어있음
    }

    public void recover() {
        this.isDeleted = false;
        this.deletedAt = null;
    }
    //    private String name;
//    //요런친구도 있네...
//    @Column(nullable = false)
    //이름을 바꾸고 싶다면 Column엔 name속성이 있다..
//    private String nickname;
//    private int age;
//    private boolean isAdult;

    //생성자는 일종의 method, class와 반드시 이름이 똑같아야 한다.
    //생성자 생성을 하면 기본을 무조건 만들어줘야한다(JPA 기준)
//    public MemberEntity(Long id, String name, String nickname, int age, boolean isAdult) {
//        this.id = id;
//        this.name = name;
//        this.nickname = nickname;
//        this.age = age;
//        this.isAdult = isAdult;
//    }

}
