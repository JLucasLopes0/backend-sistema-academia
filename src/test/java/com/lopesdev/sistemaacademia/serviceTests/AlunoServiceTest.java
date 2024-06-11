package com.lopesdev.sistemaacademia.serviceTests;

import com.lopesdev.sistemaacademia.entities.Aluno;
import com.lopesdev.sistemaacademia.repositories.AlunoRepository;
import com.lopesdev.sistemaacademia.services.AlunoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.lopesdev.sistemaacademia.enums.EnumTipoTreino.CARDIO;
import static com.lopesdev.sistemaacademia.enums.EnumTipoTreino.HIIT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @Test
    public void AtualizarEmailTest_Success() {
        Long alunoId = 1L;
        String novoEmail = "novoemail@example.com";
        Aluno aluno = new Aluno();
        aluno.setId(alunoId);
        aluno.setEmail("oldemail@example.com");

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));

        ResponseEntity<String> response = alunoService.atualizarEmail(alunoId, novoEmail);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Email atualizado com sucesso para: " + novoEmail, response.getBody());
        assertEquals(novoEmail, aluno.getEmail());
        verify(alunoRepository).save(aluno);
    }


    @Test
    public void AtualizarEmailTest_AlunoNotFound() {
        Long alunoId = 200L;
        String novoEmail = "novoemail@example.com";
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = alunoService.atualizarEmail(alunoId, novoEmail);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Aluno não encontrado com o ID: " + alunoId, response.getBody());
        verify(alunoRepository, never()).save(any());
    }

    @Test
    public void BuscarAlunosPorNomeTest_nomeIsNull(){
        List<Aluno> mockAlunos = List.of(new Aluno("Rogerio Couto"), new Aluno("Maria Silva"));
        when(alunoRepository.findAll()).thenReturn(mockAlunos);

        List<Aluno> alunos = alunoService.buscarAlunosPorNome(null);
        assertEquals(mockAlunos, alunos);
        verify(alunoRepository, times(1)).findAll();
    }

    @Test
    public void BuscarAlunosPorNomeTest_nomeIsEmpty() {
        List<Aluno> mockAlunos = List.of(new Aluno("Rogerio Couto"), new Aluno("Maria Silva"));
        when(alunoRepository.findAll()).thenReturn(mockAlunos);

        List<Aluno> alunos = alunoService.buscarAlunosPorNome("");

        assertEquals(mockAlunos, alunos);
        verify(alunoRepository, times(1)).findAll();
    }

    @Test
    public void BuscarAlunosPorNomeTest_NomeExists() {
        List<Aluno> mockAlunos = List.of(
                new Aluno(1, "Rogerio Couto", "rogeriocouto@example.com", 24, LocalDate.of(2024, 3, 5), "Rua das Hortas, 123", CARDIO, null),
                new Aluno(2, "Maria Silva", "mariasilva@example.com", 30, LocalDate.of(2024, 2, 10), "Rua das Oliveiras, 123 - Centro", HIIT, null)
        );
        String nome = "Rogerio";
        when(alunoRepository.findAll()).thenReturn(mockAlunos);

        List<Aluno> alunos = alunoService.buscarAlunosPorNome(nome);

        assertEquals(1, alunos.size());
        assertEquals("Rogerio Couto", alunos.get(0).getNome());
        verify(alunoRepository, times(1)).findAll();
    }

}