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

Lab 2:
Program przyjmuje jako arugemnt, adres strony internetowej, w takiej postaci: http(s)://www.(nazwa_strony).(domena)
Po uruchomieniu programu, sprawdza on liczbe argumentów, gdy inna niż jeden wyrzuca błąd, gdy równa jeden przechodzi dalej.

Main:
- Łączy sie z podanym adresem
- Pobiera podstawowe dane strony, oraz adres IP, które zapisuję do mapy
- Pobiera całą zawartość strony, którą zapisuję do StringBuildera
- Uruchamia kolejne dwie metody

WriteFile:
- Przygotowuje zmienne, oraz wzorce na potrzeby pobrania sekcji <head> strony
- Znajduję i zapisuję dane z sekcji <head> do odpowiedniej zmiennej
- Tworzy nowy plik do którego zapisuję parametry strony oraz sekcje <head>.

ReadContent:
- Przygotowuje zmienne oraz wzorce dla znalezienia maili oraz linkow na stronie
- Wyszukuje wszystkie linki na stronie, jesli znajdzie przynajmniej jeden, wyswietla je w konsoli
- Wyszukuje wszystkie maile na strone, jesli znajdzie przynajmniej jeden, wyswietla je w konsoli

Uwagi:
- Strona na której program testowany to: https://www.debian.org oraz http://www.onet.pl , przy czym ta druga nie ma maili na stronie.

Lab 3:
Program posiada trzy klasy:
- Lab3 - posiada metode main, która jest odpowiedzialna za uruchomienie serwera, oraz tworzenie nowych wątków, dla każdego kolejnego klienta
- Server - Posiada konstuktor który dodaje nowego klienta. Metoda Run, jest odpowiedzialna za przechwytywanie i odpowiadanie na wiadomosci klientow
- Client - Na poczatku deklarowane sa zmienne, niezbedne do polaczenia sie z serwerem. Następnie w nieskończonej pętli, uruchamiane jest pobieranie wiadomości od użytkownika i wyświetlanie odpowiedzi serwera.

Uwagi:
Brak

Lab 4:
