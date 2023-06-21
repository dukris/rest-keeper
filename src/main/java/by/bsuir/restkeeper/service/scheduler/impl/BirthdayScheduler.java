package by.bsuir.restkeeper.service.scheduler.impl;

import by.bsuir.restkeeper.domain.User;
import by.bsuir.restkeeper.domain.criteria.UserSearchCriteria;
import by.bsuir.restkeeper.service.MailService;
import by.bsuir.restkeeper.service.UserService;
import by.bsuir.restkeeper.service.scheduler.Scheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.MonthDay;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BirthdayScheduler implements Scheduler {

    private final UserService userService;
    private final MailService mailService;

    @Override
    @Scheduled(fixedDelay = 86400000)
    public void schedule() {
        List<User> users = this.userService.retrieveAllByCriteria(
                new UserSearchCriteria());
        users.forEach(user -> {
            if (user.getDateOfBirth() != null
                    && MonthDay.now().equals(MonthDay.from(user.getDateOfBirth()))) {
                mailService.send(
                        user,
                        "birthday.ftl",
                        "Happy Birthday!",
                        " ",
                        " ");
                log.info("Congratulation is sent successfully!");
            }
        });
    }

}
