# pai
Zadania na przedmiot: Projektowanie Aplikacji Internetowych

Autor:
Damian Dworak
187677

Lab 1:
Program pobiera nazwy plików, jako wprowadzone argumenty. 
Po zaczytaniu wszystich nazw sprawdza ich poprawność.

Następnie uruchamiane są trzy metody:
- Metoda jednowątkowa
- Metoda wielowątkowa sekwencyjna
- Metoda wielowątkowa jedoczesna

Po przetestowaniu programu, dla różnej liczby plików, o różnych wielkościach, możemy zaobserwować:
- Najwolniejsza jest metoda jednowątkowa. Czas jej wykonania jest nawet kilkukrotnie dłuższy niż w przypadku metod wielowątkowych.
- Najszybsza jest metoda wielowątkowa jedoczesna. W większości przypadków, czas jej trwania jest krótszy o około 25% niż metody wielowątkowej sekwencyjnej.

Uwagi:
- Co kilka testów, zdarza się, iż metoda wielowątkowa sekwencyjna wykona się szybciej od metody jednoczesnej.

