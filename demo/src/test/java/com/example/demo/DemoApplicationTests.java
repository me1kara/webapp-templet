package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	//springBootTest : 실제 로직을 검증
	//mock           : 외부데이타가 필요한 서비스에 가짜를 설정(when/then) 해서 대신함
	//@transactional,@dataJpaTest  : 실제로 로직을 수행한뒤 rollback
	//@WebMvc : api 경로 테스트
	//@ExtendWith(SpringExtension.class) spring 설정을 사용할 수 있게 해줌


}
