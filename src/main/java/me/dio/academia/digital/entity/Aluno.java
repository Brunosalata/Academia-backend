package me.dio.academia.digital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data                     // anotação do lombok para acessar os getters e setters
@NoArgsConstructor        // anotação do lombok - Construtor sem parâmetro
@AllArgsConstructor       // anotação do lombok - Construtor com todos os parâmetro
@Entity                   // representa a injeção (cria a tabela no banco de dados) - pede pra toda identidade ter um ID
@Table(name = "tb_alunos")    // se não tivesse definido o nome como tb_aluno, teria salvo como Aluno
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aluno {

  @Id                   // anotação de chave primária
  @GeneratedValue(strategy = GenerationType.IDENTITY)     //Generate.IDENTITY gera automaticamente os Id
  private Long id;

  private String nome;

  @Column(unique = true)  //valor único
  private String cpf;

  private String bairro;

  private LocalDate dataDeNascimento;

  @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)    //quando pedir informações do aluno, vem tudo, menos as avaliações físicas referente ao aluno
  @JsonIgnore     //estratégia para ignorar eventuais eventos indesejados do Json
  private List<AvaliacaoFisica> avaliacoes = new ArrayList<>();     //relação entre tabelas, que demanda chave estrangeira

}
