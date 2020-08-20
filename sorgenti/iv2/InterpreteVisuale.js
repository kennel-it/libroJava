/**
 * Interprete visuale per prseudocodice
 * studio Aspix: Edoardo Panfili, Patrizio Pesciaioli
 */
'use strict';

//funzione separata così con un commento si può staccare tutto
function debug(messaggio){
	var testo;
	
	if(messaggio.tipo!==undefined && messaggio.testo!==undefined){
		testo="istruzione: "+messaggio.tipo+"{"+messaggio.testo+"}";
	}else if(messaggio.tipo!==undefined && messaggio.condizione!==undefined){
		testo="istruzione: "+messaggio.tipo+"{"+messaggio.condizione+"}";
	}else{
		testo=messaggio;
	}
	// console.log("     "+testo);
}

/****************************************************************************
 * Ambiente di esecuzione dei singoli comandi:
 *  - mantiene le variabili
 *  - esegue singole istruzioni
 *  - valuta espressioni
 *  - rappresenta in json
 ***************************************************************************/
class Ambiente {
    
    constructor(nome){
        this.variabili = [];
        this.enfasi = new Object(); // un hash con nome variabile e relativa espressione        
        this.target = undefined;
        this.daMostrare = []; // elenco di variabili da visualizzare    
        this.nome = nome;
        this.dump = "";
        this.restituito = undefined; // il valore che questo ambiente restituisce al chiamante (normalemente risultato di un return)    
    }

	getNome(){ return this.nome; }
	setInfo(inf){ this.info = inf; }
	getInfo(){ return this.info; }
    
	setRestituito(x){ this.restituito = x; }
	getRestituito(){ return this.restituito; }
	
	
	setTarget(x){ this.target = x; }
	getTarget(){ return this.target; }
	
	addVariabile(nomeVariabile){ 
		this.variabili[this.variabili.length] = nomeVariabile;
		this.dump += nomeVariabile+"=\"\";";
	}
	
	addMostrare(nomeVariabile){ this.daMostrare.push(nomeVariabile);}
	getMostrare(){ return this.daMostrare; }
	
	addEnfasi(variabile, espressione){ this.enfasi[variabile] = espressione; }
	
	getDecl(){
		let decl = "";
		let i;
		for(i=0; i<this.variabili.length; i++ ){
			decl+= "var "+this.variabili[i]+"; ";
		}	
		return decl;
	}
	
	getDump(){ return this.dump; }

	getDumpRequest(){
		let decl = "\"\"";
		let i;
		for(i=0; i<this.variabili.length; i++ ){
			decl+= "+\""+this.variabili[i]+"=\"+JSON.stringify("+this.variabili[i]+")+\";\" ";
		}	
		return decl;
	}
	
	execute(istruzione){
		this.dump = eval(this.getDecl()+this.dump+istruzione+";"+this.getDumpRequest());
	}
	
	evaluate(espressione){
		return eval(this.getDecl()+this.dump+espressione);;
	}
	
	evaluateConPreambolo(espressione, preambolo){
		return eval(this.getDecl()+this.dump+preambolo+";"+espressione);
	}
	
	evaluateToJSON(espressione){
		return this.evaluate("var aijs_t="+espressione+";JSON.stringify(aijs_t)");
	}
	
	toString(){ return "«"+this.dump+"»"; }	
}

/************************************************************************
 * uno stack frame usato per l'esecuzione del codice
 ***********************************************************************/
class StackFrame{
    constructor(){
        this.istruzioni = null;
        this.PC = 0;    // è la prossima istruzione da eseguire
        this.guardia = "false";
        this.ambiente = null;        
    }
    setIstruzioni(ist){ this.istruzioni = ist; }
    getIstruzioni(){ return this.istruzioni; }

	restart(){ this.PC = 0; }
	getProssimaIstruzione(){
		var daEseguire = this.PC;
		this.PC++;
		return this.istruzioni[daEseguire]; 
	}
	ancoraIstruzioni(){
		return this.PC<this.istruzioni.length;
	}

