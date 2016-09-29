# Projektowanie Aplikacji Internetowych

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

Uwagi:

Lab 5:

Program ma za zadanie zasymulować przypadki zakleszczania i blokowania się wątków na przykładach: Deadlock, Livelock, oraz Starvation.
Do programu wprowadzam jako argument nazwe jednej z trzech symulacji:
- deadlock
- livelock
- starvation

Opis metod:
- Main, głównej klasy, uruchomi wtedy odpowiednią metodę i rozpocznie się symulacja.
- Deadlock, tworzy dwa obiekty, a następnie wątki dla każdego z nich. Wątki te jednocześnie próbują dostać się do tych samych zasobów, co powoduje zakleszczenie się ich. Żaden z wątków nie może otrzymać dostępu do zasobów, ktore są blokowane przez inny wątek. Proces ten może trwać w nieskończoność. Wyłącznik czasowy ustawiony jest na 30 sekund.
- Livelock, tworzy obiekty, oraz watki dla nich. Ustawia obiekty w odpowiedniej kolejnosci w metodzie watku. Ustawia ograniczenia czasowe i uruchamia watki. Metoda Hello, klasy livelock, dla jednego obiektu oczekuje na wiadomosc od drugiego, gdy jej nie dostaje przechodzi w stan uspienia, aby po wybudzeniu sprawdzic ponowenie czy wiadomosc zostala otrzymana.
- Starvation, tworzy trzy obiekty, do każdego z nich tworzy watek z metoda liczaca. Ustawia im rózne priorytety, duzy, normalny i niski. Uruchamia te watki. Możemy zaobserwować, że wątek z najwyższym priorytetem działał dłużej i osiągnąl lepszy wynik. Wątek z najniższym niestety "głodował", i nie dostawał tak często dostępu do zasobów, przez co osiągnąl gorszy wynik.
