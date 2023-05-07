package learning.springquerydsl.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(final String username) {
        this(username, 0);
    }

    public Member(final String username, final int age) {
        this(username, age, null);
    }

    public Member(final String username, final int age, final Team team) {
        this.username = username;
        this.age = age;
        this.team = team;
    }

    public void changeTeam(final Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
