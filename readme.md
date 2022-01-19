ProxyLog
***
Задача

Разработайте такой функционал:<br>
Метод некоего класса можно пометить самодельной аннотацией `@Log`, например, так:

```
class TestLogging {
    @Log
    public void calculation(int param) {};
}
```
При вызове этого метода "автоматически" в консоль должны логироваться значения параметров.
Например так.
```
class Demo {
    public void action() {
        new TestLogging().calculation(6);
    }
}
```
В консоль должно быть выведено:<br>
`executed method: calculation, param: 6`

Обратите внимание: явного вызова логирования быть не должно.