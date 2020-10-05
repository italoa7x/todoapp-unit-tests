package br.ifpb.edu.testes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.ifpb.edu.domain.Tarefa;
import br.ifpb.edu.service.TarefaService;

public class TarefaTestes {
	// dominio
	private Tarefa tarefaComTodosOsDados, tarefaIncorreta, tarefaComTodosOsDados2;
	// serviço
	private TarefaService tarefaService;

	// instancia todos os objetos que serão utilizados nos testes
	@Before
	public void instanciaEntidadesUtilizadas() {
		tarefaComTodosOsDados = new Tarefa("Titulo tarefa com todos os dados", "Descricação tarefa correta",
				new Date());
		tarefaIncorreta = null;
		tarefaComTodosOsDados2 = new Tarefa("titulo tarefa com todos os dados 2", "Descrição tarefa correta 2",
				new Date());
		tarefaService = new TarefaService();
	}

	@Test
	public void testaSalvarUmaTarefaComTodosOsDados() {
		// se passar na validação, os dois assert's serão true
		assertTrue(tarefaService.salvarTarefa(tarefaComTodosOsDados));
		assertTrue(tarefaService.salvarTarefa(tarefaComTodosOsDados2));
	}

	@Test
	public void testaSalvarUmaTarefaNaoInstanciada() {
		assertFalse(tarefaService.salvarTarefa(tarefaIncorreta));
	}

	@Test
	public void testaAtualizarUmaTarefaComTodosOsDados() {
		tarefaService.salvarTarefa(tarefaComTodosOsDados);
		// novos dados da tarefa
		tarefaComTodosOsDados.setTitulo("titulo atualizado da tarefa correta");
		tarefaComTodosOsDados.setDescricao("descricação atualizada da atividade correta");
		tarefaComTodosOsDados.setId(4);
		tarefaService.atualizarTarefa(tarefaComTodosOsDados);
	}

	@Test
	public void testaAtualizacaoDeDadosDeUmaTarefaQueNaoFoiInstanciada() {
		assertNull(tarefaService.atualizarTarefa(tarefaIncorreta));
	}

	@Test
	public void testaExclusaoDeUmaTarefaPassandoUmIdValido() {
		assertTrue(tarefaService.excluirTarefa(1));
	}

	@Test
	public void testaExclusaoDeUmaTarefaPassandoUmIdInvalido() {
		assertFalse(tarefaService.excluirTarefa(50));

	}
}
