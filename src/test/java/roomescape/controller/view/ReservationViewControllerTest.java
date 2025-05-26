package roomescape.controller.view;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import roomescape.auth.CookieProvider;
import roomescape.auth.JwtTokenProvider;
import roomescape.service.MemberService;

@WebMvcTest(ReservationViewController.class)
class ReservationViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CookieProvider cookieProvider;

    @MockitoBean
    private JwtTokenProvider jwtTokenProvider;

    @MockitoBean
    private MemberService memberService;

    @Test
    void reservation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reservation"))
                .andExpect(status().isOk())
                .andExpect(view().name("reservation"));
    }

    @Test
    void reservationMine() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reservation-mine"))
                .andExpect(status().isOk())
                .andExpect(view().name("reservation-mine"));
    }
}
