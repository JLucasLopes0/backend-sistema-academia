package com.lopesdev.sistemaacademia.serviceTests;

import com.lopesdev.sistemaacademia.entities.Aluno;
import com.lopesdev.sistemaacademia.entities.Personal;
import com.lopesdev.sistemaacademia.enums.EnumTipoTreino;
import com.lopesdev.sistemaacademia.repositories.AlunoRepository;
import com.lopesdev.sistemaacademia.services.AlunoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @Test
    void FirstCaseTestSave(){
        Aluno alunoParaSalvar = new Aluno(1, "Carlos", "carlos@email.com.br", 23,
                LocalDate.of(2023, 12, 31), "Rua ABC, Centro Urbano", EnumTipoTreino.FORCA, new Personal());
        Mockito.when(alunoRepository.save(alunoParaSalvar)).thenReturn(alunoParaSalvar);
        Aluno alunoSalvo = alunoService.save(alunoParaSalvar);
        Assertions.assertEquals(alunoParaSalvar, alunoSalvo);
    }

    //Testa o método save com um Aluno cuja propriedade nome é null.
    @Test
    void SecondCaseTestSave(){
        Aluno alunoParaSalvar = new Aluno(2, null, "carlos@email.com.br", 23,
                LocalDate.of(2023, 12, 31), "Rua ABC, Centro Urbano", EnumTipoTreino.HIIT, new Personal());
        Assertions.assertThrows(IllegalArgumentException.class, () -> alunoService.save(alunoParaSalvar));
    }

}