	setGuardia(g){ this.guardia=g;}
	getGuardia(){ return this.guardia;}
	
	setAmbiente(amb){ this.ambiente = amb; }
	getAmbiente(){ return this.ambiente; }
	
	getPC(){return this.PC;}
	incPC(){this.PC++;}
	
	toString(){
		return "StackFrame[PC="+this.PC+"  env:"+this.ambiente.toString()+"]";
	}
}


class InterpreteVisuale {
    /****************************************************************************
     * @param programma un oggetto creato con Programma()
     * @param idElementoContatore un elemento del DOM che contiene un solo numero 
     *        (le istruzioni eseguite) che verrà incrementato dall'interprete
     * @param idElementoStack un elemento del DOM che verrà usato 
     *        per inserire gli stack frames
     * @param didascalie spazio in cui visualizzare le didascalie
     ***************************************************************************/
    constructor (programma, idElementoContatore, idElementoStack, didascalie){
        this.programma = programma.codice;
        this.idRappresentazioneCodiceInDOM = programma.elencoIdOggettiDOM();
        this.stack;
        this.tempo_base = 1000;
        this.velocita = 1.0;
        this.contatore_istruzioni;
        this.amplificazione = 0.25; // usato per amplificare il valore degli elementi del vettore auando viene rappresentato come barra
        // contiene il numero di istruzioni, un elemento di HTML contenete uno zero
        this.contatore = document.getElementById(idElementoContatore);
        // contiene gli stack frames
        this.vStack = document.getElementById(idElementoStack);
        // prefisso degli elementi nel DOM che rappresentano gli ambienti di esecuzione
        this.prefissoDOM = "aijs"+idElementoStack+"_";
        // il nodo in cui visualizzare le didascalie
        this.vDidascalie =  document.getElementById(didascalie);
    }
    
    interpreta(funzioneIniziale){
        var primoFrame = new StackFrame();
        primoFrame.setIstruzioni(this.programma[funzioneIniziale].body);
        
        primoFrame.setAmbiente(new Ambiente(funzioneIniziale));
        this.contatore_istruzioni = 1;
        
        this.stack = [primoFrame];
        // elimina tutti gli elementi dallo stack 
        while(this.vStack.firstChild){
            this.vStack.removeChild(this.vStack.firstChild);
        }
        var boundProssimaIstruzione = this.prossimaIstruzione.bind(this);
        setTimeout(function(){ boundProssimaIstruzione(); }, this.tempo_base*this.velocita);
    }
    
    /************************************************************************
     * ferma l'esecuzione dello script
     ***********************************************************************/
    pausa(){
        clearTimeout(this.timer);
    }
    
    /************************************************************************
     * riavvia l'esecuzione dello script
     ***********************************************************************/
    riprendi(){
        setTimeout(this.prossimaChiamata,1);
    }
    
