function evidenzia(nome){
	cont=nome;
	base = document.getElementById("areaEvidenziabile").getElementsByTagName("div");
	for (var i = 0; i < base.length; i++) {
		nodo = base[i];
		applicabile = nodo.getAttribute("data-riguarda"); 
		if(applicabile!=null && applicabile.search(cont)!=-1){
			nodo.style.backgroundColor = 'yellow';
			nodo.style.padding = '3px';
		}else{
			nodo.style.backgroundColor = 'transparent';
			nodo.style.padding = '0px';
		}
	}	
	return false;
}