### Приложение "TODO list"
> **Используемы технологии**: Spring boot, Thymeleaf, Bootstrap, Hibernate, PostgreSql

***
**Требуемые элементы:** 
* PostgreSql 14 
* JDK 17
* Maven 3.8.1
***
**Перед запуском проекта:** 
* создать базу данных с именем ***todo***
* поменять login/password в файлах src/main/resources/db.properties
и src/main/resources/hibernate.cfg.xml на требуемый 
 ![](images/dp_properties.png) ![img.png](images/hibernate_cfg.png)

***
**Запуск приложения:** 
* запустить метод *main* в классе *src/main/java/ru/job4j/todo/Main.java*
* после запуска сервера перейти на адрес: *http://localhost:8080/index*
***
Приложение позволяет:
* добавлять задачи в список
* удалять задачи
* помечать задачи как выполненные
* редактировать задачи
* отображать завершенные/незавершенные задачи отдельными списками
 
Главная страница
![index page](images/1.Index.png)
Список всех задач (завершенных/незавершенных)
![index page](images/2.AllItemsList.png)
Список завершенных задач
![index page](images/3.CompletedItems.png)
Список новых задач
![index page](images/4.NewItems.png)
Добавление новой задачи
![index page](images/5.AddItem.png)
При нажатии на имя задачи пользователь попадает в меню управления задачей
![index page](images/6.ItemDescription.png)
При нажатии на кнопку "Изменить задачу" происходит переход в меню редактирования задачи
![index page](images/7.EditItem.png)
