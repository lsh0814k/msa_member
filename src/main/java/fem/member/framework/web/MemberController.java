package fem.member.framework.web;

import fem.member.application.usecase.InquiryMemberUsecase;
import fem.member.application.usecase.RegisterMemberUsecase;
import fem.member.framework.web.dto.MemberInfoDTO;
import fem.member.framework.web.dto.MemberOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final RegisterMemberUsecase registerMemberUsecase;
    private final InquiryMemberUsecase inquiryMemberUsecase;

    @PostMapping("/members")
    public ResponseEntity<MemberOutputDTO> registMember(@RequestBody MemberInfoDTO memberInfoDTO) {
        MemberOutputDTO memberOutputDTO = registerMemberUsecase.registerMember(memberInfoDTO);

        return ResponseEntity.status(CREATED)
                .body(memberOutputDTO);
    }

    @GetMapping("/members/{no}")
    public ResponseEntity findMember(@PathVariable("no") Long no) {
        MemberOutputDTO memberOutputDTO = inquiryMemberUsecase.findMember(no);
        return ResponseEntity.ok(memberOutputDTO);
    }
}
