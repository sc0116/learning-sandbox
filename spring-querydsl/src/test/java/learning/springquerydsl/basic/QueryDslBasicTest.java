package learning.springquerydsl.basic;

import static learning.springquerydsl.domain.QMember.member;
import static learning.springquerydsl.domain.QTeam.team;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import learning.springquerydsl.config.QueryDslConfig;
import learning.springquerydsl.domain.Member;
import learning.springquerydsl.domain.QMember;
import learning.springquerydsl.domain.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(QueryDslConfig.class)
@DataJpaTest
public class QueryDslBasicTest {

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
    void 이름으로_회원_조회1_별칭_사용() {
        /*
        select m
        from Member m
        where m.username = :username
         */
        final QMember m = new QMember("m");
        final Member actual = queryFactory.select(m)
                .from(m)
                .where(m.username.eq("member1"))
                .fetchOne();

        assertThat(actual).isEqualTo(member1);
    }

    @Test
    void 이름으로_회원_조회2_기본_인스턴스_사용() {
        /*
        select m
        from Member m
        where m.username = :username
        */
        final Member actual = queryFactory.select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        assertThat(actual).isEqualTo(member1);
    }

    @Test
    void 이름과_나이로_회원_조회() {
        /*
        select m
        from Member m
        where username = :username and age = 10
         */
        final Member actual = queryFactory.selectFrom(member)
                .where(member.username.eq("member1"),
                        member.age.eq(10))
                .fetchOne();

        assertThat(actual).isEqualTo(member1);
    }

    @Test
    void 모든_회원_조회() {
        /*
        select m
        from Member m
         */
        final List<Member> actual = queryFactory.selectFrom(member)
                .fetch();

        assertThat(actual).hasSize(4)
                .containsExactly(member1, member2, member3, member4);
    }

    @Test
    void 모든_회원_나이_역순으로_조회() {
        /*
        select m
        from Member m
        order by m.age desc
         */
        final List<Member> actual = queryFactory.selectFrom(member)
                .orderBy(member.age.desc(),
                        member.username.asc().nullsLast())
                .fetch();

        assertThat(actual).hasSize(4)
                .containsExactly(member4, member3, member2, member1);
    }

    @Test
    void 회원_이름_역순으로_페이징_조회() {
        /*
        select m
        from Member m
        order by m.username desc
        limit 2 offset 1
         */
        final List<Member> actual = queryFactory.selectFrom(member)
                .orderBy(member.username.desc())
                .offset(0)
                .limit(2)
                .fetch();

        assertThat(actual).hasSize(2)
                .containsExactly(member4, member3);
    }

    @Test
    void 회원_집합_함수_조회() {
        /*
         select COUNT(m), SUM(m.age), AVG(m.age), MAX(m.age), MIN(m.age)
         from Member m
         */
        final List<Tuple> result = queryFactory.select(
                        member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min()
                )
                .from(member)
                .fetch();

        final Tuple actual = result.get(0);
        assertAll(
                () -> assertThat(actual.get(member.count())).isEqualTo(4),
                () -> assertThat(actual.get(member.age.sum())).isEqualTo(100),
                () -> assertThat(actual.get(member.age.avg())).isEqualTo(25),
                () -> assertThat(actual.get(member.age.max())).isEqualTo(40),
                () -> assertThat(actual.get(member.age.min())).isEqualTo(10)
        );
    }

    @Test
    void 팀A에_소속된_모든_회원_조회() {
        /*
        select m
        from Member m join m.team t
        where t.name = :teamName
         */
        final List<Member> actual = queryFactory.selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(actual).hasSize(2)
                .containsExactly(member1, member2);
    }

    @Test
    void 페치_조인_사용해서_팀과_회원_한번에_조회() {
        /*
        select m
        from Member m join fetch m.team
        where m.username = :username
         */
        em.flush();
        em.clear();

        final Member actual = queryFactory.selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();

        assertThat(em.getEntityManagerFactory()
                .getPersistenceUnitUtil()
                .isLoaded(actual.getTeam())
        ).isTrue();
    }

    @Test
    void 서브_쿼리_사용해서_나이가_평균_나이_이상인_회원_조회() {
        final QMember memberSub = new QMember("memberSub");
        final List<Member> actual = queryFactory.selectFrom(member)
                .where(member.age.goe(
                        JPAExpressions.select(memberSub.age.avg())
                                .from(memberSub)
                ))
                .fetch();

        assertThat(actual).containsExactly(member3, member4);
    }

    @Test
    void case_문_사용() {
        final List<String> actual = queryFactory.select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("0 ~ 20살")
                        .when(member.age.between(21, 40)).then("21 ~ 40살")
                        .otherwise("기타"))
                .from(member)
                .fetch();

        assertThat(actual).hasSize(4)
                .containsExactly("0 ~ 20살", "0 ~ 20살", "21 ~ 40살", "21 ~ 40살");
    }

    @Test
    void 문자_더하기() {
        final String actual = queryFactory.select(member.username.concat("_").concat(member.age.stringValue()))
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        assertThat(actual).isEqualTo("member1_10");
    }
}
