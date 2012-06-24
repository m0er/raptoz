package com.raptoz.init;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.raptoz.persistence.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MyBatisTest {
	@Autowired TestMapper testMapper;
	Logger logger = LoggerFactory.getLogger(MyBatisTest.class);
	
	@Test
	public void create() throws Exception {
		assertThat(testMapper, is(notNullValue()));
	}
	
	@Test
	public void currentTime() throws Exception {
		assertThat(testMapper.getTime(), is(notNullValue(String.class)));
	}
}
