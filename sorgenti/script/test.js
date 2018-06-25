function giusta(obj){
        obj.style.backgroundColor="#88ff88";
        obj.className = obj.className + " spiega";
}
function sbagliata(obj){
        obj.style.backgroundColor="#ff0000";
        obj.className = obj.className + " spiega";
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