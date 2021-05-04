ОПИСАНИЕ ЗАДАЧИ:

Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности:

Writer (id, firstName, lastName, List<Post> posts)
Post (id, content, created, updated, List<Label> labels)
Label (id, name)
PostStatus (enum ACTIVE, UNDER_REVIEW, DELETED)

В качестве хранилища данных необходимо использовать текстовые файлы:
writers.txt, posts.txt, labels.txt
Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.

ОПИСАНИЕ ПРОЕКТА:

Слои:
model - POJO классы
repository - классы, реализующие доступ к текстовым файлам
controller - обработка запросов от пользователя
view - все данные, необходимые для работы с консолью
resourses - текстовые файлы для хранения данныз с репозиторного слоя.
Интерфейсы: GenericRepository, LabelRepository, PostRepository, WriterRepository.

ИНСТРУКЦИЯ ПО ЗАПУСКУ ПРИЛОЖЕНИЯ:

1: Скачать репозиторий https://github.com/Rentori/console-CRUD
2: Запустить исполняемый файл main.java (Предварительно изменить путь к текстовым файлам в IO классах.)
