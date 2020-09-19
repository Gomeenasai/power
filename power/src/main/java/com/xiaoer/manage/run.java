@RunWidth(SpringRunner.class)
@SpringBootTest
public class usermappertests{
    @Autowired
    usermapper mapper;

    @Test
    public void addnew(){
        user user = new user();
        user.setusername("dd");
        user.setpassword("123456");
        service.insert(user);
        System.err.println("注册成功")
    }
}