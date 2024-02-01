package fem.member.framework.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import fem.member.MemberFactory;
import fem.member.MemberInfoDTOFactory;
import fem.member.domain.model.Member;
import fem.member.framework.jpadapter.MemberRepository;
import fem.member.framework.web.dto.MemberInfoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired private ObjectMapper objectMapper;
    @Autowired private MockMvc mockMvc;
    @Autowired private MemberRepository memberRepository;

    @BeforeEach
    void init() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("멤버 조회")
    void findMember() throws Exception {
        Member member = memberRepository.save(MemberFactory.create("id", "홍길동"));

        mockMvc.perform(get("/api/members/" + member.getMemberNo())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberNo").value(member.getMemberNo()));
    }

    @Test
    @DisplayName("멤버 등록")
    void registerMember() throws Exception {
        MemberInfoDTO memberInfoDTO = MemberInfoDTOFactory.create();

        mockMvc.perform(post("/api/members")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberInfoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("id"))
                .andExpect(jsonPath("$.name").value("홍길동"));
    }
}