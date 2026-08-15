## compilare il libro

Bisogna usare [ebook](https://github.com/kennel-it/ebook):
`java -jar lib/ebook.jar sorgenti compilati`
o lanciandolo senza parametri si apre l'interfaccia grafica

# debug in locale

Usare un web server tipo `jwebserver` o `python3 -m http.server `


# come scrivere i link per l'indice
- `titlepage.xhtml?+interfacce polimorfismo archiviareOggetti`
  in questo tutti gli altri nodi vengono sbiancati
- `titlepage.xhtml?-interfacce polimorfismo archiviareOggetti`
  in questo i tre nodi vengono sbiancati
- `titlepage.xhtml#liste`
  evidenzia il nodo in questione e tutte le sue dipendenze
  **è possibile evidenziare un solo nodo**
- `titlepage.xhtml?-interfacce polimorfismo archiviareOggetti#liste`
  fa entrambe le cose

# colori
https://material.io/tools/color/