# JavaSockets
For english below 


Podczas laboratorium należy zbudować aplikację działającą w środowisku rozproszonym, wykorzystującą do komunikacji gniazda TCP/IP (klasy ServerSocket, Socket). Dokładniej - należy zaimplementować mały systemu, w którego skład wejdą instancje klas uruchamianych równolegle (na jednym lub na kilku różnych komputerach). System ten ma być symulatorem „Sieci boi sygnałowych” na wzór sieci, jaka pojawiła się w filmie „Battleship: Bitwa o Ziemię”.
Specyfikacja problemu:
Na planszy o wymiarze 40 x 40 reprezentującej „morze” znajdują się sektory o wymiarze 5 x 5 ułożone w szachownicę (inaczej mówiąc: plansza to szachownica wymiarze 8 x 8, której ,,pola’’ to sektory rozmiaru 5 x 5).
W środku sektorów znajdują się boje sygnałowe. Każda boja sygnałowa w określonych interwałach wysyła sygnał zawierający informację o poziomie morza do centrali.
Po „morzu’’ pływają „statki”. Podczas ruchu generują one fale. Niech fala generowana przez dany statek będzie reprezentowana przez prostokąt 5x5 wypełniony wartościami jak niżej, który będzie wycentrowany względem położenia tego statku na planszy (inaczej mówiąc statki poruszając się po planszy powodują przesuwanie się „wzgórków” reprezentowanych poniższymi wartościami)
01210
12321
23432
12321
01210
Zakładamy, że „wzgórek” istnieje w miejscu przebywania „statku” („wzgórek” przesuwa się wraz ze „statkiem”). Efekt tłumienia fal nie będzie modelowany (!)
Aby zasymulować zdarzenia zachodzące na morzu tworzona jest osobna aplikacja. Pełni ona rolę „świata”.
Każdy „statek” wysyła do „świata” wiadomości z informacją o zamiarze wykonania ruchu (komendą „move”, z parametrem określającym jeden z ośmiu możliwych kierunków – zakładamy, że przesunięcia będą dyskretne). Zwrotnie „statek” powinien otrzymać informację, czy wykonanie komendy się udało, czy nie. Jeśli bowiem dojdzie do zderzenia się statków, statki pójdą na dno, jeśli zaś ruch wyprowadziłby statek poza obszar planszy, ruch powinien być zabroniony. Statki mogą przepływać przez pola zajmowane przez boje.
Każdy „statek” może na żądanie otrzymać od „świata” informację o pozycjach innych statków (wysyłając komendę „scan”, w wyniku której zwracana jest informacja o położeniu innych statków).
Podczas obsługi komendy „move” od danego statku „świat” informuje „boje” znajdujące się w pobliżu tegoż statku o jego pozycji. Boje na tej podstawie muszą wyliczyć poziom morza i w odpowiednim momencie wysłać wyliczoną wartość do „centrali”. Zakładamy, że jeśli w otoczeniu danej boi znajduje się kilka statków, a „wzgórki” odpowiadające ich położeniom w jakiejś części pokrywają się, wtedy dochodzi do „interferencji fal” (tj. wartości „wzgórków” w danym dyskretnym miejscu powinny być sumowane).
„Centrala” to osobna aplikacja (klasa z metodą main), która powinna oferować interfejs graficzny, na którym będzie widać poziom morza raportowany przez boje sygnałowe. W budowanym systemie uruchomiona będzie tylko jedna jej instancja.
„Świat” to osobna aplikacja (klasa z metodą main), która powinna oferować interfejs graficzny, na którym będzie widać położenie statków. W budowanym systemie uruchomiona będzie tylko jedna jej instancja.
„Statek” to osobna aplikacja (klasa z metodą main), która powinna oferować interfejs (może być prosty, okienkowy), na którym będzie można monitorować stan statku (czy pływa, jakie komendy wydaje i co „widzi”). W budowanym systemie uruchomionych będzie kilka jej instancji.
„Boja” to osobna aplikacja (klasa z metodą main), która nie musi wystawiać żadnego interfejsu użytkownika. W budowanym systemie uruchomionych będzie wiele ich instancji (właśnie z powodu ich dużej liczby rezygnujemy tutaj z implementacji interfejsu użytkownika)
Aby doszło do komunikacji między elementami systemu należy je jakoś „poszyć”. Należy więc zaplanować, gdzie umieścić gniazdka serwerowe, a gdzie klienckie, oraz jak przekazać do uruchamianych instancji informacje o hostach i portach, na których działają te gniazdka. Poza tym należy zastanowić się, jak początkowo pozycjonować statki i jak je uruchamiać (może warto wykorzystać jakieś formularze i przyciski).
Podsumowując, schemat komunikacji w budowanym systemie ma być następujący: „boja” komunikuje się z „centralą” (raportując poziom morza) oraz „światem” (otrzymując informacje o przepływających w pobliżu statkach); „statek” komunikuje się ze „światem” (wysyłając komendy „move” i „scan”). Liczba uruchamianych instancji statków może być dowolna. Liczba uruchamianych „boi” wynosi 64.


