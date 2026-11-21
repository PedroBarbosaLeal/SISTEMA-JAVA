-- Regras que no legado ficavam só na classe Valida.
BEGIN;

ALTER TABLE fornecedores ADD CONSTRAINT fornecedores_pkey PRIMARY KEY (id);
ALTER TABLE funcionarios ADD CONSTRAINT funcionarios_pkey PRIMARY KEY (id);
ALTER TABLE produtos     ADD CONSTRAINT produtos_pkey     PRIMARY KEY (id);

ALTER TABLE cliente      ADD CONSTRAINT cliente_cpf_key             UNIQUE (cpf);
ALTER TABLE funcionarios ADD CONSTRAINT funcionarios_cpf_key        UNIQUE (cpf);
ALTER TABLE fornecedores ADD CONSTRAINT fornecedores_cnpj_key       UNIQUE (cnpj);
ALTER TABLE produtos     ADD CONSTRAINT produtos_codigo_produto_key UNIQUE (codigo_produto);

ALTER TABLE cliente ALTER COLUMN nome SET NOT NULL;
ALTER TABLE cliente ALTER COLUMN cpf  SET NOT NULL;

COMMIT;
