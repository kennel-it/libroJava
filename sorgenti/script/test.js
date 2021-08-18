function giusta(obj){
        obj.classList.add("spiega");
        obj.classList.add("rispostaGiusta");
}
function sbagliata(obj){
        obj.classList.add("spiega");
        obj.classList.add("rispostaSbagliata");
}

/****************************************************************************
 * Aggiunge un nodo come successivo a quello fornito
 * @param {Node} nodoPresente 
 * @param {Node} nodoDaAggiungere 
 ***************************************************************************/
function aggiungiSotto(nodoPresente, nodoDaAggiungere){
    if(nodoPresente.nextSibling){
        nodoPresente.parentNode.insertBefore(nodoDaAggiungere,nodoPresente.nextSibling);
    }else{
        nodoPresente.parentNode.appendChild(txt);
    }
}

/***************************************************************************
 * Controlla le risposte inserite dalll'utente nel test
 * @param {Node} nodo 
 * @param {Node} areaRisposta 
 **************************************************************************/
function controllaRisposte(nodo, areaRisposta){
    var totali = 0;
    var giuste = 0;
    // elimino tutte le vecchie risposte
    var vecchie = nodo.querySelectorAll("em");
    for (var i=0;i<vecchie.length;i++){
        nodo.removeChild(vecchie[i]);
    }
    var pezzi = nodo.querySelectorAll("span");
    for (var i=0;i<pezzi.length;i++){
        var giusta = pezzi[i].getAttribute("data-giusta").trim();
        var data = pezzi[i].innerHTML.trim();
        totali++;
        if(giusta==data){
            giuste++;
            pezzi[i].className="giusta";
        }else{
            pezzi[i].className="sbagliata";
            if(areaRisposta==null){
                var g = document.createElement("em");
                g.innerHTML=giusta;
                aggiungiSotto(pezzi[i], g);
            }
        }
    }
    if(areaRisposta!=null){
        areaRisposta.innerHTML="risposte esatte "+giuste+" su "+totali;
    }
}

/****************************************************************************
 * Analizza l'intero documento per sistemare i test con caselle da riempire
 ***************************************************************************/
function analizzaDocumento(){
    var d = document.querySelectorAll(".riempiSpazi");
    for(var x=0; x<d.length; x++){
        var nodo = d[x];
        var pezzi = nodo.querySelectorAll("span");
        for (var i=0;i<pezzi.length;i++){
            pezzi[i].setAttribute("data-giusta",pezzi[i].innerHTML);
            pezzi[i].setAttribute("contenteditable","true");
            pezzi[i].style.height="1.25em";
            pezzi[i].style.width=pezzi[i].offsetWidth*1.4+"px";
            pezzi[i].innerHTML = "";
        }

        var nc = document.createElement("div");
        nc.innerHTML="mostra soluzione";
        nc.className="mostraSoluzione";
        nc.addEventListener("click",controllaRisposte.bind(null,nodo,null));
        aggiungiSotto(nodo, nc);
        
        var elemento = document.createElement("div");
        elemento.innerHTML="click qui per controllare la risposta";
        elemento.className="controllaRisposta";
        elemento.addEventListener("click",controllaRisposte.bind(null,nodo,elemento));
        aggiungiSotto(nodo, elemento);
    }
}

window.addEventListener('load', analizzaDocumento);