function evidenzia(nome){
	cont=nome;
	base = document.getElementById("areaEvidenziabile").querySelectorAll("span.row");
	for (var i = 0; i < base.length; i++) {
		nodo = base[i];
		applicabile = nodo.getAttribute("data-riguarda"); 
		if(applicabile!=null && applicabile.search(cont)!=-1){
			nodo.style.backgroundColor = 'yellow';
		}else{
			nodo.style.backgroundColor = 'transparent';
		}
	}	
	return false;
}