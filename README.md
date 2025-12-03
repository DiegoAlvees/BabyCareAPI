# BabyCareAPI

# Diagrama do Projeto BabyCare

```mermaid
classDiagram
    class Usuario {
        Long id
        String nome
        String email
        String senhaHash
        List~Baby~ babies
    }

    class Baby {
        Long id
        String nome
        LocalDate dataNascimento
        Usuario user
        List~Rotina~ rotinas
        List~Vacina~ vacinas
    }

    class Rotina {
        Long id
        String tipo
        LocalDateTime timestamp
        Object detalhes
        Baby baby
    }

    class Vacina {
        Long id
        String nome
        LocalDate data
        Boolean status
        Baby baby
    }

    %% Relacionamentos
    Usuario "1" --> "0..*" Baby
    Baby "1" --> "0..*" Rotina
    Baby "1" --> "0..*" Vacina

