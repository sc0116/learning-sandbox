package learning.springquerydsl.intermediate;

import static learning.springquerydsl.domain.QMember.member;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import learning.springquerydsl.config.QueryDslConfig;
import learning.springquerydsl.domain.Member;
import learning.springquerydsl.domain.Team;
import learning.springquerydsl.dto.MemberDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(QueryDslConfig.class)
@DataJpaTest
public class QueryDslIntermediateTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private JPAQueryFactory queryFactory;

    private Team teamA;
    private Team teamB;
    private Member member1;
    private Member member2;
    private Member member3;
    private Member member4;


    @BeforeEach
    void setUp() {
        teamA = new Team("teamA");
        teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        member1 = new Member("member1", 10, teamA);
        member2 = new Member("member2", 20, teamA);
        member3 = new Member("member3", 30, teamB);
        member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @AfterEach
    void tearDown() {
        em.createNativeQuery("DELETE FROM Team");
        em.createNativeQuery("DELETE FROM Member");
    }

    @Test
    void 프로젝션_대상이_하나면_타입을_명확하게_지정할_수_있음() {
        final List<String> actual = queryFactory.select(member.username)
                .from(member)
                .fetch();

        assertThat(actual).hasSize(4)
                .containsExactly(member1.getUsername(), member2.getUsername(), member3.getUsername(),
                        member4.getUsername());
    }

    @Test
    void 프로젝션_대상이_둘_이상일_때_튜플로_조회하는_경우() {
        final Tuple actual = queryFactory.select(member.username, member.age)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.get(member.username)).isEqualTo("member1"),
                () -> assertThat(actual.get(member.age)).isEqualTo(10)
        );
    }

    @Test
    void 프로젝션_대상이_둘_이상일_때_DTO로_조회하는_경우() {
        /*
        select new learning.springquerydsl.dto.MemberDto(m.username, m.age)
        from Member m
        where m.username = :username
         */
        final MemberDto actual = queryFactory.select(Projections.constructor(MemberDto.class,
                        member.username,
                        member.age
                ))
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        assertThat(actual).usingRecursiveComparison()
                .isEqualTo(new MemberDto("member1", 10));
    }

    @Test
    void Where_다중_파라미터를_사용해서_동적_쿼리_해결() {
        assertThat(searchMember("member1", 10)).hasSize(1);
    }

    private List<Member> searchMember(final String usernameCond, final Integer ageCond) {
        return queryFactory.selectFrom(member)
                .where(usernameEq(usernameCond), ageEq(ageCond))
                .fetch();
    }

    private BooleanExpression usernameEq(final String usernameCond) {
        if (usernameCond != null) {
            return member.username.eq(usernameCond);
        }
        return null;
    }

    private BooleanExpression ageEq(final Integer ageCond) {
        if (ageCond != null) {
            return member.age.eq(ageCond);
        }
        return null;
    }

    @Test
    void 벌크_연산으로_대량_데이터_수정() {
        final long actual = queryFactory.update(member)
                .set(member.age, member.age.add(1))
                .execute();
        em.close();

        assertThat(actual).isEqualTo(4);
    }
}
