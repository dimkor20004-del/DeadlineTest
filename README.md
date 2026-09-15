# Deadline Test

Проект для тестирования входа в приложение Deadline.

## Запуск

1. Запустить MySQL:
   ```bash
   docker-compose up -d
2. Запустить SUT: 
   ```bash
   java -jar ./artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/deadline -P:jdbc.user=mrtotalsecurity -P:jdbc.password=CzmGtmRjc3cLGV7KXza294520qCMYXuF
3. Запустить тесты:
   ```bash
   ./gradlew test