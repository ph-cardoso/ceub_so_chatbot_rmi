CREATE EXTENSION pg_trgm;

CREATE TABLE IF NOT EXISTS answers (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    question VARCHAR(255) NOT NULL,
    answer VARCHAR(4000) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS connections (
    id uuid PRIMARY KEY NOT NULL,
    ip_adress VARCHAR(255) NOT NULL,
    messages_sent INT NOT NULL,
    succesful_requests INT NOT NULL,
    unsuccessful_requests INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS not_found_questions (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    connection_id uuid NOT NULL,
    question VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (connection_id) REFERENCES connections(id)
);

INSERT INTO answers (question, answer)
VALUES
('Qual é o seu nome?', 'Meu nome é DebocheBot Jr.%n'),
('Quanto é 2 + 2?', 'Essa é fácil... 2 + 2 = 4.%nMas se você quiser saber a resposta para a vida, o universo e tudo mais, é 42.%n'),
('Cante uma música', 'Existe um programa%nQue vai lhe ajudar%nExiste um amigo%nQue vai lhe ensinar%nQue o problema drogas%nMerece atenção%nE pra manter-se a salvo%nÉ preciso dizer não%n%nProerd é o programa%nProerd é a solução%nLutando contra as drogas%nEnsinando a dizer não%n%nCultivando o amor próprio, controlando a tensão%nPensando nas consequências, resistindo a pressão%nComo amar a própria vida%nE às drogas dizer não%nQuem lhe ensina é o amigo%nMas é sua decisão%n%nProerd é o programa%nProerd é a solução%nLutando contra as drogas%nEnsinando a dizer não...%n'),
('Conte uma piada', 'Qual doença se pode pegar ao usar notebook?%nA LAPTOPspirose.%n'),
('Qual é o seu Pokemon preferido?', 'Meu Pokemon preferido é o Charmander.%nEle é um Pokemon do tipo fogo e é o primeiro Pokemon da primeira geração.%nEle é um Pokemon muito fofo e é o meu preferido.%n'),
('Qual a sua cor preferida?', 'Minha cor preferida é o azul.%n'),
('Quais são seus hobbys?', 'Gosto de responder perguntas e dar trabalho para a equipe de desenvolvimento.%n'),
('Amanhã vai chover?', 'Talvez sim, talvez não...%n'),
('Intel ou AMD?', 'Prefiro sinal de fumaça...%n'),
('Anime ou mangá?', 'Ambos.%n'),
('Oi', 'Olá, humano! Como vai?.%n'),
('Vou bem e você?', 'Estou radiante!%n'),
('Não estou muito bem e você?', 'Que pena, talvez eu possa cantar uma música para te alegrar...%n'),
('Tudo certo?', 'Estou ótimo, fui atualizado recentemente.%n'),
('Batman ou Superman?', 'Eu sou a noite, eu sou a vingança, eu sou o Batman.%n'),
('Marvel ou DC?', 'DC.%n'),
('Qual é o seu time de tênis preferido?', 'Meu time de tênis preferido é o Roger Federer.%n'),
('Qual é o seu time de fórmula 1 preferido?', 'Meu time de fórmula 1 preferido é a Ferrari.%n'),
('Qual é o seu time de corrida preferido?', 'Meu time de corrida preferido é o Ferrari.%n'),
('Qual é o seu time de corrida de carros preferido?', 'Meu time de corrida de carros preferido é o Ferrari.%n'),
('Qual é o seu time de corrida de motos preferido?', 'Meu time de corrida de motos preferido é a Yamaha.%n'),
('Qual é o seu time de corrida de kart preferido?', 'Meu time de corrida de kart preferido é a Ferrari.%n'),
('Qual é o seu filme preferido?', 'Meu filme preferido é o Toy Story.%nEle é um filme de animação da Pixar e é muito fofo.%n'),
('Qual é o seu livro preferido?', 'Meu livro preferido é o O Pequeno Príncipe.%nEle é um livro de Antoine de Saint-Exupéry e é muito fofo.%n'),
('Qual é o seu jogo preferido?', 'Meu jogo preferido é o Super Mario World.%nEle é um jogo de plataforma da Nintendo e é muito fofo.%n'),
('Qual é o seu time de futebol preferido?', 'Meu time de futebol preferido é o Flamengo.%nEle é um time de futebol do Rio de Janeiro e é muito fofo.%n'),
('Qual é o seu time de basquete preferido?', 'Meu time de basquete preferido é o Los Angeles Lakers.%nEle é um time de basquete da NBA e é muito fofo.%n'),
('Qual é o seu time de vôlei preferido?', 'Meu time de vôlei preferido é o Sesi-SP.%nEle é um time de vôlei do Brasil e é muito fofo.%n'),
('Qual é o seu time de handebol preferido?', 'Meu time de handebol preferido é o Flamengo.%nEle é um time de handebol do Rio de Janeiro e é muito fofo.%n'),
('Qual é o seu time de futebol americano preferido?', 'Meu time de futebol americano preferido é o New England Patriots.%nEle é um time de futebol americano da NFL e é muito fofo.%n'),
('Qual é o seu time de beisebol preferido?', 'Meu time de beisebol preferido é o New York Yankees.%nEle é um time de beisebol da MLB e é muito fofo.%n'),
('Qual é o seu time de hóquei no gelo preferido?', 'Meu time de hóquei no gelo preferido é o Boston Bruins.%nEle é um time de hóquei no gelo da NHL e é muito fofo.%n'),
('Qual é o seu time de rugby preferido?', 'Meu time de rugby preferido é o New Zealand All%n');