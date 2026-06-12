## Проект

### Разработка UI и API автотестов для раздела Launches на сайте ReportPortal

## Стек технологий

- **Язык**: Java 21
- **Тестирование**: JUnit, AssertJ, RestAssured, Selenide
- **Логгирование**: slf4j
- **Прочее**: Jackson, Datafaker, Lombok
- **Контейнеризация**: Docker

## Установка

### Требования

- Java 21
- Docker (опционально)

## Запуск

1. Склонируйте репозиторий:

   ```bash
   git clone https://github.com/VicNA/reportportal-test.git
   cd reportportal-test
   ```
   
2. Создайте API ключ на [сайте портала](https://demo.reportportal.io) перейдя в _Profile → API Keys → Generate API Key_

3. Замените значение свойства api_key в app.properties новым сгенерированным ключом или передать ключ параметром при запуске тестов 

4. Запустите тесты

   ```bash
   mvn clean test
   ```
   
   API ключ можно передать через параметры

   ```bash
   mvn clean test -Dapi_key=<ваш API_KEY>
   ```
   
   Или создать переменную окружения

   ```bash
   export API_KEY=<ваш API_KEY>
   
   mvn clean test
   ```
   
5. Если доступ к [сайту портала](https://demo.reportportal.io) не стабильная, можно локальную копию портала в docker

   ```bash
   docker-compose up -d
   ```
   
   и запустить тесты, передав необходимые значения либо через переменные окружения либо через параметры

   ```bash
   export ENDPOINT=http://localhost:8080
   export API_KEY=<ваш API_KEY>
   
   mvn clean test
   ```
   
   ```bash
   mvn clean test -Dendpoint=http://localhost:8080 -Dapi_key=<ваш API_KEY>
   ```
   
   Дополнительно, можно указать каким браузером проводить UI тестирование

   ```bash
   export ENDPOINT=http://localhost:8080
   export API_KEY=<ваш API_KEY>
   export BROWSER=firefox
   
   mvn clean test
   ```
   
   ```bash
   mvn clean test -Dbrowser=firefox -Dendpoint=http://localhost:8080 -Dapi_key=<ваш API_KEY>
   ```