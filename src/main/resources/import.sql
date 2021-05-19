INSERT INTO usuario (email, nome, senha, uuid) VALUES ('admin@email.com', 'admin', '$2y$12$cyPHq1iQ9xWtpxXBO2oAIe/iRUkDmahD00kXJt49nRcjZTNc29taq', 'd8d5ba2a-3287-47a3-af1a-82eafc5aca25');

insert into restaurante (uuid, nome, taxa_frete, bairro, numero, id_usuario, cep, rua, complemento) values ("8737c009-33ae-4cb5-95ee-7c41194a5bf9", "Madero", 10.0, "Jaderlândia", 123, 1, "68746-670", "Rua José H. Pereira", "Esquina");
insert into restaurante (uuid, nome, taxa_frete, bairro, numero, id_usuario, cep, rua, complemento) values ("a81c43cd-2aa3-4657-b24a-52705bf0df2c", "Pentucci", 15.0, "Santo Antônio", 456, 1, "59611-200", "Rua Doutor João Marcelino", "Ao lado do shopping");
insert into restaurante (uuid, nome, taxa_frete, bairro, numero, id_usuario, cep, rua, complemento) values ("ab270852-b297-497b-807c-fa7fad298568", "MacDonalds", 13.0, "Princesa Isabel", 789, 1, "76964-132", "Avenida Sete de Setembro", "Centro");

insert into produto (descricao, nome, preco, id_restaurante, uuid) values ("Frango com catupiri", "Coxinha", 7.77, 1, "c26e7da3-ec11-4ebf-917c-cf76798bce5a");

insert into cliente (nome, contato, cep, rua, numero, bairro, complemento, uuid) values ("Neymar Junior", "24 99000-1234", "22795-120", "Rua Amizade", 333, "Recreio dos Bandeirantes", "Casa", "0e6084d8-4e31-4f12-a0b9-02ce3d8eaafc");

# senha (plain text) UTFPR-abc123@99
# sha256 6760dce9c29a015064b6a439b57563ee167b59b9e9a4f58f83d8d579b385eac1
# bcrypt $2y$12$cyPHq1iQ9xWtpxXBO2oAIe/iRUkDmahD00kXJt49nRcjZTNc29taq