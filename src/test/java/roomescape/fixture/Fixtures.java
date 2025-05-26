package roomescape.fixture;

import java.time.LocalDate;
import java.time.LocalTime;
import roomescape.domain.Member;
import roomescape.domain.MemberRole;
import roomescape.domain.ReservationTime;
import roomescape.domain.Theme;

public class Fixtures {
    // Member fixture
    public static final Member member = new Member(null, "히스타", MemberRole.USER, "hista@email.com", "password");

    // Theme fixture
    public static final Theme theme = new Theme(null, "테마 이름", "테마 설명", "테마 썸네일");
    public static final Theme theme1 = new Theme(null, "테마 이름1", "테마 설명1", "테마 썸네일1");
    public static final Theme theme2 = new Theme(null, "테마 이름2", "테마 설명2", "테마 썸네일2");

    // ReservationTime fixture
    public static final ReservationTime reservationTime = new ReservationTime(null, LocalTime.of(12, 0));
    public static final ReservationTime reservationTime1 = new ReservationTime(null, LocalTime.of(12, 0));
    public static final ReservationTime reservationTime2 = new ReservationTime(null, LocalTime.of(13, 0));

    // Date fixture
    public static final LocalDate oneDayPlusDate = LocalDate.now().plusDays(1);
    public static final LocalDate oneDayMinusDate = LocalDate.now().minusDays(1);
    public static final LocalDate twoDayPlusDate = LocalDate.now().plusDays(2);

    // Time fixture
    public static final LocalTime oneHourPlusTime = LocalTime.now().plusHours(1); // 현재 시간보다 1시간 뒤
}
