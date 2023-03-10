# [Курсовой проект "Сервис перевода денег"](https://github.com/netology-code/jd-homeworks/blob/master/diploma/moneytransferservice.md)

## Описание

REST-сервис предоставляет собой интерфейс для перевода денежных средств с одной карты на другую по [заранее описанной спецификации](MoneyTransferServiceSpecification.yaml).
Заранее подготовленное веб-приложение (FRONT) подключается к сервису без доработок и использует его функционал для перевода денег.

По умолчанию FRONT доступен на порту 3000, back - на порту 5500.

## Описание реализации

- Приложение разработано с использованием Spring Boot;
- Использован сборщик пакетов MAVEN;
- Код покрыт unit тестами с использованием mockito;
- Добавлены интеграционные тесты с использованием testcontainers.

## Запуск приложения (с docker и без него)

1. Необходимо скачать [FRONT](https://github.com/frepingod/netology-transfer-money-front) и выполнить инструкции из него
2. Скачать данный сервис, выполнить maven -> package

#### Запуск
1. Запустить main метод в классе TransferMoneyApplication.java либо Dockerfile

## Тестовые данные карт

- **номер карты: 1111111111111111, годен до: 11/25, cvv: 111, сумма: 100000**
- **номер карты: 2222222222222222, годен до: 11/26, cvv: 122, сумма: 100000**
