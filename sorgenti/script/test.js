function giusta(obj){
        obj.classList.add("spiega");
        obj.classList.add("rispostaGiusta");
}
function sbagliata(obj){
        obj.classList.add("spiega");
        obj.classList.add("rispostaSbagliata");
}
function sistemaIde(){
	var nome=localStorage.getItem("ide");
	var altri={"eclipse":".netbeans", "netbeans":".eclipse"};
	if(nome!=null){
		console.log(nome);
		console.log("##"+altri[nome]);
		elementList = document.querySelectorAll(altri[nome]);
		for(i=0; i<elementList.length; i++){
			console.log("  modificato "+elementList[i]);
			elementList[i].className += " IDE_inutile";	
		}
	}else{
		console.log("nessuna prefernza sull'IDE");
	}
}