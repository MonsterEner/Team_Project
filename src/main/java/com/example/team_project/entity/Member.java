package com.example.team_project.entity;

import com.example.team_project.entity.matching.MatchingMember;
import jakarta.persistence.*;
import lombok.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;
    @Column(length = 255, nullable = false)
    private String email;

    @Column(nullable = false)
    @Setter
    private String password;

    @Setter
    private String name;
    @Setter
    private String nickName;
    @Setter
    private String phone;
    private String gender;

    private String memberRole;
    @Setter
    private String addr;
    @Setter
    private String detailAddr;
    @Setter
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberImg memberImg;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    @Setter
    private Review review;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Board> board = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MatchingMember> memberMatchingList = new ArrayList<>();


    @Builder
    public Member(String email, String password, String name, String phone, String gender, String nickName, String addr, String detailAddr, String memberRole) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.nickName = nickName;
        this.addr = addr;
        this.detailAddr=detailAddr;
        this.memberRole = memberRole;
    }






}
