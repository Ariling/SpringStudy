package com.likelionSpring.springStudy.scheduling;

import com.likelionSpring.springStudy.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpiredMemberDeleteSchedulingService {
    private final MemberJpaRepository memberJpaRepository;

    //일정시간에 이게 시행이 되어야 하는데 cron이 시간을 설정해주는 것! 해당 문구는 자정이라는 뜻
    //cron expression을 검색해서 찾아보면 된다.
    //이런 형태로 데이터 삭제를 완전히 해주면 된다.
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteExpiredMember(){
        memberJpaRepository.deleteExpiredMember();
    }
}
