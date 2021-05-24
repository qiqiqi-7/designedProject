package cn.edu.hbpu.education;

import cn.edu.hbpu.education.entity.TTeachaccount;
import cn.edu.hbpu.education.mapper.TTeachaccountMapper;
import cn.edu.hbpu.education.mapper.TUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class EducationApplicationTests {
	@Resource
	private TUserMapper tUserMapper;

	@Resource
	private TTeachaccountMapper tTeachaccountMapper;

	@Resource
	private RedisTemplate<String,Object> redisTemplate;

	@Test
	void contextLoads() {
	}


	@Test
	void testRedis(){
		redisTemplate.opsForValue().set("myKey","myValue");
		System.out.println(redisTemplate.opsForValue().get("myKey"));
	}
	@Test
	void testGetOne(){
		tUserMapper.getUserByUserCode("01bcda4600534ce6a1b337bcf467563d");
	}
	@Test
	void testGetTeachOne(){
		TTeachaccount teachInfo = tTeachaccountMapper.getTeachInfo("01bcda4600534ce6a1b337bcf467563d");
		System.out.println(teachInfo);
	}
}
