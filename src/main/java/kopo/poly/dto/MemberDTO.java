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
        private String user_id;
        private String user_pw;
        private String user_email;
        private String user_addr1;
        private String user_addr2;
        private String user_addr3;
        private LocalDate user_dt;
        private int adminck;

        public MemberDTO(){
            super();
        }

        public MemberDTO(int user_seq, String user_id, String user_pw, String user_email, String user_addr1, String user_addr2,String user_addr3, LocalDate user_dt, int adminck) {
            super();
            this.user_seq = user_seq;
            this.user_id = user_id;
            this.user_pw = user_pw;
            this.user_email = user_email;
            this.user_seq = user_seq;
            this.user_addr1 = user_addr1;
            this.user_addr2 = user_addr2;
            this.user_addr3 = user_addr3;
            this.user_dt = user_dt;
            this.adminck = adminck;
        }
}
