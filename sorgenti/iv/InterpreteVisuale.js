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
function Ambiente(nome){
	this.variabili = [];
	this.enfasi = new Object(); // un hash con nome variabile e relativa espressione
	
	this.target = undefined;
	this.daMostrare = []; // elenco di variabili da visualizzare	
	this.nome = nome;
	this.dump = "";
	
	this.getNome = function(){ return this.nome; }
	this.setInfo = function(inf){ this.info = inf; }
	this.getInfo = function(){ return this.info; }
	/** il valore che questo ambiente restituisce al chiamante (normalemente risultato di un return) */
	this.restituito = undefined;
	this.setRestituito = function(x){ this.restituito = x; }
	this.getRestituito = function(){ return this.restituito; }
	
	
	this.setTarget = function(x){ this.target = x; }
	this.getTarget = function(){ return this.target; }
	
	this.addVariabile = function(nomeVariabile){ 
		this.variabili[this.variabili.length] = nomeVariabile;
		this.dump += nomeVariabile+"=\"\";";
	}
	
	this.addMostrare = function(nomeVariabile){ this.daMostrare.push(nomeVariabile);}
	this.getMostrare = function(){ return this.daMostrare; }
	
	this.addEnfasi = function(variabile, espressione){
		this.enfasi[variabile] = espressione;
	}
	
	this.getDecl = function(){
		var decl = "";
		var i;
		for(i=0; i<this.variabili.length; i++ ){
			decl+= "var "+this.variabili[i]+"; ";
		}	
		return decl;
	}
	
	this.getDump = function(){
		return this.dump;
	}

	this.getDumpRequest = function(){
		var decl = "\"\"";
		var i;
		for(i=0; i<this.variabili.length; i++ ){
			decl+= "+\""+this.variabili[i]+"=\"+JSON.stringify("+this.variabili[i]+")+\";\" ";
		}	
		return decl;
	}
	
	this.execute = function(istruzione){
		var x = eval(this.getDecl()+this.dump+istruzione+";"+this.getDumpRequest());
		this.dump = x;
	}
	
	this.evaluate = function(espressione){
		var x = eval(this.getDecl()+this.dump+espressione);
		return x;
	}
	
	this.evaluateConPreambolo = function(espressione, preambolo){
		var x = eval(this.getDecl()+this.dump+preambolo+";"+espressione);
		return x;
	}
	
	this.evaluateToJSON = function(espressione){
		return this.evaluate("var aijs_t="+espressione+";JSON.stringify(aijs_t)");
	}
	
	this.toString = function(){
		return "«"+this.dump+"»";
	}	
}

/************************************************************************
 * uno stack frame usato per l'esecuzione del codice
 ***********************************************************************/
function StackFrame(){
	this.istruzioni = null;
	this.setIstruzioni = function(ist){ this.istruzioni = ist; }
	this.getIstruzioni = function(){ return this.istruzioni; }
	
	this.PC = 0; 	// è la prossima istruzione da eseguire
	this.restart = function(){this.PC = 0;}
	this.getProssimaIstruzione = function(){
		var daEseguire = this.PC;
		this.PC++;
		return this.istruzioni[daEseguire]; 
	}
	this.ancoraIstruzioni = function(){
		return this.PC<this.istruzioni.length;
	}

	this.guardia = "false"; 
	this.setGuardia = function(g){ this.guardia=g;}
	this.getGuardia = function(){ return this.guardia;}
	
	this.ambiente = null;
	this.setAmbiente = function(amb){ this.ambiente = amb; }
	this.getAmbiente = function(){ return this.ambiente; }
	
	this.getPC = function(){return this.PC;}
	this.incPC = function(){this.PC++;}
	// this.getNome = function(){return this.nome;} FIXME: sembra sia inutilizzata
	
	this.toString = function(){
		return "StackFrame[PC="+this.PC+"  env:"+this.ambiente.toString()+"]";
	}
}


/****************************************************************************
 * @param programma un oggetto creato con Programma()
 * @param idElementoContatore un elemento del DOM che contiene un solo numero 
 * 		  (le istruzioni eseguite) che verrà incrementato dall'interprete
 * @param idElementoStack un elemento del DOM che verrà usato 
 * 		  per inserire gli stack frames
 ***************************************************************************/
