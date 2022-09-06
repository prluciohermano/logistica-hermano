package com.garagecontrolsystem.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TB_PRODUTO")
public class ProdutoModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100)
	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	private String descProduto;
	
	@Column(length = 20)
	@NotEmpty(message = "{campo.anoModelo.obrigatorio}")
	private String anoModelo;
	
	@Column(length = 20)
	@NotEmpty(message = "{campo.anoModelo.obrigatorio}")
	private String corProduto;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;

	@Column(name = "preco_entrada")
	@NotNull(message = "{campo.precoEntrada.obrigatorio}")
	private BigDecimal precoEntrada;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private CategoriaModel categoria;
	
	@ManyToOne
    @JoinColumn(name = "pessoa_id")
    private PessoaModel pessoaModel;
	
	@Lob
	private byte[] documento;
	
	
	private String nomeFileDocumento;
	
	
	private String tipoFileDocumento;


}
