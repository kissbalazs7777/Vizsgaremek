[Link to Extent Report](https://kissbalazs7777.github.io/Vizsgaremek)  
[Link to reportNG](https://kissbalazs7777.github.io/Vizsgaremek/target/surefire-reports/html/index.html)

Általam tesztelt, saját készítésű oldal: [https://kissbalazstest.000webhostapp.com/index.html](https://kissbalazstest.000webhostapp.com/index.html)  

Feladat:  
-Regisztráció: sikeres/sikertelen (már foglalt felhasználónév) regisztráció tesztelése  
-Bejelentkezés: sikeres/sikertelen (rossz felhasználónév/rossz jelszó) bejelentekzés tesztelése  
-Adatkezelési nyilatkozat használata: szöveg fájlba mentése és az elvárt eredménnyel történő összehasonlítása  
-Adatok listázása: valamennyi regisztrált felhasználó listázása - elvárt eredménnyel összehasonlítás  
-Több oldalas lista bejárása:  valamennyi regisztrált felhasználó listázása és oldalak bejárása - elvárt eredménnyel összehasonlítás  
-Új adat bevitel: blogbejegyzés sikeres/sikertelen (foglalt url/kötelező mező üresen hagyása) létrehozása  
-Ismételt és sorozatos adatbevitel adatforrásból: blogbejegyzés sikeres/sikertelen (foglalt url/kötelező mező üresen hagyása) létrehozása  
-Meglévő adat módosítása: blogbejegyzés sikeres/sikertelen (foglalt url/kötelező mező üresen hagyása) módosítása  
-Adat vagy adatok törlése: blogbejegyzés törlése  
-Adatok lementése felületről: adatkezelési nyilatkozat szövegének lementése fájlba, majd az elvárt eredménnyel történő összehasonlítása  
-Kijelentkezés: sikeres kijelentkezés  

Valamennyi teszt adatot egy excel táblázatból (testdata.xlsx) olvasok be. A webelemek lokátorait Locators.properties fájlból olvasom be. A teszt környezetet pedig a Config.properties fájlból olvasom be.
Github workflow is be van állítva, a teszteket lefuttatja automatikusan abban az esetben, ha valami változás történik a repositoryval. Ilynekor a jelentést (extent report és reportNG) automatikusan frissíti (link a jelentéshez: lásd első sor).
