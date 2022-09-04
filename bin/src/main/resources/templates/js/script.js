const formulario = document.querySelector("form");

const Inome = document.querySelector(".nome");
const Icpf = document.querySelector(".cpf");
const Irg = document.querySelector(".rg");
const Isexo = document.querySelector(".sexo");
const Icep = document.querySelector(".cep");
const Irua = document.querySelector(".rua");
const Inumero = document.querySelector(".numero");
const Ibairro = document.querySelector(".bairro");
const Icomp = document.querySelector(".comp");
const Icidade = document.querySelector(".cidade");
const Iuf = document.querySelector(".uf");

function cadastrar () {
	fetch("http://localhost:8080/api/pessoas",
			{
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json',
				},
				method: "POST",
				body: JSON.stringify({
						nome: Inome.value,
						cpf: Icpf.value,
						rg: Irg.value,
						sexo: Isexo.value,
						cep: Icep.value,
						rua: Irua.value,
						numero: Inumero.value,
						bairro: Ibairro.value,
						comp: Icomp.value,
						cidade: Icidade.value,
						uf: Iuf.value
					})
			})
			.then(function (res) {console.log(res) })
			.catch(function (res) { console.log(res) })
};

function limpar() {
	Inome.value = "";
	Icpf.value = "";
	Irg.value = "";
	Isexo.value = "";
	Icep.value = "";
	Irua.value = "";
	Inumero.value = "";
	Ibairro.value = "";
	Icomp.value = "";
	Icidade.value = "";
	Iuf.value = "";
};

formulario.addEventListener('submit', function(event) {
	event.preventDefault();
	
	cadastrar();
	limpar();
});