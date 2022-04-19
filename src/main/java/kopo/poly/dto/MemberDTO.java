package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * lombok은 코딩을 줄이기 위해 @어노테이션을 통한 자동 코드 완성기능임
 * @Getter => getter 함수를 작성하지 않았지만, 자동 생성
 * @Setter => setter 함수를 작성하지 않았지만, 자동 생성
 */
@Getter
@Setter
public class MemberDTO {

   // private String notice_seq; // 기본키, 순번
 // 테이블 작성
        private int user_seq;
        private int user_auth;
        private String user_id;
        private String user_pw;
        private String user_email;
        private String user_addr;
        private LocalDate user_dt;

        public MemberDTO(){
            super();
        }

        public MemberDTO(int user_seq, int user_auth, String user_id, String user_pw, String user_email, String user_addr, LocalDate user_dt) {
            super();
            this.user_seq = user_seq;
            this.user_auth = user_auth;
            this.user_id = user_id;
            this.user_pw = user_pw;
            this.user_email = user_email;
            this.user_seq = user_seq;
            this.user_addr = user_addr;
            this.user_dt = user_dt;
        }
}
