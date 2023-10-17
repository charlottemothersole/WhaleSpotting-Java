package com.whalespottingjava.services;

import com.whalespottingjava.models.MemberDetails;
import com.whalespottingjava.models.database.Member;
import com.whalespottingjava.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Member member = memberRepository.findByUsername(username);

        if (member == null) {
            throw new UsernameNotFoundException("Could not find member with username");
        }

        return new MemberDetails(member);
    }

    public Member getMemberById(Long value) {
        Optional<Member> member = this.memberRepository.findById(value);

        return member.orElse(null);
    }
}
