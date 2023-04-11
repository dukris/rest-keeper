package by.bsuir.restkeeper.web.security.expression;

import by.bsuir.restkeeper.domain.User;
import by.bsuir.restkeeper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityExpressions {

    private final UserService userService;

//    public boolean hasParty(Long userId, Long partyId) {
//        Party party = partyService.retrieveById(partyId);
//        return party.getCreator().getId().equals(userId);
//    }

    public boolean isUser(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.retrieveById(userId);
        return authentication.getName().equals(user.getEmail())
               && user.isEnabled();
    }

//    public boolean isPair(Long userId, Long pairId) {
//        PairMatch pairMatch1 = pairMatchService.retrieveBySenderIdAndReceiverId(userId, pairId);
//        PairMatch pairMatch2 = pairMatchService.retrieveBySenderIdAndReceiverId(pairId, userId);
//        if (pairMatch1 != null) {
//            return pairMatch1.getStatus().equals(PairMatchStatus.APPROVED);
//        }
//        if (pairMatch2 != null) {
//            return pairMatch2.getStatus().equals(PairMatchStatus.APPROVED);
//        }
//        return false;
//    }

}
