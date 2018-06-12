package site.it4u.phoenix;

import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import site.it4u.phoenix.dao.UserInfoMapper;
import site.it4u.phoenix.domain.UserInfo;
import site.it4u.phoenix.mybatis.PhoenixDataSourceConfig;

import java.util.List;

@RunWith(SpringRunner.class)
@Import(PhoenixDataSourceConfig.class)
@PropertySource("classpath:application.properties")
@ComponentScan("site.it4u.phoenix.**")
@MapperScan("site.it4u.phoenix.**")
public class PhoenixMybatisDemoApplicationTests {

	@Autowired
	UserInfoMapper userInfoMapper;

	@Test
	public void addUser() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(2);
		userInfo.setName("Jerry");
		userInfoMapper.addUser(userInfo);
	}

	@Test
	public void getUserById() {
		UserInfo userInfo = userInfoMapper.getUserById(1);
		System.out.println(userInfo.getId() + ":" + userInfo.getName());
	}

	@Test
	public void getUserByName() {
		UserInfo userInfo = userInfoMapper.getUserByName("Jerry");
		System.out.println(userInfo.getId() + ":" + userInfo.getName());
	}

	@Test
	public void deleteUser() {
		userInfoMapper.deleteUser(1);
		List<UserInfo> users = userInfoMapper.getUsers();
		users.stream().forEach(System.out::println);
	}

}
