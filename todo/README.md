# TODO APP IN SPRING BOOT

In this project we are mahaging out todo word like how many works are pending and how many works are done


## Project Links

[GitHub Links](https://github.com/laljisingh/springBoot/tree/chatApplication/todo)

### Mudules In Projects
#### Student Controller
* Add Todo
* Delete Todo
* Get Todo
* Update Todo

### MVC Mules in app
- Repository
- Controller
- service
- Model
### Database
- H2 Database


#### Controller Codes
- 
@RestController
@RequestMapping("/api/v1/todo-app")
public class TodoController {



    // Used to inject Dependency
    @Autowired
    private ITodoService todoService;



    //http://localhost:8080/api/v1/todo-app/add-todo
    @PostMapping("/add-todo")
    public int addTodo(@RequestBody Todo todo) {
        return todoService.addTodo(todo);
    }

    //http://localhost:8080/api/v1/todo-app/find-todo/id/2
    @GetMapping("/find-todo/id/{id}")
    //@RequestMapping(value="/find-todo/id/{id}")
    public Todo findTodoById(@PathVariable int id) {
        return todoService.findById(id);
    }

    //http://localhost:8080/api/v1/todo-app/find-all
    @GetMapping("find-all")
    public List<Todo> findAllTodos() { // controller is talking to the service layer
        return todoService.findAll();
    }

    //http://localhost:8080/api/v1/todo-app/update-todo/id/4
    //@RequestMapping(value="url",method=HttpRequest.PUT)
    @PutMapping("update-todo/id/{id}")
    public void updateTodo(@PathVariable int id,@RequestBody Todo todo) {
        todoService.updateTodo(id,todo);
    }

    //http://localhost:8080/api/v1/todo-app/delete-todo/id/3
    @DeleteMapping("delete-todo/id/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable int id) {
        String s = todoService.deleteTodo(id);
        return new ResponseEntity<String>(s, HttpStatus.OK);
    }

}


#                    Thanks

