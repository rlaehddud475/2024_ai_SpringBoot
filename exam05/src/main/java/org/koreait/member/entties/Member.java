package org.koreait.member.entties;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
private long seq;
private String email;
private String password;
private String name;
private LocalDateTime regDt;
private LocalDateTime modDt;
}
