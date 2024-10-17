# Task management system
## Popis projektu
Cílem projektu je vytvoření systému pro správu úkolů pomocí Java Spring Boot. Systém bude navržen jako API s klíčovými funkcionalitami pro správu uživatelů, projektů a úkolů.

### Základní prvky systému:
- **User**: Uživatel, který bude vytvářet a spravovat projekty a úkoly
- **Task**: Úkol, který lze přiřadit k projektu a spravovat jeho stav
- **Project**: Projekt, který obsahuje jednotlivé úkoly

### Požadavky na systém:
Tento systém bude poskytovat základní funkce pro správu projektů a úkolů, které jsou přiřazeny k uživatelům, a umožní uživatelům efektivně spravovat své projekty a úkoly.

- Uživatel bude mít možnost vytvářet nové projekty a úkoly
- Úkoly budou moci být přiřazeny k jednotlivým projektům
- Úkoly budou mít dva stavy: **nový** a **dokončený**
- Implementace CRUD (Create, Read, Update, Delete) operací pro správu entit **User**, **Task** a **Project**


## Technologie
- Jazyk: **Java**
- Framework: **Spring Boot**
- Databáze: **H2**
- IDE: [IntelliJ IDEA]()

---

# Řešení:
## Struktura projektu
Pod hlavním balíčkem `cz.kdolezal.taskmanagementsystem` naleznete následující rozčlenění:
- `api`: obsahuje balíčky: `exception`, `request`, dále pak jednotlivá rozhraní entit jako například: `TaskService.java `
- `controller`: obsahuje třídy, které zpracovávají HTTP požadavky a vrací odpovědi. Například `TaskController.java`
- `domain`: obsahuje doménové třídy `Project`, `Task`, `User` + enum: `TaskStatus`
- `implementation`: rozděluje řešení projektu na dvě větve - `jdbc`, `jpa`
  Výběr implementace se provádí v souboru `application.properties`: proměnná - `spring.profiles.default` může nabývat hodnot: `jpa` nebo `jdbc` v závislosti na tom, které řešení chceme použít

Výběr implementace se provádí v souboru `application.properties`: proměnná - `spring.profiles.default` může nabývat hodnot: `jpa` nebo `jdbc` v závislosti na tom, které řešení chceme použít
## Jak aplikaci spustit
Otevři projekt v IntelliJ IDEA, jdi na adresu `src/main/java/cz/kdolezal/taskmanagementsystem/TaskManagementSystemApplication.java` a vedle `main` klikni na zelenou šipku.

Alternativou je pak spuštění přes terminal pomocí příkazu:
`./mvnw spring-boot:run`

API beží na portu 8080. Bez frontentdu je možné vyzkoušet funkčnost v prohlížeči pomocí Swaggeru UI, který je na adrese:
http://localhost:8080/swagger-ui/index.html#