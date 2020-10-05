package br.ifpb.edu.testes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.ifpb.edu.domain.Tarefa;
import br.ifpb.edu.domain.Usuario;
import br.ifpb.edu.service.UsuarioService;

public class UsuarioTestes {
	// dominio
	private Usuario usuarioCorreto, usuarioSemSenha, usuarioSemEmail;
	private Tarefa tarefaSemDadosUsuarioCorreto, tarefaComTodosOsDados;
	// serviço
	private UsuarioService usuarioService;

	// instancia dos objetos usados nos testes
	@Before
	public void instanciaEntidadesUtilizadas() {
		// cria alguns objetos usuarios para serem utilizados nos testes

		// usuarios
		usuarioService = new UsuarioService();
		usuarioCorreto = new Usuario("italo", "italo@gmail.com", "abc");
		usuarioSemSenha = new Usuario("sergio", "sergio@gmail.com", null);
		usuarioSemEmail = new Usuario("jubileu", null, "jubileu123");

		// tarefas
		tarefaSemDadosUsuarioCorreto = new Tarefa(null, null, null);
		tarefaComTodosOsDados = new Tarefa("tarefa com todos os dados", "a tarefa possui todos os dados", new Date());
	}

	@Test
	public void testaSeCadastraUmUsuarioComSucesso() {
		// se cadastrar com sucesso, ser� retornado um TRUE
		assertTrue(usuarioService.salvarNovoUsuario(usuarioCorreto));
	}

	@Test
	public void testaSalvarUmUsuarioSemSenha() {
		// o usuario nao possui senha, logo ser� retornado um FALSE ao tentar inserir no
		// banco
		assertFalse(usuarioService.salvarNovoUsuario(usuarioSemSenha));
	}

	@Test
	public void testaSalvarUmUsuarioSemEmail() {
		assertFalse(usuarioService.salvarNovoUsuario(usuarioSemEmail));
	}

	@Test
	public void testaSalvarTarefaSemDadosParaOUsuarioCorreto() {
		// a tarefa nao tem nenhuma informa��o, ent�o ser� retornado um FALSE ao tentar
		// adiciona na lista de tarefas do usuario
		assertFalse(usuarioCorreto.adicionarTarefa(tarefaSemDadosUsuarioCorreto));
	}

	@Test
	public void testaLoginNoSistema() {
		// primeiro salva o usuario no banco
		usuarioService.salvarNovoUsuario(usuarioCorreto);
		// tenta logar
		Usuario logado = usuarioService.logar(usuarioCorreto.getEmail(), usuarioCorreto.getSenha());
		assertTrue(logado != null);
	}

	@Test
	public void testaAtualizacaoDeDadosDeUmUsuario() {
		usuarioService.salvarNovoUsuario(usuarioCorreto);
		// adiciona os novos dados do usuario
		usuarioCorreto.setId(4);
		usuarioCorreto.setNome("José Ítalo");
		usuarioCorreto.setEmail("jose.italo@academico.ifpb.edu.br");

		assertTrue(usuarioService.atualizarDados(usuarioCorreto) != null);
	}

}
