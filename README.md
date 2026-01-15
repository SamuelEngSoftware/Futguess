# FutGuess

## Equipe
* Renan Alencar Soares 555524
* Francisco Samuel Cabral Leitão 553382

## Descrição do Projeto
O FutGuess é um aplicativo mobile inspirado no jogo Wordle, mas voltado pro tema de Futebol. O objetivo é adivinhar o nome de um jogador de futebol em até seis tentativas. A cada tentativa, é emitido um feedback, indicando se a letra está na posição correta (verde), na posição errada (laranja) ou não-pertencente (cinza).

Os jogadores serão obtidos a partir de uma API externa de futebol, garantindo variedade e atualizações automáticas.

O app também permitirá que o usuário crie uma conta, personalize seu perfil, e consulte seu histórico de partidas, armazenado localmente. O projeto busca unir entretenimento e tecnologia, aplicando os principais conceitos aprendidos ao longo da disciplina.

---

## Funcionalidades Principais

- [ ] Cadastro e Login de Usuário: Permite que o usuário crie uma conta, faça login e gerencie seus dados pessoais.
- [ ] Jogo Principal - FutGuess: O usuário deve adivinhar o nome do jogador sorteado via API externa em até seis tentativas, com feedback visual colorido para cada tentativa.
- [ ] Histórico de Partidas: Exibe uma lista com as partidas jogadas e resultados, armazenados localmente via Room.
- [ ] Perfil do Usuário: Permite editar os seus dados pessoais (como nome, ícone, etc)
- [ ] Modo Claro/Escuro: Interface adaptável ao tema do sistema com MaterialTheme.

---

> [!WARNING]
> Daqui em diante o README.md só deve ser preenchido no momento da entrega final.

## Tecnologias Utilizadas

O projeto foi desenvolvido utilizando **Kotlin** e a moderna caixa de ferramentas **Jetpack Compose** para a interface de usuário. Abaixo, as principais bibliotecas e padrões aplicados:

* **Linguagem:** Kotlin
* **Interface (UI):** Jetpack Compose (Material Design 3)
* **Arquitetura:** MVVM (Model-View-ViewModel) com Repository Pattern
* **Injeção de Dependência:** Koin
* **Banco de Dados Local:** Room Database (SQLite)
* **Comunicação com API:** Retrofit 2 + GSON Converter
* **Programação Assíncrona & Reativa:** Kotlin Coroutines & Flow
* **Navegação:** Jetpack Navigation Compose
* **Carregamento de Imagens:** Coil
* **Segurança:** JBCrypt (Hash de senhas)

---

## Instruções para Execução

Para rodar o projeto, você precisará do **Android Studio** instalado.

```bash
# 1. Clone o repositório
git clone https://github.com/SamuelEngSoftware/Futguess.git

# 2. Navegue para o diretório
cd futguess

# 3. Abra o projeto no Android Studio
# - Abra o Android Studio
# - Selecione open e navegue até a pasta onde clonou o projeto.
# - Aguarde o Gradle sincronizar todas as dependências 

# 4. Configuração da API
# O projeto já está configurado para consumir a API pública hospedada no My JSON Server.
# Não é necessária nenhuma configuração extra, apenas certifique-se de que o emulador tenha acesso à internet.

# 5. Executar
# - Selecione um Emulador ou seu dispositivo físico.
# - Clique no botão "Run" ou pressione Shift + F10.