    prossimaIstruzione(){
        if(this.contatore){
            this.contatore.innerHTML = ""+(this.contatore_istruzioni++);
        }
        var topStack = this.stack[this.stack.length-1];
        var newFrame;
        var istruzione;
        var tmp;
        var descrizioneChiamata;
        var numeroArgomenti;
        if(topStack.ancoraIstruzioni()){
            debug("sl:"+this.stack.length+" "+topStack.toString());
            istruzione = topStack.getProssimaIstruzione();
            debug(istruzione);
            switch(istruzione.tipo){
            case "velocita_animazione":
                this.velocita = istruzione.velocita;
                break;
            case "variabile":
                topStack.getAmbiente().addVariabile(istruzione.testo);
                if(istruzione.mostra){
                    topStack.getAmbiente().addMostrare(istruzione.testo);
                }
                if(istruzione.enfasi){
                    topStack.getAmbiente().addEnfasi(istruzione.testo, istruzione.enfasi);
                }
                if(istruzione.linea){
                    this.evidenzia(istruzione);
                }
                break;
            case "mostra":  // serve in più per via dei parametri
                topStack.getAmbiente().addMostrare(istruzione.nome);
                if(istruzione.enfasi){
                    topStack.getAmbiente().addEnfasi(istruzione.nome, istruzione.enfasi);
                }
                break;
            case "vettore":
                topStack.getAmbiente().addVettore(istruzione.testo);
                break;
            case "istruzione":
                topStack.getAmbiente().execute(istruzione.testo)
                if(istruzione.linea){
                    this.evidenzia(istruzione);
                }
                break;
            case "if":
                if( topStack.getAmbiente().evaluate(istruzione.condizione) ){
                    newFrame = new StackFrame();
                    newFrame.setIstruzioni(istruzione.allora);
                    newFrame.setAmbiente(topStack.getAmbiente());
                    this.stack.push(newFrame);
                }else{
                    newFrame = new StackFrame();
                    newFrame.setIstruzioni(istruzione.altrimenti);
                    newFrame.setAmbiente(topStack.getAmbiente());
                    this.stack.push(newFrame);
                }
                if(istruzione.linea){
                    this.evidenzia(istruzione);
                }
                break;
            case "while":
                if( topStack.getAmbiente().evaluate(istruzione.condizione) ){
                    newFrame = new StackFrame();
                    newFrame.setIstruzioni(istruzione.corpo);
                    newFrame.setAmbiente(topStack.getAmbiente());
                    newFrame.setGuardia(istruzione.condizione);
                    this.stack.push(newFrame);
                }
                if(istruzione.linea){
                    this.evidenzia(istruzione);
                }
                break;
            case "chiamata":
                topStack.getAmbiente().setTarget(istruzione.target);
                newFrame = new StackFrame();
                newFrame.setIstruzioni(this.programma[istruzione.nome].body);
                newFrame.setAmbiente(new Ambiente(istruzione.nome+"-"+this.stack.length));
                descrizioneChiamata = istruzione.nome+"(";
                debug("chiamata per "+istruzione.nome);
                numeroArgomenti = 0;
                for (var key in istruzione.argomenti) {
                    // devo vedere se la variabile in questione è un vettore o meno
                    var valore = istruzione.argomenti[key];             
                    newFrame.getAmbiente().addVariabile(key);
                    tmp = topStack.getAmbiente().evaluateToJSON(valore);
                    debug(" "+key+"← "+tmp+" (( "+valore+" ))");
                    newFrame.getAmbiente().execute(key+'='+tmp);
                    newFrame.getAmbiente().addMostrare(key);
                    // rappresentazione dei parametri
                    if(numeroArgomenti>0){
                        descrizioneChiamata += ", "
                    }
                    descrizioneChiamata += key+"="+tmp;
                    numeroArgomenti++;
                }
                descrizioneChiamata += ")";
                newFrame.getAmbiente().setInfo(descrizioneChiamata);
                this.stack.push(newFrame);
                if(istruzione.linea){
                    this.evidenzia(istruzione);
                }
                break;
            case "restituisci":
                tmp = topStack.getAmbiente().evaluateToJSON(istruzione.testo);
                debug("restituisco il valore "+tmp);
                topStack.getAmbiente().setRestituito(tmp);
                if(istruzione.linea){
                    this.evidenzia(istruzione);
                }
                break;
            }
            this.rappresentaAmbiente(topStack.getAmbiente());
            var boundProssimaIstruzione = this.prossimaIstruzione.bind(this);
            this.prossimaChiamata=function(){ boundProssimaIstruzione(); };
            if(istruzione.tipo=="velocita_animazione"){
                this.timer = setTimeout(this.prossimaChiamata, 1); 
            }else{
                this.timer = setTimeout(this.prossimaChiamata, this.tempo_base*this.velocita);
            }
        }else{
            this.pulisci();
            var guardia = topStack.getGuardia();
            var esito = topStack.getAmbiente().evaluate(guardia);
            if(esito===false){
                var rrr = topStack.getAmbiente().getRestituito();
                if(this.stack.length>1){
                    // l'ultimo lo lasciamo, così, per bontà d'animo
                    if(topStack.getAmbiente().getNome()!==this.stack[this.stack.length-2].getAmbiente().getNome()){
                        this.rimuoviAmbiente(topStack.getAmbiente());
                    }
                }
                this.stack.pop();
                if(this.stack.length>0){
                    var ambi = this.stack[this.stack.length-1].getAmbiente()
                    if(ambi.getTarget()!=undefined && rrr!=undefined){
                        debug("Assegno il valore restituito "+rrr+" a "+ambi.getTarget());
                        ambi.execute(ambi.getTarget()+"="+rrr);
                    }
                }
                debug("finita esecuzione sequenza istruzioni");
            }else{
                debug("eseguo ulteriore iterazione");
                topStack.restart();
            }
            if(this.stack.length>0){
                debug("ancora qualcosa da fare");
                var boundProssimaIstruzione = this.prossimaIstruzione.bind(this);
                // XXX: qui probabilmente l'atesa deve essere zero
                // siccome non si è eseguito nulla (nessuna istruzione)
                this.prossimaChiamata=function(){ boundProssimaIstruzione(); };
                this.timer = setTimeout(this.prossimaChiamata, this.tempo_base*this.velocita);                                                      
            }
        }
        // XXX: non inserire niente qui, ci sono chiamate con timeout all'internmo dell'if
    }
    
