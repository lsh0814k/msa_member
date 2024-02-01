package fem.member.application.inputport;

import fem.member.application.outputport.MemberOutputPort;
import fem.member.application.usecase.RegisterMemberUsecase;
import fem.member.domain.model.Member;
import fem.member.domain.model.vo.Email;
import fem.member.domain.model.vo.IDName;
import fem.member.domain.model.vo.Password;
import fem.member.framework.web.dto.MemberInfoDTO;
import fem.member.framework.web.dto.MemberOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterMemberInputPort implements RegisterMemberUsecase {
    private final MemberOutputPort memberOutputPort;
    @Override
    public MemberOutputDTO registerMember(MemberInfoDTO memberInfoDTO) {
        Member member = Member.registerMember(
                IDName.createIDName(memberInfoDTO.getId(), memberInfoDTO.getName()),
                Password.createPassword(memberInfoDTO.getPassword(), memberInfoDTO.getPassword()),
                Email.create(memberInfoDTO.getEmail()));

        checkDuplicate(member.getIdName());
        return MemberOutputDTO.mapToDTO(memberOutputPort.save(member));
    }

    private void checkDuplicate(IDName idName) {
        Optional<Member> memberOptional = memberOutputPort.findByIdName(idName);
        if (memberOptional.isPresent()) {
            throw new IllegalArgumentException("동일한 id와 name이 존재합니다.");
        }
    }
}