function InterpreteVisuale(programma, idElementoContatore, idElementoStack){
	
	this.interpreta = function(funzioneIniziale){
		var primoFrame = new StackFrame();
		primoFrame.setIstruzioni(this.programma[funzioneIniziale]);
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
	this.pausa = function(){
		clearTimeout(this.timer);
	}
	
	/************************************************************************
	 * riavvia l'esecuzione dello script
	 ***********************************************************************/
	this.riprendi = function(){
		setTimeout(this.prossimaChiamata,1);
	}
	

	this.prossimaIstruzione = function(){
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
					this.evidenzia(istruzione.linea);
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
					this.evidenzia(istruzione.linea);
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
					this.evidenzia(istruzione.linea);
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
					this.evidenzia(istruzione.linea);
				}
				break;
			case "chiamata":
				topStack.getAmbiente().setTarget(istruzione.target);
				newFrame = new StackFrame();
				newFrame.setIstruzioni(this.programma[istruzione.nome]);
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
					this.evidenzia(istruzione.linea);
				}
				break;
			case "restituisci":
				tmp = topStack.getAmbiente().evaluateToJSON(istruzione.testo);
				debug("restituisco il valore "+tmp);
				topStack.getAmbiente().setRestituito(tmp);
				if(istruzione.linea){
					this.evidenzia(istruzione.linea);
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
	this.rimuoviAmbiente = function(ambiente){
		var frameAmbiente = document.getElementById(this.prefissoDOM+ambiente.getNome());
		if(frameAmbiente){
			// TODO cancella document.getElementById("stack").removeChild(frameAmbiente);
			this.vStack.removeChild(frameAmbiente);
		}
	}

	this.rappresentaAmbiente = function (ambiente){
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

	this.pulisci = function(){
		var i;
		for(i=0;i<this.idRappresentazioneCodiceInDOM.length;i++){
			document.getElementById(this.idRappresentazioneCodiceInDOM[i]).setAttribute('class', "");
		}
	}

	this.evidenzia = function(id){
		this.pulisci();
		document.getElementById(id).setAttribute('class', "esecuzione")
	}

	{// costruttore
		this.programma = programma.codice;
		this.idRappresentazioneCodiceInDOM = programma.elencoOggettiInDOM;
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
	}
	
}

// ==========================================================================

function Programma(lista){
	
	this.analizzaBody = function(sequenzaCodice){
		var lineaCodice;
		var body = [];
		var istruzione;
		// XXX: la prima dichiarazione presente in un corpo di funzione viene visualizzata subito
		// questa qui sotto serve a far comparire all'inizio uno stack frame vuoto
		var workaroundVisualizzazioneAmbienteInizialeVuoto = new Object(); 
		workaroundVisualizzazioneAmbienteInizialeVuoto.tipo = "variabile";
		workaroundVisualizzazioneAmbienteInizialeVuoto.testo = "aijs_w1";
		workaroundVisualizzazioneAmbienteInizialeVuoto.mostra = false; 
		workaroundVisualizzazioneAmbienteInizialeVuoto.enfasi = false;
		body.push(workaroundVisualizzazioneAmbienteInizialeVuoto);
		for(var j=0; j<sequenzaCodice.length; j++){
			lineaCodice = sequenzaCodice[j];
			istruzione = new Object();
			switch(lineaCodice[0]){
			case "velocita": // valore
				istruzione.tipo = "velocita_animazione";
				istruzione.velocita = lineaCodice[1];
				break;
			case "decl":	// nomevar mostra enfasi
				istruzione.tipo = "variabile";
				istruzione.testo = lineaCodice[1];
				istruzione.mostra = lineaCodice[2] ? true : false; 
				istruzione.enfasi = lineaCodice[3] ? true : false;
				if(lineaCodice[4]){
					istruzione.linea = lineaCodice[4];
					this.elencoOggettiInDOM.push(lineaCodice[4]);
				}
				break;		
			case "mostra": // nome enfasi
				istruzione.tipo = "mostra";
				istruzione.nome = lineaCodice[1];
				istruzione.enfasi = lineaCodice[2] ? true : false;
				break;
			case "istr": // istruzione linea
				istruzione.tipo = "istruzione";
				istruzione.testo = lineaCodice[1];
				if(lineaCodice[2]){
					istruzione.linea = lineaCodice[2];
					this.elencoOggettiInDOM.push(lineaCodice[2]);
				}
				break;
			case "chiamata": // nome argomenti target linea
				istruzione.tipo = "chiamata";
				istruzione.nome = lineaCodice[1];
				istruzione.argomenti = lineaCodice[2];
				istruzione.target = lineaCodice[3];
				if(lineaCodice[4]){
					istruzione.linea = lineaCodice[4];
					this.elencoOggettiInDOM.push(lineaCodice[4]);
				}
				break;
			case "if": // condizione allora altrimenti linea
				istruzione.tipo="if";
				istruzione.condizione = lineaCodice[1];
				istruzione.allora = this.analizzaBody(lineaCodice[2]);
				istruzione.altrimenti = this.analizzaBody(lineaCodice[3]);
				if(lineaCodice[4]){
					istruzione.linea = lineaCodice[4];
					this.elencoOggettiInDOM.push(lineaCodice[4]);
				}
				break;
			case "restituisci": // testo linea
				istruzione.tipo="restituisci";
				istruzione.testo = lineaCodice[1];
				if(lineaCodice[2]){
					istruzione.linea = lineaCodice[2];
					this.elencoOggettiInDOM.push(lineaCodice[2]);
				}
				break;
			case "while": // condizione corpo linea
				istruzione.tipo="while";
				istruzione.condizione = lineaCodice[1];
				istruzione.corpo = this.analizzaBody(lineaCodice[2]);
				if(lineaCodice[3]){
					istruzione.linea = lineaCodice[3];
					this.elencoOggettiInDOM.push(lineaCodice[3]);
				}
			}
			body.push(istruzione);
			// XXX:
			if(istruzione.tipo=="chiamata" && istruzione.linea){
				var workaroundVisualizzazioneValoreRitornato = new Object(); 
				workaroundVisualizzazioneValoreRitornato.tipo = "istruzione";
				workaroundVisualizzazioneValoreRitornato.testo = istruzione.target+"="+istruzione.target;
				workaroundVisualizzazioneValoreRitornato.linea = istruzione.linea; 
				workaroundVisualizzazioneValoreRitornato.enfasi = false;
				body.push(workaroundVisualizzazioneValoreRitornato);
			}
		}
		return body;
	}
	
	{// costruttore
		this.elencoOggettiInDOM = [];
		this.codice = new Object();
		
		for(var i=0; i<lista.length; i++){
			var nome =  Object.keys(lista[i])[0];
			this.codice[nome] = this.analizzaBody(lista[i][nome]);
		}
	}
}