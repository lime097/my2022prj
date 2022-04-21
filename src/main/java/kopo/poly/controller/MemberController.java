package kopo.poly.controller;

import kopo.poly.dto.MemberDTO;
import kopo.poly.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.Random;


/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역할 수행
 *
 * slf4j는 스프링 프레임워크에서 로그 처리하는 인터페이스 기술이며,
 * 로그처리 기술인 log4j와 logback과 인터페이스 역할 수행함
 * 스프링 프레임워크는 기본으로 logback을 채택해서 로그 처리함
 * */
@Slf4j
@Controller
public class MemberController {
    @Resource(name = "MemberService")
    private IMemberService memberService;
    @Autowired
    private JavaMailSender mailSender;

    /**
     * GetMapping은 GET방식을 통해 접속되는 URL 호출에 대해 실행되는 함수로 설정함을 의미함
     * PostMapping은 POST방식을 통해 접속되는 URL 호출에 대해 실행되는 함수로 설정함을 의미함
     * GetMapping(value = "index") =>  GET방식을 통해 접속되는 URL이 index인 경우 아래 함수를 실행함
     */
    //회원가입
    @RequestMapping(value="/join1", method=RequestMethod.POST)
    public String joinPOST(MemberDTO memberDTO) throws Exception{

        // 회원가입 서비스 실행
        memberService.userjoin(memberDTO);


        return "redirect:/index";

    }

    //getMapping 값 겹치지않기
    @GetMapping(value = "/join")
    public String joinpage() throws Exception {
    return "/member/join";
     }
    @GetMapping(value = "/join1")
    public String joinpage1() throws Exception {
        return "/member/join1";
    }
    @RequestMapping(value = "/memberIdChk", method = RequestMethod.POST)
    @ResponseBody
    public String memberIdChkPOST(String user_id) throws Exception{

        /* logger.info("memberIdChk() 진입"); */



        int result = memberService.idCheck(user_id);



        if(result != 0) {

            return "fail";	// 중복 아이디가 존재

        } else {

            return "success";	// 중복 아이디 x

        }

    } // memberIdChkPOST() 종료
    /* 이메일 인증 */
    @RequestMapping(value="/mailCheck", method=RequestMethod.GET)
    @ResponseBody
    public String mailCheckGET(String email) throws Exception{

        /* 뷰(View)로부터 넘어온 데이터 확인 */


        /* 인증번호(난수) 생성 */
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;


        /* 이메일 보내기 */
        String setFrom = "1820100293@gspace.kopo.ac.kr";
        String toMail = email;
        String title = "회원가입 인증 이메일 입니다.";
        String content =
                "홈페이지를 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "인증 번호는 " + checkNum + "입니다." +
                        "<br>" +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);

        }catch(Exception e) {
            e.printStackTrace();
        }

        String num = Integer.toString(checkNum);

        return num;

    }

}
