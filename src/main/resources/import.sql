INSERT INTO usuario (email, nome, senha, uuid) VALUES ('admin@email.com', 'admin', '$2y$12$cyPHq1iQ9xWtpxXBO2oAIe/iRUkDmahD00kXJt49nRcjZTNc29taq', 'd8d5ba2a-3287-47a3-af1a-82eafc5aca25');

insert into restaurante (uuid, nome, taxa_frete, bairro, numero, id_usuario) values ("8737c009-33ae-4cb5-95ee-7c41194a5bf9", "Madero", 10.0, "Cidade Jardim", 123, 1);
insert into restaurante (uuid, nome, taxa_frete, bairro, numero, id_usuario) values ("a81c43cd-2aa3-4657-b24a-52705bf0df2c", "Pentucci", 15.0, "Alphavile", 456, 1);
insert into restaurante (uuid, nome, taxa_frete, bairro, numero, id_usuario) values ("ab270852-b297-497b-807c-fa7fad298568", "MacDonalds", 13.0, "Boqueirão", 789, 1);

insert into produto (descricao, nome, preco, id_restaurante, uuid) values ("teste descrição", "teste nome", 7.77, 1, "c26e7da3-ec11-4ebf-917c-cf76798bce5a");

# senha (plain text) UTFPR-abc123@99
# sha256 6760dce9c29a015064b6a439b57563ee167b59b9e9a4f58f83d8d579b385eac1
# bcrypt $2y$12$cyPHq1iQ9xWtpxXBO2oAIe/iRUkDmahD00kXJt49nRcjZTNc29taq