    // visualizzatori: creano codice HTML per mostrare le variabili
    /************************************************************************
     * rappresentazione degli stack: ne rimuove uno
     ***********************************************************************/
    rimuoviAmbiente(ambiente){
        let frameAmbiente = document.getElementById(this.prefissoDOM+ambiente.getNome());
        if(frameAmbiente){
            this.vStack.removeChild(frameAmbiente);
        }
    }
    
    rappresentaAmbiente(ambiente){
        var variabili = ambiente.getMostrare();
        var iVariabile;
        var frameAmbiente = document.getElementById(this.prefissoDOM+ambiente.getNome());
        var rigaTitolo;
        var rigaContenuto;
        var cella;
        var nome;
        var valore;
        var nodo;
        var informazioniNodo;
        
        var preparate = document.createElement('tr');
        
        // preparo le variabili prima di eliminare la visualizzazione
        for(iVariabile=0 ; iVariabile<variabili.length ; iVariabile++){
            nome = variabili[iVariabile];
            valore = ambiente.evaluate(variabili[iVariabile]);
            if(valore instanceof Array){
                // il valore contenuto è un vettore
                var tabella;
                var riga,colonna;
                var iValore;
                var barra;
                var usaEnfasi;
                tabella = document.createElement('table');
                tabella.setAttribute('class', "aijs_vettore");
                riga = document.createElement('tr');
                tabella.appendChild(riga);
                colonna = document.createElement('td');
                colonna.setAttribute("colspan",valore.length);
                colonna.setAttribute('class', "aijs_nomeVettore");
                colonna.appendChild(document.createTextNode(nome));
                riga.appendChild(colonna);
                riga = document.createElement('tr')
                iValore;
                tabella.appendChild(riga);
                for(iValore=0; iValore<valore.length; iValore++){
                    colonna = document.createElement('td');
                    riga.appendChild(colonna);
                    barra = document.createElement('div');
                    barra.style.height=(valore[iValore]*this.amplificazione)+"em";
                    if(ambiente.enfasi[nome]){
                        try {
                            usaEnfasi = ambiente.evaluateConPreambolo(ambiente.enfasi[nome],"var aijs_i="+iValore);
                            if(usaEnfasi){
                                barra.setAttribute('class', "aijs_enfasi1");
                            }
                        }
                        catch(err) {
                            // ce ne facciamo una ragione
                        }
                    }
                    colonna.appendChild(barra);
                    barra.appendChild(document.createTextNode(valore[iValore]));
                }
                cella = document.createElement('td');
                cella.appendChild(tabella);
                // rigaContenuto.appendChild(cella);
                preparate.appendChild(cella);
            }else{
                var nodoNome;
                var nodoBr;
                var nodoValore;
                // il valore contenuto è una normale variabile
                nodo = document.createElement('div');
                nodoNome = document.createElement('small');
                nodoNome.appendChild(document.createTextNode(nome));
                nodoBr = document.createElement('br');
                nodoValore = document.createTextNode(valore);
                nodo.appendChild(nodoNome);
                nodo.appendChild(nodoBr);
                nodo.appendChild(nodoValore);
                
                nodo.setAttribute('class', "aijs_var");
                cella = document.createElement('td');
                cella.appendChild(nodo);
                preparate.appendChild(cella);
                // rigaContenuto.appendChild(cella);
            }
        }
        
        if(frameAmbiente){
            // il contenitore per questo ambiente esiste, mi limito a pulire
            // cioè elimino la seconda riga e ne inserisco una nuova
            rigaTitolo = frameAmbiente.childNodes[0];
            frameAmbiente.removeChild(frameAmbiente.childNodes[1]);
            rigaContenuto = document.createElement('tr');
            frameAmbiente.appendChild(rigaContenuto);
        }else{
            // va costruito la rappresentazione del frame
            frameAmbiente = document.createElement('table');
            frameAmbiente.setAttribute('class', "aijs_frame");
            frameAmbiente.setAttribute('id', this.prefissoDOM+ambiente.getNome());
            rigaTitolo = document.createElement('tr');
            frameAmbiente.appendChild(rigaTitolo);
            rigaContenuto = document.createElement('tr');
            frameAmbiente.appendChild(rigaContenuto);
            cella = document.createElement('td');
            rigaTitolo.appendChild(cella);
            if(typeof ambiente.getInfo() != 'undefined' ){
                informazioniNodo = document.createElement('span');
                informazioniNodo.setAttribute('class', "aijs_infoAmbiente");
                informazioniNodo.appendChild(document.createTextNode(ambiente.getInfo()));
                cella.appendChild(informazioniNodo);
            }else{
                cella.appendChild(document.createTextNode(ambiente.getNome()));
            }
            if(this.vStack.firstChild!=null){
                this.vStack.insertBefore(frameAmbiente, this.vStack.firstChild);
            }else{
                this.vStack.appendChild(frameAmbiente);
            }
        }
        rigaTitolo.firstChild.setAttribute("colspan",variabili.length);
        
        frameAmbiente.replaceChild(preparate, rigaContenuto);
    }
    
