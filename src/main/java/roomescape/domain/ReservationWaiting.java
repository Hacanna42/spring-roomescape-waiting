//package roomescape.domain;
//
//import java.time.LocalDate;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import lombok.AccessLevel;
//import lombok.NoArgsConstructor;

/*?
[고민] ReservationWaiting 엔티티가 예약 정보를 추적하려면, 예약의 동등성을 확인할 수 있는 모든 정보가 필요하다. 왜냐하면 추후 Waiting 이 끝났을 때, 실제 예약으로 전환해야 하기 때문이다.
 따라서, ReservationWaiting 이 Reservation 자체를 가지고 있는(DB 에서는 외래키로) 구조와 ReservationWaiting 이 Reservation 의 동등성을 확인할 수 있는 필드를 가지는 설계가 떠오른다.

 [Reservation 자체를 참조하는 경우(전자)]
 장점: Reservation 필드에 변경이 생겼더라도, 외래키를 참조하기 때문에 변경의 영향을 받지 않을 가능성이 있다.
 단점: 예약 대기를 생성할 때마다, 2개의 테이블을 동시에 수정해야 한다. (Reservation, ReservationWaiting)

 [Waiting 에 Reservation 의 동등성을 판단할 수 있는 필드를 두는 경우(후자)]
 장점: 예약 대기를 생성할 때, 하나의 테이블만 수정하는 것으로 작업이 끝난다.
 단점: 미래의 변경을 생각해 봤을 때, 예약 대기 뿐만이 아니라 예약 완료 혹은 예약 취소 등의 상태가 생겨날 가능성이 매우 높다.
 위의 상태가 생겨난다면 Reservation 테이블은 실제로 성사된 예약 뿐만이 아니라, 취소 및 이미 종료된 예약도 포함하게 될 가능성이 매우 높다.
 그렇다면, 다른 모든 상태는 Reservation 테이블에 저장하는데 <예약 대기> 상태만 다른 테이블에 저장하는 일관성 불일치가 생겨나서 설계를 이해하기 어려워진다.

 결론: Reservation 을 참조하기로 한다.
*/

/*?
[고민] 애초에 ReservationWaiting 도메인이 필요한가?
그냥 예약 대기가, Reservation 테이블에 [예약 대기] 상태의 예약을 생성하는 것이 끝이고, 대기열 순서를 구하는 부분을 쿼리로 처리해도 괜찮지 않을까?

장점: 설계가 매우 간단해진다.
단점: 현재 Reservation 에는 요청 시간을 기록하지 않으므로, pk의 값으로 예약 순서를 판단해야 한다. 즉 pk에 의존해야 한다.
쿼리 비용이 많이 든다? 많이 드나? 비슷할 것 같은데...

현재로서는 큰 단점을 찾을 수 없으므로,
결론: ReservationWaiting 을 만들지 않기로 한다.
 */

//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class ReservationWaiting {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private Member member;
//
//    @Column(nullable = false)
//    private Long rank;
//
//    @Column(nullable = false)
//    private LocalDate date;
//
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private ReservationTime time;
//
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private Theme theme;
//
//
//}
