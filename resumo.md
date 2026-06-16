## Atores

- Usuário
  - Pesquisador
    - Revisor (vira revisor através de convite do coordenador)
  - Coordenador (tem apenas 1)

## Requisitos

- Autenticar com email e senha
  - Usuário coordenador deve ser pré-configurado
- Cadastrar usuários (pesquisador) com email, senha e instituição
- Iniciar evento com nome, cidade, período (range de datas) e categoria dentre:
  - Full paper
  - Short paper
  - Demo
- Cadastrar áreas temáticas
- Convidar pesquisadores para serem revisores (fazer com que o pesquisador convidado tenha que selecionar as áreas temáticas ao entrar com sua conta e abrir o sistema)
- Submeter artigo com nome, resumo, coautores e áreas temáticas apenas se estiver no período de submissão, caso contrário mostrar mensagem
- Visualizar artigos submetidos com status:
  - pendente
    - submetido
    - revisão
  - concluído
    - aceito
    - rejeitado
- Distribuir artigos para revisores seguindo algumas regras:
  - distribuir conforme áreas temáticas dos revisores, aceitando artigos sem aderência às áreas apenas em casos em que faltar revisor compatível
  - distribuir igualmente tanto quanto possível
  - não dar a um revisor um artigo do qual é autor ou co-autor
  - não mostrar ao revisor quem são os autores do artigo que vai avaliar
  - notificar revisor sobre cada artigo recebido para avaliação
  - o sistema deve priorizar revisores com maior compatibilidade com as áreas temáticas do artigo
  - o autor deve aceitar o artigo
- Avaliar artigo escrevendo críticas (texto), contribuições (outro texto) e dando um veredito final dentre:
  - recusado
  - fracamente rejeitado
  - fracamente aceito
  - aceito

## Padrões que provavelmente serão usados em algum lugar

- Proxy
- Chain (Autorização)
- Facade
- State
- Observer (envio de artigo p/ revisão)
- Singleton (p/ repositories e services)
- Strategy
- Command