ENG
During the lab, you should build an application running in a distributed environment, using TCP/IP sockets (ServerSocket, Socket classes) for communication. More precisely, a small system is to be implemented, which will include instances of the classes running in parallel (on one or more different computers). This system is to be a simulator of a "Signal Buoy Network" along the lines of the network that appeared in the movie "Battleship: Battle for Earth".
Problem specification:
On a 40 x 40 board representing the 'sea' there are sectors of size 5 x 5 arranged in a chessboard (in other words: the board is an 8 x 8 chessboard, whose 'boxes' are sectors of size 5 x 5).
In the centre of the sectors are signal buoys. Each signal buoy sends a signal containing information about the sea level to the control panel at certain intervals.
There are 'ships' sailing on the 'sea'. As they move, they generate waves. Let a wave generated by a given ship be represented by a 5x5 rectangle, filled with the values as below, which will be centred with respect to the position of this ship on the board (in other words, the ships, when moving on the board, cause the "hills" represented by the values below to move)
01210
12321
23432
12321
01210
We assume that the 'hill' exists at the location of the 'ship' ('hill' moves with the 'ship'). The effect of wave attenuation will not be modelled (!).
To simulate events occurring at sea, a separate application is created. This acts as a 'world'.
Each 'ship' sends messages to the 'world' indicating that it intends to move (with a 'move' command, with a parameter specifying one of eight possible directions - we assume that the moves will be discrete). In return, the 'ship' should receive information on whether the execution of the command was successful or not. This is because if the ships collide, the ships will go down, while if the move would take the ship out of the board area, the move should be forbidden. Ships can pass through fields occupied by buoys.
Each 'ship' can receive information on the positions of other ships from the 'world' on request (by sending the 'scan' command, which returns information on the position of other ships).
When handling the 'move' command from a given ship, the 'world' informs the 'buoys' in the vicinity of that ship of its position. Based on this, the buoys have to calculate the sea level and send the calculated value to the "headquarters" at the appropriate moment. We assume that if there are several vessels in the vicinity of a given buoy and the "hills" corresponding to their positions overlap in some part, then "wave interference" occurs (i.e. the values of the "hills" in a given discrete location should be added up).
"Headquarters" is a separate application (a class with a main method) that should offer a graphical interface that will show the sea level reported by the signal buoys. Only one instance of it will be running in the system under construction.
"World" is a separate application (class with method main), which should offer a graphical interface on which the position of ships will be visible. Only one instance of it will run in the system under construction.
"Ship" is a separate application (class with the main method) that should offer an interface (can be simple, windowed) on which it will be possible to monitor the status of the ship (whether it is sailing, what commands it issues and what it "sees"). There will be several instances of it running in the system under construction.
"Buoy" is a separate application (a class with a main method) that does not need to expose any user interface. There will be many instances of it running in the system under construction (it is because of their large number that we abandon the implementation of the user interface here)
In order for there to be communication between the elements of the system, they need to be "lined up" somehow. Therefore, it is necessary to plan where to place server sockets, and where to place client sockets, as well as how to transfer information about hosts and ports on which these sockets operate to the instances being started. In addition to this, you need to think about how to position the ships initially and how to launch them (you may want to use some forms and buttons).
In summary, the communication scheme of the system under construction is to be as follows: "buoy" communicates with "control panel" (reporting sea level) and "world" (receiving information about ships passing nearby); "ship" communicates with "world" (sending "move" and "scan" commands). The number of ship instances launched can be arbitrary. The number of launched 'buoys' is 64.
