# Cool Events BOT aka java-bot
---
### Study project for OOP Course in UrFU

Cool Events BOT is a telegram-based bot powered by Java.

## Features

- Echo mode: The bot works in echo mode when the command /echo is sent. It will simply echo back any text sent by the user.
- Events mode: When the command /events is sent, the bot will ask a series of questions. It then uses the Kudago API to fetch upcoming events based on user input. The bot will provide details for suitable events.

## Tech

Cool Events BOT uses a number of projects:

- [Java](https://www.oracle.com/java/) - awesome programming language
- [Apache Maven](https://maven.apache.org/) - for building project
- [TelegramBots](https://github.com/rubenlagus/TelegramBots) - Java based API for telegram bots
- [KudaGo API](https://docs.kudago.com/api/) - API from event aggregator KudaGo
- [slf4j logger](https://slf4j.org/) - simple logging facade for java

## How to use

1. Get your telegram API token from botfather
2. Clone repo 
3. Add "Secret.java" class at root of package 
    ```Java
    package your.package
    
    class Secret {
        protected static String getApiKey() {
            return "YOUR_API_TOKEN";
        }
        protected static String getBotName() {
            return "YOUR_BOT_NAME";
        }
    }
    ```
4. Run Main

## Authors
- Alexandr Gorshkov
- Sergey Zaremba