    /************************************************************************
     * rimuove tutti gli attributi "class" 
     */
    pulisci(){
        this.idRappresentazioneCodiceInDOM.forEach( x => 
            document.getElementById(x).classList.remove("esecuzione")
        );
        this.vDidascalie.innerText = "";
    }
    
    evidenzia(elemento){
        this.pulisci();
        document.getElementById(elemento.linea).classList.add("esecuzione");
        if( elemento.didascalia ){
            this.vDidascalie.innerText = elemento.didascalia;
        }
    }
	
}


/*--------------------------------------------------+
 |                  Istruzione                      |
 +--------------------------------------------------+
 | tipo : dechiarazione o if o ...                  |
 | elementoDOM: id dell'elemento che la rappresenta |
 | didascalia: didascalia dell'operazione svolta    |
 +--------------------------------------------------*/
class Istruzione {
    constructor(tipo, elementoDOM, didascalia){
        this.tipo = tipo;
        if(elementoDOM){
            // convien prendere direttamente il nodo
            this.linea = elementoDOM;
            // this.elencoOggettiInDOM.push(lineaCodice[4]); FIXME
        }
        this.didascalia = didascalia;
    }
}
class Velocita extends Istruzione {
    constructor(velocita){
        super("velocita_animazione");   
        this.velocita = velocita;
    }
}
class Decl extends Istruzione {
    constructor(testo, mostra, enfasi, elementoDOM, didascalia){
        super("variabile", elementoDOM, didascalia);
        this.testo = testo;
        this.mostra = mostra ? true : false;
        this.enfasi = enfasi ? true : false;
    }
}
class Mostra extends Istruzione {
    constructor(nome, enfasi){
        super("mostra");
        this.nome = nome;
        this.enfasi = enfasi ? true : false;
    }
}
class Istr extends Istruzione { // vedi se possibile rinominare in assegnazione
    constructor(testo, elementoDOM, didascalia){
        super("istruzione", elementoDOM, didascalia);
        this.testo = testo;
    }
}
class Chiamata extends Istruzione {
    constructor(nome, argomenti, target, elementoDOM, didascalia){
        super("chiamata", elementoDOM, didascalia);
        this.nome = nome;
        this.argomenti = argomenti;
        this.target = target;
    }
}
class If extends Istruzione {
    constructor(condizione, allora, altrimenti, elementoDOM, didascalia){
        super("if", elementoDOM, didascalia);
        this.condizione = condizione;
        this.allora = allora;
        this.altrimenti = altrimenti;
    }
}
class Restituisci extends Istruzione {
    constructor(testo, elementoDOM, didascalia){
        super("restituisci", elementoDOM, didascalia);
        this.testo = testo;
    }
}
class While extends Istruzione {
    constructor(condizione, corpo, elementoDOM, didascalia){
        super("while", elementoDOM, didascalia);
        this.condizione = condizione;
        this.corpo = corpo;
    }
}

