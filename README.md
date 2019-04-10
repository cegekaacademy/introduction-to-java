# introduction-to-java

##Modelarea unui sistem bancar

###Detalii personale ale unui client (Person):
* nume
* cnp
* adresa
* calculeaza varsta persoanei pe baza cnp-ului
    * Prima cifra a C.N.P.-ului este: (sex barbatesc / sex femeiesc)
    * 1 / 2 - născuți între 1 ianuarie 1900 și 31 decembrie 1999
    * 3 / 4 - născuți între 1 ianuarie 1800 și 31 decembrie 1899
    * 5 / 6 - născuți între 1 ianuarie 2000 și 31 decembrie 2099

###Adresa (Address)
* strada
* numar
* oras

###Cont bancar (BankAccount)
* iban
* sold
* depositare suma
    * daca suma este >= 0
* retragere suma 
    * daca suma este >= 0 si contul are un sold suficient de mare cat sa permita retragerea
* transfer catre un alt cont bancar

###Cont bancar curent (CurrentAccount)
* tip de cont bancar cu limitare de 3000 de lei la retragere

###Cont bancar deposit (DepositAccount)
* tip de cont bancar cu limitare de 200 de lei la depunere

###Client al unei banci (BankClient)
* leaga o persoana (PersonDetail) de o lista de conturi bancare (BankAccount)
* permite calcularea soldului total pe toate conturile detinute de client
* permite calcularea unui bonus acordat clientilor cu varsta >= 50 ce va fi egal cu 5% 
din valoarea fiecarui depozit si 2% din valoarea fiecarui cont curent detinute de client