package org.impact.church.modules.dto;

import org.impact.church.modules.model.Member;

import java.time.LocalDate;

public record MemberDTO(Long id, String firstName, String lastName,
                        String gender, LocalDate birthDate, String email,
                        String phoneNumber, String address, String status) {

    public MemberDTO(Member member){
        this( member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getGender(),
                member.getBirthDate(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getAddress(),
                member.getStatus());
    }
}
