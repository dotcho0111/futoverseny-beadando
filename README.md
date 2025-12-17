# Futóverseny kezelő alkalmazás

Ez egy Spring Boot alapú webalkalmazás futóversenyek és eredmények kezelésére.

## Technológiai stack
* **Backend:** Java 17/21, Spring Boot 3
* **Adatbázis:** H2 (In-memory)
* **Frontend:** Thymeleaf + HTML
* **API:** RESTful API JSON formátummal

## Funkciók
* Futók és versenyek kezelése.
* Időeredmények rögzítése.
* Átlagidő számítása egy adott versenyre:
  $$Average = \frac{\sum{time}}{count}$$
* Thymeleaf felület a versenyek listázásához és részleteihez.

## Tesztelés
A `postman` mappában található JSON fájlt importáld a Postmanbe a végpontok teszteléséhez.