/****************************************************************************
 * Una funzione ha un nome e un elenco di istruzioni
 ***************************************************************************/
class Funzione {
    constructor(nome, ...istruzioni){
        this.nome = nome;
        this.body = [];
        
        // FIXME: per quale accidenti di motivo lo fai?
        let workaroundVisualizzazioneAmbienteInizialeVuoto = new Object(); 
        workaroundVisualizzazioneAmbienteInizialeVuoto.tipo = "variabile";
        workaroundVisualizzazioneAmbienteInizialeVuoto.testo = "aijs_w1";
        workaroundVisualizzazioneAmbienteInizialeVuoto.mostra = false; 
        workaroundVisualizzazioneAmbienteInizialeVuoto.enfasi = false;
        this.body.push(workaroundVisualizzazioneAmbienteInizialeVuoto);
        
        istruzioni.forEach( x => {
            this.body.push(x);
            // FIXME: che accidenti fa il pezzo qui sotto??
            if(x.tipo=="chiamata" && x.linea){
                let workaroundVisualizzazioneValoreRitornato = new Object(); 
                workaroundVisualizzazioneValoreRitornato.tipo = "istruzione";
                workaroundVisualizzazioneValoreRitornato.testo = x.target+"="+x.target;
                workaroundVisualizzazioneValoreRitornato.linea = x.linea; 
                workaroundVisualizzazioneValoreRitornato.enfasi = false;
                workaroundVisualizzazioneValoreRitornato.didascalia = "il valre restituito verrà copiato in "+x.target;
                this.body.push(workaroundVisualizzazioneValoreRitornato);
            }
        });
    }
}

/****************************************************************************
 * Un programma è un elenco di funzioni
 ***************************************************************************/
class Programma {
    constructor(...listaFunzioni){
        this.elencoOggettiInDOM = []; // FIXME: da togliere!
        this.codice = new Object();
        
        for(var i=0; i<listaFunzioni.length; i++){
            this.codice[ listaFunzioni[i].nome ] = listaFunzioni[i];
        }
    }
    elencoIdOggettiDOM(){
        let nomiFunzioni = Object.keys(this.codice);
        let nomi = [];
        nomiFunzioni.forEach( x => {
            this.codice[x].body.forEach( y => {
                if(y.linea){   
                    nomi.push( y.linea );
                }
            })
        });
        return nomi;
    }
}