package roomescape.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationStatus;
import roomescape.domain.Theme;
import roomescape.fixture.Fixtures;

@DataJpaTest
class ThemeRepositoryTest {
    private final ThemeRepository themeRepository;
    private final EntityManager entityManager;

    @Autowired
    public ThemeRepositoryTest(ThemeRepository themeRepository, EntityManager entityManager) {
        this.themeRepository = themeRepository;
        this.entityManager = entityManager;
    }

    private void setEnvironmentToTheme1WinAndTheme2Lose() {
        entityManager.persist(Fixtures.member);
        entityManager.persist(Fixtures.theme1);
        entityManager.persist(Fixtures.theme2);
        entityManager.persist(Fixtures.reservationTime1);
        entityManager.persist(Fixtures.reservationTime2);

        Reservation theme1Reservation = new Reservation(
                null,
                Fixtures.member,
                Fixtures.oneDayPlusDate,
                Fixtures.reservationTime1,
                Fixtures.theme1,
                ReservationStatus.RESERVED
        );

        Reservation theme1Reservation2 = new Reservation(
                null,
                Fixtures.member,
                Fixtures.oneDayPlusDate,
                Fixtures.reservationTime2,
                Fixtures.theme1,
                ReservationStatus.RESERVED
        );

        Reservation theme2Reservation = new Reservation(
                null,
                Fixtures.member,
                Fixtures.oneDayPlusDate,
                Fixtures.reservationTime1,
                Fixtures.theme2,
                ReservationStatus.RESERVED
        );

        entityManager.persist(theme1Reservation);
        entityManager.persist(theme1Reservation2);
        entityManager.persist(theme2Reservation);
        entityManager.flush();
    }

    @Test
    void findRankByDate() {
        // given
        setEnvironmentToTheme1WinAndTheme2Lose();

        // when
        List<Theme> rankByDate = themeRepository.findRankByDate(Fixtures.oneDayMinusDate, Fixtures.twoDayPlusDate, 10);

        // then
        assertThat(rankByDate.getFirst()).isEqualTo(Fixtures.theme1);
        assertThat(rankByDate.getLast()).isEqualTo(Fixtures.theme2);
    }

    @DisplayName("findRankByDate() 의 limit 파라미터를 테스트한다.")
    @Test
    void findRankByDateWithLimit() {
        // given
        setEnvironmentToTheme1WinAndTheme2Lose();

        // when
        List<Theme> rankByDate = themeRepository.findRankByDate(Fixtures.oneDayMinusDate, Fixtures.twoDayPlusDate, 1);

        // then
        assertThat(rankByDate).hasSize(1);
    }

}
