# TODO APP IN SPRING BOOT

In this project we are mahaging out todo word like how many works are pending and how many works are done


## Project Links

[GitHub Links](https://github.com/laljisingh/springBoot/tree/chatApplication/todo)

### Mudules In Projects
#### user Controller
* Add User
* Get User By Id And All User
* Update User

#### Post Controller
* Add Post
* Get Post By Id And All Post
* Update Post
* Delete Post
### DataBasese
- H2 database

### MVC Mules in app
- Repository
- Controller
- service
- Model

#### Controller Codes
- @RestController
  public class UserController {

  @Autowired
  UserService service;

  @PostMapping(value = "/user")
  public ResponseEntity saveUser(@RequestBody String userData) {

        User user = setUser(userData);
        int userId = service.saveUser(user);
        return new ResponseEntity("user saved with id- " +userId, HttpStatus.CREATED);

  }

  @GetMapping(value = "/user")
  public ResponseEntity<String> getUser(@Nullable @RequestParam String userId) {

        JSONArray userDetails = service.getUser(userId);
        return new ResponseEntity(userDetails.toString(), HttpStatus.OK);
  }



    @PutMapping(value = "/user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody String userData) {

        User user = setUser(userData);
        service.updateUser(user, userId);

        return new ResponseEntity("user updated", HttpStatus.OK);

    }


    private User setUser(String userData) {

        JSONObject jsonObject = new JSONObject(userData);
        User user = new User();
        user.setUserId(jsonObject.getInt("userId"));
        user.setAge(jsonObject.getInt("age"));
        user.setEmail(jsonObject.getString("email"));
        user.setFirstName(jsonObject.getString("firstName"));
        user.setLastName(jsonObject.getString("lastName"));
        user.setPhoneNumber(jsonObject.getString("phoneNumber"));

        return user;

    }



#                    Thanks