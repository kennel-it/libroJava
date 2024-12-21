# per compilare il libro:
  - creare cartella "compilati"
  - click con il destro su "build.xml" e run as Ant build

# debug in locale
Se firefox solleva eccezioni per richieste cors utilizzando i file (senza passare per server web)
va impostata a false la proprietà security.fileuri.strict_origin_policy (accessibile via about:config)
come reference: http://kb.mozillazine.org/Security.fileuri.strict_origin_policy

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