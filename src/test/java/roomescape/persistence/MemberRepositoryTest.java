package roomescape.persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import roomescape.domain.Member;
import roomescape.domain.MemberRole;

@DataJpaTest
class MemberRepositoryTest {
    private final MemberRepository memberRepository;
    private final EntityManager entityManager;
    private final Member dummyMember = new Member(null, "히스타", MemberRole.USER, "hista@email.com", "password");

    @Autowired
    public MemberRepositoryTest(MemberRepository memberRepository, EntityManager entityManager) {
        this.memberRepository = memberRepository;
        this.entityManager = entityManager;
    }

    @Test
    void findByEmail() {
        // given
        entityManager.persist(dummyMember);

        // when
        Optional<Member> foundMemberOptional = memberRepository.findByEmail(dummyMember.getEmail());

        // then
        assertTrue(foundMemberOptional.isPresent(), "Member should be found by email");
    }

    @Test
    void existsByEmail() {
        // given
        entityManager.persist(dummyMember);

        // when
        boolean actual = memberRepository.existsByEmail(dummyMember.getEmail());

        // then
        assertTrue(actual, "Member should exist by email");
    }
}
